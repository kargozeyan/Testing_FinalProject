package am.aua;

import am.aua.pom.cart.Cart;
import am.aua.pom.cartitem.CartItem;
import am.aua.pom.confirmmodal.ConfirmModal;
import am.aua.pom.header.Header;
import am.aua.pom.item.Item;
import am.aua.pom.searchresultpage.SearchResultPage;
import am.aua.predicates.CartItemPredicate;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class AddRemoveCartTest extends BaseTest {
    private SearchResultPage searchResultPage;

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();

        Header header = homePage.getHeader();
        header.inputSearch("էջանիշ");
        searchResultPage = header.submitSearch();
    }

    @Test
    public void testEmptyCart() {
        Header header = searchResultPage.getHeader();
        Cart cart = header.openCart();
        assertThat(cart.getEmptyMessage())
                .isNotNull()
                .isEqualTo("You have no items in your shopping cart.");
    }

    @Test
    public void testAddingSingleElement() {
        List<Item> results = searchResultPage.getItems();
        Item item = results.get(2);
        CartItemPredicate expectedCartItem = new CartItemPredicate(item.getTitle(), item.getPrice());
        item.addToCart();

        Header header = searchResultPage.getHeader();
        Cart cart = header.openCart();
        List<CartItem> cartItems = cart.getItems();

        assertThat(cartItems)
                .hasSize(1)
                .first()
                .matches(expectedCartItem);
    }

    @Test
    public void testAddingMultipleElements() throws InterruptedException {
        List<CartItemPredicate> expectedCartItems = IntStream.of(0, 1, 2, 5)
                .mapToObj(i -> searchResultPage.getItems().get(i))
                .peek(Item::addToCart)
                .sorted(Comparator.comparing(Item::getTitle))
                .map(item -> new CartItemPredicate(item.getTitle(), item.getPrice()))
                .toList();

        Thread.sleep(2_000);
        Header header = searchResultPage.getHeader();
        Cart cart = header.openCart();
        List<CartItem> cartItems = cart.getItems().stream()
                .sorted(Comparator.comparing(CartItem::getTitle))
                .toList();

        assertThat(cartItems).hasSize(expectedCartItems.size());
        for (int i = 0; i < cartItems.size(); i++) {
            assertThat(cartItems.get(i)).matches(expectedCartItems.get(i));
        }
    }

    @Test
    public void testAddingMultipleElementsWithMultipleCount() throws InterruptedException {
        List<Item> results = searchResultPage.getItems();
        Map<Item, Integer> itemsToAdd = Map.of(
                results.get(0), 2,
                results.get(3), 1,
                results.get(1), 3
        );

        List<CartItemPredicate> expectedCartItems = itemsToAdd.entrySet().stream()
                .peek(entry -> {
                    for (int i = 0; i < entry.getValue(); i++) {
                        entry.getKey().addToCart();
                    }
                })
                .sorted(Comparator.comparing(itemIntegerEntry -> itemIntegerEntry.getKey().getTitle()))
                .map(entry -> new CartItemPredicate(entry.getKey().getTitle(), entry.getKey().getPrice(), entry.getValue()))
                .toList();
        Thread.sleep(2_000);
        Header header = searchResultPage.getHeader();
        Cart cart = header.openCart();
        List<CartItem> cartItems = cart.getItems().stream()
                .sorted(Comparator.comparing(CartItem::getTitle))
                .toList();

        assertThat(cartItems).hasSize(expectedCartItems.size());
        for (int i = 0; i < cartItems.size(); i++) {
            assertThat(cartItems.get(i)).matches(expectedCartItems.get(i));
        }
    }

    @Test
    public void testRemovingFromCart() throws InterruptedException {
        List<Item> results = searchResultPage.getItems();
        List<Item> itemsToAdd = List.of(results.get(0), results.get(1), results.get(2), results.get(5));

        itemsToAdd.forEach(Item::addToCart);
        Thread.sleep(2_000);
        Header header = searchResultPage.getHeader();
        Cart cart = header.openCart();
        List<CartItem> cartItems = cart.getItems();
        while (true) {
            ConfirmModal confirmModal = cartItems.get(0).delete();
            confirmModal.confirm();
            Thread.sleep(2_000);
            if (cartItems.size() > 1) {
                cartItems = cart.getItems();
            } else break;
        }

        assertThat(cart.getEmptyMessage())
                .isNotNull()
                .isEqualTo("You have no items in your shopping cart.");
    }
}

package am.aua;

import am.aua.pom.cart.Cart;
import am.aua.pom.cartitem.CartItem;
import am.aua.pom.header.Header;
import am.aua.pom.item.Item;
import am.aua.pom.searchresultpage.SearchResultPage;
import am.aua.predicates.CartItemPredicate;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemCountCartTest extends BaseTest {
    private Cart cart;

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        Header header = homePage.getHeader();
        header.inputSearch("էջանիշ");
        SearchResultPage searchResultPage = header.submitSearch();

        IntStream.of(3, 0, 2, 3, 0, 3)
                .mapToObj(i -> searchResultPage.getItems().get(i))
                .forEach(Item::addToCart);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        header = searchResultPage.getHeader();
        cart = header.openCart();
    }

    @Test()
    public void testIncrementing() {

        List<CartItem> cartItems = cart.getItems();

        List<CartItemPredicate> expectedCartItemPredicates = cartItems
                .stream()
                .map(cartItem -> new CartItemPredicate(cartItem.getTitle(), cartItem.getPrice(), cartItem.getQuantity() + 1))
                .toList();

        int expectedTotalPrice = cartItems
                .stream()
                .mapToInt(cartItem -> cartItem.getPrice() * (cartItem.getQuantity() + 1))
                .sum();

        for (int i = 0; i < cartItems.size(); i++) {
            cart = cart.incrementItemAt(i);
        }
        List<CartItem> results = cart.getItems();
        for (int i = 0; i < results.size(); i++) {
            assertThat(results.get(i)).matches(expectedCartItemPredicates.get(i));
        }
        assertThat(cart.getTotalPrice()).isEqualTo(expectedTotalPrice);
    }

    @Test
    public void testDecrementing() {
        List<CartItem> cartItems = cart.getItems();

        List<CartItemPredicate> expectedCartItemPredicates = cartItems
                .stream()
                .map(cartItem -> new CartItemPredicate(cartItem.getTitle(), cartItem.getPrice(), Math.max(cartItem.getQuantity() - 1, 1)))
                .toList();

        int expectedTotalPrice = cartItems
                .stream()
                .mapToInt(cartItem -> cartItem.getPrice() * Math.max(cartItem.getQuantity() - 1, 1))
                .sum();

        for (int i = 0; i < cartItems.size(); i++) {
            cart = cart.decrementItemAt(i);
        }
        List<CartItem> results = cart.getItems();
        for (int i = 0; i < results.size(); i++) {
            assertThat(results.get(i)).matches(expectedCartItemPredicates.get(i));
        }
        assertThat(cart.getTotalPrice()).isEqualTo(expectedTotalPrice);
    }
}

package am.aua.predicates;

import am.aua.pom.cartitem.CartItem;

import java.util.Objects;
import java.util.function.Predicate;

public class CartItemPredicate implements Predicate<CartItem> {
    private final String title;
    private final int quantity;
    private final int price;

    public CartItemPredicate(String title, int price) {
        this(title, price, 1);
    }

    public CartItemPredicate(String title, int price, int quantity) {
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public boolean test(CartItem cartItem) {
        return cartItem.getQuantity() == this.quantity
                && cartItem.getPrice() == this.price
                && Objects.equals(cartItem.getTitle(), this.title);
    }
}

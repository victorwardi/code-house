package run.victor.api.codehouse.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.util.Assert;

/**
 * @author Victor Wardi - @victorwardi
 */
@Entity
@Table(name = "order_purchase")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @NotNull
    @Size(min = 1)
    @ElementCollection
    private Set<ItemOrder> items = new HashSet<>();

    public Order(Purchase purchase, Set<ItemOrder> items) {
        Assert.isTrue(!items.isEmpty(), "Order must have at least one item");
        this.purchase = purchase;
        this.items.addAll(items);
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public Set<ItemOrder> getItems() {
        return items;
    }

    public boolean checkTotal(BigDecimal total) {
        final BigDecimal totalFromServer = items.stream().map(ItemOrder::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        return total.compareTo(totalFromServer) == 0;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", items=" + items + '}';
    }
}

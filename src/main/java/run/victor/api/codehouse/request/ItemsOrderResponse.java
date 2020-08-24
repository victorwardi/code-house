package run.victor.api.codehouse.request;

import java.util.Set;
import java.util.stream.Collectors;

import run.victor.api.codehouse.model.ItemOrder;

/**
 * @author Victor Wardi - @victorwardi
 */
public class ItemsOrderResponse {

    private Set<ItemOrderResponse> items;

    public ItemsOrderResponse(Set<ItemOrder> items) {
        this.items = items.stream().map(this::fromModel).collect(Collectors.toSet());;
    }

    private ItemOrderResponse fromModel(ItemOrder itemOrder){
        final BookResponse bookResponse = BookResponse.fromModel(itemOrder.getBook());
        return new ItemOrderResponse(bookResponse, itemOrder.getQuantity(), itemOrder.getCurrentBookPrice());
    }

    public Set<ItemOrderResponse> getItems() {
        return items;
    }
}

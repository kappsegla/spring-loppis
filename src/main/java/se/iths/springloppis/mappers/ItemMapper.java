package se.iths.springloppis.mappers;

import se.iths.springloppis.dtos.Item;
import se.iths.springloppis.entity.ItemEntity;

public class ItemMapper {

    public static ItemEntity itemToItemEntity(Item item) {
        var itemE = new ItemEntity();
        itemE.setName(item.getName());
        itemE.setCategory(item.getCategory());
        //...
        return itemE;
    }

    public static Item itemEntitytoItem(ItemEntity itemEntity) {
        var item = new Item();
        item.setName(itemEntity.getName());
        //...
        return item;
    }
}

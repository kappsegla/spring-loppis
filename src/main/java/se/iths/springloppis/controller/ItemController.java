package se.iths.springloppis.controller;

import org.springframework.web.bind.annotation.*;
import se.iths.springloppis.entity.ItemEntity;
import se.iths.springloppis.service.ItemService;

import java.util.Optional;

@RestController
@RequestMapping("items")
public class ItemController {

    ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("")
    public ItemEntity createItem(@RequestBody ItemEntity itemEntity) {
        return itemService.createItem(itemEntity);
    }

    @DeleteMapping("{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }

    @GetMapping("{id}")
    public Optional<ItemEntity> findItemById(@PathVariable Long id) {
        return itemService.findItemById(id);
    }

    @GetMapping("")
    public Iterable<ItemEntity> findAllItems() {
        return itemService.findAllItems();
    }
}

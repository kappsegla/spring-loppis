package se.iths.springloppis.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import se.iths.springloppis.dtos.Item;
import se.iths.springloppis.entity.ItemEntity;
import se.iths.springloppis.service.ItemService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping()
    public ResponseEntity<Item> createItem(@RequestBody Item item){
        Item createdItem = itemService.createItem(item);
        return ResponseEntity
                .created(URI.create(ServletUriComponentsBuilder.fromCurrentRequest().build().toString() + "/" + createdItem.getId()))
                .body(createdItem);
    }

    @PatchMapping("{id}")
    public ResponseEntity<ItemEntity> updateItemName(@PathVariable Long id, @RequestBody String name){
        return new ResponseEntity<>(itemService.updateItem(id, name),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<ItemEntity>> findItemById(@PathVariable Long id) {
        Optional<ItemEntity> foundItem = itemService.findItemById(id);
        return new ResponseEntity<>(foundItem, HttpStatus.OK);

    }

    @GetMapping()
    public ResponseEntity<Iterable<ItemEntity>> findAllItems() {
        Iterable<ItemEntity> allItems = itemService.findAllItems();
        return new ResponseEntity<>(allItems, HttpStatus.OK);
    }

    @GetMapping("/hej")
    public Test test(@RequestBody Test test){
        return test;
    }
}

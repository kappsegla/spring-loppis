package se.iths.springloppis.service;


import org.springframework.stereotype.Service;
import se.iths.springloppis.entity.ItemEntity;
import se.iths.springloppis.repository.ItemRepository;

import java.util.Optional;

@Service
public class ItemService {

// Field injection - not recommended
//    @Autowired
    ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemEntity createItem(ItemEntity itemEntity) {
        return itemRepository.save(itemEntity);
    }

    public void deleteItem(Long id) {
        Optional<ItemEntity> foundItem = itemRepository.findById(id);
        itemRepository.deleteById(foundItem.get().getId());
    }

    public Optional<ItemEntity> findItemById(Long id) {
        return itemRepository.findById(id);
    }

    public Iterable<ItemEntity> findAllItems() {
        return itemRepository.findAll();
    }


}

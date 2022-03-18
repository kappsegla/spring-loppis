package se.iths.springloppis.service;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.iths.springloppis.dtos.Item;
import se.iths.springloppis.entity.ItemEntity;
import se.iths.springloppis.mappers.ItemMapper;
import se.iths.springloppis.repository.ItemRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ItemService {

    // Field injection - not recommended
//    @Autowired
    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;

    public ItemService(ItemRepository itemRepository, ModelMapper modelMapper) {
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
    }

    public Item createItem(Item item) {

        ItemEntity itemEntity = modelMapper.map(item, ItemEntity.class);

        return modelMapper.map(itemRepository.save(itemEntity), Item.class);
    }

    @Transactional
    public ItemEntity updateItem(Long id, String name) {
        ItemEntity fromDatabase = itemRepository.findById(id).orElseThrow();
        fromDatabase.setName(name);
        //itemRepository.save(fromDatabase); //Not needed if annotated with transactional
        return fromDatabase;
    }

    public void deleteItem(Long id) {
        ItemEntity foundItem = itemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        itemRepository.deleteById(foundItem.getId());
    }

    public Optional<ItemEntity> findItemById(Long id) {
        return itemRepository.findById(id);
    }

    public Iterable<ItemEntity> findAllItems() {
        return itemRepository.findAll();
    }
}

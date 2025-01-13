package oort.cloud.shop.service;

import oort.cloud.shop.entity.Item;
import oort.cloud.shop.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService{
    private final ItemRepository itemRepository;


    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Long addItem(Item item){
        itemRepository.save(item);
        return item.getId();
    }

    public Item findById(Item item){
        return itemRepository.findOne(item.getId());
    }

    public List<Item> findAll(){
        return itemRepository.findAll();
    }
}

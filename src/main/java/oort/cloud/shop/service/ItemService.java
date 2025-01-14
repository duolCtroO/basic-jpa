package oort.cloud.shop.service;

import oort.cloud.shop.entity.item.Item;
import oort.cloud.shop.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemService{
    private final ItemRepository itemRepository;


    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Long addItem(Item item){
        itemRepository.save(item);
        return item.getId();
    }

    public Item findById(Long id){
        return itemRepository.findOne(id);
    }

    public List<Item> findAll(){
        return itemRepository.findAll();
    }
}

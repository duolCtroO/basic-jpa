package oort.cloud.shop.controller;


import oort.cloud.shop.entity.item.Item;
import oort.cloud.shop.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/new")
    public ResponseEntity itemSave(@RequestBody Item item){
        itemService.addItem(item);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("아이템 추가 성공");
    }

}

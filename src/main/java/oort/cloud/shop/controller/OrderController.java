package oort.cloud.shop.controller;


import jakarta.validation.Valid;
import oort.cloud.shop.data.OrderRequest;
import oort.cloud.shop.entity.item.Item;
import oort.cloud.shop.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity order(@RequestBody @Valid OrderRequest orderRequest){
        orderService.order(orderRequest.getMemberId(), orderRequest.getItemId(), orderRequest.getCount());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("주문 성공");
    }
}

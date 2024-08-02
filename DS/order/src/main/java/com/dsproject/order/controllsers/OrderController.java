package com.dsproject.order.controllsers;
import com.dsproject.order.dto.OrderDTO;
import com.dsproject.order.entities.Order;
import com.dsproject.order.entities.OrderRequest;
import com.dsproject.order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")  // Ensure the URL path is correct and consistent
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

        @GetMapping
    public String getOrderInfo() {
        return "Order Service";
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }


    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderRequest orderRequest) {
        try {
            OrderDTO order = orderService.createOrder(orderRequest);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the exception and return a meaningful error response
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

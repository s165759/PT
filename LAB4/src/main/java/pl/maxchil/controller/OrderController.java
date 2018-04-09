package pl.maxchil.controller;

import org.aspectj.weaver.ast.Or;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.maxchil.Repository.OrderRepository;
import pl.maxchil.model.Disc;
import pl.maxchil.model.Order;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {this.orderRepository = orderRepository;}

    @GetMapping
    List<Order> listOrders(){return orderRepository.findAll(); }

    @PostMapping
    ResponseEntity<Void> addOrder(@RequestBody Order order){

        //Map ordering = order.getDiscs().stream().collect(Collectors.toMap(Disc::getId, (s) -> s.getAmount() - 1 , (s, a) -> s - 1));

        return ResponseEntity.ok().build();
    }
}

package com.knoldus.springwebflux.ordermanagement.service;

import com.knoldus.springwebflux.ordermanagement.model.OrderRequest;
import com.knoldus.springwebflux.ordermanagement.repo.OrderRepository;
import com.knoldus.springwebflux.ordermanagement.model.OrderBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * This class is a Service class, which is having method which contains the business logic and also
 * calling of repository class methods. and returning the response to controller class.
 */
@Service
public class OrderService implements IOrderService {

  @Autowired OrderRepository orderRepository;

  public OrderService() {
  }

  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public void create(OrderRequest order) {

    OrderBill orderBill = new OrderBill(
            UUID.randomUUID().toString(),
            LocalDateTime.now().toString(),
            order.getProduct().stream().mapToLong(productDetails -> productDetails.getPrice()).sum(),
            order);

    orderRepository.save(orderBill)
            .subscribe();
  }

  public Mono<OrderBill> findById(String orderId) {
    return orderRepository.findById(orderId);
  }

  public Flux<OrderBill> findAll() {
    Flux<OrderBill> all = orderRepository.findAll();
    /*
     * Here we are getting the publisher from the repository class and
     * then we are applying delay of 2 sec, so that we can check this is a reactive streaming endpoint
     *
     * We just need to call this endpoint from the browser, because browsers are asynchronous
     * so you can get result data one-by-one (with 2 sec interval)
     *
     * or if we use postman, we can see whole result in same time, because the postman is synchronous and
     * it will wait till all the event recieved and then only it will show us all the event at same time.
     */
    return all
            .delayElements(Duration.ofSeconds(2));
  }

  public Mono<OrderBill> update(OrderBill bill) {
    bill.setOrderDate(LocalDateTime.now().toString());

    bill.setTotalOrderPrice(bill.getOrderRequest()
            .getProduct().stream()
            .mapToLong(productDetails -> productDetails.getPrice()).sum());

    bill.setOrderRequest(bill.getOrderRequest());
    return orderRepository.save(bill);
  }

  public void delete(String orderId) {
    orderRepository.deleteById(orderId).subscribe();
  }

}

package com.example.OrderManagement.service;

import com.example.OrderManagement.model.OrderRequest;
import com.example.OrderManagement.model.OrderRepository;
import com.example.OrderManagement.model.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

/**
 * This class is a Service class, which is having method which contains the business logic and also
 * calling of repository class methods. and returning the response to controller class.
 */
@Service
public class OrderService implements IOrderService {

  @Autowired OrderRepository orderRepository;

  public Mono<OrderResponse> create(OrderRequest order) {
    /*
     * we are using UUID as orderId in this template, so this method will create the orderId and
     * then it will all the repository method and bet the result and return to controller class.
     */
    OrderResponse orderResponse = new OrderResponse();
    orderResponse.setOrderId(UUID.randomUUID().toString());
    orderResponse.setOrderRequest(order);
    return orderRepository.save(orderResponse);
  }

  public Mono<OrderResponse> findById(String orderId) {
    return orderRepository.findById(orderId);
  }

  public Flux<OrderResponse> findAll() {
    Flux<OrderResponse> all = orderRepository.findAll();
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
    return all.delayElements(Duration.ofSeconds(2));
  }

  public Mono<OrderResponse> update(OrderResponse order) {
    return orderRepository.save(order);
  }

  public Mono<Void> delete(String orderId) {
    return orderRepository.deleteById(orderId);
  }
}

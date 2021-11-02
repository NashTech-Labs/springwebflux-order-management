package com.knoldus.springwebflux.ordermanagement.service;

import com.knoldus.springwebflux.ordermanagement.model.OrderRequest;
import com.knoldus.springwebflux.ordermanagement.model.OrderBill;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IOrderService {
  void create(OrderRequest e);

  Mono<OrderBill> findById(String orderId);

  Flux<OrderBill> findAll();

  Mono<OrderBill> update(OrderBill order);

  void delete(String orderId);
}

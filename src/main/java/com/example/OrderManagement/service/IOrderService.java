package com.example.OrderManagement.service;

import com.example.OrderManagement.model.OrderRequest;
import com.example.OrderManagement.model.OrderResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IOrderService {
  Mono<OrderResponse> create(OrderRequest e);

  Mono<OrderResponse> findById(String orderId);

  Flux<OrderResponse> findAll();

  Mono<OrderResponse> update(OrderResponse order);

  Mono<Void> delete(String orderId);
}

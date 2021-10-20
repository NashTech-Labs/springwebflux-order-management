package com.example.OrderManagement.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This is the model class, which we are using to store into mongo and also returning the same for
 * any GET request.
 */
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Document
public class OrderResponse {

  @Id String orderId;
  OrderRequest orderRequest;

  public OrderResponse() {}

  public OrderResponse(String orderId, OrderRequest orderRequest) {
    this.orderId = orderId;
    this.orderRequest = orderRequest;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public OrderRequest getOrderRequest() {
    return orderRequest;
  }

  public void setOrderRequest(OrderRequest orderRequest) {
    this.orderRequest = orderRequest;
  }

  @Override
  public String toString() {
    return "OrderResponse{" + "orderId='" + orderId + '\'' + ", orderRequest=" + orderRequest + '}';
  }
}

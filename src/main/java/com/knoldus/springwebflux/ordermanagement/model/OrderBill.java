package com.knoldus.springwebflux.ordermanagement.model;

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
public class OrderBill {

  @Id
  String orderId;
  String orderDate;
  Long totalOrderPrice;
  OrderRequest orderRequest;

  public OrderBill() {}

  public OrderBill(String orderId, String orderDate, Long totalOrderPrice, OrderRequest orderRequest) {
    this.orderId = orderId;
    this.orderDate = orderDate;
    this.totalOrderPrice = totalOrderPrice;
    this.orderRequest = orderRequest;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(String orderDate) {
    this.orderDate = orderDate;
  }

  public Long getTotalOrderPrice() {
    return totalOrderPrice;
  }

  public void setTotalOrderPrice(Long totalOrderPrice) {
    this.totalOrderPrice = totalOrderPrice;
  }

  public OrderRequest getOrderRequest() {
    return orderRequest;
  }

  public void setOrderRequest(OrderRequest orderRequest) {
    this.orderRequest = orderRequest;
  }

  @Override
  public String toString() {
    return "OrderResponse{" +
            "orderId='" + orderId + '\'' +
            ", orderDate='" + orderDate + '\'' +
            ", totalOrderPrice=" + totalOrderPrice +
            ", orderRequest=" + orderRequest +
            '}';
  }
}

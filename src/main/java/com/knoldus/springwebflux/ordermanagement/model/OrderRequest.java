package com.knoldus.springwebflux.ordermanagement.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/** This is the model class to get order request json into Object. */
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Document
public class OrderRequest {

  String customerName;
  String customerMobileNo;
  String address;
  List<Product> product;

  public OrderRequest() {}


  public OrderRequest(List<Product> product, String customerName, String customerMobileNo, String address) {
    this.customerName = customerName;
    this.customerMobileNo = customerMobileNo;
    this.address = address;
    this.product = product;
  }

  public List<Product> getProduct() {
    return product;
  }

  public void setProduct(List<Product> product) {
    this.product = product;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getCustomerMobileNo() {
    return customerMobileNo;
  }

  public void setCustomerMobileNo(String customerMobileNo) {
    this.customerMobileNo = customerMobileNo;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "OrderRequest{" +
            "customerName='" + customerName + '\'' +
            ", customerMobileNo='" + customerMobileNo + '\'' +
            ", address='" + address + '\'' +
            ", product=" + product +
            '}';
  }
}

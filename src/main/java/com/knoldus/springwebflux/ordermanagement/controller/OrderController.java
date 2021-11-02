package com.knoldus.springwebflux.ordermanagement.controller;

import com.knoldus.springwebflux.ordermanagement.model.OrderRequest;
import com.knoldus.springwebflux.ordermanagement.model.OrderBill;
import com.knoldus.springwebflux.ordermanagement.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This is the Controller class using to handle the request and having the method to addProduct,
 * availableProduct, createOrder, etc
 */
@RestController
public class OrderController {

  @Autowired
  private IOrderService orderService;

  /** This method is used to create a new order */
  @RequestMapping(value = {"/createOrder"}, method = RequestMethod.POST)
  public void createOrder(@RequestBody OrderRequest order) {
    orderService.create(order);
  }

  /** This method is used get the order details for a particular orderID. */
  @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
  public ResponseEntity<Mono<OrderBill>> getOrderById(@PathVariable("id") String id) {
    Mono<OrderBill> order = orderService.findById(id);
    HttpStatus status = order != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    return new ResponseEntity<Mono<OrderBill>>(order, status);
  }

  /** This method is used to get all the orders details */
  @RequestMapping(value = "/allOrder", method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<OrderBill> getAllOrder() {

    return orderService.findAll();
  }

  /** This method is used to update an existing order */
  @RequestMapping(value = "/updateOrder", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  public Mono<OrderBill> updateOrder(@RequestBody OrderBill e) {

    return orderService.update(e);
  }

  /** This method is used to delete an existing order */
  @RequestMapping(value = "/deleteOrder/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.OK)
  public void deleteOrder(@PathVariable("id") String id) {
    /*
     * In the below code we are subscribing delete events emitted by a Publisher.
     */
    orderService.delete(id);
  }
}

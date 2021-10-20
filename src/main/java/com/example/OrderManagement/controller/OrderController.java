package com.example.OrderManagement.controller;

import com.example.OrderManagement.model.OrderRequest;
import com.example.OrderManagement.model.OrderResponse;
import com.example.OrderManagement.model.Product;
import com.example.OrderManagement.service.OrderService;
import com.example.OrderManagement.util.AvailableProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * This is the Controller class using to handle the request and having the method to addProduct,
 * availableProduct, createOrder, etc
 */
@RestController
public class OrderController {
  @Autowired private OrderService orderService;

  /**
   * This method is used to add the product into the available product map, if the productId is not
   * exist in that available product map.
   */
  @RequestMapping(
      value = {"/addProduct"},
      method = RequestMethod.POST)
  public ResponseEntity<Object> addProduct(@RequestBody Product order) {
    AtomicReference<HttpStatus> status = new AtomicReference<>();

    // TODO: Reactive stream: below line explaination
    AvailableProducts.getProductsMap()
        .subscribe(
            x -> {
              status.set(
                  x.containsKey(order.getProductId()) ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
            });
    if (status.get().is4xxClientError()) {
      return new ResponseEntity<>("ProductId is already exists ", status.get());
    } else {
      AvailableProducts.updateProductMap(order);
      return new ResponseEntity<>(order, status.get());
    }
  }

  /** This method is used to get all the available Product in the system. */
  @RequestMapping(
      value = {"/availableProduct"},
      method = RequestMethod.GET)
  public ResponseEntity<Object> getAvailableProduct() {
    return new ResponseEntity<>(AvailableProducts.getProductsMap().log(), HttpStatus.OK);
  }

  /** This method is used to create a order */
  @RequestMapping(
      value = {"/createOrder"},
      method = RequestMethod.POST)
  public ResponseEntity<Mono<OrderResponse>> createOrder(@RequestBody OrderRequest order) {
    AtomicReference<HttpStatus> status = new AtomicReference<>();
    /*
     *In the below code we are subscribing events emitted by a Publisher.
     * and checking if the order that we want to create is available in the productList or not.
     * If not available then it will return bad request.
     * If available then it will create a order for you and return the details with orderId.
     */
    AvailableProducts.getProductsMap()
        .subscribe(
            x -> {
              List<Product> products = order.getProduct();
              products.forEach(
                  product -> {
                    status.set(
                        x.containsKey(product.getProductId())
                            ? HttpStatus.BAD_REQUEST
                            : HttpStatus.OK);
                  });
            });
    /*
     * Here we are checking that the status code we set in above line is in 400 series or not
     * if yes then it will only return the status code
     * else if will return the order details with 200 as status code.
     */
    if (status.get().is4xxClientError()) {
      return new ResponseEntity<>(status.get());
    }
    return new ResponseEntity<>(orderService.create(order), status.get());
  }

  /** This method is used get the order details for a particular ID. */
  @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
  public ResponseEntity<Mono<OrderResponse>> getOrderById(@PathVariable("id") String id) {
    Mono<OrderResponse> order = orderService.findById(id);
    HttpStatus status = order != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    return new ResponseEntity<Mono<OrderResponse>>(order, status);
  }

  /** This method is used to get all the orders details */
  @RequestMapping(
      value = "/allOrder",
      method = RequestMethod.GET,
      produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<OrderResponse> getAllOrder() {
    return orderService.findAll();
  }

  /** This method is used to update the existing order */
  @RequestMapping(value = "/updateOrder", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  public Mono<OrderResponse> updateOrder(@RequestBody OrderResponse e) {
    return orderService.update(e);
  }

  /** This method is used to delete a existing order */
  @RequestMapping(value = "/deleteOrder/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.OK)
  public void deleteOrder(@PathVariable("id") String id) {
    /*
     * In the below code we are subscribing delete events emitted by a Publisher.
     */
    orderService.delete(id).subscribe();
  }
}

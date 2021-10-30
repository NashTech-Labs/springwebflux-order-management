package com.knoldus.springwebflux.ordermanagement.controller;

import com.knoldus.springwebflux.ordermanagement.model.OrderBill;
import com.knoldus.springwebflux.ordermanagement.model.OrderRequest;
import com.knoldus.springwebflux.ordermanagement.model.Product;
import com.knoldus.springwebflux.ordermanagement.repo.OrderRepository;
import com.knoldus.springwebflux.ordermanagement.service.OrderService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = OrderController.class)
@Import(OrderService.class)
public class OrderControllerTest {

  @MockBean OrderRepository repository;

  @Autowired private WebTestClient webClient;
  private static OrderBill orderBill;
  private static OrderRequest orderRequest;

  @BeforeAll
  private static void setup(){
    List<Product> products = new ArrayList<>();
    products.add(new Product(12, "desktop", 100L));
    orderRequest = new OrderRequest(products, "Knoldus", "09090909", "Noida");
    orderBill = new OrderBill("1", LocalDateTime.now().toString(), 100L, orderRequest);
  }

  @Test
  void WhenCallCreateOrder_ThenStatusShouldHttpOk() {

    Mockito.when(repository.save(any())).thenReturn(Mono.just(orderBill));

    webClient
        .post()
        .uri("/createOrder")
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(orderRequest))
        .exchange()
        .expectStatus()
        .isOk();
    Mockito.verify(repository, times(1)).save(any());
  }


  @Test
  void WhenCallDeleteByPassingId_ThenReturnStatusOk() {
    Mono<Void> voidReturn = Mono.empty();
    Mockito.when(repository.deleteById("1")).thenReturn(voidReturn);

    webClient.delete().uri("/deleteOrder/{id}", "1")
            .exchange()
            .expectStatus()
            .isOk();
  }

  @Test
  void WhenCallGetOrderById_ThenStatusOkAndResponseBodyShouldNotBeNull()
  {

    Mockito.when(repository.findById("1"))
            .thenReturn(Mono.just(orderBill));

    webClient.get().uri("/order/{id}", "1")
            .exchange()
            .expectStatus().isOk();

    Mockito.verify(repository, times(1)).findById("1");
  }


  @Test
  public void WhenCallGetAllOrder_ThenStatusOkAndResponseBodyShouldNotBeNull() {
    OrderBill bill = new OrderBill();
    bill.setOrderId("1");

    Mockito.when(repository.findAll()).thenReturn(Flux.just(bill));

    byte[] bodyContentSpec =
        webClient
            .get()
            .uri("/allOrder")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody()
            .returnResult()
            .getResponseBody();
    Assert.notNull(new String(bodyContentSpec));
    Mockito.verify(repository, times(1)).findAll();
  }

  @Test
  public void WhenCallUpdateOrder_ThenStatusOkAndResponseBodyShouldNotBeNull() {
    Mockito.when(repository.save(any())).thenReturn(Mono.just(orderBill));

    webClient
            .put()
            .uri("/updateOrder")
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromObject(orderBill))
        .exchange()
        .expectStatus()
        .isOk();
    Mockito.verify(repository, times(1)).save(any());
  }

}

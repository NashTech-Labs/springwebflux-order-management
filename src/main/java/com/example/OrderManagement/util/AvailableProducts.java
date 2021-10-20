package com.example.OrderManagement.util;

import com.example.OrderManagement.model.OrderRequest;
import com.example.OrderManagement.model.Product;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * This class is having the method to get Available Product or if you want to add new Products into
 * Product list.
 */
public class AvailableProducts {

  public static Map<Integer, Product> productsMap = new HashMap<>();
  public static Flux<Map<Integer, Product>> fluxMap;
  /**
   * This is static block, that is executed when the class load in JVM and we are adding some
   * product here, so that when the application up then these products are available.
   */
  static {

    productsMap.put(1, new Product(1, "mouse", 300));
    productsMap.put(2, new Product(2, "earphone", 500));
    fluxMap = Flux.just(productsMap);
  }

  /** This method will return the available product map */
  public static Flux<Map<Integer, Product>> getProductsMap() {
    return fluxMap;
  }

  /**
   * This method will add the new product into available product map. But when we tried to add
   * anything into flux object we need to concat that will returns new object that we need to assign
   * to existing variable.
   */
  public static void updateProductMap(Product product) {
    Map<Integer, Product> newProduct = new HashMap<Integer, Product>();
    newProduct.put(product.getProductId(), product);
    /*
    * In the below code, we are concating the 2 flux into one, as we know Flux and Mono are immutable.
    * So we are concating two flux to create a new flux.
    */
    fluxMap = Flux.concat(fluxMap, Flux.just(newProduct));
  }
}

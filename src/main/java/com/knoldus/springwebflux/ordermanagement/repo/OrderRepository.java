package com.knoldus.springwebflux.ordermanagement.repo;

import com.knoldus.springwebflux.ordermanagement.model.OrderBill;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * This is the Repository Interface which is implemented from ReactiveMongoRepository interface and
 * this Mongo interface have all the required method that we are using to retrive and story the data
 * into db.
 */
public interface OrderRepository extends ReactiveMongoRepository<OrderBill, String> {}

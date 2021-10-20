package com.example.OrderManagement.model;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * This is the Repository Interface which is implemented from ReactiveMongoRepository interface and
 * this Mongo interface have all the required method that we are using to retrive and story the data
 * into db.
 */
public interface OrderRepository extends ReactiveMongoRepository<OrderResponse, String> {}

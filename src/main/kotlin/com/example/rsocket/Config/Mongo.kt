package com.example.rsocket

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients

@Configuration
@EnableReactiveMongoRepositories
public class MongoConfig: AbstractReactiveMongoConfiguration() {
  @Value("\${mongodb.port}")
  private lateinit var port: String;
    
  @Value("\${mongodb.dbname}")
  private lateinit var dbName: String;

  override fun reactiveMongoClient(): MongoClient {
    return MongoClients.create();
  }

  override protected fun getDatabaseName(): String {
    return dbName
  }

  @Bean
  fun reactiveMongoTemplate(): ReactiveMongoTemplate {
    return ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
  }
}
package com.example.rsocket

import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface HighYieldNoteRepository: ReactiveMongoRepository<HighYieldNote, Int> {
  @Query("{ 'id': ?0 }")
  abstract fun findById(id: String): Flux<HighYieldNote>

  @Query("{ 'underlying': ?0 }")
  abstract fun findByUnderlying(underlying: String): Flux<HighYieldNote>

  @Query("{ 'id': ?0 }", delete = true)
  abstract fun deleteById(id: String): Mono<Unit>
}
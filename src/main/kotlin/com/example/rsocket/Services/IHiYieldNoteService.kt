package com.example.rsocket

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
 
public interface IHiYieldNoteService {
  abstract fun create(e: HighYieldNote)

  abstract fun findOneById(id: String): Mono<HighYieldNote>

  abstract fun findById(id: String): Flux<HighYieldNote>

  abstract fun findOneByUnderlying(underlying: String): Mono<HighYieldNote>

  abstract fun findByUnderlying(underlying: String): Flux<HighYieldNote>

  abstract fun findAll(): Flux<HighYieldNote>

  abstract fun update(e: HighYieldNote): Mono<HighYieldNote>

  abstract fun delete(id: String): Mono<Unit>
}
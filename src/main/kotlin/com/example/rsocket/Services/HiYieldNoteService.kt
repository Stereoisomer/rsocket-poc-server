package com.example.rsocket

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
 
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
public class HiYieldNoteService: IHiYieldNoteService {
     
  @Autowired
  lateinit var hiYieldNoteRepo: HighYieldNoteRepository

  override fun create(e: HighYieldNote) {
    hiYieldNoteRepo.save(e).subscribe()
  }

  override fun findOneById(id: String): Mono<HighYieldNote> {
    return hiYieldNoteRepo.findById(id).next()
  }

  override fun findById(id: String): Flux<HighYieldNote> {
    return hiYieldNoteRepo.findById(id)
  }

  override fun findOneByUnderlying(underlying: String): Mono<HighYieldNote> {
    return hiYieldNoteRepo.findByUnderlying(underlying).next()
  }

  override fun findByUnderlying(underlying: String): Flux<HighYieldNote> {
    return hiYieldNoteRepo.findByUnderlying(underlying)
  }

  override fun findAll(): Flux<HighYieldNote> {
    return hiYieldNoteRepo.findAll()
  }

  override fun update(e: HighYieldNote): Mono<HighYieldNote> {
    return hiYieldNoteRepo.save(e)
  }

  override fun delete(id: String): Mono<Unit> {
    return hiYieldNoteRepo.deleteById(id)
  } 
}
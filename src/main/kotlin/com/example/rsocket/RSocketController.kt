package com.example.rsocket

import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.MessageExceptionHandler
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDate


@Controller
class RSocketController {

  @Autowired
  private lateinit var hiYieldNoteService: HiYieldNoteService

  @MessageMapping("yield-create")
  fun createInstruments(instruments: Flux<String>): Flux<String> {
    println("Inside yield-create")
    return Flux.from(instruments)
      .doOnNext { hiYieldNoteService.create(HighYieldNote(
        "bsc.${it.toString()}",
        "bsc",
        it.toString(),
        3000.0.toFloat(),
        LocalDate.parse("2022-04-01")
      ))}
      .map { "ok" }
  }

  @MessageMapping("get-yield")
  fun getInstruments(instrument: Mono<String>): Mono<HighYieldNote> {
    println("Inside get-yield")

    return Mono
      .from(instrument)
      .handle { t, u -> hiYieldNoteService.findOneByUnderlying(t) }
  }
}

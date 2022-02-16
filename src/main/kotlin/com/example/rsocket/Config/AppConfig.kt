package com.example.rsocket

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.buffer.DefaultDataBufferFactory
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.messaging.rsocket.RSocketStrategies
import reactor.util.retry.Retry
import java.time.Duration

import org.springframework.http.codec.cbor.Jackson2CborDecoder
import org.springframework.http.codec.cbor.Jackson2CborEncoder
import org.springframework.messaging.rsocket.annotation.support.RSocketMessageHandler
import org.springframework.web.util.pattern.PathPatternRouteMatcher

@Configuration
public class AppConfiguration {
  companion object {
    @Bean
    fun getRSocketRequester(): RSocketRequester {
      val strategies = RSocketStrategies.builder()
        .routeMatcher(PathPatternRouteMatcher())
        .build()
      val responder =
        RSocketMessageHandler.responder(strategies, RSocketController())
      return RSocketRequester.builder()
        .rsocketConnector { it.acceptor(responder) }
        .tcp("localhost", 8001)

//      val builder: RSocketRequester.Builder = RSocketRequester.builder()
//
//      return builder
//        .rsocketConnector{
//            rSocketConnector ->
//          rSocketConnector.reconnect(Retry.fixedDelay(2, Duration.ofSeconds(2)))
//        }
//        .rsocketStrategies(rsocketStrategies())
//        .tcp("localhost", 8000);
    }

  }
}
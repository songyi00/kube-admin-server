package com.example.kubeadminserver.adaptor.`in`

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RequestPredicate
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class RouterConfig {

    @Bean
    fun routerFunction(serverMonitoringHandler: ServerMonitoringHandler): RouterFunction<*> = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            GET("/server-monitoring/pods/{namespace}", RequestPredicates.all()
                .and(queryParam("namespace") { true }), serverMonitoringHandler::getPodsInNamespace
            )
        }
    }
}
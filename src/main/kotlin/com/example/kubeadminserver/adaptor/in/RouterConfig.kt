package com.example.kubeadminserver.adaptor.`in`

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class RouterConfig {

    @Bean
    fun routerFunction(serverMonitoringHandler: ServerMonitoringHandler): RouterFunction<*> = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            GET("/hello", serverMonitoringHandler::hello)
            GET(
                "/server-monitoring/pods/{namespace}", serverMonitoringHandler::getPodsInNamespace
            )
        }
    }
}
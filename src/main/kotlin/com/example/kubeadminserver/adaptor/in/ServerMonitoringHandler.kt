package com.example.kubeadminserver.adaptor.`in`

import com.example.kubeadminserver.application.port.ServerMonitoringUseCase
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait

@Controller
class ServerMonitoringHandler(
    private val serverMonitoringUseCase: ServerMonitoringUseCase
) {
    suspend fun hello(serverRequest: ServerRequest): ServerResponse {
        return ServerResponse.status(HttpStatus.OK).buildAndAwait()
    }

    suspend fun getPodsInNamespace(request: ServerRequest): ServerResponse {
        val namespace = request.pathVariable("namespace")
        return ServerResponse.status(HttpStatus.CREATED)
            .bodyValueAndAwait(serverMonitoringUseCase.getPodsInNameSpace(namespace))
    }
}
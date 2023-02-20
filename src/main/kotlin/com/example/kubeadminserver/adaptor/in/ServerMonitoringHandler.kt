package com.example.kubeadminserver.adaptor.`in`

import com.example.kubeadminserver.application.port.ServerMonitoringUseCase
import com.example.kubeadminserver.application.service.ServerMonitoringService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Controller
class ServerMonitoringHandler(
    private val serverMonitoringUseCase: ServerMonitoringUseCase
) {
    suspend fun getPodsInNamespace(request: ServerRequest): ServerResponse {
        val namespace = request.queryParam("namespace")
            .orElseThrow { IllegalArgumentException("not found namespace") }
        return ServerResponse.status(HttpStatus.CREATED)
            .bodyValueAndAwait(serverMonitoringUseCase.getPodsInNameSpace(namespace))
    }
}
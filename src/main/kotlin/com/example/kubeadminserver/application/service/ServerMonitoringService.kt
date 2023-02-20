package com.example.kubeadminserver.application.service

import com.example.kubeadminserver.adaptor.config.K8sConfig
import com.example.kubeadminserver.application.port.ServerMonitoringUseCase
import com.example.kubeadminserver.domain.PodInfo
import com.example.kubeadminserver.domain.PodStatus
import io.fabric8.kubernetes.api.model.Pod
import org.springframework.stereotype.Service

@Service
class ServerMonitoringService(private val k8sConfig: K8sConfig) : ServerMonitoringUseCase {
    override suspend fun getPodsInNameSpace(namespace: String): List<PodInfo> {
        val pods = getPods(namespace) ?: emptyList()
        return pods.map {
            PodInfo(
                it.metadata.name,
                PodStatus(it.status.conditions, it.status.containerStatuses, it.status.message, it.status.startTime)
            )
        }
    }

    private fun getPods(namespace: String): MutableList<Pod>? =
        k8sConfig.getKubernetesClient()
            .pods()
            .inNamespace(namespace)
            .list()
            .items
}
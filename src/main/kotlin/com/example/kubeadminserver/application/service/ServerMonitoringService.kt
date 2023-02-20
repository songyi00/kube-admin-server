package com.example.kubeadminserver.application.service

import com.example.kubeadminserver.adaptor.config.K8sConfig
import com.example.kubeadminserver.application.port.ServerMonitoringUseCase
import com.example.kubeadminserver.domain.ContainerState
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
                PodStatus(getPodState(it), it.status.message, it.status.startTime)
            )
        }
    }

    private fun getPodState(it: Pod): String {
//        val statuses = it.status.containerStatuses
//        if (statuses.isEmpty()){
//            return ContainerState.TERMINATED
//        }
//        if (statuses[0].state.running != null) {
//            return ContainerState.RUNNING
//        }
//        if (statuses[0].state.waiting  != null) {
//            return ContainerState.WAITING
//        }
//        return ContainerState.TERMINATED
        return it.status.phase
    }

    private fun getPods(namespace: String): MutableList<Pod>? =
        k8sConfig.getKubernetesClient()
            .pods()
            .inNamespace(namespace)
            .list()
            .items
}
package com.example.kubeadminserver.application.service

import com.example.kubeadminserver.adaptor.config.K8sConfig
import com.example.kubeadminserver.application.port.ServerMonitoringUseCase
import com.example.kubeadminserver.domain.Phase
import com.example.kubeadminserver.domain.PodInfo
import com.example.kubeadminserver.domain.PodStatus
import io.fabric8.kubernetes.api.model.Pod
import org.springframework.stereotype.Service

@Service
class ServerMonitoringService(private val k8sConfig: K8sConfig) : ServerMonitoringUseCase {
    override suspend fun getPodsInNameSpace(namespace: String): List<PodInfo> {
        val pods = getPods(namespace) ?: emptyList()
        return pods.map {
            podInfo(it)
        }
    }

    //TODO(추후 fabric8 라이브러리 객체 값을 어댑터에서 서비스 dto 로 변경 후 사용하도록 변경하기
    // -> 응용 로직에 외부 라이브러리가 들어가는 것이 좋지 않아보임
    private fun podInfo(it: Pod): PodInfo {
        val status = it.status
        return PodInfo(
            it.metadata.name,
            PodStatus(getPodState(status.phase), status.message, status.startTime)
        )
    }

    private fun getPodState(phase: String): Phase {
        return Phase.getPhase(phase)
    }

    private fun getPods(namespace: String): MutableList<Pod>? =
        k8sConfig.getKubernetesClient()
            .pods()
            .inNamespace(namespace)
            .list()
            .items
}
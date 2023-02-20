package com.example.kubeadminserver.application.port

import com.example.kubeadminserver.domain.PodInfo

interface ServerMonitoringUseCase {
    suspend fun getPodsInNameSpace(namespace: String): List<PodInfo>
}
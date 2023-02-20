package com.example.kubeadminserver.domain

import io.fabric8.kubernetes.api.model.ContainerStatus
import io.fabric8.kubernetes.api.model.PodCondition

data class PodStatus(
    val conditions: MutableList<PodCondition>?,
    val containerStatuses: MutableList<ContainerStatus>?,
    val message: String?,
    val startTime: String?
)
package com.example.kubeadminserver.domain

data class PodStatus(
    val state: ContainerState,
    val message: String?,
    val startTime: String?
)
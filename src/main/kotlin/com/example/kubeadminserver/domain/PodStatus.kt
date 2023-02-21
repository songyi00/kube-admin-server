package com.example.kubeadminserver.domain

data class PodStatus(
    val state: Phase,
    val message: String?,
    val startTime: String?
)
package com.example.kubeadminserver.domain

data class PodInfo(
    val name: String,
    val status: PodStatus
)
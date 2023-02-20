package com.example.kubeadminserver.domain

data class PodStatus(
    val state: String, // TODO(Running, Pending, Failed) enum 변경
    val message: String?,
    val startTime: String?
)
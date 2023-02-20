package com.example.kubeadminserver.domain

enum class ContainerState{
    RUNNING,
    TERMINATED,
    WAITING,
    ERROR;
}
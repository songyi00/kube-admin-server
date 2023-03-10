package com.example.kubeadminserver.adaptor.config

import io.fabric8.kubernetes.client.DefaultKubernetesClient
import io.fabric8.kubernetes.client.KubernetesClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class K8sConfig {
    @Bean
    fun getKubernetesClient(): KubernetesClient {
        return DefaultKubernetesClient()
    }
}
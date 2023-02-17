package com.example.kubeadminserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KubeAdminServerApplication

fun main(args: Array<String>) {
	runApplication<KubeAdminServerApplication>(*args)
}

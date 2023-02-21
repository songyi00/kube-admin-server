package com.example.kubeadminserver.domain

import java.lang.IllegalArgumentException

enum class Phase(val value: String) {
    RUNNING("Running"),
    PENDING("Pending"),
    SUCCEEDED("Succeeded"),
    FAILED("Failed");

    companion object {
        fun getPhase(value: String): Phase {
            return when (value) {
                RUNNING.value -> RUNNING
                PENDING.value -> PENDING
                FAILED.value -> FAILED
                SUCCEEDED.value -> SUCCEEDED
                else -> {
                    throw IllegalArgumentException("not found phase")
                }
            }
        }
    }
}

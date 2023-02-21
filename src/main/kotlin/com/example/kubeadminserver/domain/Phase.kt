package com.example.kubeadminserver.domain

import java.lang.IllegalArgumentException


enum class Phase(val value: String) {
    RUNNING("Running"),
    PENDING("Pending"),
    SUCCEEDED("Succeeded"),
    FAILED("Failed");

    companion object {
        private val mapping = values().associateBy(Phase::value)

        fun fromValue(value: String): Phase {
            return mapping[value] ?: throw IllegalArgumentException("not found phase")
        }
    }
}

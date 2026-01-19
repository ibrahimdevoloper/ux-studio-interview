package com.ibrahim.interview.Exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

data class ApiError(
    private val type: String? = null,
    private val title: String? = null,
    private val status: Int = HttpStatus.BAD_REQUEST.value(),
    private val detail: String? = null,
    private val instance: String? = null,
    )

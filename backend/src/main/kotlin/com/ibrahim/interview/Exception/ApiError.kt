package com.ibrahim.interview.Exception

import lombok.Getter
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

data class ApiError(
    val type: String? = null,
    val title: String? = null,
    val status: Int = HttpStatus.BAD_REQUEST.value(),
    val detail: String? = null,
    val instance: String? = null
    )

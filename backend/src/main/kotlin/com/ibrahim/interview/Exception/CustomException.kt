package com.ibrahim.interview.Exception

import lombok.Getter
import org.springframework.http.HttpStatus

@Getter
class CustomException : Exception {
    var status: HttpStatus? = null

    constructor(message: String?) : super(message)

    constructor(message: String?, status: HttpStatus?) : super(message) {
        this.status = status
    }

}

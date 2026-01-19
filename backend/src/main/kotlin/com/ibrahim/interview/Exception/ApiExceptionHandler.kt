package com.ibrahim.interview.Exception

import com.google.cloud.storage.StorageException
import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ApiExceptionHandler{


    @ExceptionHandler(CustomException::class)
    fun handleCustomException(ex: CustomException, request: WebRequest):
            ResponseEntity<ApiError> {
        val response: ApiError = ApiError(
            "CustomException",
            "Bad Request",
            HttpStatus.BAD_REQUEST.value(),
            ex.message,
            request.getContextPath()
        )
//        ex.printStackTrace()
//        if (ex.status == null) {
//            return ResponseEntity(response, HttpStatus.BAD_REQUEST)
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body<ApiError>(response)
//        }
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body<ApiError>(response)

//        return ResponseEntity.status(ex.status?:HttpStatus.BAD_REQUEST).body<ApiError>(response)
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleRecordNotFound(ex: EntityNotFoundException, request: WebRequest):
            ResponseEntity<ApiError> {
        val response: ApiError = ApiError(
            "EntityNotFoundException",
            "Not Found",
            HttpStatus.NOT_FOUND.value(),
            ex.message,
            request.getContextPath()
        )
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body<ApiError>(response)
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(StorageException::class)
    fun handleStorageException(
        ex: StorageException?,
        request: WebRequest
    ): ResponseEntity<ApiError> {
        val response: ApiError = ApiError(
            "FirebaseStorageException",
            ex?.message ?: "Firebase Storage Error Acurred",
            HttpStatus.BAD_REQUEST.value(),
            "Firebase Storage Error Acurred",
            request.getContextPath()
        )
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body<ApiError>(response)
    }
}
package com.ibrahim.interview.RequestFrom

import lombok.Data
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.multipart.MultipartFile

@Data
class ContactRequestForm (
    val firstName: String?,
    val lastName: String?,
    val phoneNumber: String?,
    @RequestPart ("image")
    val image: MultipartFile?,
    val email: String?,
)
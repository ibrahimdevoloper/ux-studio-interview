package com.ibrahim.interview.Dto


data class ContactDTO(
    val id: Int?,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String?,
    val email: String?,
    val imagePath: String?,
    val imageReference: String?,
    val storageProvider: String?,
)
package com.ibrahim.interview.Entity

import jakarta.annotation.Generated
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
data class Contact (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String?,
    val email: String?,
    val imagePath: String?,
    val imageReference: String?,
    val storageProvider: String?,
)
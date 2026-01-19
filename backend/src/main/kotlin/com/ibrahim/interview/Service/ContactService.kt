package com.ibrahim.interview.Service

import com.ibrahim.interview.Repository.ContactRepo
import com.ibrahim.interview.Dto.ContactDTO
import com.ibrahim.interview.Entity.Contact
import com.ibrahim.interview.Mapper.ContactDTOMapper
import com.ibrahim.interview.RequestFrom.ContactRequestForm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.UUID
import java.net.URLEncoder
import com.google.cloud.storage.BlobId
import com.google.cloud.storage.BlobInfo
import com.google.cloud.storage.Storage
import com.google.firebase.cloud.StorageClient
import com.ibrahim.interview.Exception.CustomException
import org.springframework.data.repository.findByIdOrNull

@Service
class ContactService(
    private val contactRepo: ContactRepo,
    private val contactMapper: ContactDTOMapper,
    @Value("\${firebase.bucket-name}") private val bucketName: String
) {
    fun create(form: ContactRequestForm): ContactDTO {

        if (form.firstName == null || form.lastName == null) {
            throw CustomException("You should send at least both first name and the last name");
        }
        var imagePath: String? = null
        var imageReference: String? = null
        var storageProvider: String? = null
        //I could not create an AWS Account in time,
        //so I used firebase storage as an alternative.
        if (form.image != null) {
            val fileName = "contacts/${UUID.randomUUID()}-${form.image.originalFilename}"
            val bucket = StorageClient.getInstance().bucket(bucketName)

            val blob = bucket.create(
                fileName,
                form.image.inputStream,
                form.image.contentType
            )
            imageReference = fileName
            storageProvider = "Firebase"

            val encodedPath = URLEncoder.encode(fileName, "UTF-8")
            imagePath = "https://firebasestorage.googleapis.com/v0/b/$bucketName/o/$encodedPath?alt=media"
        }
        val contact = Contact(
            0,
            form.firstName,
            form.lastName,
            form.phoneNumber,
            form.email,
            imagePath,
            imageReference,
            storageProvider

        )
        contactRepo.save(contact)
        return contactMapper.fromModelToDto(contact)
    }

    fun update(form: ContactRequestForm, contactId: Int): ContactDTO {

        var contact: Contact? = contactRepo.findByIdOrNull(contactId)
        if (contact == null) {
            throw CustomException("Contact not found");
        }

        var imagePath: String? = null
        var imageReference: String? = null
        var storageProvider: String? = null
        //I could not create an AWS Account in time,
        //so I used firebase storage as an alternative.
        if (form.image != null) {
            val fileName = "contacts/${UUID.randomUUID()}-${form.image.originalFilename}"
            val bucket = StorageClient.getInstance().bucket(bucketName)

            if (contact.imageReference != null) {
                bucket.get(contact.imageReference).delete()
            }

            val blob = bucket.create(
                fileName,
                form.image.inputStream,
                form.image.contentType
            )
            imageReference = fileName
            storageProvider = "Firebase"

            val encodedPath = URLEncoder.encode(fileName, "UTF-8")
            imagePath = "https://firebasestorage.googleapis.com/v0/b/$bucketName/o/$encodedPath?alt=media"
        }

        contact = Contact(
            contactId,
            form.firstName ?: contact.firstName,
            form.lastName ?: contact.lastName,
            form.phoneNumber ?: contact.phoneNumber,
            form.email,
            imagePath,
            imageReference,
            storageProvider
        )
        contactRepo.save(contact)
        return contactMapper.fromModelToDto(contact)
    }

}
package com.ibrahim.interview.Controller

import com.ibrahim.interview.Dto.ContactDTO
import com.ibrahim.interview.RequestFrom.ContactRequestForm
import com.ibrahim.interview.Service.ContactService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController()
@RequestMapping("api/v1/contacts")
class ContactController(val contactService: ContactService) {

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun createContact(form: ContactRequestForm): ResponseEntity<ContactDTO> {
        val contact = contactService.create(form)
        return ResponseEntity(contact, HttpStatus.CREATED)
    }

    @PutMapping("/{id}", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun updateContact(
        form: ContactRequestForm,
        @PathVariable("id") contactId: Int
    )
            : ResponseEntity<ContactDTO> {
        val contact = contactService.update(form, contactId)
        return ResponseEntity(contact, HttpStatus.ACCEPTED)
    }

    @DeleteMapping("/{id}")
    fun deleteContact(
        @PathVariable("id") contactId: Int
    )
            : ResponseEntity<String> {
        val message = contactService.delete( contactId)
        return ResponseEntity(message, HttpStatus.ACCEPTED)
    }
}
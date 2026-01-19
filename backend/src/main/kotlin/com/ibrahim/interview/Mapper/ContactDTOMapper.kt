package com.ibrahim.interview.Mapper

import com.ibrahim.interview.Dto.ContactDTO
import com.ibrahim.interview.Entity.Contact
import com.ibrahim.interview.utils.DTOMapper
import org.springframework.stereotype.Component

@Component
class ContactDTOMapper : DTOMapper<Contact, ContactDTO> {

    override fun fromDtoToModel(dto: ContactDTO): Contact {
        val contact:Contact = Contact(
            dto.id?:0,
            dto.firstName,
            dto.lastName,
            dto.phoneNumber,
            dto.email,
            dto.imagePath,
            dto.imageReference,
            dto.storageProvider)
        return contact
    }

    override fun fromModelToDto(model: Contact): ContactDTO {
        val contactDTO:ContactDTO = ContactDTO(
            model.id,
            model.firstName,
            model.lastName,
            model.phoneNumber,
            model.email,
            model.imagePath,
            model.imageReference,
            model.storageProvider)
        return contactDTO
    }
}
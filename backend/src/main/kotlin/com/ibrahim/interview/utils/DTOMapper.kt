package com.ibrahim.interview.utils

interface DTOMapper<Model, Dto> {
    fun fromModelToDto(model: Model): Dto
    fun fromDtoToModel(dto: Dto): Model
}
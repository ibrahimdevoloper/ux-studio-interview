package com.ibrahim.interview.Repository

import com.ibrahim.interview.Entity.Contact
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ContactRepo: CrudRepository<Contact, Int>{
    @Query("SELECT c FROM Contact as c")
    fun getAllContact(): List<Contact>
}
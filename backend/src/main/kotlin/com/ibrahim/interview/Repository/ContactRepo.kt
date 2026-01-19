package com.ibrahim.interview.Repository

import com.ibrahim.interview.Entity.Contact
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ContactRepo: CrudRepository<Contact, Int>
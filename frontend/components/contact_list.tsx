"use client";

import { useState, useEffect } from "react";
import ContactItem from "./contact_item";
import styles from "../app/page.module.css";
import PopupEditContact from "./PopupEditContact";

interface Contact {
  id: number;
  firstName: string;
  lastName: string;
  phoneNumber: string;
  email?: string;
  imagePath?: string | null;
  imageReference?: string | null;
  storageProvider?: string | null;
}


const fallbackContacts: Contact[] = [
  {
    id: 5,
    firstName: "sdsd",
    lastName: "sdsdsd",
    phoneNumber: "095950145",
    email: "ibrahimsss",
    imagePath:
      "https://firebasestorage.googleapis.com/v0/b/test-harbuk.firebasestorage.app/o/contacts%2F5a48aabf-1d6b-4f4f-8811-6d00eac9f92d-photo_2025-09-18_10-34-29.jpg?alt=media",
    imageReference:
      "contacts/5a48aabf-1d6b-4f4f-8811-6d00eac9f92d-photo_2025-09-18_10-34-29.jpg",
    storageProvider: "Firebase",
  },

];

function ContactsList() {
  const [contacts, setContacts] = useState<Contact[]>([]);
  const [loading, setLoading] = useState(true);
  const [editContact, setEditContact] = useState<Contact | null>(null);

  useEffect(() => {
    async function fetchContacts() {
      try {
        const response = await fetch("http://127.0.0.1:8080/api/v1/contacts");
        if (!response.ok) throw new Error("Failed to fetch");

        const data: Contact[] = await response.json();
        console.error("Fetch contacts error:", data);
 
        if (!data || data.length === 0) {
          setContacts(fallbackContacts);
        } else {
          setContacts(data);
        }
      } catch (error) {
        console.error("Fetch contacts error:", error);
        setContacts(fallbackContacts);
      } finally {
        setLoading(false);
      }
    }
    fetchContacts();
  }, []);

  function handleEdit(contact: Contact) {
    setEditContact(contact);
    
  }

  function closeEdit() {
    setEditContact(null);
  }

  if (loading) return <div>Loading...</div>;

  return (
    <div className={styles.contactsList}>
      {contacts.map((c) => (
        <ContactItem key={c.id} contact={c} onEdit={() => handleEdit(c)} />
      ))}

      {editContact && (
        <PopupEditContact contact={editContact} onClose={closeEdit} />
      )}
    </div>
  );
}

export default ContactsList;
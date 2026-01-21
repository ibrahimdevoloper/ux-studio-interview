"use client";

import { useState, useRef } from "react";
import styles from "../app/page.module.css";

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

interface PopupEditContactProps {
  contact: Contact;
  onClose: () => void;
}

export default function PopupEditContact({
  contact,
  onClose,
}: PopupEditContactProps) {
  const [firstName, setFirstName] = useState(contact.firstName);
  const [lastName, setLastName] = useState(contact.lastName);
  const [phone, setPhone] = useState(contact.phoneNumber);
  const [email, setEmail] = useState(contact.email || "");
  const [imageFile, setImageFile] = useState<File | null>(null);
  const [previewSrc, setPreviewSrc] = useState(
    contact.imagePath || "/default-avatar.png",
  );

  const fileInputRef = useRef<HTMLInputElement>(null);

  const handleChangePictureClick = () => {
    fileInputRef.current?.click();
  };

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files && e.target.files.length > 0) {
      const file = e.target.files[0];
      setImageFile(file);

      const reader = new FileReader();
      reader.onload = () => {
        if (reader.result) {
          setPreviewSrc(reader.result as string);
        }
      };
      reader.readAsDataURL(file);
    }
  };

  const handleDeletePicture = () => {
    setImageFile(null);
    setPreviewSrc("/default-avatar.png");
    if (fileInputRef.current) fileInputRef.current.value = "";
  };

  const handleDone = async () => {
    const formData = new FormData();
    formData.append("firstName", firstName);
    formData.append("lastName", lastName);
    formData.append("phoneNumber", phone);
    formData.append("email", email);
    if (imageFile) {
      formData.append("image", imageFile);
    }

    try {
      const response = await fetch(
        `http://127.0.0.1:8080/api/v1/contacts/${contact.id}`,
        {
          method: "PUT",
          body: formData,
        },
      );
      if (!response.ok) {
        throw new Error("Failed to update contact");
      }

      onClose();
    } catch (error) {
      console.error(error);
      alert("Error updating contact. Please try again.");
    }
  };

  return (
    <div className={styles.popupOverlay}>
      <div className={styles.popup}>
        <h2>Edit contact</h2>
        <div className={styles.pictureSection}>
          <img
            src={previewSrc}
            alt={`${firstName} ${lastName}`}
            className={styles.avatarEdit}
          />
          <div className={styles.pictureActions}>
            <button
              className={styles.changePictureBtn}
              onClick={handleChangePictureClick}
            >
              <span className={styles.iconRotate}>⟲</span>{" "}
           
              Change picture
            </button>
            <button
              className={styles.deletePictureBtn}
              onClick={handleDeletePicture}
              aria-label="Delete picture"
            >
              🗑
            </button>
          </div>
          <input
            ref={fileInputRef}
            type="file"
            accept="image/*"
            onChange={handleFileChange}
            style={{ display: "none" }}
          />
        </div>

        <input
          type="text"
          value={firstName}
          onChange={(e) => setFirstName(e.target.value)}
          className={styles.inputField}
          placeholder="First name"
          autoFocus
        />
        <input
          type="text"
          value={lastName}
          onChange={(e) => setLastName(e.target.value)}
          className={styles.inputField}
          placeholder="Last name"
        />
        <input
          type="text"
          value={phone}
          onChange={(e) => setPhone(e.target.value)}
          className={styles.inputField}
          placeholder="Phone number"
        />
        <input
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          className={styles.inputField}
          placeholder="Email address"
        />

        <div className={styles.popupActions}>
          <button className={styles.cancelBtn} onClick={onClose}>
            Cancel
          </button>
          <button className={styles.doneBtn} onClick={handleDone}>
            Done
          </button>
        </div>
      </div>
    </div>
  );
}

"use client";

import { useState } from "react";
import styles from "../app/page.module.css";

function HeaderBar() {
  const [showPopup, setShowPopup] = useState(false);


  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [email, setEmail] = useState("");
  const [imageFile, setImageFile] = useState<File | null>(null);


  function handleImageChange(e: React.ChangeEvent<HTMLInputElement>) {
    if (e.target.files && e.target.files.length > 0) {
      setImageFile(e.target.files[0]);
    }
  }


  async function handleSubmit() {
    const formData = new FormData();
    formData.append("firstName", firstName);
    formData.append("lastName", lastName);
    formData.append("phoneNumber", phoneNumber);
    formData.append("email", email);

    if (imageFile) {
      formData.append("image", imageFile);
    }

    try {
      const response = await fetch("http://127.0.0.1:8080/api/v1/contacts", {
        method: "POST",
        body: formData, 
      });

      if (!response.ok) {
        throw new Error("Failed to add contact");
      }

    

      setShowPopup(false);

      setFirstName("");
      setLastName("");
      setPhoneNumber("");
      setEmail("");
      setImageFile(null);
    } catch (error) {
      console.error("Error adding contact:", error);
      alert("Error !");
    }
  }

  return (
    <>
      <div className={styles.header}>
        <h1>Contacts</h1>
        <div className={styles.actions}>
          <button className={styles.addbtn} onClick={() => setShowPopup(true)}>
            + Add new
          </button>
        </div>
      </div>

      {showPopup && (
        <div className={styles.popupOverlay}>
          <div className={styles.popup}>
            <h2>Add contact</h2>
            <div className={styles.pictureSection}>
              <div className={styles.avatarIcon}>👤</div>
              <input
                type="file"
                accept="image/*"
                onChange={handleImageChange}
                style={{ marginTop: "8px" }}
              />
            </div>
            <input
              type="text"
              placeholder="First name"
              className={styles.inputField}
              value={firstName}
              onChange={(e) => setFirstName(e.target.value)}
            />
            <input
              type="text"
              placeholder="Last name"
              className={styles.inputField}
              value={lastName}
              onChange={(e) => setLastName(e.target.value)}
            />
            <input
              type="text"
              placeholder="Phone number"
              className={styles.inputField}
              value={phoneNumber}
              onChange={(e) => setPhoneNumber(e.target.value)}
            />
            <input
              type="email"
              placeholder="Email address"
              className={styles.inputField}
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
            <div className={styles.popupActions}>
              <button
                className={styles.cancelBtn}
                onClick={() => setShowPopup(false)}
              >
                Cancel
              </button>
              <button className={styles.doneBtn} onClick={handleSubmit}>
                Done
              </button>
            </div>
          </div>
        </div>
      )}
    </>
  );
}

export default HeaderBar;
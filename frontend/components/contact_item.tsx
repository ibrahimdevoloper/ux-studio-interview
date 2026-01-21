"use client";

import { useState } from "react";
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

interface ContactItemProps {
  contact: Contact;
  onEdit: () => void;
}

export default function ContactItem({ contact, onEdit }: ContactItemProps) {
  const [showMenu, setShowMenu] = useState(false);

  const fullName = `${contact.firstName} ${contact.lastName}`;
  const phone = contact.phoneNumber;
  const avatarSrc = contact.imagePath || "/default-avatar.png";

  return (
    <div className={styles.contactItem}>
      <img src={avatarSrc} alt="" className={styles.avatar} />
      <div className={styles.contactInfo}>
        <div className={styles.contactName}>{fullName}</div>
        <div className={styles.contactPhone}>{phone}</div>
      </div>
      <div className={styles.actions}>
        <button className={styles.iconBtn} title="Mute">
          🔇
        </button>
        <button className={styles.iconBtn} title="Headphones">
          🎧
        </button>
        <div className={styles.menuWrapper}>
          <button
            className={styles.menuBtn}
            onClick={() => setShowMenu(!showMenu)}
            aria-label="More options"
          >
            ⋯
          </button>
          {showMenu && (
            <div className={styles.dropdownMenu}>
              <button
                className={styles.menuItem}
                onClick={() => {
                  onEdit();
                  setShowMenu(!showMenu);
                }}
              >
                Edit
              </button>
              <button className={styles.menuItem}>Favourite</button>
              <button className={styles.menuItem}>Remove</button>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

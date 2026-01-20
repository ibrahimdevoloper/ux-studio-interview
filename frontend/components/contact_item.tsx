import styles from "../app/page.module.css";
function ContactItem() {
    return (
        <div className={styles.contactItem}>
            <img src="https://picsum.photos/300/300" className={styles.avatar} />
        <div className={styles.info}>
            <div className={styles.name}>Timothy Lewis</div>
            <div className={styles.phone}>+36 01 234 5678</div>
        </div>
        <button className={styles.itemActions}>
        ⋮
        </button>
      </div>
  );
}
export default ContactItem;
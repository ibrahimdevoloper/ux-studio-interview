import ContactItem from "./contact_item";
import styles from "../app/page.module.css";


 function ContactsList() {
    var contacts = [1,10,10,10,10,10,10];
  return (
    <div className={styles.contactsList}>
      {contacts.map(c => (
        // <ContactItem key={c.id} contact={c} />
        <ContactItem />
      ))}
    </div>
  );
}
export default ContactsList;
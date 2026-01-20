// function HeaderBar({ onAdd }) {
import styles from "../app/page.module.css";
function HeaderBar() {
  return (
  <div className={styles.header}>
  <h1>Contacts</h1>
  <div className={styles.actions}>
    {/* <button className={styles.iconbtn}>⚙</button>
    <button className={styles.iconbtn}>👤</button> */}
    <button className={styles.addbtn}>+ Add new</button>
  </div>
</div>
  );
}

export default HeaderBar;
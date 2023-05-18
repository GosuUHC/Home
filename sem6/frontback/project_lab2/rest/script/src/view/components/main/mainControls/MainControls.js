import Button from "view/components/common/Button";
import styles from "./MainControls.module.css";
import { useUserData, useUserIsAdmin } from "vm/api";
import NotificationBell from "./NotificationBell";

function MainControls(props) {
  const paths = props.paths;
  const { handleLogout } = useUserData();
  const { isAdmin } = useUserIsAdmin();

  return (
    <div className={styles.headerWrapper}>
      <div className={styles.logoutWrapper}>
        <Button
          className={styles.primary}
          name="logout"
          navigatePath={paths.logout}
          onClick={handleLogout}
        ></Button>
      </div>
      <div className={styles.catalogue}>
        <select className={styles.itemSelect} onChange={props.onChangeItemType}>
          <option value="">Catalogue</option>
          <option value="cpu">CPU</option>
          <option value="gpu">GPU</option>
          <option value="ram">RAM</option>
          <option value="motherboard">Motherboard</option>
        </select>
        <div className={styles.catalogueActions}>
          <Button
            className={styles.secondary}
            name="Item cart"
            navigatePath={paths.itemCart}
          ></Button>
          <Button
            className={styles.secondary}
            name="Orders"
            navigatePath={paths.orders}
          ></Button>
          {isAdmin && (
            <Button
              className={styles.secondary}
              name="Admin"
              navigatePath={paths.admin}
            ></Button>
          )}
          <div className={styles.notificationWrapper}>
            <NotificationBell />
          </div>
        </div>
      </div>
    </div>
  );
}

export default MainControls;

import { useState } from "react";
import { updateOrderStatus } from "model/admin/modelAdmin"; 
import styles from "./AdminControlPanel.module.css";

const AdminControlPanel = () => {
  const [orderId, setOrderId] = useState("");
  const [orderStatus, setOrderStatus] = useState("processing");

  const handleUpdateOrderStatus = () => {
    updateOrderStatus(orderId, orderStatus)
      .then((response) => console.log(response))
      .catch((error) => console.error(error));
  };

  return (
    <div className={styles.adminControlPanelContainer}>
      <h3 className={styles.adminH3}>Admin panel</h3>
      <div>
        <label htmlFor="orderId">Order id:</label>
        <input
          id="orderId"
          value={orderId}
          onChange={(e) => setOrderId(e.target.value)}
        />
        <br />

        <label htmlFor="orderStatus">Order status:</label>
        <select
          id="orderStatus"
          value={orderStatus}
          onChange={(e) => setOrderStatus(e.target.value)}
        >
          <option value="processing">processing</option>
          <option value="delivering">delivering</option>
          <option value="completed">completed</option>
        </select>
        <br />
        <button onClick={handleUpdateOrderStatus}>Update order status</button>
      </div>
    </div>
  );
};

export default AdminControlPanel;

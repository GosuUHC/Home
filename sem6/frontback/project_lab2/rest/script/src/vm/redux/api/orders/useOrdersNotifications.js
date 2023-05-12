import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import {
  addOrderNotification,
  removeOrderNotification,
} from "vm/redux/impl/slice";
import { manageNotifications } from "model/main/orders/modelOrders";

function useOrdersNotifications(login) {
  const dispatch = useDispatch();
  const { ordersNotifications } = useSelector(
    ({ notifications }) => notifications
  );

  const { subscribe, unsubscribe } = manageNotifications(login);

  const delNotification = (id) => {
    dispatch(removeOrderNotification(id));
  };

  useEffect(() => {
    const onNotification = (notification) => {
      dispatch(addOrderNotification(notification));
    };
    
    subscribe(onNotification);
    return () => unsubscribe(onNotification);
  }, [dispatch, subscribe, unsubscribe]);

  return { ordersNotifications, delNotification };
}

export { useOrdersNotifications };

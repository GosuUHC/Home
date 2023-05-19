import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import { manageMessages } from "model/common/messagesModel";
import {
  addMessageNotification,
  removeMessageNotification,
} from "vm/redux/impl/slice";

function useMessageNotifications() {
  const dispatch = useDispatch();
  const { messageNotifications } = useSelector(
    ({ notifications }) => notifications
  );
  const { subscribe, unsubscribe } = manageMessages();

  const delNotification = (message) => {
    dispatch(removeMessageNotification(message));
  };

  useEffect(() => {
    const onMessage = (message) => {
      dispatch(addMessageNotification(message));
    };
    
    subscribe(onMessage);
    return () => unsubscribe(onMessage);
  }, [dispatch, subscribe, unsubscribe]);

  return { messageNotifications, delNotification };
}

export { useMessageNotifications };

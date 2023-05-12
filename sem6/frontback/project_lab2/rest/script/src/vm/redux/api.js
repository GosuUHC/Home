import { Provider as ReduxProvider } from "react-redux";
import store from "./impl/store";

import { useOrders } from "./api/orders/useOrders";
import { useCatalogue } from "./api/catalogue/useCatalogue";
import { useItemType } from "./api/catalogue/useItemType";
import { useItemCart } from "./api/itemCart/useItemCart";
import { useAdminOrders } from "./api/admin/useAdminOrders";
import { useOrdersNotifications } from "./api/orders/useOrdersNotifications";
import { useMessageNotifications } from "./api/common/useMessageNotifications";

function buildProvider() {
  return (props) => {
    return <ReduxProvider store={store}>{props.children}</ReduxProvider>;
  };
}

export {
  buildProvider,
  useOrders,
  useItemType,
  useCatalogue,
  useItemCart,
  useAdminOrders,
  useOrdersNotifications,
  useMessageNotifications,
};

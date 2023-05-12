import { configureStore } from "@reduxjs/toolkit";
import {
  itemCartReducer,
  ordersReducer,
  catalogueReducer,
  adminReducer,
  notificationsReducer,
} from "./slice";

// auto thunk and auto combineReducers
const store = configureStore({
  reducer: {
    itemCart: itemCartReducer,
    orders: ordersReducer,
    catalogue: catalogueReducer,
    admin: adminReducer,
    notifications: notificationsReducer,
  },
});

export default store;

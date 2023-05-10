import { configureStore } from "@reduxjs/toolkit";
import {
  itemCartReducer,
  ordersReducer,
  catalogueReducer,
  adminReducer,
} from "./slice";

// auto thunk and auto combineReducers
const store = configureStore({
  reducer: {
    itemCart: itemCartReducer,
    orders: ordersReducer,
    catalogue: catalogueReducer,
    admin: adminReducer,
  },
});

export default store;

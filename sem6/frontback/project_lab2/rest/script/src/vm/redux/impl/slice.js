import { createSlice } from "@reduxjs/toolkit";

// ****************************************************************************************

const initialStateItemCart = {
  itemsList: [],
  loaded: false,
  checks: [],
  counts: [],
};

const itemCartSlice = createSlice({
  name: "itemCart",
  initialState: initialStateItemCart,
  reducers: {
    addItems: (state, action) => {
      state.itemsList = action.payload;
    },
    updateItemsLoaded: (state, action) => {
      state.loaded = action.payload;
    },
    setItemsCounts: (state, action) => {
      state.counts = action.payload;
    },
    updateItemsCount: (state, action) => {
      const { index, value } = action.payload;
      state.counts[index] = value;
    },
  },
});

export const {
  addItems,
  updateItemsLoaded,
  setItemsChecks,
  setItemsCounts,
  updateItemsCheck,
  updateItemsCount,
} = itemCartSlice.actions;
export const itemCartReducer = itemCartSlice.reducer;

// ****************************************************************************************

const ordersInitialState = {
  tableData: [],
  loaded: false,
  checks: [],
  selectAllValue: true,
};

const ordersSlice = createSlice({
  name: "orders",
  initialState: ordersInitialState,
  reducers: {
    setOrdersTableData: (state, action) => {
      state.tableData = action.payload;
    },
    updateOrdersLoaded: (state, action) => {
      state.loaded = action.payload;
    },
    setOrdersChecks: (state, action) => {
      state.checks = action.payload;
    },
    updateOrdersCheck: (state, action) => {
      const { index, value } = action.payload;
      state.checks[index] = value;
    },
    reverseChecks: (state, action) => {
      state.checks.fill(state.selectAllValue);
      state.selectAllValue = !state.selectAllValue;
    },
    resetSelectAllValue: (state, action) => {
      state.selectAllValue = ordersInitialState.selectAllValue;
    },
  },
});

export const {
  setOrdersTableData,
  updateOrdersLoaded,
  setOrdersChecks,
  updateOrdersCheck,
  reverseChecks,
  resetSelectAllValue,
} = ordersSlice.actions;
export const ordersReducer = ordersSlice.reducer;

// ****************************************************************************************

const catalogueInitialState = {
  itemType: "",
  tableData: [],
  loaded: false,
  addedToCartIndices: [],
};

const catalogueSlice = createSlice({
  name: "catalogue",
  initialState: catalogueInitialState,
  reducers: {
    setItemType: (state, action) => {
      state.itemType = action.payload;
    },
    setCatalogueTableData: (state, action) => {
      state.tableData = action.payload;
    },
    updateCatalogueLoaded: (state, action) => {
      state.loaded = action.payload;
    },
    addAddedToCartIndices: (state, action) => {
      state.addedToCartIndices = [...state.addedToCartIndices, action.payload];
    },
    resetAddedToCartIndices: (state, action) => {
      state.addedToCartIndices = catalogueInitialState.addedToCartIndices;
    },
  },
});

export const {
  setItemType,
  setCatalogueTableData,
  updateCatalogueLoaded,
  addAddedToCartIndices,
  resetAddedToCartIndices,
} = catalogueSlice.actions;
export const catalogueReducer = catalogueSlice.reducer;

// ****************************************************************************************

const initialStateAdmin = {
  tableData: [],
  loaded: false,
  pageNumber: 1,
  ordersIdAndUpdatedStatus: [],
  totalCount: 0,
};

const adminSlice = createSlice({
  name: "admin",
  initialState: initialStateAdmin,
  reducers: {
    setAdminTableData: (state, action) => {
      state.tableData = action.payload;
    },
    updateAdminLoaded: (state, action) => {
      state.loaded = action.payload;
    },
    setAdminPageNumber: (state, action) => {
      state.pageNumber = action.payload;
    },
    manageOrders: (state, action) => {
      const { orderid, newStatus } = action.payload;
      const orderIndex = state.ordersIdAndUpdatedStatus.findIndex(
        (order) => order.id === orderid
      );

      if (orderIndex !== -1) {
        // if the orderid exists, update its status
        state.ordersIdAndUpdatedStatus[orderIndex].newStatus = newStatus;
      } else {
        // if the orderid doesn't exist, add it to the ordersIdAndUpdatedStatus array
        state.ordersIdAndUpdatedStatus.push({
          id: orderid,
          newStatus,
        });
      }
    },
    resetChangedOrders: (state) => {
      state.ordersIdAndUpdatedStatus =
        initialStateAdmin.ordersIdAndUpdatedStatus;
    },
    setTotalCount: (state, action) => {
      state.totalCount = action.payload;
    },
  },
});

export const {
  setAdminTableData,
  updateAdminLoaded,
  setAdminPageNumber,
  manageOrders,
  resetChangedOrders,
  setTotalCount,
} = adminSlice.actions;

export const adminReducer = adminSlice.reducer;

// ****************************************************************************************

const initialStateNotifications = {
  ordersNotifications: [],
  messageNotifications: [],
};

const notificationsSlice = createSlice({
  name: "notifications",
  initialState: initialStateNotifications,
  reducers: {
    addOrderNotification: (state, action) => {
      state.ordersNotifications.push(action.payload);
    },
    removeOrderNotification: (state, action) => {
      state.ordersNotifications = state.ordersNotifications.filter(
        (notification) => notification.id !== action.payload
      );
    },
    addMessageNotification: (state, action) => {
      if (!state.messageNotifications.includes(action.payload)) {
        state.messageNotifications.push(action.payload);
      }
    },
    removeMessageNotification: (state, action) => {
      state.messageNotifications = state.messageNotifications.filter(
        (notification) => notification !== action.payload
      );
    },
    resetNotifications: (state) => {
      state.ordersNotifications = initialStateNotifications.ordersNotifications;
      state.messageNotifications =
        initialStateNotifications.messageNotifications;
    },
  },
});
export const {
  addOrderNotification,
  removeOrderNotification,
  addMessageNotification,
  removeMessageNotification,
  resetNotifications,
} = notificationsSlice.actions;
export const notificationsReducer = notificationsSlice.reducer;

// ****************************************************************************************

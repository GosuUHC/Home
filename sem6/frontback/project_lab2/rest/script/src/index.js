import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import PageAuth from "./view/pages/auth/PageAuth";
import PageMain from "./view/pages/main/PageMain";
import PageItemCart from "view/pages/main/itemCart/PageItemCart";
import PageOrders from "view/pages/main/orders/PageOrders";
import PageRegistration from "view/pages/reg/PageRegistration";
import PageItemCatalogue from "view/pages/main/itemCatalogue/PageItemCatalogue";

import { buildMobxProvider, buildReduxProvider } from "vm/api";
import PageAdmin from "view/pages/admin/PageAdmin";

const root = ReactDOM.createRoot(document.getElementById("root"));

const pgAuth = <PageAuth navPathMain="/main" />;
const pgReg = <PageRegistration />;
const pgOrd = <PageOrders />;
const pgItemCart = <PageItemCart />;
const pgCatalogue = <PageItemCatalogue />;
const pgAdmin = <PageAdmin />;

const paths = {
  itemCart: "item-cart",
  orders: "orders",
  catalogue: "catalogue",
  logout: "/auth",
  admin: "admin",
};

const comps = {
  pageItemCart: pgItemCart,
  pageOrders: pgOrd,
  pageCatalogue: pgCatalogue,
  pageAdmin: pgAdmin,
};

const pgMain = <PageMain paths={paths} comps={comps} />;

const ReduxProvider = buildReduxProvider();
const MobxProvider = buildMobxProvider();

const router = (
  <ReduxProvider>
    <MobxProvider>
      <Router>
        <div>
          <Routes>
            <Route path="/auth" element={pgAuth} />
            <Route path="/" element={pgAuth} />
            <Route path="/reg" element={pgReg} />
            <Route path="/main/*" element={pgMain} />
          </Routes>
        </div>
      </Router>
    </MobxProvider>
  </ReduxProvider>
);

root.render(router);

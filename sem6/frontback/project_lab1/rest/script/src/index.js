import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import CompPageAuth from "./view/pages/auth/component";
import CompPageMain from "./view/pages/main/component";
import CompPageItemCart from "./view/pages/main/itemCart/component";
import CompPageOrders from "./view/pages/main/orders/component";
import CompPageRegistration from "./view/pages/reg/component";

const root = ReactDOM.createRoot(document.getElementById("root"));
const pgAuth = <CompPageAuth navPathMain="/main" />;
const pgReg = <CompPageRegistration />;
const pgOrd = <CompPageOrders />;
const pgItemCart = <CompPageItemCart />;
const paths = {
  itemCart: "item-cart",
  orders: "orders",
  catalogue: "catalogue",
  logout: "/auth",
};
const comps = {
  pageItemCart: pgItemCart,
  pageOrders: pgOrd,
};
const pgMain = <CompPageMain paths={paths} comps={comps} />;
const router = (
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
);
root.render(router);

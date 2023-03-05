import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import CompPageAuth from "./view/pages/auth/component";
import CompPageRegistration from "./view/pages/reg/component";
/* 
  components import
*/

const root = ReactDOM.createRoot(document.getElementById("root"));
const pgAuth = <CompPageAuth></CompPageAuth>;
const pgReg = <CompPageRegistration></CompPageRegistration>;
const router = (
  <Router>
    <div>
      <Routes>
        <Route path="/auth" element={pgAuth} />
        <Route path="/" element={pgAuth} />
        <Route path="/reg" element={pgReg} />
      </Routes>
    </div>
  </Router>
);
root.render(router);

/*

  let pagesRouterImport = await import("./view/router/pagesRouter.js");

  let pagesRouter = pagesRouterImport.PagesRouterFactory.createInstance();

  ///////////////  url     view
  pagesRouter.add("auth", "auth");
  pagesRouter.add("main", "main");
  pagesRouter.add("registration", "registration");

  if (localStorage.getItem("token")) {
    pagesRouter.default("main");
  } else {
    pagesRouter.default("auth");
  }

  pagesRouter.go();
  */

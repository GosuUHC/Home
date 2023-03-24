import React, { useState } from "react";
import { Routes, Route, useNavigate } from "react-router-dom";

import { proceedLogout } from "../../../model/auth/modelAuth.js";
import CompMainControls from "../../components/main/comp-main-controls/component.js";
import CompPageItemCatalogue from "./itemCatalogue/component.js";

function CompPageMain(props) {
  const [itemType, setItemType] = useState("");
  const navigate = useNavigate();
  let paths = props.paths;
  let comps = props.comps;

  return (
    <div>
      <CompMainControls
        paths={paths}
        onClickLogout={() => proceedLogout()}
        onChangeItemType={(e) => {
          setItemType(e.target.value);
          navigate(paths.catalogue);
        }}
      ></CompMainControls>

      <Routes>
        <Route path={paths.itemCart} element={comps.pageItemCart}></Route>
        <Route path={paths.orders} element={comps.pageOrders}></Route>
        <Route
          path={paths.catalogue}
          element={<CompPageItemCatalogue itemType={itemType} key={itemType} />}
        ></Route>
      </Routes>
    </div>
  );
}

export default CompPageMain;

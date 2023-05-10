import { Routes, Route, useNavigate } from "react-router-dom";

import MainControls from "view/components/main/mainControls/MainControls.js";
import { useItemType } from "vm/api.js";

import styles from "./PageMain.module.css";

function PageMain(props) {
  const { handleItemTypeChange } = useItemType();
  const navigate = useNavigate();
  const paths = props.paths;
  const comps = props.comps;

  

  return (
    <div className={styles.wrapper}>
      {comps.adminComp}
      <MainControls
        paths={paths}
        onChangeItemType={(e) => {
          handleItemTypeChange(e.target.value);
          navigate(paths.catalogue);
        }}
      ></MainControls>

      <div>
        <Routes>
          <Route path={paths.itemCart} element={comps.pageItemCart}></Route>
          <Route path={paths.orders} element={comps.pageOrders}></Route>
          <Route path={paths.catalogue} element={comps.pageCatalogue}></Route>
          <Route path={paths.admin} element={comps.pageAdmin}></Route>
        </Routes>
      </div>
    </div>
  );
}

export default PageMain;

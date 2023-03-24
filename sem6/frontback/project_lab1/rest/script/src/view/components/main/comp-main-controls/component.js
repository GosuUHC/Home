import Button from "../../common/button";

function CompMainControls(props) {
  let paths = props.paths;

  return (
    <div>
      <div className="logoutWraper">
        <Button
          name="logout"
          navigatePath={paths.logout}
          onClick={props.onClickLogout}
        ></Button>
      </div>
      <div className="catalogue">
        <select className="itemSelect" onChange={props.onChangeItemType}>
          <option value="">Catalogue</option>
          <option value="cpu">CPU</option>
          <option value="gpu">GPU</option>
          <option value="ram">RAM</option>
          <option value="motherboard">Motherboard</option>
        </select>
        <div className="catalogueActions">
          <Button name="Item cart" navigatePath={paths.itemCart}></Button>
          <Button name="Orders" navigatePath={paths.orders}></Button>
        </div>
      </div>
    </div>
  );
}

export default CompMainControls;

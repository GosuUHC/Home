import React from "react";
import Button from "view/components/common/Button.js";
import TextField from "view/components/common/textField";
import styles from "./Auth.module.css";

function Auth(props) {
  return (
    <div className={styles.form}>
      <TextField
        type="text"
        placeholder="Login"
        onChange={(e) => props.onChangeLogin(e.target.value)}
      ></TextField>
      <TextField
        type="password"
        placeholder="Password"
        onChange={(e) => props.onChangePassword(e.target.value)}
      ></TextField>
      <Button onClick={props.proceedAuth} name="Enter"></Button>
    </div>
  );
}

export default Auth;

import React from "react";
import Button from "view/components/common/Button.js";
import TextField from "view/components/common/TextField";

import styles from "./RegistrationForm.module.css";

function RegistrationForm(props) {
  return (
    <div className={styles.form}>
      <TextField
        type="text"
        placeholder="Login"
        onChange={(event) => props.onChangeLogin(event.target.value)}
      ></TextField>
      <TextField
        type="text"
        placeholder="Password"
        onChange={(event) => props.onChangePassword(event.target.value)}
      ></TextField>
      <TextField
        type="text"
        placeholder="Password check"
        onChange={(event) => props.onChangePasswordCheck(event.target.value)}
      ></TextField>
      <Button
        name="Register"
        onClick={props.proceedRegistration}
        navigatePath="/auth"
      ></Button>
    </div>
  );
}

export default RegistrationForm;

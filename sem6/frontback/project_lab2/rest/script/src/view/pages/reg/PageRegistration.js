import { useState } from "react";
import { Link } from "react-router-dom";
import {
  proceedRegistration,
  checkData,
} from "model/registration/modelRegistration";
import RegistrationForm from "view/components/registration/comp-reg/RegistrationForm";

import styles from "./PageRegistration.module.css";

function PageRegistration(Props) {
  const [login, setLogin] = useState("");
  const [password, setPassword] = useState("");
  const [passwordCheck, setPasswordCheck] = useState("");

  const proceedReg = () => {
    if (checkData(password, passwordCheck)) {
      proceedRegistration(login, password);
    }
  };

  const onChangeLogin = (login) => {
    setLogin(login);
  };

  const onChangePassword = (password) => {
    setPassword(password);
  };

  const onChangePasswordCheck = (passwordCheck) => {
    setPasswordCheck(passwordCheck);
  };

  return (
    <div className={styles.regContainer}>
      <h1 className={styles.regTitle}>Register</h1>
      <RegistrationForm
        onChangeLogin={onChangeLogin}
        onChangePassword={onChangePassword}
        onChangePasswordCheck={onChangePasswordCheck}
        proceedRegistration={proceedReg}
      />
      <div>
        Already have an account?{" "}
        <Link className={styles.loginLink} to="/auth">
          Login here
        </Link>
      </div>
    </div>
  );
}

export default PageRegistration;

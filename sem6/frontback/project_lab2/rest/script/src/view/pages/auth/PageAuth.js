import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";

import Auth from "view/components/auth/comp-auth/Auth";

import styles from "./PageAuth.module.css";
import { useUserData } from "vm/api";

function PageAuth(props) {
  const [login, setLogin] = useState("");
  const [password, setPassword] = useState("");
  const { userData, handleLogin } = useUserData();
  const navigate = useNavigate();

  const onChangeLogin = (login) => {
    setLogin(login);
  };

  const onChangePassword = (password) => {
    setPassword(password);
  };

  useEffect(() => {
    if (userData.authorized === true) {
      navigate(props.navPathMain);
    }
  }, [userData.authorized, navigate, props.navPathMain]);

  return (
    <div className={styles.authContainer}>
      <h1 className={styles.authTitle}>Login</h1>
      <Auth
        onChangeLogin={onChangeLogin}
        onChangePassword={onChangePassword}
        proceedAuth={() => handleLogin(login, password)}
      />
      <Link className={styles.createAccountLink} to="/reg">
        Create an account
      </Link>
    </div>
  );
}

export default PageAuth;

import React from "react";
import { Navigate } from "react-router-dom";
import { proceedAuth } from "../../../model/auth/modelAuth";

import CompAuth from "../../components/auth/comp-auth/compAuth.js";
import Button from "../../components/common/button";

class CompPageAuth extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      login: "",
      password: "",
      authorized: false,
    };
  }

  onChangeLogin = (login) => {
    this.setState({ login: login });
  };

  onChangePassword = (password) => {
    this.setState({ password: password });
  };

  proceedLogin = () => {
    proceedAuth(this.state.login, this.state.password)
      .then(() => {
        this.setState({ authorized: true });
      })
      .catch(() => {
        this.setState({ authorized: "err" });
      });
  };

  render() {
    if (this.state.authorized === true) {
      return <Navigate to={this.props.navPathMain} replace={true}></Navigate>;
    }
    return (
      <div>
        <h1>Login</h1>
        <CompAuth
          onChangeLogin={this.onChangeLogin}
          onChangePassword={this.onChangePassword}
          proceedAuth={this.proceedLogin}
        ></CompAuth>
        <br></br>
        <Button name="Registration" navigatePath="/reg"></Button>
      </div>
    );
  }
}

export default CompPageAuth;

import React from "react";
import { proceedAuth } from "../../../model/auth/modelAuth";

import CompAuth from "../../components/auth/comp-auth/compAuth.js";
import Button from "../../components/common/button";

class CompPageAuth extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      login: "",
      password: "",
    };
  }

  onChangeLogin = (login) => {
    this.setState({ login: login });
  };

  onChangePassword = (password) => {
    this.setState({ password: password });
  };

  render() {
    return (
      <div>
        <CompAuth
          onChangeLogin={this.onChangeLogin}
          onChangePassword={this.onChangePassword}
          proceedAuth={() => proceedAuth(this.state.login, this.state.password)}
        ></CompAuth>
        <br></br>
        <Button name="Registration" navigatePath="/reg"></Button>
      </div>
    );
  }
}

export default CompPageAuth;

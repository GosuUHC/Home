import React from "react";
import {
  proceedRegistration,
  checkData,
} from "../../../model/registration/modelRegistration";
import CompRegistration from "../../components/registration/comp-reg/compReg";

class CompPageRegistration extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      login: "",
      password: "",
      passwordCheck: "",
    };
  }

  onChangeLogin = (login) => {
    this.setState({ login: login });
  };

  onChangePassword = (password) => {
    this.setState({ password: password });
  };

  onChangePasswordCheck = (passwordCheck) => {
    this.setState({ passwordCheck: passwordCheck });
  };

  proceedReg = () => {
    if (checkData(this.state.password, this.state.passwordCheck)) {
      proceedRegistration(this.state.login, this.state.password);
    }
  };

  render() {
    return (
      <CompRegistration
        onChangeLogin={this.onChangeLogin}
        onChangePassword={this.onChangePassword}
        onChangePasswordCheck={this.onChangePasswordCheck}
        proceedRegistration={this.proceedReg}
      ></CompRegistration>
    );
  }
}

export default CompPageRegistration;

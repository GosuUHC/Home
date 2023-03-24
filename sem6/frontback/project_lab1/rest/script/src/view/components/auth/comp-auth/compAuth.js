import React from "react";
import Button from "../../common/button.js";
import TextField from "../../common/textField.js";

class CompAuth extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div>
        <TextField
          type="text"
          placeholder="Login"
          onChange={(e) => this.props.onChangeLogin(e.target.value)}
        ></TextField>
        <TextField
          type="password"
          placeholder="Password"
          onChange={(e) => this.props.onChangePassword(e.target.value)}
        ></TextField>
        <Button onClick={this.props.proceedAuth} name="Login"></Button>
      </div>
    );
  }
}

export default CompAuth;

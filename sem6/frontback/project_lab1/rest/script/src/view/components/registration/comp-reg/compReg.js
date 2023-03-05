import React from "react";
import Button from "../../common/button.js";
import TextField from "../../common/textField.js";

class CompRegistration extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div>
        <Button name="Go back" navigatePath="/auth"></Button>
        <br></br>
        <TextField
          type="text"
          placeholder="Login"
          onChange={(event) => this.props.onChangeLogin(event.target.value)}
        ></TextField>
        <TextField
          type="text"
          placeholder="Password"
          onChange={(event) => this.props.onChangePassword(event.target.value)}
        ></TextField>
        <TextField
          type="text"
          placeholder="Password check"
          onChange={(event) => this.props.onChangePasswordCheck(event.target.value)}
        ></TextField>
        <Button
          name="Register"
          onClick={this.props.proceedRegistration}
          navigatePath="/auth"
        ></Button>
      </div>
    );
  }
}

export default CompRegistration;

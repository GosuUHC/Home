import React from "react";

class TextField extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <input
        type={this.props.type}
        placeholder={this.props.placeholder}
        onChange={this.props.onChange}
      ></input>
    );
  }
}

export default TextField;

import React from "react";

class CompItemCatalogue extends React.Component {
  render() {
    if (!this.props.tableData) {
      return <div></div>;
    }
    let data = this.props.tableData;

    const catalogueHeaders = (
      <thead>
        <tr>
          {Object.keys(data[0]).map((cellData, i) => {
            return <td key={i}>{cellData}</td>;
          })}
        </tr>
      </thead>
    );

    const catalogueRender = data.map((elem, i) => {
      let values = Object.values(elem);
      return (
        <tr
          key={i}
          onClick={this.props.handleClick}
          className={data[i].className}
        >
          {values.map((val, i) => {
            return <td key={i}>{val}</td>;
          })}
        </tr>
      );
    });

    return (
      <div className="catalogueTableData">
        <table>
          {catalogueHeaders}
          <tbody>{catalogueRender}</tbody>
        </table>
      </div>
    );
  }
}

export default CompItemCatalogue;

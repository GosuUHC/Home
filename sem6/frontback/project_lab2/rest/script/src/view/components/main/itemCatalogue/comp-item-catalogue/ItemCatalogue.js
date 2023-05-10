import React from "react";

function ItemCatalogue(props) {
  const { tableData, handleClick } = props;

  if (!tableData) {
    return null;
  }

  const renderCatalogue = tableData.map((elem, i) => {
    
  })

  // const catalogueHeaders = (
  //   <thead>
  //     <tr>
  //       {Object.keys(tableData[0]).map((cellData, i) => (
  //         <td key={i}>{cellData}</td>
  //       ))}
  //     </tr>
  //   </thead>
  // );

  // const catalogueRender = tableData.map((elem, i) => {
  //   const values = Object.values(elem);
  //   return (
  //     <tr key={i} onClick={handleClick}>
  //       {values.map((val, j) => (
  //         <td key={j}>{val}</td>
  //       ))}
  //     </tr>
  //   );
  // });

  // return (
  //   <div className="catalogueTableData">
  //     <table>
  //       {catalogueHeaders}
  //       <tbody>{catalogueRender}</tbody>
  //     </table>
  //   </div>
  // );
}

export default ItemCatalogue;

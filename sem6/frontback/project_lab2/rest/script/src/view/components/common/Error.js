import React from "react";
import styles from "./Error.module.css";

const Error = ({ errorMessage }) => {
  return (
    <div className={styles.errorContainer}>
      <span>{errorMessage}</span>
    </div>
  );
};

export default Error;
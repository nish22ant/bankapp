import { useState, useContext } from "react";
import { ThemeContext } from "../ThemeContext";
import DepositForm from "./DepositForm";

export default function Deposit() {
  const theme = useContext(ThemeContext);
  const darkMode = theme.state.darkMode;
  return (
    <div id="transfer" className={darkMode ? "bg-dark" : "bg-light"}>
      <h1
        className={`title text-center mx-auto ${
          darkMode ? "text-light" : "text-dark"
        }`}
      >
        <i style={{ fontSize: "2rem", fontWeight: "700" }}>D</i>epositFunds._
      </h1>
      <div className="container">
        <DepositForm darkMode={darkMode} />
      </div>
    </div>
  );
}

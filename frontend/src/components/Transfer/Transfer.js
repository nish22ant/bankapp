import "./transfer.css";
import { useContext } from "react";
import { ThemeContext } from "../ThemeContext";
import TransferForm from "./TransferForm";

const Transfer = () => {
  const theme = useContext(ThemeContext);
  const darkMode = theme.state.darkMode;
  return (
    <div id="transfer" className={darkMode ? "bg-dark" : "bg-light"}>
      <h1
        className={`title text-center mx-auto ${
          darkMode ? "text-light" : "text-dark"
        }`}
      >
        <i style={{ fontSize: "2rem", fontWeight: "700" }}>T</i>ransferFunds
      </h1>
      <div className="container">
        <TransferForm darkMode={darkMode} />
      </div>
    </div>
  );
};

export default Transfer;

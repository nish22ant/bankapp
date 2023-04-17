import "./PayBills.css";
import PayBillsForm from "./PayBillsForm";
import { ThemeContext } from "../ThemeContext";
import { useContext } from "react";

const PayBills = () => {
  const theme = useContext(ThemeContext);
  const darkMode = theme.state.darkMode;
  return (
    <div id="transfer" className={darkMode ? "bg-dark" : "bg-light"}>
      <h1
        className={`title text-center mx-auto ${
          darkMode ? "text-light" : "text-dark"
        }`}
      >
        <i style={{ fontSize: "2rem", fontWeight: "700" }}>P</i>ayBills
      </h1>
      <div className="container">
        <PayBillsForm darkMode={darkMode} />
      </div>
    </div>
  );
};

export default PayBills;

import "./transfer.css";
import { useState, useContext } from "react";
import { ThemeContext } from "../ThemeContext";
import TransferForm from "./TransferForm";
import Message from "../Utils/Message";

const Transfer = () => {
  const [message, SetMessage] = useState("");
  const theme = useContext(ThemeContext);
  const darkMode = theme.state.darkMode;

  const updateMessage = (newStatus) => {
    SetMessage(newStatus);
  };
  return (
    <div id="transfer" className={darkMode ? "bg-dark" : "bg-light"}>
      <div>
        <h1
          className={`title text-center mx-auto ${
            darkMode ? "text-light" : "text-dark"
          }`}
        >
          <i style={{ fontSize: "2rem", fontWeight: "700" }}>T</i>ransferFunds
        </h1>
        <div className="container">
          <TransferForm darkMode={darkMode} updateMessage={updateMessage} />
        </div>
      </div>
      {console.log(message)}
      <div className="status">
        {message ? <Message message={message} darkMode={darkMode} /> : ""}
      </div>
    </div>
  );
};

export default Transfer;

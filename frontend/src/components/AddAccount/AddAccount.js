import "./AddAccount.css";
import { useContext, useState } from "react";
import { ThemeContext } from "../ThemeContext";
import AddAccountForm from "./AddAccountForm";
import Message from "../Utils/Message";


const AddAccount = () => {
  const [message, SetMessage] = useState("");
  const theme = useContext(ThemeContext);
  const darkMode = theme.state.darkMode;
  const updateMessage = (newStatus) => {
    SetMessage(newStatus);
  };
  return (
    <div id="transfer" className={darkMode ? "bg-dark" : "bg-light"}>
      <h1
        className={`title text-center mx-auto ${
          darkMode ? "text-light" : "text-dark"
        }`}
      >
        <i style={{ fontSize: "2rem", fontWeight: "700" }}>A</i>ddAccount._
      </h1>
      <div className="container">
        <AddAccountForm darkMode={darkMode} updateMessage={updateMessage} />
      </div>
      {console.log(message)}
      <div className="status">
        {message ? <Message message={message} darkMode={darkMode} /> : ""}
      </div>
    </div>
  );
};

export default AddAccount;

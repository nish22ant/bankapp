import "./Contact.css";
import Message from "../Utils/Message";
import ContactForm from "./ContactFrom";
import { useContext, useState } from "react";
import { ThemeContext } from "../ThemeContext";

const Contact = () => {
    const [message, SetMessage] = useState("")
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
          <i style={{ fontSize: "2rem", fontWeight: "700" }}>C</i>ontact._
        </h1>
        <div className="container">
          <ContactForm darkMode={darkMode} updateMessage={updateMessage} />
        </div>
      </div>
      {console.log(message)}
      <div className="status">
        {message ? <Message message={message} darkMode={darkMode} /> : ""}
      </div>
    </div>
  );
};

export default Contact;

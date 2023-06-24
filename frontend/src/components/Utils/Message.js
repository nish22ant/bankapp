import { Fragment } from "react";
import "./Message.css";
const Message = (props) => {
  const darkMode = props.darkMode;
  const messages = props.message;

  let messageContent;

  if (Array.isArray(messages)) {
    messageContent = messages.map((message) => (
      <li key={message.id}>{message}<br /></li>
    ));
  } else {
    messageContent = messages;
  }

  return (
    <fieldset className={!darkMode ? "text-dark" : "text-white"}>
      <legend>Status</legend>
      <ul>{messageContent}</ul>
    </fieldset>
  );
};

export default Message;

import "./Message.css";
const Message = (props) => {
    const darkMode = props.darkMode;
    const message = props.message;

    

  return (
    
    <fieldset className={!darkMode ? "text-dark" : "text-white"}>
      <legend >Status</legend>
      <p>{message}</p>
    </fieldset>
  );
};

export default Message;

const Message = (props) => {
    const darkMode = props.darkMode;
    

  return (
    
    <fieldset className={darkMode ? "border-light" : "border-dark"}>
      <legend >Status</legend>
      <p>This is my content inside the border.</p>
    </fieldset>
  );
};

export default Message;

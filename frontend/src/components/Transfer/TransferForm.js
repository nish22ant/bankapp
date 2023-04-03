import { useState } from "react";
import axios from "axios";

const TransferForm = (props) => {
  const darkMode = props.darkMode;
  const [response, setResponse] = useState("");
  const [transferData, setTransferData] = useState({
    fromAccountNumber: "",
    toAccountNumber: "",
    amount: "",
    accountPassword: "",
    passwordAgain: "",
  });

  const handleInputChange = (event) => {
    setTransferData({
      ...transferData,
      [event.target.name]: event.target.value,
    });
  };
  const handleTransferSubmit = async (event) => {
    event.preventDefault();
    const formData = new FormData(event.target);
    formData.append("fromAccountNumber", event.target.fromAccountNumber.value);
    formData.append("toAccountNumber", event.target.toAccountNumber.value);
    formData.append("amount", event.target.amount.value);
    formData.append("accountPassword", event.target.accountPassword.value);
    formData.append("passwordAgain", event.target.passwordAgain.value);

    await axios.post("http://localhost:65535/bankapp_servlet/api/transfer", formData)
      .then((res) => {
        setResponse(res.data)
        console.log(res)
        props.updateMessage(res.data)
      })
      .catch((err) => {
        console.log(err);
      })
  };

  return (
    <form
      className={`p-3 form-border ${
        !darkMode ? "border-dark" : "border-secondary"
      }`}
      onSubmit={handleTransferSubmit} enctype="multipart/form-data"
    >
      <div className="form-group">
        <input
          type="number"
          id="fromAccountNumber"
          name="fromAccountNumber"
          placeholder="From Account Number"
          className={`form-control ${
            darkMode ? "bg-dark text-light" : "bg-light"
          }`}
          value={transferData.fromAccountNumber}
          onChange={handleInputChange}
        />
      </div>
      <div className="form-group">
        <input
          type="number"
          id="toAccountNumber"
          name="toAccountNumber"
          placeholder="To Account Number"
          className={`form-control ${
            darkMode ? "bg-dark text-light" : "bg-light"
          }`}
          value={transferData.toAccountNumber}
          onChange={handleInputChange}
        />
      </div>
      <div className="form-group">
        <input
          type="number"
          id="amount"
          name="amount"
          placeholder="Amount"
          className={`form-control ${
            darkMode ? "bg-dark text-light" : "bg-light"
          }`}
          value={transferData.amount}
          onChange={handleInputChange}
        />
      </div>
      <div className="form-group">
        <input
          type="text"
          id="accountPassword"
          name="accountPassword"
          placeholder="accountPassword"
          className={`form-control ${
            darkMode ? "bg-dark text-light" : "bg-light"
          }`}
          value={transferData.accountPassword}
          onChange={handleInputChange}
        />
      </div>
      <div className="form-group">
        <input
          type="text"
          id="passwordAgain"
          name="passwordAgain"
          placeholder="Re-enter Password"
          className={`form-control ${
            darkMode ? "bg-dark text-light" : "bg-light"
          }`}
          value={transferData.passwordAgain}
          onChange={handleInputChange}
        />
        <button
          type="submit"
          className={`btn ${
            !darkMode
              ? "btn-dark text-light btn-outline-light mx-auto"
              : "btn-light text-dark mx-auto"
          }`}
        >
          Submit
        </button>
      </div> 
    </form>
  );
};

export default TransferForm;

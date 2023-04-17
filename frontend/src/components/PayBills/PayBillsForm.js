import { useState } from "react";
import axios from "axios";

const PayBillsForm = (props) => {
  const darkMode = props.darkMode;
  const [response, setResponse] = useState("");
  const [payData, setPayData] = useState({
    payee: "",
    fromAccountNumber: "",
    accountPassword: "",
    amount: "",
    date: new Date().toISOString().substring(0,10)
  });

  const handleInputChange = (event) => {
    setPayData({
      ...payData,
      [event.target.name]: event.target.value,
    });
  };

  const handlePaySubmit = async (event) => {
    event.preventDefault();
    const formData = new FormData(event.target);
    formData.append("payee", event.target.payee.value);
    formData.append("fromAccountNumber", event.target.fromAccountNumber.value);
    formData.append("date", event.target.date.value);
    formData.append("accountPassword", event.target.accountPassword.value);
    formData.append("amount", event.target.amount.value);
    axios
      .post("http://localhost:65535/bankapp_servlet/api/pay", formData)
      .then((res) => {
        setResponse(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  };
  return (
    <form
      className={`p-3 form-border ${
        !darkMode ? "border-dark" : "border-secondary"
      }`}
      onSubmit={handlePaySubmit}
    >
      <div className="form-group">
        <input
          type="number"
          id="payee"
          name="payee"
          placeholder="Payee"
          className={`form-control ${
            darkMode ? "bg-dark text-light" : "bg-light"
          }`}
          value={payData.payee}
          onChange={handleInputChange}
        />
      </div>
      <div className="form-group">
        <input
          type="text"
          id="fromAccountNumber"
          name="fromAccountNumber"
          placeholder="From Account Number"
          className={`form-control ${
            darkMode ? "bg-dark text-light" : "bg-light"
          }`}
          value={payData.fromAccountNumber}
          onChange={handleInputChange}
        />
      </div>
      <div className="form-group">
        <input
          type="date"
          id="date"
          name="date"
          placeholder="Date"
          className={`form-control ${
            darkMode ? "bg-dark text-light" : "bg-light"
          }`}
          value={payData.date}
          onChange={handleInputChange}
        />
      </div>
      <div className="form-group">
        <input
          type="text"
          id="accountPassword"
          name="accountPassword"
          placeholder="Account Password"
          className={`form-control ${
            darkMode ? "bg-dark text-light" : "bg-light"
          }`}
          value={payData.accountPassword}
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
          value={payData.amount}
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
      <div>{response}</div>
    </form>
  );
};

export default PayBillsForm;

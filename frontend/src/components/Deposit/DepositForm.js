import { useState } from "react";
import axios from "axios";
const DepositForm = (props) => {
  const darkMode = props.darkMode;
  const [response, setResponse] = useState("");
  const [accountNumber, setAccountNumber] = useState("");
  const [amount, setAmount] = useState("");
  const [image, setImage] = useState(null);
  const [depositData, setDepositData] = useState({
    accountNumber: "",
    chequeNumber: "",
    accountPassword: "",
    amount: "",
  });

  const handleImageChange = (e) => {
    setImage(e.target.files[0]);
  };

  const handleInputChange = (event) => {
    setDepositData({
      ...depositData,
      [event.target.name]: event.target.value,
    });
  };


  const handleDepositSubmit = async (event) => {
    event.preventDefault();
    const formData = new FormData(event.target);
    console.log("From React:",image);
    formData.append("accountNumber", event.target.accountNumber.value);
    formData.append("chequeNumber", event.target.chequeNumber.value);
    formData.append("selectedFile", image);
    formData.append("accountPassword", event.target.accountPassword.value);
    formData.append("amount", event.target.amount.value);
    axios
      .post("http://localhost:65535/bankapp_servlet/api/deposit", formData)
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
      onSubmit={handleDepositSubmit}
    >
      <div className="form-group">
        <input
          type="number"
          id="accountNumber"
          name="accountNumber"
          placeholder="Account Number"
          className={`form-control ${
            darkMode ? "bg-dark text-light" : "bg-light"
          }`}
          value={depositData.accountNumber}
          onChange={handleInputChange}
        />
      </div>
      <div className="form-group">
        <input
          type="text"
          id="chequeNumber"
          name="chequeNumber"
          placeholder="Cheque Number"
          className={`form-control ${
            darkMode ? "bg-dark text-light" : "bg-light"
          }`}
          value={depositData.chequeNumber}
          onChange={handleInputChange}
        />
      </div>
      <div className="form-group">
        <input
          type="file"
          id="selectedFile"
          name="selectedFile"
          placeholder="Upload Cheque"
          className={`form-control ${
            darkMode ? "bg-dark text-light" : "bg-light"
          }`}
          value={depositData.selectedFile}
          onChange={handleImageChange}
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
          value={depositData.accountPassword}
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
          value={depositData.amount}
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

export default DepositForm;

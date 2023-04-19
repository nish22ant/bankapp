import { useState } from "react";
import axios from "axios";

const AddAccountForm = (props) => {
  const darkMode = props.darkMode;
const [response, setResponse] = useState("");
const [addAccountData, setAddAccountData] = useState({
    accountName: "",
    accountHeldBy: "",
    routingNumber: "",
    accountNumber: "",
    accountType: "" 
});

const handleInputChange = (event) => {
    setAddAccountData({
        ...addAccountData,
        [event.target.name]: event.target.value,
    })
}

const handleAddAccountSubmit = async(event) => {
    event.preventDefault();
    const formData = new FormData(event.target);
    formData.append("accountName", event.target.accountName.value);
    formData.append("accountHeldBy", event.target.accountHeldBy.value);
    formData.append("routingNumber", event.target.routingNumber.value);
    formData.append("accountNumber", event.target.accountNumber.value);
    formData.append("accountType", event.target.accountType.value);

    await axios.post("http://localhost:65535/bankapp_servlet/api/add", formData)
    .then((res) => {
        setResponse(res.data)
        console.log(res)
        props.updateMessage(res.data)
      })
      .catch((err) => {
        console.log(err);
      })
}

  return ( 
    <form
      className={`p-3 form-border ${
        !darkMode ? "border-dark" : "border-secondary"
      }`}
      onSubmit={handleAddAccountSubmit} enctype="multipart/form-data"
    >
      <div className="form-group">
        <input
          type="text"
          id="accountName"
          placeholder="Account Holder's Name"
          className={`form-control ${
            darkMode ? "bg-dark text-light" : "bg-light"
          }`}
          value = {addAccountData.accountName}
          onChange={handleInputChange}
        />
      </div>
      <div className="form-group">
        <select
          className={`form-control ${
            darkMode ? "bg-dark text-light" : "bg-light"
          }`}
          onChange={handleInputChange}
        >
          <option disabled selected>
            Account Held By
          </option>
          <option>Individual</option>
          <option>Company</option>
        </select>
      </div>
      <div className="form-group">
        <input
          type="number"
          id="routingNumber"
          placeholder="Routing Number"
          className={`form-control ${
            darkMode ? "bg-dark text-light" : "bg-light"
          }`}
          value = {addAccountData.routingNumber}
          onChange={handleInputChange}
        />
      </div>
      <div className="form-group">
        <input
          type="number"
          id="accountNumber"
          placeholder="Account Number"
          className={`form-control ${
            darkMode ? "bg-dark text-light" : "bg-light"
          }`}
          value = {addAccountData.accountNumber}
          onChange={handleInputChange}
        />
      </div>
      <div className="form-group">
        <select
          className={`form-control ${
            darkMode ? "bg-dark text-light" : "bg-light"
          }`}
          onChange={handleInputChange}
        >
          <option disabled selected>
            Account Type
          </option>
          <option>Checking</option>
          <option>Savings</option>
        </select>
      </div>
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
    </form>
  );
};

export default AddAccountForm;

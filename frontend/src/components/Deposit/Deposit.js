import { useState } from "react";
export default function Deposit() {
  const [response, setResponse] = useState("");
  const [accountNumber, setAccountNumber] = useState("");
  const [amount, setAmount] = useState("");
  const [depositData, setDepositData] = useState({
    accountNumber: "",
    // chequeNumber: "",
    // selectedFile: "",
    // password: "",
    amount: "",
  });

  const handleInputChange = (event) => {
    setDepositData({
      ...depositData,
      [event.target.name]: event.target.value,
    });
  };
  const handleSubmit = (event) => {
    event.preventDefault();
    const formData = new FormData(event.target);
    formData.append("accountNumber", event.target.accountNumber.value);
    formData.append("amount", event.target.amount.value);
    fetch(
      `http://localhost:65535/bankapp_servlet/api/deposit?accountNumber=${depositData.accountNumber}&amount=${depositData.amount}`,
      {
        method: "POST",
        body: formData,
      }
    )
      .then((res) => res.text())
      .then((text) => {
        setResponse(text);
      })
      .catch("Yahoo");
  };
  return (
    <div>
      <form onSubmit={handleSubmit}>
        <label>Enter Account Number</label> <br />
        <input
          id="accountNumber"
          type="text"
          name="accountNumber"
          value={depositData.accountNumber}
          onChange={handleInputChange}
        />
        <br />
        <label>Enter Amount</label> <br />
        <input
          id="amount"
          type="text"
          name="amount"
          value={depositData.amount}
          onChange={handleInputChange}
        />
        <br />
        <button type="submit">Submit</button>
      </form>
      <div>{response}</div>
    </div>
  );
}

import { useState } from "react";
export default function Deposit() {
  const [response, setResponse] = useState("");
  const [accountNumber, setAccountNumber] = useState("");
  const [amount, setAmount] = useState("");
  const [depositData, setDepositData] = useState({
    accountNumber: "",
    chequeNumber: "",
    selectedFile: "",
    password: "",
    amount,
  });
  const handleSubmit = (event) => {
    event.preventDefault();
    const formData = new FormData(event.target);
    formData.append("accountNumber", event.target.accountNumber.value);
    formData.append("amount", event.target.amount.value);
    fetch(
      `http://localhost:8080/bankapp_servlet/api/deposit?accountNumber=${accountNumber}&amount=${amount}`,
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
          value={accountNumber}
          onChange={(e) => setAccountNumber(e.target.value)}
        />
        <br />
        <label>Enter Amount</label> <br />
        <input
          id="amount"
          type="text"
          name="amount"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
        />
        <br />
        <button type="submit">Submit</button>
      </form>
      <div>{response}</div>
    </div>
  );
}

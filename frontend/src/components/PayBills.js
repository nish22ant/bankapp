import { useState } from "react";
const PayBills = () => {
  const [accountNumber, setAccountNumber] = useState("");
  const [password, setAccountPassword] = useState("");
  const [amount, setAmount] = useState("");

  return (
    <div>
      <form>
        <label>Account Number:</label>
        <input />
      </form>
    </div>
  );
};

export default PayBills;

import React from "react";
import axios from "axios";
import TransTable from "./TransTable";
const InvalidTransactions = () => {
  const [transactions, setTransactions] = React.useState([]);

  React.useEffect(() => {
    getInvalidTransactions();
  }, []);

  const getInvalidTransactions = async () => {
    const result = await axios.get(
      "http://localhost:8080/GetInvalidTransactions"
    );
    setTransactions(result.data);
    console.log(result.data);
  };
  return <TransTable transaction={transactions}></TransTable>;
};

export default InvalidTransactions;
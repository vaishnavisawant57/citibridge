import React from "react";
import axios from "axios";
import TransTable from "./TransTable";
const ValidTransactions = () => {
  const [transactions, setTransactions] = React.useState([]);

  React.useEffect(() => {
    getValidTransactions();
  }, []);

  const getValidTransactions = async () => {
    const result = await axios.get(
      "http://localhost:8080/GetValidTransactions"
    );
    setTransactions(result.data);
    console.log(result.data);
  };
  return <TransTable transaction={transactions}></TransTable>;
};

export default ValidTransactions;

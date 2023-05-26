import React from "react";
import axios from "axios";
import TransTable from "./TransTable";
const AllTransactions = () => {
  const [transactions, setTransactions] = React.useState([]);

  React.useEffect(() => {
    getAllTransactions();
  }, []);

  const getAllTransactions = async () => {
    const result = await axios.get("http://localhost:8080/transaction");
    setTransactions(result.data);
    console.log(result.data);
  };
  return <TransTable transaction={transactions}></TransTable>;
};

export default AllTransactions;

import React from "react";
import axios from "axios";
import TransTable from "./Tables/TransTable";
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
  return (
    <div className="vBase">
      <TransTable transaction={transactions}></TransTable>
    </div>
  );
};

export default ValidTransactions;

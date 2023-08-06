import React from "react";
import axios from "axios";
import TransTable from "./Tables/TransTable";
import { CSVLink } from "react-csv";

const AllTransactions = () => {
  const [transactions, setTransactions] = React.useState([]);
  const [trans, setTrans] = React.useState([]);
  React.useEffect(() => {
    getAllTransactions();
  }, []);

  const getAllTransactions = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8080/api/displayTarget"
      );
      const data = response.data; // Access the 'data' property of the response
      setTrans(data);
      const formattedTransactions = formatTransactions(data);
      setTransactions(formattedTransactions);
    } catch (error) {
      console.error("Error fetching transactions:", error);
    }
  };

  const formatTransactions = (transactions) => {
    return transactions.map((element) => {
      return {
        transaction_ref_no: `=""${element.transaction_ref_no}""`,
        date: `=""${element.date}""`,
        payer_account: `=""${element.payer_account}""`,
        payer_name: `=""${element.payer_name}""`,
        payee_account: `=""${element.payee_account}""`,
        payee_name: `=""${element.payee_name}""`,
        amount: `=""${element.amount}""`,
      };
    });
  };

  return (
    <div className="vBase" style={{ flexDirection: "column" }}>
      <TransTable transaction={trans}></TransTable>
      <CSVLink data={transactions} filename="Feed File">
        <button className="downloadBtn">Download</button>
      </CSVLink>
    </div>
  );
};

export default AllTransactions;

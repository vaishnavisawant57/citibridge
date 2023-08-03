import React, { useState } from "react";
import "./TransTable.css";
import * as AiIcons from "react-icons/ai";

const TransTable = ({ transaction }) => {
  const [filters, setFilters] = useState({
    refNumber: "",
    date: "",
    payerAccount: "",
    payerName: "",
    payeeAccount: "",
    payeeName: "",
    amount: "",
  });

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setFilters({ ...filters, [name]: value });
  };

  const handleFilterClick = () => {
    // Filter the transactions when the filter icon is clicked
    // Logic similar to the filtering logic in the previous example
    const filteredTransactions = transaction.filter((tran) => {
      const refNumberMatch = tran.transaction_ref_no.includes(
        filters.refNumber
      );
      const dateMatch = tran.date.includes(filters.date);
      const payerAccountMatch = tran.payer_account.includes(
        filters.payerAccount
      );
      const payerNameMatch = tran.payer_name.includes(filters.payerName);
      const payeeAccountMatch = tran.payee_account.includes(
        filters.payeeAccount
      );
      const payeeNameMatch = tran.payee_name.includes(filters.payeeName);
      const amountMatch = tran.amount.includes(filters.amount);

      return (
        refNumberMatch &&
        dateMatch &&
        payerAccountMatch &&
        payerNameMatch &&
        payeeAccountMatch &&
        payeeNameMatch &&
        amountMatch
      );
    });

    // Set the filtered transactions to be displayed
    setFilteredTransactions(filteredTransactions);
  };

  const [filteredTransactions, setFilteredTransactions] = useState(transaction);

  return (
    <div>
      <div className="filters">
        <div className="filter-item">
          <input
            type="text"
            name="refNumber"
            placeholder="Ref number"
            // value={filters.refNumber}
            onChange={handleInputChange}
          />
          <button onClick={handleFilterClick}>
            <AiIcons.AiOutlineFilter />
          </button>
        </div>
        <div className="filter-item">
          <input
            type="text"
            name="date"
            placeholder="Date"
            // value={filters.date}
            onChange={handleInputChange}
          />
          <button onClick={handleFilterClick}>
            <AiIcons.AiOutlineFilter />
          </button>
        </div>
        {/* Add similar filter-item divs for other columns */}
      </div>
      <table className="styled-table">
        <thead>
          <tr>
            <th>Ref number</th>
            <th>Date</th>
            <th>Payer Account Number</th>
            <th>Payer Name</th>
            <th>Payee Account Number</th>
            <th>Payee Name</th>
            <th>Amount</th>
          </tr>
        </thead>
        <tbody>
          {filteredTransactions.map((tran, index) => {
            return (
              <tr key={index}>
                <td>{tran.transaction_ref_no}</td>
                <td>{tran.date}</td>
                <td>{tran.payer_account}</td>
                <td>{tran.payer_name}</td>
                <td>{tran.payee_account}</td>
                <td>{tran.payee_name}</td>
                <td>{tran.amount}</td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
};

export default TransTable;

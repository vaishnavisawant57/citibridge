import React, { useState } from "react";
import * as AiIcons from "react-icons/ai";
import * as RiIcons from "react-icons/ri";

const InvalidTransTable = ({ transaction }) => {
  const [searchTerm, setSearchTerm] = useState("");
  const [sortByAmount, setSortByAmount] = useState(null);

  // Function to handle changes in the search input
  const handleSearchChange = (event) => {
    setSearchTerm(event.target.value);
  };

  // Function to sort transactions by amount
  const handleSortByAmount = () => {
    if (sortByAmount === "asc") {
      setSortByAmount("desc");
    } else {
      setSortByAmount("asc");
    }
  };

  // Filter and sort the transactions based on the search term and sortByAmount
  const filteredTransactions = transaction
    .filter((tran) =>
      Object.values(tran)
        .join(" ")
        .toLowerCase()
        .includes(searchTerm.toLowerCase())
    )
    .sort((a, b) => {
      if (sortByAmount === "asc") {
        return a.amount - b.amount;
      } else if (sortByAmount === "desc") {
        return b.amount - a.amount;
      }
      return 0;
    });

  return (
    <div>
      <div className="search-container">
        <AiIcons.AiOutlineSearch className="search-icon" />
        <input
          type="text"
          placeholder="Search transactions..."
          value={searchTerm}
          onChange={handleSearchChange}
        />
      </div>
      <div className="table-container">
        <table className="styled-table">
          <thead>
            <tr>
              <th>Ref number</th>
              <th>Date</th>
              <th>Payer Account Number</th>
              <th>Payer Name</th>
              <th>Payee Account Number</th>
              <th>Payee Name</th>
              <th>
                Amount
                <button onClick={handleSortByAmount}>
                  {sortByAmount === "asc" ? (
                    <RiIcons.RiArrowUpSFill />
                  ) : (
                    <RiIcons.RiArrowDownSFill />
                  )}
                </button>
              </th>
              <th>Reason</th>
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
                  <td>{tran.reason}</td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default InvalidTransTable;

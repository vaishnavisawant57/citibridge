import React from "react";

const TransTable = ({ transaction }) => {
  return (
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
        {transaction.map((tran, index) => {
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
  );
};

export default TransTable;

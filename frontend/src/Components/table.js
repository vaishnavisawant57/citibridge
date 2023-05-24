import React, { Component } from "react";

class TableExample extends Component {
  render() {
    const data = [
      { id: 1, name: "John", age: 25 },
      { id: 2, name: "Jane", age: 30 },
      { id: 3, name: "Bob", age: 35 },
    ];

    return (
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Age</th>
          </tr>
        </thead>
        <tbody>
          {data.map((item) => (
            <tr key={item.id}>
              <td>{item.id}</td>
              <td>{item.name}</td>
              <td>{item.age}</td>
            </tr>
          ))}
        </tbody>
      </table>
    );
  }
}

export default TableExample;

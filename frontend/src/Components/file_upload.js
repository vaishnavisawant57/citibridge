import React, { Component } from "react";
import { OutTable, ExcelRenderer } from "react-excel-renderer";

class ExcelPage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      rows: [],
      cols: [],
    };
    this.fileInputRef = React.createRef();
  }
  handleButtonClick = () => {
    this.fileInputRef.current.click();
  };
  fileHandler = (event) => {
    let fileObj = event.target.files[0];

    //just pass the fileObj as parameter
    ExcelRenderer(fileObj, (err, resp) => {
      if (err) {
        console.log(err);
      } else {
        this.setState({
          cols: resp.cols,
          rows: resp.rows,
        });
      }
    });
  };
  render() {
    const { rows, cols } = this.state;
    return (
      <div>
        <div
          style={{
            display: "flex",
            flexDirection: "column",
            justifyContent: "center",
            alignItems: "center",
            height: "100vh",
            background: "#f0f0f0",
          }}
        >
          <button
            style={{
              padding: "16px 32px",
              fontSize: "24px",
              borderRadius: "8px",
              background: "#146C94",
              color: "#fff",
              border: "none",
              cursor: "pointer",
              boxShadow: "0px 2px 4px rgba(0, 0, 0, 0.3)",
              marginBottom: "16px",
            }}
            onClick={this.handleButtonClick}
          >
            Select File
          </button>
          <input
            ref={this.fileInputRef}
            type="file"
            onChange={this.fileHandler.bind(this)}
            style={{ display: "none" }}
          />
          <OutTable
            data={this.state.rows}
            columns={this.state.cols}
            tableClassName="transactions"
            tableHeaderRowClass="heading"
            tableBodyRowClass="data-row"
          />
        </div>
      </div>
    );
  }
}
export default ExcelPage;

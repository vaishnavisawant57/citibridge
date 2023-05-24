import React, { Component } from "react";

class FilePage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      fileContents: "",
    };
    this.fileInputRef = React.createRef();
  }

  handleButtonClick = () => {
    this.fileInputRef.current.click();
  };

  handleFileChosen = (e) => {
    const file = e.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = () => {
        const contents = reader.result;
        this.setState({ fileContents: contents });
      };
      reader.readAsText(file);
    }
  };

  render() {
    return (
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
            background: "#ff4081",
            color: "#fff",
            border: "none",
            cursor: "pointer",
            boxShadow: "0px 2px 4px rgba(0, 0, 0, 0.3)",
            marginBottom: "16px",
          }}
          onClick={this.handleButtonClick}
        >
          Upload File
        </button>
        <input
          ref={this.fileInputRef}
          style={{ display: "none" }}
          type="file"
          onChange={this.handleFileChosen}
        />
        <div
          style={{
            padding: "16px",
            backgroundColor: "#fff",
            borderRadius: "8px",
            boxShadow: "0px 2px 4px rgba(0, 0, 0, 0.3)",
            width: "400px",
            maxHeight: "400px",
            overflow: "auto",
          }}
        >
          <pre style={{ whiteSpace: "pre-wrap", margin: "0" }}>
            {this.state.fileContents}
          </pre>
        </div>
      </div>
    );
  }
}

export default FilePage;

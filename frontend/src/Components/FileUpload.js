import React, { useState } from "react";
import axios from "axios";
import "./FileUpload.css";

const FileUpload = () => {
  const [file, setFile] = useState();
  const [statusMessage, setStatusMessage] = useState({
    status: "",
    message: "",
  });

  const handleFileUpload = (event) => {
    // const file = event.target.files[0];
    setFile(event.target.files[0]);
  };

  const submitFile = () => {
    const formData = new FormData();
    formData.append("file", file);
    console.log(formData);
    // console.log(file.name);
    if (file) {
      try {
        axios
          .post("http://localhost:8080/transaction/upload", formData)
          .then((response) => {
            // handle the response
            console.log(response);
            setStatusMessage({
              status: "success",
              message: response.data.message,
            });
            setTimeout(() => {
              setStatusMessage({
                status: "",
                message: "",
              });
            }, 2000);
          })
          .catch((error) => {
            // handle errors
            console.log(error);
            setStatusMessage({
              status: "error",
              message: "Error while uploading file",
            });
          });
      } catch (err) {
        console.log(err);
      }
    }
  };

  // render a simple input element with an onChange event listener that calls the handleFileUpload function
  return (
    <div className="base">
      <div className="file-container">
        {file && <p className="fileName">{file.name}</p>}
        <label className="btn uploadBtn center">
          <input
            type="file"
            accept=".xls, .xlsx"
            onChange={handleFileUpload}
            style={{
              display: "none",
            }}
          />
          Upload File
        </label>
        <button onClick={submitFile} className="btn validateBtn">
          Validate
        </button>
        {statusMessage.message && (
          <p
            className={`status-message ${
              statusMessage.status === "success"
                ? "success-message"
                : "error-message"
            }`}
          >
            {statusMessage.message}
          </p>
        )}
      </div>
    </div>
  );
};
export default FileUpload;

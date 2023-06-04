import React, { useState } from "react";
import axios from "axios";
import "./FileUpload.css";
import Button from "@material-ui/core/Button";
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

  return (
    <div className="homePage">
      <div className="overlap">
        <img className="group" alt={"Group"} src={"assets/group-6.png"} />
        <div className="group-3">
          <div className="title-bank">
            <h1 className="text-wrapper">Clearing Feed Generator</h1>
          </div>
          <div className="description">
            <p className="p">Upload with Confidence, Validate with Ease!</p>
          </div>
          <div className="validate">
            <div className="upload">
              <div className="overlap-group">
                <label className="text-wrapper-2">
                  <input
                    type="file"
                    onChange={handleFileUpload}
                    style={{
                      display: "none",
                    }}
                  />
                  Upload
                </label>
              </div>
            </div>
            <div className="check">
              <div className="overlap-group" onClick={submitFile}>
                <div className="text-wrapper-2">Check</div>
              </div>
            </div>
          </div>
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
        <img
          className="iconphone"
          alt={"Iconphone"}
          src={"assets/iconphone-2.svg"}
        />
        <div className="ellipse-4" />
      </div>
      <div className="overlap-group2">
        <img
          className="icon-bank"
          alt={"Icon Bank"}
          src="assets/icon-bank-2.svg"
        />
        <img className="img" alt={"Group"} src={"assets/group-5.png"} />
      </div>
      <div className="ellipse-1" />

      <div className="left"></div>
      <div className="right"></div>
    </div>
  );
};
export default FileUpload;

import React from "react";

const FileTable = ({ files }) => {
  return (
    <table className="styled-table">
      <thead>
        <tr>
          <th>File Ref Id</th>
          <th>File Name</th>
          <th>Uploaded On</th>
          <th>Status</th>
        </tr>
      </thead>
      <tbody>
        {files.map((file, index) => {
          return (
            <tr key={index}>
              <td>{file.refFileId}</td>
              <td>{file.fileName}</td>
              <td>{file.uploadDate}</td>
              <td>{file.fileStatus}</td>
            </tr>
          );
        })}
      </tbody>
    </table>
  );
};

export default FileTable;

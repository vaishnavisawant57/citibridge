import React, { useState } from "react";

const FileTable = ({ files }) => {
  const [searchTerm, setSearchTerm] = useState("");

  // Function to handle changes in the search input
  const handleSearchChange = (event) => {
    setSearchTerm(event.target.value);
  };

  // Filter the files based on the search term
  const filteredFiles = files.filter((file) =>
    Object.values(file)
      .join(" ")
      .toLowerCase()
      .includes(searchTerm.toLowerCase())
  );

  return (
    <div>
      <div className="search-container">
        <input
          type="text"
          placeholder="Search files..."
          value={searchTerm}
          onChange={handleSearchChange}
        />
      </div>
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
          {filteredFiles.map((file, index) => {
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
    </div>
  );
};

export default FileTable;

import React from "react";
import axios from "axios";
import FileTable from "../Components/Tables/FileTable";
import Navbar from "../Components/NavBar/navs";
const FileStatus = () => {
  const [files, setFiles] = React.useState([]);

  React.useEffect(() => {
    getFileStatus();
  }, []);

  const getFileStatus = async () => {
    const result = await axios.get("http://localhost:8080/GetFileStatus");
    setFiles(result.data);
    console.log(result.data);
  };
  return (
    <div>
      <Navbar />
      <div className="vBase">
        <FileTable files={files}></FileTable>
      </div>
    </div>
  );
};

export default FileStatus;

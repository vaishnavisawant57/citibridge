import React from "react";
import axios from "axios";
import FileTable from "../Components/FileTable";
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
  return <FileTable files={files}></FileTable>;
};

export default FileStatus;
package com.demo.clearingfeed.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class File {
	@Id
	String fileName;
	String fileStatus;
	String uploadDate;
	
	public File(String fileName,String uploadDate,String fileStatus) {
		this.fileName=fileName;
		this.fileStatus=fileStatus;
		this.uploadDate=uploadDate;
	}
	
	public File() {}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileStatus() {
		return fileStatus;
	}

	public void setFileStatus(String fileStatus) {
		this.fileStatus = fileStatus;
	}

	public String getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

}

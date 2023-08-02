package com.demo.clearingfeed.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class File {
	@Id
	int refFileId;
	String uploadDate;
	String fileStatus;
	String fileName;
	
	
	
	public File(int refFileId,String fileName,String uploadDate,String fileStatus) {
		this.refFileId=refFileId;
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
	public int getRefFileId() {
		return refFileId;
	}

	public void setRefFileId(int refFileId) {
		this.refFileId = refFileId;
	}
}

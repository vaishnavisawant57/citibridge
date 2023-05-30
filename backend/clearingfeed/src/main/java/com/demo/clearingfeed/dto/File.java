package com.demo.clearingfeed.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class File {
	String fileName;
	String fileStatus;
	String uploadDate;
}

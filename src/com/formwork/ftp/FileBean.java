package com.formwork.ftp;

import java.util.List;

import org.apache.commons.net.ftp.FTPFile;

public class FileBean {
	private boolean isFile;
	private String name;
	private String path;
	private Long size;
	private FTPFile ftpFile;
	private List<FileBean> ListFileBean;
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public FTPFile getFtpFile() {
		return ftpFile;
	}
	public void setFtpFile(FTPFile ftpFile) {
		this.ftpFile = ftpFile;
	}
	public boolean getIsFile() {
		return isFile;
	}
	public void setIsFile(boolean isFile) {
		this.isFile = isFile;
	}
	
	public boolean isFile() {
		return isFile;
	}
	public void setFile(boolean isFile) {
		this.isFile = isFile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public List<FileBean> getListFileBean() {
		return ListFileBean;
	}
	public void setListFileBean(List<FileBean> listFileBean) {
		ListFileBean = listFileBean;
	}
}

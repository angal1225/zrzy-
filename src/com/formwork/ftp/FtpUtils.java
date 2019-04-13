package com.formwork.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpUtils {
	private FTPClient ftpClient;
	private String path;
	private String addr;
	private int port;
	private String username;
	private String password;
	
	public FtpUtils(String path, String addr, int port, String username,
			String password) {
		this.path = path;
		this.addr = addr;
		this.port = port;
		this.username = username;
		this.password = password;
	}
	
	public FtpUtils(String addr, int port, String username, String password) {
		this("", addr, port, username, password);
	}
	//连接ftp
	public boolean connect() {
		ftpClient = new FTPClient();
		try {
			ftpClient.setControlEncoding("utf-8");
			ftpClient.connect(addr, port);
			ftpClient.login(username, password);
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				return false;
			}
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.changeWorkingDirectory(path);
		} catch (SocketException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	//关闭连接
	public boolean disconnect() {
		if (ftpClient.isConnected()) {
			try {
				ftpClient.logout();
				ftpClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	//指定路径下创建文件夹
	public void makeDirectory(String parentPath, String directory) {
		changeWorkingDirectory(parentPath);
		try {
			ftpClient.makeDirectory(directory);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//当前路径下创建文件夹
	public void makeDirectory(String directory) {
		try {
			ftpClient.makeDirectory(directory);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//把目录变为指定目录
	public void changeWorkingDirectory(String path) {
		try {
			ftpClient.changeWorkingDirectory(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//获取指定路径下的FTP文件，返回一个文件
	public FTPFile[] getFtpFileList(String path) {
		try {
			return ftpClient.listFiles(path);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	//获取文件
	public List<FileBean> getFileList(String path) {
		return this.getFileList(path, null);
	}
	//上传文件
	public boolean upLoadFile(String name, InputStream inputStream) {
		try {
			ftpClient.storeFile(name, inputStream);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean upLoadFile(File file) {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return upLoadFile(file.getName(), fileInputStream);
	}

	public boolean upLoadFile(String path) {
		File file = new File(path);
		return upLoadFile(file);
	}
	
	public InputStream getInputStream(String fileName) {
		try {
			return ftpClient.retrieveFileStream(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	//删除文件和文件夹
	public boolean deleteAllDirectory(String path) {
		FTPFile[] files = this.getFtpFileList(path);
		boolean sign = false;
		for (FTPFile file : files) {
			if (file.isDirectory()) {
				sign = this.deleteAllDirectory(path + "/" + file.getName());
				if (!sign) {
					return false;
				}
			}else if (file.isFile()) {
				sign = this.deleteFile(path + "/" + file.getName());
				if (!sign) {
					return false;
				}
			}
		}

		deleteDirectory(path);
		return true;
	}
	//删除文件夹
	public boolean deleteDirectory(String path) {
		try {
			ftpClient.removeDirectory(path);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	//删除文件
	public boolean deleteFile(String path) {
		try {
			return ftpClient.deleteFile(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteFile(String path, String name) {
		this.changeWorkingDirectory(path);
		try {
			return ftpClient.deleteFile(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean completePendingCommand() {
		try {
			return ftpClient.completePendingCommand();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	//遍历所有文件
	private List<FileBean> getFileList(String path, FileBean fileBean) {
		List<FileBean> listFileBean = new ArrayList<FileBean>();
		this.changeWorkingDirectory(path);
		FTPFile[] ftpFiles = null;
		try {
			ftpFiles = ftpClient.listFiles();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (ftpFiles != null) {
			for (FTPFile ftpFile : ftpFiles) {
				FileBean tempBean = new FileBean();
				if (ftpFile.isFile()) {
					tempBean.setFile(true);
					tempBean.setName(ftpFile.getName());
					tempBean.setSize(ftpFile.getSize());
					if(path.substring(path.length()-1, path.length()).equals("/")){
						tempBean.setPath(path + ftpFile.getName());
					}else{
						tempBean.setPath(path +"/"+ ftpFile.getName());
					}
					tempBean.setFtpFile(ftpFile);
				} else {
					tempBean.setFile(false);
					tempBean.setName(ftpFile.getName());
					tempBean.setSize(ftpFile.getSize());
					if(path.substring(path.length()-1, path.length()).equals("/")){
						tempBean.setPath(path + ftpFile.getName());
					}else{
						tempBean.setPath(path +"/"+ ftpFile.getName());
					}
					tempBean.setFtpFile(ftpFile);
					this.getFileList(tempBean.getPath(), tempBean);
				}
				listFileBean.add(tempBean);
			}

			if (fileBean != null) {
				fileBean.setListFileBean(listFileBean);
			}
		}

		return listFileBean;
	}
	
	public static void main(String[] args) {
		FtpUtils ftpUtils = new FtpUtils("172.16.2.17", 21, "test", "123456");
		ftpUtils.connect();
		/*
		InputStream inputStream = ftpUtils.getInputStream("/2group/666.jpg");
		
		
		try {
			FileOutputStream fos = new FileOutputStream("D:\\ftptest\\666.jpg");
			
			byte[] bs = new byte[1024];
			while(inputStream.read(bs) != -1){
				fos.write(bs);
				bs = new byte[1024];
			}
			fos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
		ftpUtils.changeWorkingDirectory("/2group/");
		ftpUtils.upLoadFile("E:\\apache-tomcat-8.0.52.zip");
		ftpUtils.disconnect();
	}
}
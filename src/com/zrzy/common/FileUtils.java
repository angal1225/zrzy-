
package com.zrzy.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileUtils {
	public static boolean copyInputStreamToFile(String src, String dis){
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		
		try {
			fileInputStream = new FileInputStream(src);
			fileOutputStream = new FileOutputStream(dis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return copyInputStreamToFile(fileInputStream, fileOutputStream);
	}
	
	public static boolean copyInputStreamToFile(FileInputStream src, FileOutputStream dis){
		FileChannel inChannel = null;
		FileChannel outChannel = null;
		
		try {
			inChannel = src.getChannel();
			outChannel = dis.getChannel();
			inChannel.transferTo(0, inChannel.size(), outChannel);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}

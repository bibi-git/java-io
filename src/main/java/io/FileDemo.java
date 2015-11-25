package io;

import java.io.File;
import java.io.IOException;

/**
 * @author bibi
 *
 */
public class FileDemo {
	
	/**
	 * 分隔斜线
	 */
	private static String DS = File.separator;
	
	public static void fileCreate(){
		//System.out.println(File.separatorChar);
		String path = "D:"+DS+"a";
		File file = new File(path);
		System.out.println(file.exists());
		if(!file.exists()){
			file.mkdir();
			//file.delete();//删除目录
		}
		System.out.println(file.isDirectory());
		System.out.println(file.isFile());
		
		String path2 = path+DS+"1.txt";
		File file2 = new File(path2);
		
		if(!file2.exists()){
			try {
				file2.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println(file2);
		
	}
	
	
	public static void fileDir(File dir) throws IOException {
		if (!dir.exists()) {
			throw new IllegalArgumentException("目录：" + dir + "不存在.");
		}
		if (!dir.isDirectory()) {
			throw new IllegalArgumentException(dir + "不是目录");
		}
		
		/*String[] filenames = dir.list();
		for (String string : filenames) {
			System.out.println(dir + DS + string);
		}*/

		File[] files = dir.listFiles();
		if (files != null && files.length > 0) {
			for (File file : files) {
				if (file.isDirectory()) {
					// 递归
					fileDir(file);
				} else {
					System.out.println(file);
				}
			}
		}

	}

}

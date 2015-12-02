package io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class RandomAccessFileDemo {
	
	
	public static void raf() throws IOException{
		File demo = new File("demo");//相对路径
		if(!demo.exists()){
			demo.mkdir();
		}
		
		File file = new File(demo, "raf.txt");
		if(!file.exists()){
			file.createNewFile();
		}
		
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		
		//指针的位置
		System.out.println(raf.getFilePointer());
		raf.write('A');
		System.out.println(raf.getFilePointer());
		raf.write('B');
		System.out.println(raf.getFilePointer());
		
		int i = 0x7fffffff;
		//用write方法每次只能写一个字节，如果要把i写进去就得写4次
		raf.write(i>>>24);//高8位
		raf.write(i>>>16);
		raf.write(i>>>8);
		raf.write(i);
		System.out.println(raf.getFilePointer());
		
		//可以直接写一个int,内部已经实现
		raf.writeInt(i);
		System.out.println(raf.getFilePointer());
		
		String s = "中";
		byte[] gbk = s.getBytes("gbk");
		raf.write(gbk);
		System.out.println(raf.length());
		
		
		
		//读文件，必须把指针移到头部
		raf.seek(0);
		
		byte[] buf = new byte[(int)raf.length()];
		
		//一次性读取，把文件中的内容都读到字节数组中
		raf.read(buf);
		System.out.println(Arrays.toString(buf));
		
		//构造成字符串
		String s1= new String(buf);
		System.out.println(s1);
		
		for (byte b : buf) {
			System.out.print(Integer.toHexString(b & 0xff)+" ");
		}
		
		raf.close();
		
	}

}

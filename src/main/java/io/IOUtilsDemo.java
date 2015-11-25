package io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * @author bibi
 * 
 * IO流(输入流、输出流)
 * 字节流、字符流
 * 1.字节流
 * 1)InputStream、OutputStream
 * InputStream抽象了应用程序读取数据的方式
 * OutputStream抽象了应用程序写出数据的方式
 * 
 * 2)EOF = End 读到-1就读到结尾
 * 
 * 3)输入流基本方法
 * int b = in.read();读取一个字节无符号填充到int低八位。-1是EOF
 * in.read(byte[] buf)
 * in.read(byte[] buf,int start,int size)
 * 
 * 4)输出流基本方法
 * out.write(int b) 写出一个byte到流，b的低八位
 * out.write(byte[] buf)将buf字节数组都写入到流
 * out.write(byte[] buf,int start,int size)
 * 
 * 5)FileInputStream--->具体实现了在文件上读取数据
 * 
 * 6)FileOutputStream 实现了向文件中写出byte数据的方法
 * 
 * 7)DataInputStream/DataOutputStream
 * 对流功能的扩展，可以更加方便的读取int,long,字符等类型数据
 * DataOutputStream
 * 		writeInt()/writeDouble()/writeUTF()
 * 
 * 8)BufferedInputStream&BufferedOutputStream
 * 这两个流类为IO提供了带缓冲区的操作，一般打开文件进行写入
 * 或读取操作时，都会加上缓冲，这种流模式提高了IO的性能
 * 从应用程序中把输入放入文件，相当于将一缸水倒入到另一个缸中：
 * FileOutputStream--->write()方法相当于一滴一滴的把水“转移”过去
 * DataOutputStream--->writeXxx()方法会方便一些，相当于一瓢一瓢把水“转移”
 * BufferedOutputStream--->write()方法更方便，相当于一瓢一瓢先放入桶中，再从桶中倒入缸中
 */
public class IOUtilsDemo {
	
	/**
	 * 读取指定文件内容，按照16进制输出到控制台
	 * 并且每输出10个byte换行
	 * @param fileName
	 * @throws  
	 */
	public static void fileInputStream1(String fileName) throws IOException{
		
		//把文件作为字节流进行读操作
		FileInputStream in  = new FileInputStream(fileName);
		int b;
		int i = 1;
		while((b = in.read())!=-1){
			/*if(b <= 0xf){
				//单位数前面步0
				System.out.print("0");
			}*/
			System.out.print(Integer.toHexString(b)+" ");
			if(i++%10==0){
				System.out.println();
			}
		}
		in.close();
	}
	
	public static void fileInputStream2(String fileName) throws IOException{
		
		//把文件作为字节流进行读操作
		FileInputStream in  = new FileInputStream(fileName);
		byte[] buf = new byte[20 * 1024];
		/*
		 *从int中批量读取字节，放入到buf这个字节数组中，
		 *从第0个位置开始放，最多放buf.length个
		 *返回的是读到的字节个数 
		 */
		
		/*
		int b = in.read(buf, 0, buf.length);//一次性读完，说明字节数组足够到
		for (int i = 0; i<b; i++) {
			
			System.out.print(Integer.toHexString(buf[i] & 0xff)+"  ");
			if((i+1)%10==0){
				System.out.println();
			}
		}
		*/
		
		int b = 0;
		int j = 1;
		while((b = in.read(buf, 0, buf.length))!=-1){
			for(int i= 0;i<b;i++){
				System.out.print(Integer.toHexString(buf[i] & 0xff)+" ");
				if(j++%10==0){
					System.out.println();
				}
			}
		}
		
		
		in.close();
	}
	
	public static void fileOutputStream1() throws IOException{
		//如果该文件不存在，则直接创建，如果存在，删除后创建
		FileOutputStream out = new FileOutputStream("demo/out.txt");
		//FileOutputStream out = new FileOutputStream("demo/out.txt",true);
		
		out.write('A');//写出A的低八位
		out.write('B');//写出B的低八位
		int a = 255;//write只能写八位，那么写一个int需要写4次每次8位
		out.write(a >>> 24);
		out.write(a >>> 16);
		out.write(a >>> 8);
		out.write(a);
		byte[] utf = "中国".getBytes();
		out.write(utf);
		out.close();
		
		fileInputStream2("demo/out.txt");
		
	}
	
	public static void copyFileByByte(File srcFile,File destFile)throws IOException{
		
		if(!srcFile.exists()){
			throw new IllegalArgumentException("文件"+srcFile+"不存在");
		}
		
		if(!srcFile.isFile()){
			throw new IllegalArgumentException(srcFile+"不是文件");
		}
		
		FileInputStream in = new FileInputStream(srcFile);
		FileOutputStream out = new FileOutputStream(destFile);
		int b;
		while((b = in.read())!=-1){
			out.write(b);
			out.flush();
		}
		in.close();
		out.close();
		
	}
	
	public static void copyFileByBufArray(File srcFile,File destFile)throws IOException{
		
		if(!srcFile.exists()){
			throw new IllegalArgumentException("文件"+srcFile+"不存在");
		}
		
		if(!srcFile.isFile()){
			throw new IllegalArgumentException(srcFile+"不是文件");
		}
		
		FileInputStream in = new FileInputStream(srcFile);
		FileOutputStream out = new FileOutputStream(destFile);
		byte[] buf = new byte[8*1024];
		int b;
		while((b = in.read(buf,0,buf.length))!=-1){
			out.write(buf, 0, b);
			out.flush();
		}
		in.close();
		out.close();
		
	}
	
	public static void copyFileByData(File srcFile,File destFile)throws IOException{
		
		if(!srcFile.exists()){
			throw new IllegalArgumentException("文件"+srcFile+"不存在");
		}
		
		if(!srcFile.isFile()){
			throw new IllegalArgumentException(srcFile+"不是文件");
		}
		
		DataInputStream bis = new DataInputStream(new FileInputStream(srcFile));
		DataOutputStream bos = new DataOutputStream(new FileOutputStream(destFile));
		
		int c;
		while((c = bis.read())!=-1){
			bos.write(c);
			bos.flush();
		}
		bis.close();
		bos.close();
		
	}
	
	public static void copyFileByBuffer(File srcFile,File destFile)throws IOException{
		
		if(!srcFile.exists()){
			throw new IllegalArgumentException("文件"+srcFile+"不存在");
		}
		
		if(!srcFile.isFile()){
			throw new IllegalArgumentException(srcFile+"不是文件");
		}
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFile));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile));
		
		int c;
		while((c = bis.read())!=-1){
			bos.write(c);
			bos.flush();
		}
		bis.close();
		bos.close();
		
	}
	
	public static void dataOutputStream(String fileName) throws IOException{
		
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName));
		dos.writeInt(10); //4
		dos.writeInt(-10); //4
		dos.writeLong(10l);//8
		dos.writeDouble(10.5);//8
		//采用utf-8编码写出
		dos.writeUTF("中国");//6
		//采用utf-16be编码写出
		dos.writeChars("中国");//4
		dos.close();
		
		IOUtilsDemo.fileInputStream1(fileName);
	}
	
	public static void dataInputStream(String fileName) throws IOException{
		
		DataInputStream dis = new DataInputStream(new FileInputStream(fileName));
		
		int i = dis.readInt();
		System.out.println(i);
		
		i = dis.readInt();
		System.out.println(i);
		
		long l = dis.readLong();
		System.out.println(l);
		
		double d = dis.readDouble();
		System.out.println(d);
		
		String  s = dis.readUTF();
		System.out.println(s);
		
		dis.close();
	}
	
	public static void inputStreamReaderAndWrite(File srcFile,File destFile) throws IOException{
		
		InputStreamReader isr = new InputStreamReader(new FileInputStream(srcFile));//默认是项目编码
		//InputStreamReader isr = new InputStreamReader(new FileInputStream(srcFile),"utf-8");//默认是项目编码
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(destFile));
		//OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(destFile),"utf-8");
		
		
		int c;
		
		/*while((c = isr.read())!=-1){
			System.out.print((char)c);
		}*/
		
		char[] buffer = new char[8*1024];
		while((c = isr.read(buffer, 0, buffer.length))!=-1){
			String s = new String(buffer,0,c);
			System.out.print(s);
			osw.write(buffer, 0, c);
		}
		
		isr.close();
		osw.close();
	}
	
	public static void fileReaderAndWrite(File srcFile,File destFile) throws IOException{
		
		FileReader fr = new FileReader(srcFile);
		FileWriter fw = new FileWriter(destFile);
		//FileWriter fw = new FileWriter(destFile,true);
		
		int c;
		char[] buffer = new char[8*1024];
		while((c = fr.read(buffer, 0, buffer.length))!=-1){
			String s = new String(buffer,0,c);
			System.out.print(s);
			fw.write(buffer, 0, c);
			fw.flush();
		}
		
		fr.close();
		fw.close();
	}
	
	public static void bufferedReaderAndWrite(File srcFile,File destFile) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile)));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destFile)));
		PrintWriter pw = new PrintWriter(destFile);
		
		String line;
		while((line = br.readLine())!=null){
			System.out.println(line);//没有包括换行
			//bw.write(line);
			//bw.newLine();//单独写出换行
			//bw.flush();
			
			pw.println(line);
			//pw.print(line);
			pw.flush();
		}
		
		br.close();
		bw.close();
		pw.close();
	}
	
}

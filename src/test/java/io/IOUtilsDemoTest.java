package io;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class IOUtilsDemoTest {
	
	@Test
	public void testFileInputStream() throws IOException{
		long start = System.currentTimeMillis();
		//IOUtilsDemo.fileInputStream1("E:/eclipse/java-io/src/main/java/io/EncodeDemo.java");
		//IOUtilsDemo.fileInputStream2("E:/eclipse/java-io/src/main/java/io/EncodeDemo.java");
		//IOUtilsDemo.fileInputStream1("d:/a/2.txt");
		IOUtilsDemo.fileInputStream2("d:/a/2.txt");
		long end = System.currentTimeMillis();
		System.out.println();
		System.out.println(end - start);
	}
	
	
	@Test
	public void testFileOutputStream() throws IOException{
		long start = System.currentTimeMillis();
		IOUtilsDemo.fileOutputStream1();
		long end = System.currentTimeMillis();
		//System.out.println(end - start);
	}
	
	
	@Test
	public void testCopyFile() throws IOException{
		long start = System.currentTimeMillis();
		IOUtilsDemo.copyFileByByte(new File("d:/a/111.rar"), new File("d:/a/222.rar"));//13232
		//IOUtilsDemo.copyFileByBufArray(new File("d:/a/111.rar"), new File("d:/a/222.rar"));//30
		//IOUtilsDemo.copyFileByData(new File("d:/a/111.rar"), new File("d:/a/222.rar"));//12399
		//IOUtilsDemo.copyFileByBuffer(new File("d:/a/111.rar"), new File("d:/a/222.rar"));//9097
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
	
	
	@Test
	public void testDataOutputStream() throws IOException{
		long start = System.currentTimeMillis();
		IOUtilsDemo.dataOutputStream("demo/DataOutput.txt");
		long end = System.currentTimeMillis();
		//System.out.println(end - start);
	}
	
	@Test
	public void testDataInputStream() throws IOException{
		long start = System.currentTimeMillis();
		IOUtilsDemo.dataInputStream("demo/DataOutput.txt");
		long end = System.currentTimeMillis();
		//System.out.println(end - start);
	}
	
	
	@Test
	public void testInputStreamReaderAndWrite() throws IOException{
		IOUtilsDemo.inputStreamReaderAndWrite(new File("d:/a/abc.txt"), new File("d:/a/ab.txt"));
	}
	
	@Test
	public void testFileReaderAndWrite() throws IOException{
		IOUtilsDemo.inputStreamReaderAndWrite(new File("d:/a/abc.txt"), new File("d:/a/ab.txt"));
	}
	
	@Test
	public void testBufferedReaderAndWrite() throws IOException{
		IOUtilsDemo.bufferedReaderAndWrite(new File("d:/a/abc.txt"), new File("d:/a/ab.txt"));
	}

}

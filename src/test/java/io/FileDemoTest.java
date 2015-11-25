package io;

import java.io.File;
import java.io.IOException;
import org.junit.Test;

public class FileDemoTest {
	
	
	@Test
	public void testFileCreate(){
		FileDemo.fileCreate();
	}
	
	@Test
	public void testFileDir() throws IOException{
		String dir = "D:/Apache24/";
		FileDemo.fileDir(new File(dir));
	}

}

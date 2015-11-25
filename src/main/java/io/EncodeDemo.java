package io;

public class EncodeDemo {
	
	public void encode(String str) throws Exception{
		
		
		System.out.println("测试字符串："+str+"\n");
		
		
		System.out.println("========================");
		System.out.println("默认编码--16进制字节序列：");
		byte[] bytes1 = str.getBytes();
		for(byte b :bytes1){
			System.out.print(Integer.toHexString(b & 0xff)+" ");
		}
		System.out.println();
		System.out.println("字节序列转成字符串：");
		String str1 = new String(bytes1);
		System.out.println(str1);
		
		
		
		
		System.out.println("========================");
		System.out.println("gbk编码--16进制字节序列：");
		byte[] bytes2 = str.getBytes("gbk");
		for(byte b :bytes2){
			System.out.print(Integer.toHexString(b & 0xff)+" ");
		}
		System.out.println();
		System.out.println("字节序列转成字符串：");
		String str2 = new String(bytes2, "gbk");
		System.out.println(str2);
		
		
		
		
		System.out.println("========================");
		System.out.println("utf-8编码--16进制字节序列：");
		byte[] bytes3 = str.getBytes("utf-8");
		for(byte b :bytes3){
			System.out.print(Integer.toHexString(b & 0xff)+" ");
		}
		System.out.println();
		System.out.println("字节序列转成字符串：");
		String str3 = new String(bytes3, "utf-8");
		System.out.println(str3);
		
		
		
		
		System.out.println("========================");
		System.out.println("utf-16be编码--16进制字节序列：");
		byte[] bytes4 = str.getBytes("utf-16be");
		for(byte b :bytes4){
			System.out.print(Integer.toHexString(b & 0xff)+" ");
		}
		System.out.println();
		System.out.println("字节序列转成字符串：");
		String str4 = new String(bytes4, "utf-16be");
		System.out.println(str4);
		
		
	}

}

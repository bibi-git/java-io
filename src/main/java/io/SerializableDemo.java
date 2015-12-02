package io;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * @author bibi
 * 
 * 对象的序列化，反序列化
 * 1)对象序列化，就是将Object转换成byte序列，反之叫对象的反序列化
 * 2)序列化流(ObjectOutputStream),是过滤流----writeObject
 *   反序列化流(ObjectInputStream)----readObject
 *   
 * 3)序列化接口(Serializable)
 *  对象必须实现序列化接口，才能进行序列化，否则将出现异常
 *  这个接口，没有任何方法，只是一个标准
 *  
 *  private void writeObject(java.io.ObjectOutputStream s)
			throws java.io.IOException {
		s.defaultWriteObject();// 把jvm能默认序列化的元素进行序列化操作
		s.writeInt(stuage);// 自己完成stuage的序列化
		// s.writeObject(obj);
	}

	private void readObject(java.io.ObjectInputStream s)
			throws java.io.IOException, ClassNotFoundException {
		s.defaultReadObject();// 把jvm能默认反序列化的元素进行反序列化操作
		this.stuage = s.readInt();// 自己完成stuage反序列化操作
	}
 *
 */
public class SerializableDemo{
	
	public static void main(String[] args) throws Exception{
		
		String file = "demo/obj.dat";
		
		
		/*//1.对象的序列化
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
		
		Student student = new Student("1001", "张三", 20);
		oos.writeObject(student);
		oos.flush();
		oos.close();*/
		
		
		//2.对象的反序列化
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		Student stu = (Student) ois.readObject();
		System.out.println(stu);
		ois.close();
		
			
	}

}

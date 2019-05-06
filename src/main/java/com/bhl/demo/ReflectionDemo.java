package com.bhl.demo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

import com.bhl.annotation.BookName;
import com.bhl.pojo.Book;

public class ReflectionDemo {
	
	
	/**
	 * 获取Class的三种方法
	 * @throws ClassNotFoundException
	 */
	public void getClazz() throws ClassNotFoundException {
		
		//1) 类名.class
		Class<?> clazz1 = Book.class;
		
		//2)Class.forName("包名.类名");
		//框架开发中常用
		Class<?> clazz2 = Class.forName("com.bhl.Book");
		
		//3)实例对象.getClass();(获取已经存在的类对象)
		Book book = new Book();
		Class<?> clazz3 = book.getClass();
		
		//说明：任意的一个类在同一个 JVM 内部,类对象是唯一的,此类对象会在第一次类加载时创建。
		System.out.println(clazz1==clazz2);
		System.out.println(clazz2==clazz3);
		
	}
	
	/**
	 * 通过Class获取构造器并创建实例
	 * @throws Exception
	 */
	public void createExample() throws Exception {
		
		Class<?> clazz = Class.forName("com.bhl.Book");
		
		//无参构造函数创建
		Object obj = clazz.getDeclaredConstructor().newInstance();
		System.out.println(obj);
		
		//有参构造函数创建
		obj = clazz.getDeclaredConstructor(String.class).newInstance("JAVA");
		System.out.println(obj);

	}
	
	
	/**
	 * 获取字段
	 * @throws Exception
	 */
	public void getField() throws Exception {
		
		Class<?> book = Class.forName("com.bhl.Book");
		
	    // 获取取clazz对应类中的所有字段(父类继承来的字段)--方法字段（一）
        // 不能获取private字段
		Field[] fields = book.getFields();
		for (Field fd : fields) {
			System.out.print(fd.getName()+" ");
		}
		
		System.out.println();
		
		 // 获取当前类所有声明的字段，包括私有字段 --方法字段（二）
		fields = book.getDeclaredFields();
		for (Field fd : fields) {
			System.out.print(fd.getName()+" ");
		}
		
		System.out.println();
		
		Field fd = book.getField("price");
		Book bk = new Book();
		
		fd.set(bk, "100");
		System.out.println(fd.get(bk));
		System.out.println(bk);
		
		//私有字段
		fd = book.getDeclaredField("bookName");
		
		fd.setAccessible(true);
		fd.set(bk, "大话设计模式");
		System.out.println(fd.get(bk));
		System.out.println(bk);
		
	}
	
	
	/**
	 * 获取方法
	 * @throws Exception
	 */
	public void getMethod() throws Exception {
		Class<?> book = Class.forName("com.bhl.Book");
		
	    // 获取取clazz对应类中的所有方法(父类继承来的方法)--方法数组（一）
        // 不能获取private方法
		Method[] methods = book.getMethods();
		for (Method md : methods) {
			System.out.print(md.getName()+" ");
		}
		System.out.println();
		
        // 获取当前类所有声明的方法，包括私有方法 --方法数组（二）
		methods = book.getDeclaredMethods();
		
		for (Method md : methods) {
			System.out.print(md.getName()+" ");
		}
		
		System.out.println();
		
		Method method = book.getMethod("setBookName",String.class);
		
		Book bk = new Book();
		method.invoke(bk, "设计模式之禅");
		System.out.println(bk);
		
		//私有方法
		method = book.getDeclaredMethod("setName",String.class);
		
		//取消 Java 语言访问检查
		method.setAccessible(true);
		
		method.invoke(bk, "人月神话");
		System.out.println(bk);
		
	}
	/**
	 * 获取注解
	 * @throws Exception
	 */
	
	@Test
	public void getAnnotation() throws Exception {
		
		Class<?> clazz = Class.forName("com.bhl.Book");
		
		//获取当前类上所有注解
		Annotation[] annotations = clazz.getDeclaredAnnotations();
		
		for (Annotation an : annotations) {
			System.out.println(an);
		}
		
		
		Method[] methods = clazz.getDeclaredMethods();
		
		Book book = new Book();
		
		for (Method method : methods) {
			
			//获取指定注解
			BookName annotation = method.getAnnotation(BookName.class);
			if(annotation!=null) {
				method.invoke(book, annotation.bookName());
			}
			
		}
		System.out.println(book);
		
	}
}

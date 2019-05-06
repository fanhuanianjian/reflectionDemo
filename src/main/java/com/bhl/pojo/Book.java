package com.bhl.pojo;

import com.bhl.annotation.BookName;
import com.bhl.annotation.MyBook;

@MyBook
public class Book {
	
	public Book() {}
	
	public Book(String bookName) {
		
		this.bookName = bookName;
	}

	private String bookName;

	public String price;
	
	private void setName(String bookName) {
		
		this.bookName = bookName;
		
	}

	public String getBookName() {
		return bookName;
	}

	@BookName(bookName ="重构-改善既有代码的设计")
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [bookName=" + bookName + ", price=" + price + "]";
	}
	
	
}

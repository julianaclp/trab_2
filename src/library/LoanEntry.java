package library;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

public class LoanEntry {
	private GregorianCalendar borrowDate;
	private GregorianCalendar returnDate;
	private Book book;
	private String person;
	final static int BORROW = 1;
	final static int RETURN = 2;
	
	public LoanEntry(Book book) {
		this.book = book;
	}
	
	public HashMap<String, Object> borrowBook(String person) {
		HashMap<String, Object> response = new HashMap<String, Object>();
		this.person = person;
		if(book.getStatus() == Book.BORROWED) {
			response.put("success", false);
			response.put("message", "Livro já está emprestado. Verifique os dados e tente novamente");
			return response;
		}
		book.setStatus(Book.BORROWED);
		response.put("success", true);
		response.put("message", "Livro emprestado com sucesso!");
		return response;
	}
	
	public HashMap<String, Object> returnBook() {
		HashMap<String, Object> response = new HashMap<String, Object>();
		if(book.getStatus() == Book.AVAILABLE) {
			response.put("success", false);
			response.put("message", "Livro ainda não foi emprestado. Verifique os dados e tente novamente!");
			return response;
		}
		book.setStatus(Book.AVAILABLE);
		response.put("success", true);
		response.put("message", "Livro devolvido com sucesso!");
		return response;
	}
	
	public String getDate(int action) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		if(action == BORROW && borrowDate != null) return format.format(borrowDate);
		if(action == RETURN && returnDate != null) return format.format(returnDate);
		return "NULL";
	}
	
	public Book getBook() {
		return this.book;
	}
}

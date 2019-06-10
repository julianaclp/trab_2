package library;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class Loan {
	private GregorianCalendar borrowDate;
	private GregorianCalendar returnDate;
	private Book book;
	private String person;
	final static int BORROW = 1;
	final static int RETURN = 2;
	
	public Loan(Book book) {
		this.book = book;
	}
	
	public HashMap<String, Object> borrowBook(String person, int day, int month, int year) {
		HashMap<String, Object> response = new HashMap<String, Object>();
		this.person = person;
		if(book.getStatus() == Book.BORROWED) {
			response.put("success", false);
			response.put("message", "Livro já está emprestado.");
			return response;
		}
		book.setStatus(Book.BORROWED);
		borrowDate = new GregorianCalendar(year, month - 1, day);
		response.put("success", true);
		response.put("message", "Livro emprestado com sucesso!");
		return response;
	}
	
	public HashMap<String, Object> returnBook(String person, int day, int month, int year) {
		HashMap<String, Object> response = new HashMap<String, Object>();
		if(book.getStatus() == Book.AVAILABLE) {
			response.put("success", false);
			response.put("message", "Livro ainda não foi emprestado. Verifique os dados e tente novamente!");
			return response;
		}
		book.setStatus(Book.AVAILABLE);
		returnDate = new GregorianCalendar(year, month - 1, day);
		response.put("success", true);
		response.put("message", "Livro devolvido com sucesso!");
		return response;
	}
	
	public String getDate(int action) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		if(action == BORROW && borrowDate != null) return format.format(borrowDate);
		if(action == RETURN && returnDate != null) return format.format(returnDate);
		return "Ocorreu um erro";
	}
	
	
}

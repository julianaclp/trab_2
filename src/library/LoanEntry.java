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
	public final static int BORROW = 1;
	public final static int RETURN = 2;
	
	public LoanEntry(Book book) {
		this.book = book;
	}
	
	public HashMap<String, Object> borrowBook(String person) {
		//M�todo que realiza o empr�stimo de um livro
		//OP��ES DE RETORNO
		//result{"success" : true, "message" : mensagem de sucesso} caso o livro esteja dispon�vel para empr�stimo
		//result{"success" : false, "message" : mensagem de erro} caso o livro n�o esteja dispon�vel para empr�stimo
		HashMap<String, Object> response = new HashMap<String, Object>();
		this.person = person;
		if(book.getStatus() == Book.BORROWED) {
			response.put("success", false);
			response.put("message", "Livro j� est� emprestado. Verifique os dados e tente novamente");
			return response;
		}
		book.setStatus(Book.BORROWED);
		response.put("success", true);
		response.put("message", "Livro emprestado com sucesso!");
		return response;
	}
	
	public HashMap<String, Object> returnBook() {
		//M�todo que realiza a devolu��o de um livro
		//OP��ES DE RETORNO
		//result{"success" : true, "message" : mensagem de sucesso} caso o livro esteja tenha sido devolvido com sucesso
		//result{"success" : false, "message" : mensagem de erro} caso o usu�rio tente devolver um livro que n�o est� emprestado
		HashMap<String, Object> response = new HashMap<String, Object>();
		if(book.getStatus() == Book.AVAILABLE) {
			response.put("success", false);
			response.put("message", "Livro ainda n�o foi emprestado. Verifique os dados e tente novamente!");
			return response;
		}
		book.setStatus(Book.AVAILABLE);
		setDate(new GregorianCalendar(), RETURN); //coloca a data de devolu��o como sendo a data de hoje
		response.put("success", true);
		response.put("message", "Livro devolvido com sucesso!");
		return response;
	}
	
	public String getDate(int action) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		if(action == BORROW && borrowDate != null) return format.format(borrowDate.getTime());
		if(action == RETURN && returnDate != null) return format.format(returnDate.getTime());
		return "--";
	}
	
	public void setDate(GregorianCalendar date, int action) {
		if(action == BORROW) borrowDate = date;
		if(action == RETURN) returnDate = date;
	}
	
	public String getPerson() {
		return this.person;
	}
	
	public Book getBook() {
		return this.book;
	}
}

package library;

import java.util.GregorianCalendar;

import org.json.JSONObject;

public class Loan {
	private GregorianCalendar borrowDate;
	private GregorianCalendar returnDate;
	final static int EMPRESTAR = 1;
	final static int DEVOLVER = 2;
	int action;
	
	public Loan(int action) {
		this.action = action;
	}
	
	public String loanEntry(int id, Library l) {
		if(action == EMPRESTAR) return borrowBook(id, l);
		else if(action == DEVOLVER) return returnBook(id, l);
		return "A��o n�o reconhecida";
	}
	
	private String borrowBook(int id, Library l) {
		JSONObject response = l.searchBook(id);
		if((boolean) response.opt("exists") == true) { 
			Book b = (Book) response.opt("book");
			if(b.getStatus() == Book.BORROWED) return "Livro indispon�vel para empr�stimo";
			b.setStatus(Book.BORROWED);
			return "Livro emprestado com sucesso";
		}
		else return (String) response.opt("message");  
	}
	
	private String returnBook(int id, Library l) {
		JSONObject response = l.searchBook(id);
		if((boolean) response.opt("exists") == true) { 
			Book b = (Book) response.opt("book");
			if(b.getStatus() == Book.AVAILABLE) return "Livro n�o est� emprestado. Verifique os dados e tente novamente";
			b.setStatus(Book.AVAILABLE);
			return "Livro devolvido com sucesso";
		}
		else return (String) response.opt("message");  
	}
	
}

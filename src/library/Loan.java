package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Loan implements Iterable<LoanEntry> {
	private ArrayList<LoanEntry> alLoan = null;
	private static Loan myLoans;
	
	public static Loan getInstance() {
		if(myLoans == null) myLoans = new Loan();
		return myLoans;
	}
	
	private Loan() {
		alLoan = new ArrayList<LoanEntry>();
	}
	
	public void newEntry(LoanEntry loan) {
		alLoan.add(loan);
	}
	
	public HashMap<String, Object> getActiveEntry(Book book) {
		//Método responsável por pegar o empréstimo em aberto
		//Opções de retorno:
		//result{"success" : true; "data" : dados do empréstimo} caso o empréstimo exista
		//result{"success" : false; "message" : mensagem de erro} caso o empréstimo não exista
		HashMap<String, Object> result = new HashMap<String, Object>();
		for (LoanEntry l : alLoan) {
			if(l.getBook().getId() == book.getId() && l.getDate(LoanEntry.RETURN).equals("--")) {
				result.put("success", true);
				result.put("data", l);
				return result;
			}
		}
		result.put("success", false);
		result.put("data", "Não existe empréstimo para esse livro");
		return result;
	}
	
	public String getHistory(Book book) {
		//Pega o histórico de empréstimos de um livro e retorna uma String
		String result = "";
		for(LoanEntry l : alLoan) {
			if(l.getBook().getId() == book.getId()) {
				result += book + "\n";
				result += "Emprestado por: " + l.getPerson() + "\n";
				result += "Data de empréstimo: " + l.getDate(LoanEntry.BORROW) + "\n";
				result += "Data de devolução: " + l.getDate(LoanEntry.RETURN) + "\n";
				result += "\n";
			}
		}
		if(result.length() == 0) result = "Não há empréstimos para esse livro.";
		return result;
	}
	
	@Override
	public Iterator<LoanEntry> iterator() {
		return alLoan.iterator();
	}

}

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
		//M�todo respons�vel por pegar o empr�stimo em aberto
		//Op��es de retorno:
		//result{"success" : true; "data" : dados do empr�stimo} caso o empr�stimo exista
		//result{"success" : false; "message" : mensagem de erro} caso o empr�stimo n�o exista
		HashMap<String, Object> result = new HashMap<String, Object>();
		for (LoanEntry l : alLoan) {
			if(l.getBook().getId() == book.getId() && l.getDate(LoanEntry.RETURN).equals("--")) {
				result.put("success", true);
				result.put("data", l);
				return result;
			}
		}
		result.put("success", false);
		result.put("data", "N�o existe empr�stimo para esse livro");
		return result;
	}
	
	public String getHistory(Book book) {
		//Pega o hist�rico de empr�stimos de um livro e retorna uma String
		String result = "";
		for(LoanEntry l : alLoan) {
			if(l.getBook().getId() == book.getId()) {
				result += book + "\n";
				result += "Emprestado por: " + l.getPerson() + "\n";
				result += "Data de empr�stimo: " + l.getDate(LoanEntry.BORROW) + "\n";
				result += "Data de devolu��o: " + l.getDate(LoanEntry.RETURN) + "\n";
				result += "\n";
			}
		}
		if(result.length() == 0) result = "N�o h� empr�stimos para esse livro.";
		return result;
	}
	
	@Override
	public Iterator<LoanEntry> iterator() {
		return alLoan.iterator();
	}

}

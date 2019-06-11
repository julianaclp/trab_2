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
		HashMap<String, Object> result = new HashMap<String, Object>();
		for (LoanEntry l : alLoan) {
			if(l.getBook().getId() == book.getId() && l.getDate(LoanEntry.RETURN).equals("NULL")) {
				result.put("success", true);
				result.put("data", l);
				return result;
			}
		}
		result.put("success", false);
		result.put("data", "Não existe empréstimo para esse livro");
		return result;
	}
	
	@Override
	public Iterator<LoanEntry> iterator() {
		return alLoan.iterator();
	}

}

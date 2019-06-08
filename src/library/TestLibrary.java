package library;

public class TestLibrary {

	public static void main(String[] args) {
		Library l = Library.getInstance();
		l.addBook(new Book("Pet Sematary"));
		l.addBook(new Book("Good Omens"));
		l.addBook(new Book("The Shining"));
		System.out.println(new Loan(Loan.EMPRESTAR).loanEntry(2, l));
		System.out.println(new Loan(Loan.EMPRESTAR).loanEntry(2, l));
		System.out.println(new Loan(Loan.DEVOLVER).loanEntry(2, l));
		System.out.println(new Loan(Loan.DEVOLVER).loanEntry(2, l));
		System.out.println(new Loan(Loan.EMPRESTAR).loanEntry(4, l));
		//System.out.println(l.printLibrary());

	}

}

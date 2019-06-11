package library;

public class TestLibrary {

	public static void main(String[] args) {
		Library l = Library.getInstance();
		Loan loans = Loan.getInstance();
		l.addBook(new Book("Pet Sematary"));
		l.addBook(new Book("Good Omens"));
		l.addBook(new Book("The Shining"));
		System.out.println(l.printLibrary());
//		System.out.println(l.searchBook(4));
		loans.newEntry(new LoanEntry(l.getBook(2)));
		//System.out.println(new LoanEntry(l.getBook(2)).borrowBook("juliana", 10, 6, 2019));
		//System.out.println(new LoanEntry(l.getBook(2)).borrowBook("juliana", 10, 6, 2019));

		//System.out.println(l.printLibrary());

	}

}

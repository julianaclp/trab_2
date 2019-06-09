package library;

public class TestLibrary {

	public static void main(String[] args) {
		Library l = Library.getInstance();
		l.addBook(new Book("Pet Sematary"));
		l.addBook(new Book("Good Omens"));
		l.addBook(new Book("The Shining"));
		System.out.println(new LoanEntry(LoanEntry.BORROW, l.getBook(2)).borrowBook("juliana"));
		System.out.println(new LoanEntry(LoanEntry.BORROW, l.getBook(2)).borrowBook( "juliana"));
		System.out.println(new LoanEntry(LoanEntry.RETURN, l.getBook(2)).returnBook());
		System.out.println(new LoanEntry(LoanEntry.RETURN, l.getBook(2)).returnBook());
		System.out.println(new LoanEntry(LoanEntry.BORROW, l.getBook(4)).borrowBook("juliana"));
		//System.out.println(l.printLibrary());

	}

}

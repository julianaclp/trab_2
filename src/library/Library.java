package library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class Library implements Iterable<Book> {
	private ArrayList<Book> alBooks = null;
	private static Library myLibr;
	
	public static Library getInstance() {
		if(myLibr == null) myLibr = new Library();
		return myLibr;
	}
	
	private Library() {
		alBooks = new ArrayList<Book>();
	}
	
	public void addBook(Book book) {
		book.setId(idGenerator());
		alBooks.add(book);
	}
	
	public Book getBook(int i) throws IndexOutOfBoundsException {
		if(i >= alBooks.size()) throw new IndexOutOfBoundsException("Livro inexistente. Verifique os dados e tente novamente!");
		return alBooks.get(i);
	}
	
	public void orderById() {
		Collections.sort(alBooks);
	}
	
	public void orderByIdDesc() {
		Collections.sort(alBooks, Collections.reverseOrder());
	}
	
	public int idGenerator() {
		if(alBooks.size() == 0) return 1;
		this.orderById();
		return this.getBook(alBooks.size() - 1).getId() + 1;
	}
	
	public String printLibrary() {
		String lib = "";
		for (Book b : alBooks) {
			lib += b.getTitle() + " " + b.getId() + "\n";
		}
		return lib;
	}
	
	public HashMap<String, Object> searchBook(int id) {
		HashMap<String, Object> response = new HashMap<String, Object>();
		for(Book b : alBooks) {
			if(b.getId() == id) {
				response.put("exists", true);
			    response.put("book", b);
			};
		}
		if(response.size() == 0) {
			response.put("exists", false);
			response.put("message", "Livro inexistente");
		}
		return response;
	}

	@Override
	public Iterator<Book> iterator() {
		return alBooks.iterator();
	}
	
	

}

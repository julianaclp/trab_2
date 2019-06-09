package library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

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
	
	public JSONObject searchBook(int id) {
		JSONObject response = new JSONObject();
		for(Book b : alBooks) {
			if(b.getId() == id) {
				try {
			        response.put("exists", true);
			        response.put("book", b);
			    } catch (JSONException e) {}  
			};
		}
		if(response.length() == 0) {
			try {
				response.put("exists", new Boolean(false));
				response.put("message", "Livro inexistente");
			} catch (JSONException e) {}
		}
		return response;
	}

	@Override
	public Iterator<Book> iterator() {
		return alBooks.iterator();
	}
	
	

}

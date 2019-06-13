package library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class Library implements Iterable<Book> {
	private ArrayList<Book> alBooks = null;
	private static Library myLibr;
	private int lastId;
	
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
	
	public void orderByAZ() {
		Collections.sort(alBooks);
	}
	
	public void orderByZA() {
		Collections.sort(alBooks, Collections.reverseOrder());
	}
	
	/*public int idGenerator() {
		if(alBooks.size() == 0) return 1;
		this.orderById();
		return this.getBook(alBooks.size() - 1).getId() + 1;
	}*/
	
	private int idGenerator() {
		if(alBooks.size() == 0) lastId = 1;
		else lastId++;
		return lastId;
	}
	
	public String printLibrary() {
		String lib = "";
		for (Book b : alBooks) {
			lib += b + "\n";
		}
		return lib;
	}
	
	public String listByYear(int year) {
		String result = "";
		for (Book b : alBooks) {
			if(b.getReleaseYear() == year) result += b;
		}
		if(result.length() == 0) result = "Não há livros lançados nesse ano.";
		return result;
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
	
	public void removeBook(Book b) {
		alBooks.remove(b);
	}
	
	public int getSize() {
		return alBooks.size();
	}
	
	public int getAtHomeBooks() {
		int n = 0;
		for (Book b : alBooks) {
			if(b.getStatus() == Book.AVAILABLE) n++;
		}
		return n;
	}
	
	public int getBorrowedBooksNumber() {
		int n = 0;
		for (Book b : alBooks) {
			if(b.getStatus() == Book.BORROWED) n++;
		}
		return n;
	}
	
	public float getLibraryPrice() {
		float n = 0;
		for(Book b : alBooks) n += b.getPrice();
		return n;
	}
	
	public int getAcademicNumber() {
		int n = 0;
		for(Book b : alBooks) {
			if(b.getType() == Book.Type.ACADEMIC) n++;
		}
		return n;
	}
	
	public int getLiteraryNumber() {
		int n = 0;
		for(Book b : alBooks) {
			if(b.getType() == Book.Type.LITERARY) n++;
		}
		return n;
	}
	
	public String getBorrowedBooks() {
		String result = "";
		for(Book b : alBooks) {
			if(b.getStatus() == Book.BORROWED) result += b + "\n";
		}
		if(result.length() == 0) result = "Não há livros emprestados";
		return result;
	}
	
	public String getBooksByType() {
		String result = "ACADÊMICOS: \n";
		if(getAcademicNumber() == 0) result += "Não há livros do tipo acadêmico.\n";
		else {
			for(Book b : alBooks) {
				if(b.getType() == Book.Type.ACADEMIC) result += b + "\n";
			}
		}	
		result += "\nLITERÁRIOS: \n";
		if(getLiteraryNumber() == 0) result += "Não há livros do tipo literário.\n";
		else {
			for(Book b : alBooks) {
				if(b.getType() == Book.Type.LITERARY) result += b + "\n";
			}	
		}
		return result;
	}
	
	public String getStats() {
		String stats = "ESTATÍSTICAS \n";
		stats += "Quantidade total de livros: " + getSize() + "\n";
		stats += "Quantidade de livros em casa: " + getAtHomeBooks() + "\n";
		stats += "Quantidade de livros emprestados :" + getBorrowedBooksNumber() + "\n";
		stats += "Quantidade de livros por categoria \n";
		stats += "Literários: " + getLiteraryNumber() + "\n";
		stats += "Acadêmicos: " + getAcademicNumber() + "\n";
		stats += "Valor investido na biblioteca: R$" + String.format("%.2f", getLibraryPrice()) + "\n";
		return stats;
	}
	

	@Override
	public Iterator<Book> iterator() {
		return alBooks.iterator();
	}
	
	

}

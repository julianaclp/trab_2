package library;

public class Book implements Comparable<Book> {
	public enum Type {
		ACADEMIC,
		LITERARY;
	}
	private int id;
	private String title;
	private int releaseYear;
	private int acquisitionYear;
	private float price;
	private String author;
	private boolean status;
	final static boolean BORROWED = true;
	final static boolean AVAILABLE = false;
	Type type;
	
	public Book(String title, Type type) {
		super();
		this.title = title;
		this.type = type;
	}
	
	public Book(String title, String author, Type type, int releaseYear, int acquisitionYear, float price) {
		this.title = title;
		this.author = author;
		this.releaseYear = releaseYear;
		this.acquisitionYear = acquisitionYear;
		this.price = price;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getAcquisitionYear() {
		return acquisitionYear;
	}

	public void setAcquisitionYear(int acquisitionYear) {
		this.acquisitionYear = acquisitionYear;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public String getStringType() {
		if(type == Type.ACADEMIC) return "Acad�mico";
		return "Liter�rio";
	}
	
	public Type getType() {
		return type;
	}
	
	/*public int compareTo(Book b) {
		Integer id1 = (Integer) this.id;
		Integer id2 = (Integer) b.id;
		return id1.compareTo(id2);
	}*/
	
	public int compareTo(Book b) {
		return this.title.compareToIgnoreCase(b.title);
	}
	
	public String toString() {
		return title + " - " + author + " (ID = " + id + ")";
	}
	
	public String getFullInfo() {
		return title + " - " + author + " - " + releaseYear + " - " + acquisitionYear + " - " + price + "\n";
	}
	

}

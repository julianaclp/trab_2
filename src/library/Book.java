package library;

public class Book implements Comparable<Book> {
	private int id;
	private String title;
	private int releaseYear;
	private int acquisitionYear;
	private float price;
	private String author;
	private boolean status;
	final static boolean BORROWED = true;
	final static boolean AVAILABLE = false;
	
	public Book(String title) {
		super();
		this.title = title;
	}
	
	public Book(String title, String author, int releaseYear, int acquisitionYear, float price) {
		this.title = title;
		this.author = author;
		this.releaseYear = releaseYear;
		this.acquisitionYear = acquisitionYear;
		this.price = price;
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
	
	public int compareTo(Book b) {
		Integer id1 = (Integer) this.id;
		Integer id2 = (Integer) b.id;
		return id1.compareTo(id2);
	}
	
	public String toString() {
		return title + " - " + author;
	}
	
	public String getFullInfo() {
		return title + " - " + author + " - " + releaseYear + " - " + acquisitionYear + " - " + price + "\n";
	}
	

}

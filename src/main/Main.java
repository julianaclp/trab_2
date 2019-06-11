package main;
import java.util.HashMap;
import java.util.Scanner;

import library.*;

public class Main {

	private static Scanner k = new Scanner(System.in);
	private static Common c = new Common();
	
	public static void main(String[] args) {
		int opt;
		Library lib = Library.getInstance();
		lib.addBook(new Book("Pet Sematary"));
		lib.addBook(new Book("Good Omens"));
		lib.addBook(new Book("The Shining"));
		Loan loans = Loan.getInstance();
		do {
			menu();
			System.out.print("\nOpcao: ");
			opt = k.nextInt();
			switch(opt) {
			case 1:
				System.out.println("Adicionar livro");
				addBook(lib);
				pressToContinue();
				break;
			case 2:
				System.out.println("Emprestar");
				borrowBook(lib, loans);
				pressToContinue();
				break;
			case 3:
				System.out.println("Devolver");
				returnBook(lib, loans);
				pressToContinue();
				break;
			case 4:
				System.out.println("Listar");
				listBooks(lib);
				pressToContinue();
				break;
			case 5:
				System.out.println("Excluir");
				pressToContinue();
				break;
			case 6:
				System.out.println("Estatísticas");
				statBooks(lib);
				pressToContinue();
				break;
			case 7:
				System.exit(0);
			default:
				System.out.println("Opção inexistente.");
				pressToContinue();
				break;
			}
		} while(opt != 7);
		

	}
	
	public static void menu() {
		System.out.println("-----Menu-----");
		System.out.println("1 - Adicionar livro");
		System.out.println("2 - Emprestar");
		System.out.println("3 - Devolver");
		System.out.println("4 - Listar");
		System.out.println("5 - Excluir livro");
		System.out.println("6 - Estatísticas");
		System.out.println("7 - Sair");
	}
	
	public static void pressToContinue() {
		System.out.println("Pressione enter para voltar ao menu");
		k.nextLine();
		k.nextLine();
	}
	
	public static void addBook(Library l) {
		Common c = new Common();
		Scanner k = new Scanner(System.in);
		Book b;
		String title = null;
		String author = null;
		int releaseYear;
		int acquisitionYear;
		float price;
		while(true) {
			try {
				System.out.println("Digite o título do livro");
				title = c.isStringValid(k.nextLine());
				break;
			} catch (Exception e) {
				System.out.println("Digite um título válido");
				continue;
			}
		}
		b = new Book(title);
		while(true) {
			try {
				System.out.println("Digite o autor do livro");
				title = c.isStringValid(k.nextLine());
				break;
			} catch (Exception e) {
				System.out.println("Digite um autor válido");
				continue;
			}
		}
		b.setAuthor(author);
		while(true) {
			try {
				System.out.println("Digite o ano de lançamento do livro");
				releaseYear = k.nextInt();
				break;
			} catch (Exception e) {
				System.out.println("Digite um número válido");
				k.next();
				continue;
			}
		}
		b.setReleaseYear(releaseYear);
		while(true) {
			try {
				System.out.println("Digite o ano de aquisição do livro");
				acquisitionYear = k.nextInt();
				break;
			} catch (Exception e) {
				System.out.println("Digite um número válido");
				k.next();
				continue;
			}
		}
		b.setAcquisitionYear(acquisitionYear);
		while(true) {
			try {
				System.out.println("Digite o valor de aquisição do livro");
				price = k.nextFloat();
				break;
			} catch (Exception e) {
				System.out.println("Digite um número válido");
				k.next();
				continue;
			}
		}
		b.setPrice(price);
		l.addBook(b);
	}

	public static void borrowBook(Library lib, Loan l) {
		Scanner k = new Scanner(System.in);
		int bookId;
		Book book;
		String person;
		LoanEntry entry;
		HashMap<String, Object> result;
		while(true) {
			try {
				System.out.println("Digite o nome da pessoa que está emprestando o livro");
				person = c.isStringValid(k.nextLine());
				break;
			} catch (Exception e) {
				System.out.println("Digite um nome válido");
				continue;
			}
		}
		while(true) {
			try {
				System.out.println("Digite a ID do livro que deseja emprestar: ");
				bookId = k.nextInt();
				result = lib.searchBook(bookId);
				if((boolean) result.get("exists") == false) {
					System.out.println(result.get("message"));
					continue;
				}
				book = (Book) result.get("book");
				entry = new LoanEntry(book);
				result = entry.borrowBook(person);
				if((boolean) result.get("success") == false) {
					System.out.println(result.get("message"));
					continue;
				}
				else System.out.println(result.get("message"));
				l.newEntry(new LoanEntry(book));
				break;
			} catch (Exception e) {
				System.out.println("Digite um número inteiro");
				k.next();
				continue;
			}
		}
	}
	
	public static void returnBook(Library lib, Loan l) {
		int bookId;
		HashMap<String, Object> result;
		Book book;
		LoanEntry entry;
		while(true) {
			try {
				System.out.println("Digite a ID do livro que deseja devolver: ");
				bookId = k.nextInt();
				result = lib.searchBook(bookId);
				if((boolean) result.get("exists") == false) {
					System.out.println(result.get("message"));
					continue;
				}
				book = (Book) result.get("book");
				result = l.getActiveEntry(book);
				if((boolean) result.get("success") == false) {
					System.out.println(result.get("data"));
					break;
				}
				entry = (LoanEntry) result.get("data");
				result = entry.returnBook();
				if((boolean) result.get("success") == false) {
					System.out.println(result.get("message"));
					continue;
				}
				System.out.println(result.get("message"));
				break;
			} catch (Exception e) {
				System.out.println("Digite um número inteiro");
				k.next();
				continue;
			}
		}
	}

	public static void listBooks(Library lib) {
		System.out.println("1 - Listar livros de um determinado ano");
		System.out.println("2 - Listar livros em ordem alfabética");
		System.out.println("3 - Listar livros que estão emprestados");
		System.out.println("4 - Listar livros por tipo");
	}
	
	public static void statBooks(Library lib) {
		System.out.println("ESTATÍSTICAS");
		System.out.println("Quantidade total de livros:");
		System.out.println(lib.getSize());
		System.out.println("Quantidade de livros em casa:");
		System.out.println(lib.getAtHomeBooks());
		System.out.println("Quantidade de livros por categoria");
		System.out.println("Literários: ");
		System.out.println("Acadêmicos: ");
		System.out.println("Quantidade de livros emprestados:");
		System.out.println(lib.getBorrowedBooks());
		System.out.println("Valor investido na biblioteca:");
		System.out.println(lib.getLibraryPrice());
	}
}

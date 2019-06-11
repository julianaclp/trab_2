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
				pressToContinue();
				break;
			case 5:
				System.out.println("Excluir");
				pressToContinue();
				break;
			case 6:
				System.out.println("Estat�sticas");
				pressToContinue();
				break;
			case 7:
				System.exit(0);
			default:
				System.out.println("Op��o inexistente.");
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
		System.out.println("6 - Estat�sticas");
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
				System.out.println("Digite o t�tulo do livro");
				title = c.isStringValid(k.nextLine());
				break;
			} catch (Exception e) {
				System.out.println("Digite um t�tulo v�lido");
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
				System.out.println("Digite um autor v�lido");
				continue;
			}
		}
		b.setAuthor(author);
		while(true) {
			try {
				System.out.println("Digite o ano de lan�amento do livro");
				releaseYear = k.nextInt();
				break;
			} catch (Exception e) {
				System.out.println("Digite um n�mero v�lido");
				k.next();
				continue;
			}
		}
		b.setReleaseYear(releaseYear);
		while(true) {
			try {
				System.out.println("Digite o ano de aquisi��o do livro");
				acquisitionYear = k.nextInt();
				break;
			} catch (Exception e) {
				System.out.println("Digite um n�mero v�lido");
				k.next();
				continue;
			}
		}
		b.setAcquisitionYear(acquisitionYear);
		while(true) {
			try {
				System.out.println("Digite o valor de aquisi��o do livro");
				price = k.nextFloat();
				break;
			} catch (Exception e) {
				System.out.println("Digite um n�mero v�lido");
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
				System.out.println("Digite o nome da pessoa que est� emprestando o livro");
				person = c.isStringValid(k.nextLine());
				break;
			} catch (Exception e) {
				System.out.println("Digite um nome v�lido");
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
				System.out.println("Digite um n�mero inteiro");
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
				System.out.println("Digite um n�mero inteiro");
				k.next();
				continue;
			}
		}
	}

}

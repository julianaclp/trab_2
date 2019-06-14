package main;
import java.util.HashMap;
import java.util.Scanner;

import library.*;

public class Main implements Helper {

	private static Scanner k = new Scanner(System.in);
	
	public static void main(String[] args) { 
		int opt; 
		Library lib = Library.getInstance();
		lib.addBook(new Book("Pet Sematary",
				"Stephen King",
				Book.Type.LITERARY,
				1983,
				2018,
				(float) 25.50));
		lib.addBook(new Book("Good Omens",
				"Neil Gaiman", 
				Book.Type.ACADEMIC,
				1990,
				2019, 
				(float) 30));
		lib.addBook(new Book("The Shining",
				"Stephen King",
				Book.Type.LITERARY,
				1977,
				2010, 
				(float) 31.50));
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
				listBooks(lib, loans);
				pressToContinue();
				break;
			case 5:
				System.out.println("Excluir");
				removeBook(lib);
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
		Scanner k = new Scanner(System.in);
		Book b;
		int type; 
		while(true) {
			type = Helper.isNumberValidLoop(k, "Digite o tipo do livro: \n 1 - Acadêmico \n 2 - Literário");
			if(type == 1 || type == 2) break;
			continue;
		}
		k.nextLine();
		b = new Book(Helper.isStringValidLoop(k, "Digite o título do livro"), type == 1 ? Book.Type.ACADEMIC : Book.Type.LITERARY);
		b.setAuthor(Helper.isStringValidLoop(k, "Digite o autor do livro"));
		b.setReleaseYear(Helper.isNumberValidLoop(k, "Digite o ano de lançamento do livro:"));
		b.setAcquisitionYear(Helper.isNumberValidLoop(k, "Digite o ano de aquisição do livro:"));
		b.setPrice(Helper.isFloatValidLoop(k, "Digite o valor de aquisição do livro"));
		l.addBook(b);
		System.out.println(l.printLibrary());;
	}

	public static void borrowBook(Library lib, Loan l) {
		Scanner k = new Scanner(System.in);
		String person;
		LoanEntry entry;
		HashMap<String, Object> result;
		person = Helper.isStringValidLoop(k, "Digite o nome da pessoa que está emprestando o livro");
		result = lib.searchBook(Helper.isNumberValidLoop(k, "Digite a ID do livro que deseja emprestar: "));
		if((boolean) result.get("exists") == false) {
			System.out.println(result.get("message"));
			return;
		} 
		entry = new LoanEntry((Book) result.get("book"));
		result = entry.borrowBook(person);
		if((boolean) result.get("success") == false) {
			System.out.println(result.get("message"));
			return;
		}
		entry.setDate(Helper.isDateValidLoop(k, "DATA DE EMPRÉSTIMO"), LoanEntry.BORROW);
		System.out.println(result.get("message"));
		l.newEntry(entry);
		return;
	}
	
	public static void returnBook(Library lib, Loan l) {
		HashMap<String, Object> result;
		LoanEntry entry;
		result = lib.searchBook(Helper.isNumberValidLoop(k, "Digite a ID do livro que deseja devolver: "));
		if((boolean) result.get("exists") == false) {
			System.out.println(result.get("message"));
			return;
		}
		result = l.getActiveEntry((Book) result.get("book"));
		if((boolean) result.get("success") == false) {
			System.out.println(result.get("data"));
			return;
		}
		entry = (LoanEntry) result.get("data");
		result = entry.returnBook();
		if((boolean) result.get("success") == false) {
			System.out.println(result.get("message"));
			return;
		}
		System.out.println(result.get("message"));
		return;
	}

	public static void listBooks(Library lib, Loan l) {
		Scanner k = new Scanner(System.in);
		int opt = 0;
		System.out.println("1 - Listar livros de um determinado ano");
		System.out.println("2 - Listar livros em ordem A-Z");
		System.out.println("3 - Listar livros em ordem Z-A");
		System.out.println("4 - Listar livros que estão emprestados");
		System.out.println("5 - Listar livros por tipo");
		System.out.println("6 - Listar histórico de empréstimos");
		do {
			try {
				opt = k.nextInt();
				switch(opt) {
				case 1:
					int year = Helper.isNumberValidLoop(k, "Digite o ano de lançamento do livro");
					System.out.println(lib.listByYear(year));
					break;
				case 2:
					lib.orderByAZ();
					System.out.println(lib.printLibrary());
					break;
				case 3:
					lib.orderByZA();
					System.out.println(lib.printLibrary());
				case 4:
					System.out.println(lib.getBorrowedBooks());
					break;
				case 5:
					System.out.println(lib.getBooksByType());
					break;
				case 6:
					k.nextLine();
					int id = Helper.isNumberValidLoop(k, "Digite a ID do livro que deseja consultar: ");
					HashMap<String, Object> result = lib.searchBook(id);
					if((boolean) result.get("exists") == false) {
						System.out.println(result.get("message"));
						return;
					}
					System.out.println(l.getHistory((Book) result.get("book")));
					break;
				}
				break;
			} catch (Exception e) {
				System.out.println("Digite um número válido");
				k.next();
				continue;
			}
		} while(opt < 1 || opt > 5);
	}
	
	public static void statBooks(Library lib) {
		System.out.println(lib.getStats());
	}

	public static void removeBook(Library lib) {
		HashMap<String, Object> result = lib.searchBook(Helper.isNumberValidLoop(k, "Digite a ID do livro que deseja excluir: "));
		if((boolean) result.get("exists") == false) {
			System.out.println("Livro inexistente. Não foi possível excluir.");
			return;
		}
		lib.removeBook((Book) result.get("book"));
		System.out.println(lib.printLibrary());
	}
}

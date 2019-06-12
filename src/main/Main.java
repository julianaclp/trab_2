package main;
import java.util.HashMap;
import java.util.Scanner;

import library.*;

public class Main {

	private static Scanner k = new Scanner(System.in);
	
	public static void main(String[] args) {
		int opt;
		Library lib = Library.getInstance();
		lib.addBook(new Book("Pet Sematary",
				"Stephen King",
				1983,
				2018,
				(float) 25.50));
		lib.addBook(new Book("Good Omens",
				"Neil Gaiman", 
				1990,
				2019, 
				(float) 30));
		lib.addBook(new Book("The Shining",
				"Stephen King",
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
		Scanner k = new Scanner(System.in);
		Book b;
		int type; 
		while(true) {
			type = Common.isNumberValidLoop(k, "Digite o tipo do livro: \n 1 - Acadêmico \n 2 - Literário");
			if(type == 1 || type == 2) break;
			continue;
		}
		k.nextLine();
		b = new Book(Common.isStringValidLoop(k, "Digite o título do livro"), type == 1 ? Book.Type.ACADEMIC : Book.Type.LITERARY);
		b.setAuthor(Common.isStringValidLoop(k, "Digite o autor do livro"));
		b.setReleaseYear(Common.isNumberValidLoop(k, "Digite o ano de lançamento do livro:"));
		b.setAcquisitionYear(Common.isNumberValidLoop(k, "Digite o ano de aquisição do livro:"));
		b.setPrice(Common.isFloatValidLoop(k, "Digite o valor de aquisição do livro"));
		l.addBook(b);
	}

	public static void borrowBook(Library lib, Loan l) {
		Scanner k = new Scanner(System.in);
		String person;
		LoanEntry entry;
		HashMap<String, Object> result;
		person = Common.isStringValidLoop(k, "Digite o nome da pessoa que está emprestando o livro");
		result = lib.searchBook(Common.isNumberValidLoop(k, "Digite a ID do livro que deseja emprestar: "));
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
		System.out.println(result.get("message"));
		l.newEntry(entry);
		return;
	}
	
	public static void returnBook(Library lib, Loan l) {
		HashMap<String, Object> result;
		LoanEntry entry;
		result = lib.searchBook(Common.isNumberValidLoop(k, "Digite a ID do livro que deseja devolver: "));
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

	public static void listBooks(Library lib) {
		Scanner k = new Scanner(System.in);
		int opt = 0;
		System.out.println("1 - Listar livros de um determinado ano");
		System.out.println("2 - Listar livros em ordem alfabética");
		System.out.println("3 - Listar livros que estão emprestados");
		System.out.println("4 - Listar livros por tipo");
		do {
			try {
				opt = k.nextInt();
				switch(opt) {
				case 1:
					int year = Common.isNumberValidLoop(k, "Digite o ano de lançamento do livro");
					System.out.println(lib.listByYear(year));
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				}
				break;
			} catch (Exception e) {
				System.out.println("Digite um número válido");
				k.next();
				continue;
			}
		} while(opt < 1 || opt > 4);
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

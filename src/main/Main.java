package main;
import java.util.Scanner;

import library.*;

public class Main {

	private static Scanner k = new Scanner(System.in);
	
	public static void main(String[] args) {
		int opt;
		Library lib = Library.getInstance();
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
				pressToContinue();
				break;
			case 3:
				System.out.println("Devolver");
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
				System.out.println("Estatísticas");
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

}

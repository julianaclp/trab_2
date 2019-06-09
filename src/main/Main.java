package main;
import java.util.Scanner;

import library.*;

public class Main {

	private static Scanner k = new Scanner(System.in);
	
	public static void main(String[] args) {
		int opt;
		do {
			menu();
			System.out.print("\nOpcao: ");
			opt = k.nextInt();
			switch(opt) {
			case 1:
				System.out.println("Adicionar livro");
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

}

package main;

import java.time.DateTimeException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public interface Helper {
	public static String isStringValid(String s) 
	//método que cria uma nova exceção caso o usuário queira entrar com alguma string vazia
	//Como, por exemplo, o nome de uma pessoa ou o título de um livro
    { 
        if (s == null || s.length() == 0) 
            throw new IllegalArgumentException("The argument cannot be null"); 
        return s;
    } 
	
	public static int isNumberValidLoop(Scanner k, String message) {
		//Verifica se o número digitado é um inteiro válido
		//E permanece em um loop enquanto não for
		int n;
		while(true) {
			try {
				System.out.println(message);
				n = k.nextInt();
				break;
			} catch (Exception e) {
				System.out.println("Digite um número válido.");
				k.next();
				continue;
			}
		}
		return n;
	}
	
	public static float isFloatValidLoop(Scanner k, String message) {
		//Verifica se o número digitado é um float válido
		//E permanece em um loop enquanto não for
		float n;
		while(true) {
			try {
				System.out.println(message);
				n = k.nextFloat();
				break;
			} catch (Exception e) {
				System.out.println("Digite um número válido.");
				k.next();
				continue;
			}
		}
		return n;
	}
	
	public static String isStringValidLoop(Scanner k, String message) {
		//Verifica se a string digitada é válida (de acordo com a exceção definida em isStringValid() )
		//E permanece em um loop enquanto não for
		String result = "";
		while(true) {
			try {
				System.out.println(message);
				result = isStringValid(k.nextLine());
				break;
			} catch (Exception e) {
				System.out.println("Digite um título válido");
				continue;
			}
		}
		return result;
	}
	
	public static GregorianCalendar isDateValidLoop(Scanner k, String message) {
		//Verifica se a data digitada é válida
		//E permanece em um loop se não for
		GregorianCalendar date; 
		int day;
		int month;
		int year;
		while(true) {
			try {
				System.out.println(message);
				day = isNumberValidLoop(k, "Digite o dia: ");
				month = isNumberValidLoop(k, "Digite o mês: ");
				year = isNumberValidLoop(k, "Digite o ano: ");
				isDateValid(day, month, year);
				date = new GregorianCalendar(year, month - 1, day);
				break;
			} catch(DateTimeException e) {
				System.out.println("Data inválida. Verifique os dados e tente novamente");
				continue;
			}
		}
		k.nextLine();
		return date;
	}
	
	public static boolean isDateValid(int day, int month, int year) {
		//Verifica se uma data é válida e joga uma exceção do tipo DateTimeException se não for
		GregorianCalendar data = new GregorianCalendar();
		GregorianCalendar today = new GregorianCalendar();
		if(month > 12) throw new DateTimeException("Data inválida.");
		data.set(Calendar.MONTH, month - 1);
		if(day > data.getActualMaximum(Calendar.DAY_OF_MONTH)) throw new DateTimeException("Data inválida.");
		data.set(Calendar.DAY_OF_MONTH, day);
		data.set(Calendar.YEAR, year);
		if(data.getTimeInMillis() > today.getTimeInMillis()) throw new DateTimeException("Data inválida.");
		return true;
	}

}

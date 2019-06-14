package main;

import java.time.DateTimeException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public interface Helper {
	public static String isStringValid(String s) 
	//m�todo que cria uma nova exce��o caso o usu�rio queira entrar com alguma string vazia
	//Como, por exemplo, o nome de uma pessoa ou o t�tulo de um livro
    { 
        if (s == null || s.length() == 0) 
            throw new IllegalArgumentException("The argument cannot be null"); 
        return s;
    } 
	
	public static int isNumberValidLoop(Scanner k, String message) {
		//Verifica se o n�mero digitado � um inteiro v�lido
		//E permanece em um loop enquanto n�o for
		int n;
		while(true) {
			try {
				System.out.println(message);
				n = k.nextInt();
				break;
			} catch (Exception e) {
				System.out.println("Digite um n�mero v�lido.");
				k.next();
				continue;
			}
		}
		return n;
	}
	
	public static float isFloatValidLoop(Scanner k, String message) {
		//Verifica se o n�mero digitado � um float v�lido
		//E permanece em um loop enquanto n�o for
		float n;
		while(true) {
			try {
				System.out.println(message);
				n = k.nextFloat();
				break;
			} catch (Exception e) {
				System.out.println("Digite um n�mero v�lido.");
				k.next();
				continue;
			}
		}
		return n;
	}
	
	public static String isStringValidLoop(Scanner k, String message) {
		//Verifica se a string digitada � v�lida (de acordo com a exce��o definida em isStringValid() )
		//E permanece em um loop enquanto n�o for
		String result = "";
		while(true) {
			try {
				System.out.println(message);
				result = isStringValid(k.nextLine());
				break;
			} catch (Exception e) {
				System.out.println("Digite um t�tulo v�lido");
				continue;
			}
		}
		return result;
	}
	
	public static GregorianCalendar isDateValidLoop(Scanner k, String message) {
		//Verifica se a data digitada � v�lida
		//E permanece em um loop se n�o for
		GregorianCalendar date; 
		int day;
		int month;
		int year;
		while(true) {
			try {
				System.out.println(message);
				day = isNumberValidLoop(k, "Digite o dia: ");
				month = isNumberValidLoop(k, "Digite o m�s: ");
				year = isNumberValidLoop(k, "Digite o ano: ");
				isDateValid(day, month, year);
				date = new GregorianCalendar(year, month - 1, day);
				break;
			} catch(DateTimeException e) {
				System.out.println("Data inv�lida. Verifique os dados e tente novamente");
				continue;
			}
		}
		k.nextLine();
		return date;
	}
	
	public static boolean isDateValid(int day, int month, int year) {
		//Verifica se uma data � v�lida e joga uma exce��o do tipo DateTimeException se n�o for
		GregorianCalendar data = new GregorianCalendar();
		GregorianCalendar today = new GregorianCalendar();
		if(month > 12) throw new DateTimeException("Data inv�lida.");
		data.set(Calendar.MONTH, month - 1);
		if(day > data.getActualMaximum(Calendar.DAY_OF_MONTH)) throw new DateTimeException("Data inv�lida.");
		data.set(Calendar.DAY_OF_MONTH, day);
		data.set(Calendar.YEAR, year);
		if(data.getTimeInMillis() > today.getTimeInMillis()) throw new DateTimeException("Data inv�lida.");
		return true;
	}

}

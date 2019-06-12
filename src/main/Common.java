package main;

import java.util.Scanner;

public class Common {
	public static String isStringValid(String s) 
    { 
        if (s == null || s.length() == 0) 
            throw new IllegalArgumentException("The argument cannot be null"); 
        return s;
    } 
	
	public static int isNumberValidLoop(Scanner k, String message) {
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

}

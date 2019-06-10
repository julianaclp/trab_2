package main;

public class Common {
	public String isStringValid(String s) 
    { 
        if (s == null || s.length() == 0) 
            throw new IllegalArgumentException("The argument cannot be null"); 
        return s;
    } 

}

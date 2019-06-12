package library;

public class TestLibrary {

	public enum Currency {
        PENNY(1), NICKLE(5), DIME(10), QUARTER(25);
        private int value;

        private Currency(int value) {
                this.value = value;
        }
        
        public int getPrice() {
        	return value;
        }
	}

  
    // Driver method 
    public static void main(String[] args) 
    { 
    	
        System.out.println(Currency.NICKLE.getPrice()); 
    } 

}

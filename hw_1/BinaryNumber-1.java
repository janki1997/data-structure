// janki patel
//CWID - 10457365
//Hw1- CS_570A

package binarynumber;

public class BinaryNumber {
	
	private int data[];
	private boolean overflow;
	
	
	//a binary number of length.	
	
	public BinaryNumber(int length) 
	{
		if (length < 0){
			throw new IllegalArgumentException("enter a proper number");
			}
		data = new int[length];
		for(int i=0;i<length;i++) {
			data[i]=0;
		}
	}
	
	
	// a binary number given a string.
	
	public BinaryNumber(String str)
	{
		int k,a;
		int length = str.length();
		data = new int[length];
		for(k=0; k<length; k++) 
		{
			char b = str.charAt(k);
			a = Character.getNumericValue(b) ;
			if(a == 0 || a== 1 ) {
			data[k] = a;
			}
			else {
				System.out.println("enter a proper binary number");
			}
		}
	}
	
	//length 

	 public  int getLength()
	 {
       int a = data.length;
		 return a;
	 } 
	
	 
	 // get digit from index value
	 
	 public int getDigit(int index)
	 {
			if(index > data.length) {
				System.out.println("enter proper index");
				System.exit(1);
				return 0;
	 		}
			else {
			return data[index];
			}
	}
	 
	 
	 // right shift operation
	 
	 public void shiftR(int amount) 
	 {
		 if (amount < 0) {
				throw new IllegalArgumentException("error");
			}
		 BinaryNumber Shift = new BinaryNumber(data.length + amount);
			for (int i = amount; i < Shift.getLength(); i++) {
				Shift.data[i] = data[i - amount];
			}
			this.data = Shift.data;
			System.out.println("new number is: "+ this.toString());
		 }
	 
	 
	 // for converting a binary number to a decimal number
	 
	 public int toDecimal() 
	 {
			int v1 = 0;
			for (int i = 0; i < data.length; i ++) {
				v1 = v1 * 2 + data[i];
			}
			return v1;
		}
	 
	 
	 // accumulation 
	 
	 public void add(BinaryNumber aBinaryNumber)
	 {
			if (aBinaryNumber.getLength() != data.length) {
				System.out.println("length is not same");
			}
			else {
				
	        int b = 0;
			int total[] = new int[data.length];
			for (int i = 0;  i < data.length ; i ++) 
			{
				int temp = data[i] + aBinaryNumber.getDigit(i);
			
				if (temp == 0 && b==0) {
					b = 0;
					total[i] = 0;
					}
				
				else if ((temp == 1 && b==0)||(temp == 0 && b==1)) {
					b=0;
					total[i] = 1;
				}
				
				else if ((temp == 2 && b==0)||(temp == 1 && b==1)) {
					b=1;
					total[i] = 0;
				}
				
				else if ((temp == 3 && b==0)||(temp == 2 && b==1)) {
					b=1;
					total[i] = 1;
				}
			}
			data = total;
			
			if (b == 1) 
			{
				overflow = true;
			}
			System.out.println("sum= " + toString());
			}
		}
	 
	 
     public void clearOverﬂow() 
     {
    	 overflow = false;
       }
     
     
     // for converting a binary number to a string
     
     public String toString() 
     {
 		if (overflow == true) {
 			return "Overflow";
 		} 
 		else {
 			String binaryString = "";
 			for (int i = 0; i < data.length; i++) {
 				binaryString = binaryString + data[i];
 			}
 			return binaryString;
 		}
 	}
     
     
     public static void main(String[] args) 
     {
    	BinaryNumber num = new BinaryNumber(4);
 		BinaryNumber d1 = new BinaryNumber("10110");
 		BinaryNumber d2 = new BinaryNumber("0101");
 		BinaryNumber d3 = new BinaryNumber("11101");
 		System.out.println("number= "+num);
 		System.out.println("length of string= "  + d2.getLength());
 		System.out.println("digit at given index=" + d2.getDigit(3));
 		d2.shiftR(5);
 		d1.add(d3);
 		System.out.println("after conversion new number=" + d2.toDecimal());
 	}
}

	 


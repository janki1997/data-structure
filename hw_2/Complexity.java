package test;

public class Complexity {

	 public static void method1(int n) {
			int counter = 0;
			for (int i = n; i >0; i--) {
			       for (int j = n; j >0; j--) {
					System.out.println("Operation " + counter);
					counter++;
					}
				}
			System.out.println("Time Complexity  of method 1 is : " + counter);
		 }
	 
	
	 public static void method2(int n) {
		 int counter = 0;
		 for (int i = n; i >0; i--) {
			for (int j = n; j >0; j--) {
					for (int k = n; k > 0; k--) {
						System.out.println("Operation " + counter);
						counter++;
					}
				}
		 	}
		 System.out.println("Time Complexity  of method 2 is : " + counter);
	 }
	 
	 public static void method3(int n) {
		 int counter = 0;
		 for (int i = (n-1); i > 0; i /= 2) {
			 System.out.println("Operation " + counter);
			 counter++;
		 }
		 System.out.println("Time Complexity  of method 3 is " + counter);
	 }
	 

	 public static void method4(int n) {
		 int counter = 0;
		 for (int i = n; i >=1; i--) {
			    for(int j = 1; j < n; j = j * 2) {
			    	System.out.println("Operation " + counter);
			    	counter++;
			    }
		}
		 System.out.println("Time Complexity  of method 4 is " + counter);
	 
	 }

	 public static void method5(int n) {
	 int counter = 0;
	 for (int j = 2; j < n; j = j * j) {
		 System.out.println("Operation " + counter);
		 counter++;
	}
	 System.out.println("Time Complexity  of method 5 is  " + counter);
	 
	 }

public static void main(String[] args) {
		 
		 System.out.println("method 1 :" );
		  Complexity.method1(3);
		 System.out.println("method 2 :");
		 Complexity.method2(3);
		 System.out.println("method 3 :");
		 Complexity.method3(16);
		 System.out.println("method 4 :");
		 Complexity.method4(16);
		 System.out.println("method 5 :");
		 Complexity.method5(16);
	 }	 

}

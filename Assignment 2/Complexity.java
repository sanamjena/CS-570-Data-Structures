import java.math.*;

public class Complexity {
	static int ctr = 0;

	public void method0(int n) {
		int counter = 0;
		for (int i=0;i<n;i++) {
			System.out.println("Operation "+counter);
			counter++;
		}
	}
	
	public void method1 (int n) {
		int counter = 0;
		for (int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.println("Operation "+counter);
				counter++;
			}
		}
	}
	
	public void method2 (int n) {
		int counter = 0;
		for (int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				for(int k=0;k<n;k++) {
					System.out.println("Operation "+counter);
					counter++;
				}
			}
		}
	}
	
	public void method3 (int n) {
		int counter = 0;
		for(int i=1;i<n;i=i*2) {
			System.out.println("Operation "+counter);
			counter++;
		}
	}
	
	public void method4 (int n) {
		int counter = 0;
		for(int i=1; i<=n;i++) {
			for(int j=1; j<n; j=j*2) {
				System.out.println("Operation "+counter);
				counter++;
			}
		}
	}
	public static void method5 (int num) {
		int pow = (int) Math.sqrt(Math.pow(2, num));
		System.out.println("Operation "+ctr);
		ctr++;
		num = num/2;
		if (num>1)
			method5(num);
		
		
	}
	
/* JUSTIFICATION FOR METHOD5
 * Time Complexity is considered as O(LogLogn) if the loop variables is reduced / 
 * increased exponentially by a constant amount.	
 */
	public static int method6(int n) {
		if(n<=1) {
			System.out.println("Operation "+ctr);
			ctr++;
			return n;
		}
		System.out.println("Operation "+ctr);
		ctr++;
		return method6(n-2) + method6(n-1);
	}

	public static void main(String[] args) {
		Complexity c = new Complexity();
		c.method0(5);
		c.method1(5);
		c.method2(2);
		c.method3(8);
		c.method4(8);
		c.method5(16);
		c.method6(5);
			
	}

}

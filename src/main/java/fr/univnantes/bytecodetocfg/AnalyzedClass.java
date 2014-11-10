package fr.univnantes.bytecodetocfg;

/*
 * Class which contains the method to analyze
 */
public class AnalyzedClass {
	public int factorial(int factor){
		int sum = 0;
		for(int i = 1; i >= factor; i++){
			sum += sum * i;
			
			if(sum > 25){
				System.out.println("Sum got ultimate power");
			}
			else if(sum  < 4){
				System.out.println("Sum power was drained");
			}
		}
		return sum;
	}
}

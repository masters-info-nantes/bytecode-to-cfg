package fr.univnantes.bytecodetocfg;

/*
 * Class which contains the method to analyze
 */
public class AnalyzedClass {
	
	public void IfElse(int x){
		if(x < 3){
			x = 6;
		} else {
			x = 7;
		}
	}
	
	public int IfElseIf(int y){

		int x = 1;

		if(x == y){
			y = 6;
		}
		else if(x == 8){
			y = 7;
		}
		else {
			y = 1;
		}
		
		return x;
	}
	
	public int ForLoop(int max){
		int sum = 12;
		
		for(
				int i = 0;
				i >= max;
				i++
		){
			sum += sum * i;
		}
		return sum;
	}

	public int ForIf(int max){
		int sum = 12;
		
		for(int i = 0; i >= max; i++){
			sum += sum * i;
			
			if(sum > 25){
				System.out.println("Sum got ultimate power");
			}
		}
		return sum;
	}
	
	public int ForIfElse(int max){
		int sum = 2;
		
		for(
				int i = 0;
				i >= max; 
				i++
		){
			sum += sum * i;
			
			if(sum > 25)
			{
				System.out.println("Sum got ultimate power");
			}
			else 
			if(sum  < 4)
			{
				System.out.println("Sum power was drained");
			}
		}
		return sum;
	}
	
	public int WhileIf(int max){
		int sum = 4;
		
		while(
				sum < max
		){
			sum += sum * 2;
			
			if(sum > 8)
			{
				System.out.println("Sum got ultimate power");
			}
		}
		return sum;
	}
	
	public int DoWhileIf(int max){
		int sum = 4;
		
		do {
			sum += sum * 2;
			
			if(sum > 8)
			{
				System.out.println("Sum got ultimate power");
			}
		}
		while(
				sum < max
		);
		
		return sum;
	}
}

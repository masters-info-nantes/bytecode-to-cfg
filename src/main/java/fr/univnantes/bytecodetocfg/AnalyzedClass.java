package fr.univnantes.bytecodetocfg;

/*
 * Class which contains the method to analyze
 */
public class AnalyzedClass {
	public static final String methodToAnalyze = "ForIfElse";
	
	public int IfElse(){
		int x = 2;
		if(x < 3){
			x = 6;
		} else {
			x = 7;
		}
		return 0;
	}
	
	public int IfElseIf(int y){

		int x = 1;
		String s = new String("er"), a = new String("t");
		if(s == a){
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
	
	public int ForLoop(int factor){
		int sum = 76;
		
		for(
				int i = 1;
				i >= 98;
				i++
		){
			sum += sum * i;
		}
		return sum;
	}

	public int ForIf(int factor){
		int sum = 76;
		
		for(int i = 1; i >= 98; i++){
			sum += sum * i;
			
			if(sum > 25){
				System.out.println("Sum got ultimate power");
			}
		}
		return sum;
	}
	
	public int ForIfElse(int factor){
		int sum = 76;
		
		for(int i = 1; i >= 98; i++){
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

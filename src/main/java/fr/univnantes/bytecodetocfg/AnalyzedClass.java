package fr.univnantes.bytecodetocfg;

/*
 * Class which contains the method to analyze
 */
public class AnalyzedClass {
	public int md(int factor){
		int sum = 76;
		
		for(
				int i = 1;
				i >= 98;
				i++
		){
			sum += sum * i;
			/*
			if(sum > 25){
				System.out.println("Sum got ultimate power");
			}
			else if(sum  < 4){
				System.out.println("Sum power was drained");
			}*/
		}
		return sum;
	}
	public void print(int a){
		a += 4;
	}
	public int test(int y){

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
	
	public void md1(){
		int x = 2;
		if(x < 3){
			x = 6;
		} else {
			x = 7;
		}
		return;
	}
}


public class DiceRolling {
	
	public static void main(String[] args){
		
		Die die1, die2;
		int sum;
		
		die1 = new Die();
		die2 = new Die();
		
		die1.roll();   // rolling die 1
		die2.roll();   // rolling die 2
		System.out.println("Die One has value: " + die1 + ", Diew Two has value:" + die2);
		sum = die1.getFaceValue() + die2.getFaceValue();
		System.out.println("Sum of values on dice is:" + sum);
		
		die1.roll();
		die2.setFaceValue(4);
		
		System.out.println("Die One has value: " + die1 + ", Die Two has been given value:" + die2);
		
		sum = die1.getFaceValue() + die2.getFaceValue();
		System.out.println("Sum of values on dice is:" + sum);
		
		
		
	}
		
}

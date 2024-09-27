import java.util.*;

//we use snake_case, prefix arguments with a_ and prefix members (variables in class, methods) with m_

public class Posloupnost {
	//a_ denotes parameter
	public static long f(int a_x) {
		//we do not need to check lower bounds - will be covered by other statements

		if(a_x < 10)      return a_x;
		else if(a_x < 20) return 10 + (a_x - 10) * 2;
		else if(a_x < 30) return 30 + (a_x - 20) * 3;
		else if(a_x < 40) return 60 + (a_x - 30) * 4;
		else if(a_x < 50) return 100 + (a_x - 40) * 5;
		else if(a_x < 60) return 150 + (a_x - 50) * 6;
		else              return 210 + (a_x - 50) * 7;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Integer amount_of_numbers = 0;
		try {
			amount_of_numbers = Integer.parseInt(input.nextLine());
		}
		catch(Exception e) {
			input.close();
			System.out.println(e);
			System.exit(1);
		}
		input.close();

		amount_of_numbers++; //start at 1

		//for each 1 <= x < amount_of_numbers
		for(int x = 1; x < amount_of_numbers; x++) {
			System.out.print(String.format("%d ", f(x)));
		}
	}
};
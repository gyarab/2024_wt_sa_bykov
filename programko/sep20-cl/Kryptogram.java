import java.util.*;

class Kryptogram {  
    static char[] input = new String("SEND+MORE=MONEY").toCharArray();
    static List<Character> uniqueCharacters = new ArrayList<Character>();
    static List<Integer> assignedNumbers = new ArrayList<Integer>();
    static List<Character> noZero = new ArrayList<Character>();

    public static boolean validation() {
        char[] localInput = input.clone();

        //replace chars with numbers
        for(int i = 0; i < localInput.length; i++) {
            if(!Character.isAlphabetic(localInput[i])) continue;

            int index = Kryptogram.uniqueCharacters.indexOf(localInput[i]);

            localInput[i] = (char)(assignedNumbers.get(index)+'0'); //ascii alignment
        }

        String[] expression = new String(localInput).split("\\+|-|\\*|/|=");
        return Integer.parseInt(expression[0]) + Integer.parseInt(expression[1]) == Integer.parseInt(expression[2]);
    }

    public static void main(String[] programArguments) {
        noZero.add('S');
        noZero.add('M');

        //save all unique chars
        for(char c : input) {
            if(Character.isAlphabetic(c) && !Kryptogram.uniqueCharacters.contains(c)) {
                uniqueCharacters.add(c);
                assignedNumbers.add(0);
            }
        }

        System.out.println(uniqueCharacters);

        int goodResults = 0;

        //assign all possible combinations to char
        long max = (long)Math.pow(10, uniqueCharacters.size());
        long min = (long)Math.pow(10, 3);

        for(long i = min; i < max; i++) {
            //clear array
            for(int j = 0; j < assignedNumbers.size(); j++) {
                assignedNumbers.set(j, -1);
            }

            char[] digits = String.valueOf(i).toCharArray();
            boolean skipMe = false;
            for(int j = 0; j < uniqueCharacters.size(); j++) {
                boolean outOfBounds = j >= digits.length;
                int digit = 0;
                if(!outOfBounds) { digit = (int)(digits[j] - '0'); } //ascii alignment

                assert digit >= 0 && digit <= 9;

                //if current character in noZero and our number is zero: skip 
                if(noZero.contains(uniqueCharacters.get(j)) && (outOfBounds || digit == 0)) {
                    skipMe = true;
                    break;
                };

                //if number already assigned: skip
                int index = assignedNumbers.indexOf(digit);
                if(index != -1) {
                    skipMe = true;
                    break;
                }

                assignedNumbers.set(j, digit);
            }

            if(skipMe) continue;

            //validate combination
            if(Kryptogram.validation()) {
                goodResults++;
                for(int k = 0; k < uniqueCharacters.size(); k++) {
                    System.out.println(String.format("%c = %d", uniqueCharacters.get(k), assignedNumbers.get(k)));
                }
                System.out.println("");
            }
        }

        System.out.println(String.format("Results: %d", goodResults));
    }
};
import java.util.*;
public class FractionCalculator {
    public static Scanner input = new Scanner(System.in);


    public static void main (String[] args){
        intro();

        while(true){
            String operation=getOperation();
            Fraction frac1= getFraction();
            Fraction frac2= getFraction();

            Fraction result= new Fraction (1,1);
            String result2="";

            if (operation.equals("=")){
                System.out.println(frac1+" "+operation+" "+frac2+" is "+frac1.equals(frac2));
            } else{
                if (operation.equals("+")){
                    result=frac1.add(frac2);
                } else if (operation.equals("-")) {
                    result = frac1.subtract(frac2);
                }else if (operation.equals("/")) {
                    if(frac2.getNumerator()==0){
                        result2="Undefined";
                    }else{
                        result = frac1.divide(frac2);
                    }
                }else if (operation.equals("*")) {
                    if(frac2.getNumerator()==0){
                        result2="0";
                    }else{
                        result = frac1.multiply(frac2);
                    }
                }

                //print results
                 if (result2!="") {// division and multiplication by zero is undefined
                    System.out.println(frac1+" "+operation+" "+"0"+" = "+result2);
                } else if (result.getNumerator()%result.getDenominator()==0){//*convertir en numero entero si es posible!!!
                    System.out.println(frac1+" "+operation+" "+frac2+" = "+(result.getNumerator()/result.getDenominator()));
                } else{
                    System.out.println(frac1+" "+operation+" "+frac2+" = "+result.toString());
                }
            }

        }

    }

    public static void intro (){
        System.out.println("This program is a fraction calculator.");
        System.out.println("It will add, subtract, multiply and divide fractions until you type \"Q\" to quit.");
        System.out.println("Please enter your fraction in the form a/b, where a and b are integers,and b is greater than zero.");
        System.out.println("-----------------------------------------------------------------------------------");

    }

    public static String getOperation() { //Asks the user to enter in a valid mathematical operation. If the user enters anything except "+", "-
        System.out.print("Please enter an operation (+,-,/,*,=) or \"Q\" to quit: ");
        String operation = input.nextLine();
        int x = 0;
        while (x == 0) {
            if (operation.equalsIgnoreCase("q")) {
                System.exit(0);
            } else if (operation.equals("+") || operation.equals("-") || operation.equals("/") || operation.equals("*") || operation.equals("=")){
                x++;
            } else{
                System.out.print("Invalid input, enter a valid operation (+,-,/,*,=) or \"Q\" to quit: ");
                operation = input.nextLine();
            }
        }
        return operation;

    }

    public static Fraction getFraction(){ //It prompts the user for a String that is a validFraction. If they enter any thing that is not a valid Fraction, it should re-prompt them until it is valid
        System.out.print("Please enter a Fraction (a/b) or integer (a): ");
        String ab = input.nextLine();

        //validate input
        while (!validFraction(ab)){
            System.out.print("Invalid Fraction, Please enter (a/b) or (a), where a and b are integers and b is greater than zero: ");
            ab = input.nextLine();
        }

        //convert to num,den
        int num=0;
        int den=0;
        if (ab.contains("/")){//Use Integer.parseInt() to change strings to int
            num=Integer.parseInt(ab.substring(0,ab.indexOf("/") ));
            den=Integer.parseInt(ab.substring(ab.indexOf("/")+1,ab.length() ));
        } else {
            num=Integer.parseInt(ab);
            den=1;
        }

        //return Fraction
        Fraction fracConv= new Fraction(num,den);
        return fracConv;
    }

    public static boolean validFraction(String fraction){ //returns true if the parameter is in the form "a/b" where a is any int and b is any positive int
        boolean valid;

        if (fraction.startsWith("-")){//removes negative sign - (The first character may or may not be a "-" character)
            fraction=fraction.substring(1,fraction.length());

        }

        // If a negative shows up anywhere else, then it is not a valid fraction. (Den can not be zero)
        if (fraction.contains(" ")||fraction.contains("-")||fraction.charAt(fraction.indexOf("/")+1)==('0')) { //||fraction.charAt(0)=='0'
            valid=false;

        } else if (fraction.contains("/")) {  //If there is no "/" character, then every character in the string must be a number (if you removed the "-" sign).
            fraction = fraction.replace("/", "");
        }

        //after this you should have a string containing only numbers to consider it valid && Both substrings must be non-empty.
        if (fraction.matches("[0-9]+")){
            valid=true;
        }else{
            valid=false;
        }

        return valid;

    }

}


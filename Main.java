import java.util.Scanner;

class Main
{ 
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        Setting.printPURPLE("***Program intro***");
        System.out.println("Welcome to the online Hill Cipher Encript and Decriptor.");
        System.out.println("After each paragraph, please press enter to proceed.");
        System.out.println("Please do not include numbers or spaces in the message for encryption.");
        System.out.println("When entering the entries of the matrix, please enter integers and press enter after each entry.");
        System.out.println("When making choices, please type in lowercase characters only.");
        Setting.proceed();

        Boolean cont = true;
        while (cont){
            System.out.println("Please choose from the following:");
            Setting.printGREEN("a. Introduction to Hill Cipher");
            Setting.printGREEN("b. Introduction to Matrix methods used");
            Setting.printGREEN("c. Encrption");
            Setting.printGREEN("d. Decrption");
            Setting.printGREEN("e. Exit.");
            System.out.println();
            String choice = s.nextLine();
            System.out.println();

            if (choice.equals("a")){  
                System.out.println("Please use the link to the google doc.");
                Setting.printBLUE("https://docs.google.com/document/d/1b2sGHirkbQjNSXTPl59s8nRyD-lO0RZz1wPVkolT2LQ/edit?usp=sharing");
            }
            else if (choice.equals("b")){
                System.out.println("Please use the link to the google doc.");
                Setting.printBLUE("https://docs.google.com/document/d/1b2sGHirkbQjNSXTPl59s8nRyD-lO0RZz1wPVkolT2LQ/edit?usp=sharing");
            }
            else if (choice.equals("c")){
                System.out.println("Please enter the message to be encripted "); 
                HillCipher hc = new HillCipher(s.nextLine()); 
                System.out.println("hc created, the encripted message and key are: "); 
                hc.generateKey(); 
                System.out.println(Conversion.num_text(hc.encript())); 
                hc.printKey();  
            }
            else if (choice.equals("d")){
                System.out.println("Please enter the encripted message to be decripted "); 
                String message = s.nextLine(); 
                System.out.println("message: "+ message); 
                HillCipher hc = new HillCipher(message); 
                System.out.println("Please enter the key "); 
                int[][] key = new int[message.length()][message.length()]; 
                for(int i = 0; i < message.length(); i++){ 
                    for(int j = 0; j < message.length(); j++){ 
                        key[i][j] = Integer.parseInt(s.nextLine()); 
                    } 
                } 
                hc.setKey(key); 
                System.out.println("key: "); 
                hc.printKey(); 
                System.out.println(Conversion.num_text(hc.decript())); 
            }
            else if (choice.equals("e")){
                cont = false;
                System.out.println("Thank you for using the program."); 
            }
            else{
                System.out.println("Please enter a valid answer.");
            }

            Setting.proceed();
        }
    } 
}


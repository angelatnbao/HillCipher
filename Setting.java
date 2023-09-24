
import java.util.Scanner;

class Setting{
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_PURPLE = "\u001B[35m";
  public static final String ANSI_CYAN = "\u001B[36m";

  public static void printBLUE(String s){
    System.out.println(ANSI_BLUE+s+ANSI_RESET);
  }
  public static void printRED(String s){
    System.out.println(ANSI_RED+s+ANSI_RESET);
  }
  public static void printYELLOW(String s){
    System.out.println(ANSI_YELLOW+s+ANSI_RESET);
  }
  public static void printCYAN(String s){
    System.out.println(ANSI_CYAN+s+ANSI_RESET);
  }
  public static void printGREEN(String s){
    System.out.println(ANSI_GREEN+s+ANSI_RESET);
  }
  public static void printPURPLE(String s){
    System.out.println(ANSI_PURPLE+s+ANSI_RESET);
  }

  public static void proceed() {
    Scanner s = new Scanner(System.in);
    s.nextLine();
  }
  public static void proceed(String word) {
    System.out.println(word);
    Scanner s = new Scanner(System.in);
    s.nextLine();
    System.out.println();
  }
} 
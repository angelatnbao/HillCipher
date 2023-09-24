class Conversion{ 
  private static String alphabet = "abcdefghijklmnopqrstuvwxyz";
  
  public static int [][] text_num(String code){
    int [][] num = new int [code.length()][1];
    for (int i=0; i<code.length(); i++){
      num [i][0] = alphabet.indexOf(code.charAt(i));
    }
    return num;
  }   

  public static String num_text(Matrix matrix){
    String c = "";
    for (int i = 0; i < matrix.numOfRow(); i++){
        for(int j = 0; j < matrix.numOfCol(); j++){ 
            int k = matrix.get(i,j)%26; 
            if(k < 0)
                k += 26; 
            char x = alphabet.charAt(k); 
            c = c + x; 
        } 
    }
  return c; 
  }
} 
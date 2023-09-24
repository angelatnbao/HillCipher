import java.util.Scanner;

/* Hill Cipher encryption/decryption 
 */ 
public class HillCipher{
    private static Matrix key; 
    private Matrix message;  //an 1 by n matrix, which is a vector 
    private int N;           //the size of the message, or number of characters in the message 

    /**
     * Constructor that takes in String m as parameter 
     */
    public HillCipher(String m){ 
        this.message = new Matrix(Conversion.text_num(m)); 
        //System.out.println("Matrix message: " + Conversion.num_text(message)); 
        N = message.numOfRow(); //set the size as number of characters in message 
        //System.out.println("N: "+N); 
    } 

    /**
     * private helper to generate a random key, which is a matrix of N*N 
     */
    public void generateKey (){ 
        
        key = new Matrix(N); 
        int valid = -1; 
        while (valid == -1){ 
            for(int i = 0; i < N; i++){ 
                for(int j = i; j < N; j++) {
                    double r = 26.0 * Math.random(); 
                    //System.out.println("random #: " + r); 
                    key.set(i, j, (int) r); 
                    if(i == j)
                        key.set(i, j, (int) (1+25.0 * Math.random())); 
                } 
            } 
            int det = key.det(); 
            for (int i = 0; i < 26; i++) { 
                int temp = (det * i) % 26;
                if (temp == 1) { 
                    valid = i;
                    break; 
                }
            } 
            //System.out.println("validity: "+valid); 
            //key.print(); 
        } 
        assert key.invertible(); 
    }

    public void setKey(int[][] k){ 
        key = new Matrix(k); 
    }

    public Matrix encript(){
        return message.multiply(key); 
    } 

    public Matrix decript(){ 
        //Matrix de = message.multiply(key.inverse()); 
        //System.out.println("product"); 
        //de.print(); 
        return message.multiply(key.inverse()); 
    } 

    public void printKey(){ 
        key.print(); 
    } 

    /*
    public int [][] encript (){
    Scanner s = new Scanner(System.in);
    System.out.println("Enter the message to be encripted: ");
    String text = s.nextLine();

    int r = text.length(); 
    key = new int [r][r]; //create a r by r matrix where r is the length of the message
    int [][] matrix = Conversion.text_num(text); //converts the message to matrix / vector

    System.out.println("Enter the elements of the key: ");
    for(int i=0; i<r; i++)
    for(int j=0; j<r; j++)
    key[i][j] = s.nextInt();

    int [][] num = Matrix.multiply(key, matrix); //multiple the key with the message matrix to get the encoded matrix
    return num;
    }

    public String decript (){
    Scanner s = new Scanner(System.in);
    System.out.println("Enter the number of rows of the matrix: ");
    int r = s.nextInt();

    int [][] matrix = new int [r][1]; //creates a empty matrix / vector with the length equals to that of the message
    key = new int [r][r];

    System.out.println("Enter the elements of matrix: ");
    for(int i=0; i< r; i++)
    matrix[i][1] = s.nextInt();

    System.out.println("Enter the elements of the key: ");
    for(int i=0; i<r; i++)
    for(int j=0; j<r; j++)
    key[i][j] = s.nextInt();

    double [][] num = Matrix.multiply(Matrix.inverse(key),matrix); //the message matrix is obtained by multiplying the inverse of the key to the encoded matrix
    return Conversion.num_text(num);//the message matrix is converted back to text
    }
     */
} 
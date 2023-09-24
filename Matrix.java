import java.util.Scanner;

class Matrix{ 
    private int[][] m;     //stores the entries of the matrix as a 2D array 
    private int N;         //number of rows 
    private int M;         //number of columns 

    /**
     * Constructor that creates square matrix of size N 
     */
    public Matrix(int N){ 
        this.N = N; 
        this.M = N; 
        m = new int[this.N][this.M]; 
    } 

    /**
     * Constructor that creates square matrix of N rows and M cols 
     */ 
    public Matrix(int N, int M){ 
        this.N = N; 
        this.M = M; 
        m = new int[this.N][this.M]; 
    } 

    /**
     * Constructor that creates a matrix the entries described by the 2D array 
     */ 
    public Matrix(int[][] m){ 
        this.m = m; 
        N = m.length; 
        M = m[0].length; 
    } 

    /**
     * Returns: number of rows 
     */
    public int numOfRow(){ 
        return N; 
    } 

    /**
     * Returns: number of columns 
     */
    public int numOfCol(){ 
        return M; 
    } 

    /**
     * Returns: the entry at the ith row and jth column 
     */
    public int get(int i, int j){ 
        return m[i][j]; 
    } 

    /**
     * set the entry at (i, j) as e 
     */
    public void set(int row, int col, int e){ 
        m[row][col] = e; 
    } 

    /**
     * Returns: the determinant of the matrix 
     */
    public int det(){ 
        if (m.length != m[0].length)
            throw new IllegalStateException("invalid dimensions");

        if (m.length == 1 && m[0].length ==1) 
            return m[0][0];

        if (m.length == 2)
            return m[0][0] * m[1][1] - m[0][1] * m[1][0];

        int det = 0;
        for (int i = 0; i < m[0].length; i++)
            det += Math.pow(-1, i) * m[0][i] * minor(0, i).det();
        return det; 
    } 

    /**
     * Returns: the minor obtained by deleting the ith and jth row 
     */
    private Matrix minor(int row, int column) { 
        Matrix minor = new Matrix(N-1, M-1); 

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (i != row && j != column)
                    minor.set(i < row ? i : i - 1, j < column ? j : j - 1, m[i][j]); 

        return minor; 
    } 

    /**
     * Returns: the invertibility of the matrix 
     */
    public boolean invertible(){ 
        if (det() == 0)
            return false; 
        return true; 
    } 

    /**
     * Returns: the inverse of the matrix 
     */
    public Matrix inverse() { 
        Matrix inverse = new Matrix(N); 

        // minors and cofactors
        for (int i = 0; i < N; i++) { 
            for (int j = 0; j < M; j++) { 
                //System.out.println("minor: " + i+ j); 
                //minor(i, j).print(); 
                inverse.set(i, j, (int) Math.pow(-1, i + j) * minor(j, i).det()); 
            }
        } 

        //System.out.println("after setting minors: "); 
        //inverse.print(); 
        
        int det = det(); 
        int modInverse = -1;
        for (int i = 0; i < 26; i++) {
            int tempInverse = (det * i) % 26;
            if (tempInverse == 1) {
                modInverse = i;
                break;
            }
        }

        //System.out.println("modInverse = " +modInverse); 
        /* 
        int factor; 
        for (factor = 1; factor < 26; factor ++){ 
            if((det%26*factor) %26 == 1) 
                break; 
        } 
        
        System.out.println("factor = " +factor); 
        */ 
       
        // adjugate and determinant 
        for (int i = 0; i < inverse.numOfRow(); i++) {
            for (int j = 0; j < inverse.numOfCol(); j++) { 
                inverse.set(i, j, (inverse.get(i, j) * modInverse)%26); 
                //double temp = inverse.get(i, j);
                //inverse.set(i, j, inverse.get(i, j) * det); 
                //inverse.set(j, i, temp * det); 
                //inverse[i][j] = inverse[j][i] * det;
                //inverse[j][i] = temp * det;
            }
        } 

        //System.out.println("!!!!!!!inverse: "); 
        //inverse.print(); 
        return inverse; 
    } 

    /**
     * Returns: the product matrix of key*this matrix 
     */
    public Matrix multiply(Matrix key) {
        if (key.numOfCol() != this.numOfRow())
            throw new IllegalStateException("invalid dimensions");

        Matrix product = new Matrix(key.numOfRow(), this.numOfCol());
        for (int i = 0; i < key.numOfRow(); i++) {
            for (int j = 0; j < this.numOfCol(); j++) {
                int sum = 0;
                for (int k = 0; k < key.numOfCol(); k++)
                    sum += key.get(i, k) * this.get(k, j);
                product.set(i, j, sum);
            }
        }
        return product;
    } 

    /**
     * print the matrix 
     */
    public void print(){ 
        for (int i = 0; i < this.numOfRow(); i++) { 
            System.out.print("| "); 
            for (int j = 0; j < this.numOfCol(); j++) {
                System.out.print(get(i, j) + " "); 
            } 
            System.out.println("| "); 
        } 
    } 

    public static void main(String[] args){ 
        Scanner s = new Scanner(System.in); 
        System.out.println("Please enter a matrix of size 3 "); 
        int[][] m = new int[3][3]; 
        for(int i = 0; i < 3; i++){ 
            for(int j = 0; j < 3; j++){ 
                m[i][j] = Integer.parseInt(s.nextLine()); 
            } 
        } 
        Matrix matrix = new Matrix(m); 

        System.out.println("The matrix you entered is: "); 
        matrix.print(); 
        System.out.println("The determinant of the matrix is "+matrix.det()); 
        System.out.println("The entry at 2nd row and 3rd columne is "+matrix.get(1, 2)); 
        System.out.println(""); 
        System.out.println("Please enter the number you would like to change to for the entry at 2nd row and 1st columne"); 
        matrix.set(1, 0, Integer.parseInt(s.nextLine())); 
        System.out.println("Your matrix is now: "); 
        matrix.print(); 
        System.out.println("The matrix's invertibility is: "+matrix.invertible()); 
        if(matrix.invertible()){ 
            System.out.println("The inverse of the matrix is "); 
            matrix.inverse().print(); 
            System.out.println("The multiplication of the matrix with its inverse is "); 
            matrix.multiply(matrix.inverse()).print(); 
        } 
    } 
} 
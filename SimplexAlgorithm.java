/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Johnrey
 */
public class SimplexAlgorithm {
    
    public float[][] matrix;
    
    public int row;
    public int col;
    
    SimplexAlgorithm(int row, int col){
        this.row = row;
        this.col = col;
        this.matrix = new float[row][col];
    }
    
    public void setMatrix(float[][] matrix){
        this.matrix = matrix;
    }
    
    public int getPivotColumn(){
        int pc = 1;
        float sir = -0.0000001f;
//        float sir = -1f;
        for(int i = col - 1; i >= 1; i--){
//            System.out.println((matrix[0][i] <= sir) + " " + matrix[0][i] + " " + sir);
            if(matrix[0][i] <= sir){
                pc = i;
                sir = matrix[0][i];
            }
        }        
        return pc;
    }
    
    public int getPivotRow(){
        int pc = getPivotColumn();
        int pr = row - 1;
        float ratio = 999999999;
//        System.out.println(matrix[1][0] +"/"+ matrix[1][pc]);
        for(int i = row - 1; i >= 1; i--){
            if(matrix[i][0] <= 0 || matrix[i][pc] <= 0){
                continue;
            }
            if((matrix[i][0] / matrix[i][pc]) < ratio){
//                System.out.println(matrix[i][0] +"/"+ matrix[i][pc]);
                pr = i;
                ratio = matrix[i][0] / matrix[i][pc];
            }
        }
//        System.out.println("rat" + ratio);
        return pr;
    }
    
    /**
     * check if b1 to bi > 0
     * @return boolean
    */
    public boolean checkB(){
        boolean ok = true;
        for(int i = 1; i < row; i++){
            if(matrix[i][0] < 0){
                ok = false;
            }
        }
        return ok;
    }
    
    /**
     * check if c1 to ci > 0
     * @return boolean
    */
    public boolean checkC(){
        boolean ok = true;
        for(int i = 1; i < col; i++){
            if(matrix[0][i] < 0){
                ok = false;
            }
        }
        return ok;
    }
    
    public boolean checkUnbounded(){
        boolean ok = true;
        int pc = getPivotColumn();
        for(int i = 1; i < row; i++){
            if(matrix[i][pc] != 0){
                ok = false;
            }
        }
        return ok;
    }
    
    public void makeIdentityColumn(){
        int pc = getPivotColumn();
        int pr = getPivotRow();
        System.out.println("pivot at: " + pr + ", " + pc);
        float divideBy = matrix[pr][pc];
        System.out.println("R" + pr + " / " + roundOff(divideBy));
        for(int i = 0; i < col; i++){
            matrix[pr][i] /= divideBy;
        }
        printTableau();
        for(int i = 0; i < row; i++){
            if(i == pr){
                continue;
            }
            float makeZeroBy = matrix[i][pc];
//            System.out.println(matrix[i][pc] + " + " + matrix[pr][pc] + " = " + makeZeroBy + " " + (matrix[i][pc] + matrix[pr][pc] != 0));
            if((matrix[i][pc] + matrix[pr][pc] != 0.0f) || (matrix[i][pc] * matrix[pr][pc] > 0.0f)){
                makeZeroBy *= -1;
            }
            if(matrix[i][pc] == -1f){
                makeZeroBy = 1f;
            }
            System.out.println("R" + i + " + R" + pr + " * " + roundOff(makeZeroBy));
            for(int j = 0; j < col; j++){                
                matrix[i][j] += matrix[pr][j] * makeZeroBy;                
            }
            printTableau();
        }
    }
    
    public void printX(){
        System.out.println("xz: " + roundOff(matrix[0][0]));
        for(int i = 1; i < col; i++){
            float x = 0;
            if(checkIdentityColumn(i)){
                for(int j = 1; j < row; j++){
                    if(matrix[j][i] == 1){
                        x = matrix[j][0];
                    }
                }
            }
            System.out.println("x" + i + ": " + roundOff(x));
        }
    }
    
    public boolean checkIdentityColumn(int c){        
        int counter1 = 0, counter0 = 0;
        for(int i = 0; i < row; i++){
            if(matrix[i][c] == 0){
                counter0++;
            }
            if(matrix[i][c] == 1){
                counter1++;
            }
        }
        return (counter1 == 1 && counter0 == row - 1);
    }
    
    public void printTableau(){
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                System.out.print(roundOff(matrix[i][j]) + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    float roundOff(float f){        
        return (float) (Math.round(f * 100.0) / 100.0);
    }
    
}

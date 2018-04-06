/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Johnrey
 */

public class Main {        
    
    public static void main(String[] args){    
//        float[][] m = {
//            {0, -5, -1, -3, -1, -2  },
//            {12, 1, 2, -3, 1, 0  },
//            {15, 2, 0, 2, 3, 4  },
//            {13, 2, 4, 5, -3, 0},
//            {16, -1, 5, 2, -2, -1}
//        };
//        float[][] matrix = {
//            {d, ci},
//            {bj, Aij}
//        }//
//        where i = number of column, j = number of rows
//        matrix[0][0] is d
//        matrix[0][i] is c
//        matrix[j][0] is b
//        matrix[1 ... j][1 ... i] is A
        float [][] m2 = {
            {0, -1, -2, 1, 0, 0, 0},  
            {14, 2, 1, 1, 1, 0, 0},
            {28, 4, 2, 3, 0, 1, 0},
            {30, 2, 5, 5, 0, 0, 1}
        };
        SimplexAlgorithm sa = new SimplexAlgorithm(m2.length, m2[0].length);
        sa.setMatrix(m2);
        while(!sa.checkC()){
            sa.printTableau();
            sa.makeIdentityColumn();
        }        
        System.out.println((sa.checkB()) ? "Feasible" : "Infeasible");
        if(sa.checkB()){
            sa.printX();            
        }
    }
    
    
}

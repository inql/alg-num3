package ug.pprotocols;

import ug.pprotocols.algorithm.Mcarlo;
import ug.pprotocols.matrix.Case;
import ug.pprotocols.matrix.MatrixGenerator;

public class Main {

    public static void main(String[] args) {

        Case caseToCheck = new Case(8,4,15);


        for (int i=10000; i<100000000; i*=2){
            System.out.println("Iteration count: "+i);
            Mcarlo mc = new Mcarlo(i);
            System.out.println(mc.countProbability(caseToCheck));
        }

        MatrixGenerator matrixGenerator = new MatrixGenerator(caseToCheck);
        System.out.println(matrixGenerator.getSolution(matrixGenerator.generateEquation()));

    }
}

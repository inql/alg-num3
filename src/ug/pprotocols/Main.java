package ug.pprotocols;

import ug.pprotocols.algorithm.Mcarlo;
import ug.pprotocols.matrix.Case;
import ug.pprotocols.matrix.MatrixGenerator;

public class Main {

    public static void main(String[] args) {

        Mcarlo mc = new Mcarlo(10000);
        System.out.println(mc.countProbability(new Case(8, 4, 15)));

        MatrixGenerator matrixGenerator = new MatrixGenerator(3);
        System.out.println(matrixGenerator.generateEquation());

    }
}

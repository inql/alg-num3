package ug.pprotocols;

import ug.pprotocols.algorithm.Mcarlo;
import ug.pprotocols.matrix.MatrixGenerator;

public class Main {

    public static void main(String[] args) {

        Mcarlo mc = new Mcarlo(10000);
        System.out.println(mc.countProbability(30,18));

        MatrixGenerator matrixGenerator = new MatrixGenerator(3);
        System.out.println(matrixGenerator.generateEquation());

    }
}

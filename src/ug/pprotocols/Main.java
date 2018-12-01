package ug.pprotocols;

import ug.pprotocols.matrix.MatrixGenerator;

public class Main {

    public static void main(String[] args) {

        MatrixGenerator matrixGenerator = new MatrixGenerator(3);
        System.out.println(matrixGenerator.generateEquation());



    }
}

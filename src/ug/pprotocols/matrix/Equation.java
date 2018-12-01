package ug.pprotocols.matrix;

import ug.pprotocols.datatypes.MatrixCompatible;

import java.util.Arrays;

public class Equation<T extends MatrixCompatible> {

    public MyMatrix<T> matrixA;
    public MatrixCompatible[] vectorB;
    public MatrixCompatible vectorX;

    public Equation(MyMatrix<T> matrixA, MatrixCompatible[] vectorB, MatrixCompatible vectorX) {
        this.matrixA = matrixA;
        this.vectorB = vectorB;
        this.vectorX = vectorX;
    }

    @Override
    public String toString() {
        return "Equation{\n" +
                "matrixA=\n" + matrixA +
                ", \nvectorB=\n" + Arrays.deepToString(vectorB) +
                ", \nvectorX=\n" + vectorX +
                '}';
    }
}

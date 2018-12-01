package ug.pprotocols.matrix;

import ug.pprotocols.ChoiceType;
import ug.pprotocols.algorithm.GaussImpl;
import ug.pprotocols.datatypes.DataType;
import ug.pprotocols.datatypes.MatrixCompatible;
import ug.pprotocols.datatypes.MatrixCompatibleFactory;
import ug.pprotocols.operations.DataOperation;
import ug.pprotocols.operations.DoubleOperation;

import java.util.Arrays;

public class Equation<T extends MatrixCompatible> {

    public MyMatrix<T> matrixA;
    public MatrixCompatible[] vectorB;
    public MatrixCompatible[] vectorX;
    public GaussImpl gauss;

    public Equation(MyMatrix<T> matrixA, MatrixCompatible[] vectorB, MatrixCompatible vectorX) {
        this.matrixA = matrixA;
        this.vectorB = vectorB;
        gauss = new GaussImpl(matrixA,new MatrixCompatibleFactory(DataType.DOUBLE), new DoubleOperation(), ChoiceType.PARTIAL);
        this.vectorX = gauss.gauss(vectorB);
    }

    @Override
    public String toString() {
        return "Equation{\n" +
                "matrixA=\n" + matrixA +
                ", \nvectorB=\n" + Arrays.deepToString(vectorB) +
                ", \nvectorX=\n" + Arrays.deepToString(vectorX) +
                '}';
    }
}

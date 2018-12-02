package ug.pprotocols.matrix;

import ug.pprotocols.ChoiceType;
import ug.pprotocols.algorithm.GaussImpl;
import ug.pprotocols.datatypes.DataType;
import ug.pprotocols.datatypes.MatrixCompatible;
import ug.pprotocols.datatypes.MatrixCompatibleFactory;
import ug.pprotocols.operations.DoubleOperation;

import java.util.Arrays;

public class Equation<T extends MatrixCompatible> {

    private MyMatrix<T> matrixA;
    private MatrixCompatible[] vectorB;
    private MatrixCompatible[] vectorXGauss;
    private MatrixCompatible[] vectorXJac;
    private MatrixCompatible[] vectorXGS;
    private GaussImpl gauss;

    Equation(MyMatrix<T> matrixA, MatrixCompatible[] vectorB, MatrixCompatible vectorX) {
        this.matrixA = matrixA;
        this.vectorB = vectorB;
        gauss = new GaussImpl(matrixA,new MatrixCompatibleFactory(DataType.DOUBLE), new DoubleOperation(), ChoiceType.PARTIAL);
        this.vectorXGauss = gauss.gauss(vectorB);
        gauss = new GaussImpl(matrixA,new MatrixCompatibleFactory(DataType.DOUBLE), new DoubleOperation(), ChoiceType.PARTIAL);
        this.vectorXJac = gauss.jacobian(vectorB);
    }

    @Override
    public String toString() {
        return "Equation{\n" +
                "matrixA=\n" + matrixA +
                ", \nvectorB=\n" + Arrays.deepToString(vectorB);
    }

    public MyMatrix<T> getMatrixA() {
        return matrixA;
    }

    public void setMatrixA(MyMatrix<T> matrixA) {
        this.matrixA = matrixA;
    }

    public MatrixCompatible[] getVectorB() {
        return vectorB;
    }

    public void setVectorB(MatrixCompatible[] vectorB) {
        this.vectorB = vectorB;
    }

    public MatrixCompatible[] getVectorXGauss() {
        return vectorXGauss;
    }

    public MatrixCompatible[] getVectorXJac() {
        return vectorXJac;
    }

    public MatrixCompatible[] getVectorXGS() {
        return vectorXGS;
    }
}

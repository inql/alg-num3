package ug.pprotocols.matrix;

import ug.pprotocols.ChoiceType;
import ug.pprotocols.Type;
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
    private MatrixCompatible[] vectorXGaussSparse;
    private MatrixCompatible[] vectorXGS;
    private GaussImpl gauss;

    Equation(MyMatrix<T> matrixA, MatrixCompatible[] vectorB, MatrixCompatible vectorX) {
        this.matrixA = matrixA;
        this.vectorB = vectorB;
    }

    @Override
    public String toString() {
        return "Equation{\n" +
                "matrixA=\n" + matrixA +
                ", \nvectorB=\n" + Arrays.deepToString(vectorB);
    }

    public void evaluate(Type type){
        switch (type){
            case GAUSS:
                gauss = new GaussImpl(matrixA,new MatrixCompatibleFactory(DataType.DOUBLE),new DoubleOperation(), ChoiceType.PARTIAL);
                this.vectorXGauss = gauss.gauss(vectorB,false);
                break;
            case JACOBIAN:
                gauss = new GaussImpl(matrixA,new MatrixCompatibleFactory(DataType.DOUBLE),new DoubleOperation(), ChoiceType.PARTIAL);
                this.vectorXJac = gauss.jacobian(vectorB,0.0001);
                break;
            case GAUSS_SPARSE:
                gauss = new GaussImpl(matrixA,new MatrixCompatibleFactory(DataType.DOUBLE),new DoubleOperation(), ChoiceType.PARTIAL);
                this.vectorXGaussSparse = gauss.gauss(vectorB,true);
            case GAUSS_SEIDEL:
                gauss = new GaussImpl(matrixA,new MatrixCompatibleFactory(DataType.DOUBLE),new DoubleOperation(), ChoiceType.PARTIAL);
                this.vectorXGS = gauss.gaussSeidel(vectorB,0.0001);
                break;
        }
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

    public MatrixCompatible[] getVectorXGaussSparse() {
        return vectorXGaussSparse;
    }
}

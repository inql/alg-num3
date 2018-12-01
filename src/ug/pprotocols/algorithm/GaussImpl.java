package ug.pprotocols.algorithm;

import ug.pprotocols.ChoiceType;
import ug.pprotocols.matrix.MyMatrix;
import ug.pprotocols.Swapper;
import ug.pprotocols.datatypes.MatrixCompatible;
import ug.pprotocols.datatypes.MatrixCompatibleFactory;
import ug.pprotocols.operations.DataOperation;

import java.math.BigInteger;

public class GaussImpl {

    public MyMatrix myMatrix;
    private final MatrixCompatibleFactory matrixCompatibleFactory;
    private final DataOperation dataOperation;
    private final ChoiceType choiceType;

    public GaussImpl(MyMatrix myMatrix, MatrixCompatibleFactory matrixCompatibleFactory, DataOperation dataOperation, ChoiceType choiceType) {
        this.myMatrix = myMatrix;
        this.matrixCompatibleFactory = matrixCompatibleFactory;
        this.dataOperation = dataOperation;
        this.choiceType = choiceType;
    }

    @SuppressWarnings("unchecked")
    public MatrixCompatible[] gauss(MatrixCompatible[] vectorB){
        int n = vectorB.length;
        for(int p = 0; p<n; p++){
            switchRowOrColumn(p,p,vectorB);
            for(int i = p+1; i<n; i++){
                MatrixCompatible alpha = dataOperation.divide(myMatrix.getValue(i,p),myMatrix.getValue(p,p));
                vectorB[i] = dataOperation.subtract(vectorB[i],dataOperation.multiply(alpha,vectorB[p]));
                for (int j=p; j<n; j++){
                    myMatrix.setValue(i,j,dataOperation.subtract(myMatrix.getValue(i,j),dataOperation.multiply(alpha,myMatrix.getValue(p,j))));
                }
            }


        }

        MatrixCompatible[] vectorX = matrixCompatibleFactory.createArray(n);
        for (int i =n-1; i>=0; i--){
            MatrixCompatible sum = matrixCompatibleFactory.createWithDenominator(BigInteger.ZERO,BigInteger.ONE);
            for(int j = i+1; j<n; j++){
                sum = dataOperation.add(sum,dataOperation.multiply(myMatrix.getValue(i,j),vectorX[myMatrix.columns[j]]));
            }
            vectorX[myMatrix.columns[i]] = dataOperation.divide(dataOperation.subtract(vectorB[i],sum),myMatrix.getValue(i,i));
        }
        for(int i =0; i<vectorX.length; i++){
            vectorX[myMatrix.columns[i]].setValue(vectorX[myMatrix.columns[i]].getValue());
        }
        return vectorX;

    }

    @SuppressWarnings("unchecked")
    public MatrixCompatible[] switchRowOrColumn(int startX, int startY, MatrixCompatible[] vectorB){


        if(choiceType == ChoiceType.NONE)
            return vectorB;

        else if (choiceType == ChoiceType.PARTIAL)
        {
            int rowToSwitch = startX;
            for (int i=startX; i < myMatrix.rows.length; i++)
            {
                if ( dataOperation.abs(myMatrix.getValue(i,startY)).compareTo(dataOperation.abs(myMatrix.getValue(rowToSwitch,startY))) > 0)
                    rowToSwitch = i;

            }

            myMatrix.swap(startX,rowToSwitch, Swapper.ROW);
            MatrixCompatible temp = vectorB[startX];
            vectorB[startX] = vectorB[rowToSwitch];
            vectorB[rowToSwitch] = temp;

            return vectorB;

        } else if (choiceType == ChoiceType.FULL)
        {
            int rowToSwitch = startY;
            int columnToSwitch = startX;
            for (int i=startY; i < myMatrix.rows.length; i++)
            {
                for (int o=startX; o < myMatrix.columns.length; o++) {

                    if (dataOperation.abs(myMatrix.getValue(i, o)).compareTo(dataOperation.abs(myMatrix.getValue(rowToSwitch, columnToSwitch))) > 0) {
                        rowToSwitch = i;
                        columnToSwitch = o;
                    }
                }

            }
            myMatrix.swap(startX,rowToSwitch, Swapper.ROW);
            myMatrix.swap(startY,columnToSwitch, Swapper.COLUMN);
            MatrixCompatible temp = vectorB[startX];
            vectorB[startX] = vectorB[rowToSwitch];
            vectorB[rowToSwitch] = temp;

            return vectorB;

        }
        return vectorB;

    }

    @SuppressWarnings("unchecked")
    public MatrixCompatible[] multiplyMatrixWithVector(MyMatrix a, MatrixCompatible[] vector)
    {
        if (a.columns.length != vector.length)
            return null;
        MatrixCompatible[] result = matrixCompatibleFactory.createArray(vector.length);

        for (int i=0; i < vector.length; i++)
            result[i] = matrixCompatibleFactory.createWithNominator(new BigInteger("0"));

        for (int x = 0; x < vector.length; x++)
        {
            for (int y = 0; y < vector.length; y++)
            {
                result[x] = dataOperation.add(result[x], dataOperation.multiply(a.getValue(x, y), vector[y]));

            }

        }


        return result;
    }


}
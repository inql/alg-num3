package ug.pprotocols.matrix;

import ug.pprotocols.datatypes.DataType;
import ug.pprotocols.datatypes.DoubleComp;
import ug.pprotocols.datatypes.MatrixCompatible;
import ug.pprotocols.datatypes.MatrixCompatibleFactory;

import java.util.HashMap;
import java.util.Map;

public class MatrixGenerator {

    public int agentsCount;
    public Map<Integer, Case> indexToKey;
    public MatrixCompatibleFactory matrixCompatibleFactory;

    double nOver2;

    public int numberOfEquations;

    public MatrixGenerator(int n){

        this.matrixCompatibleFactory = new MatrixCompatibleFactory(DataType.DOUBLE);
        this.agentsCount = n;
        generateKeys();
        this.numberOfEquations = indexToKey.keySet().size();

        this.nOver2 = (double)newton((long)n,2L);
    }


    public Equation generateEquation(){
        MatrixCompatible[][] matrixA = matrixCompatibleFactory.createMatrix(numberOfEquations,numberOfEquations);
        MatrixCompatible[] vectorB = matrixCompatibleFactory.createArray(numberOfEquations);

        for(int i = 0 ; i<vectorB.length-1; i++){
            vectorB[i] =  matrixCompatibleFactory.createWithNominator(0D);
        }
            vectorB[vectorB.length-1] = matrixCompatibleFactory.createWithNominator(1D);

        for(int i = 0; i< matrixA.length; i++){
            for(int j = 0; j<matrixA.length; j++){
                matrixA[i][j] = generateValue(i,j);
            }
        }

        return new Equation<>(new MyMatrix<>((DoubleComp[][]) matrixA),vectorB,null);

    }

    private MatrixCompatible generateValue(int i, int j){
        Case currentCaseRow = indexToKey.get(i);

        int yesCountsRow = currentCaseRow.yesVoters;
        int noCountsRow = currentCaseRow.noVoters;

        Case currentCaseColumn = indexToKey.get(j);

        int yesCountsColumn = currentCaseColumn.yesVoters;
        int noCountsColumn = currentCaseColumn.noVoters;


        if(yesCountsRow == agentsCount && noCountsRow ==0 && i == j)
            return matrixCompatibleFactory.createWithNominator(1D);
        else if(yesCountsRow == 0 && noCountsRow == agentsCount && i == j)
            return matrixCompatibleFactory.createWithNominator(1D);
        else if(yesCountsRow == 0 && noCountsRow == 0 && i == j)
            return matrixCompatibleFactory.createWithNominator(1D);
        else if (yesCountsRow == yesCountsColumn && noCountsRow == noCountsColumn && (yesCountsRow > 1 || noCountsRow > 1 || agentsCount - yesCountsRow - noCountsRow > 1))
            return undecidedAgentsChosenCase(agentsCount, yesCountsRow, noCountsRow);
        else if (yesCountsRow + 1 == yesCountsColumn && noCountsRow == noCountsColumn && yesCountsRow > 0 && yesCountsRow + noCountsRow < agentsCount)
            return yesAndUndecidedAgentChosenCase(agentsCount, yesCountsRow, noCountsRow);
        else if (yesCountsRow == yesCountsColumn && noCountsRow + 1 == noCountsColumn && noCountsRow > 0 && yesCountsRow + noCountsRow < agentsCount)
            return noAndUndecidedAgentChosenCase(agentsCount, yesCountsRow, noCountsRow);
        else if (yesCountsRow - 1 == yesCountsColumn && noCountsRow - 1 == noCountsColumn && yesCountsRow > 0 && noCountsRow > 0)
            return yesAndNoAgentChosenCase(yesCountsRow, noCountsRow);
        else if (i == j)
            return matrixCompatibleFactory.createWithNominator(-1D);
        else
            return matrixCompatibleFactory.createWithNominator(0D);
    }



    private MatrixCompatible undecidedAgentsChosenCase(int agents, int yesVoteCount, int noVoteCount){

        double result = -1.0;

        if(yesVoteCount>1)
            result += (double)newton(yesVoteCount,2)/nOver2;

        if(noVoteCount > 1)
            result += (double)newton(noVoteCount,2)/nOver2;

        if(agents-yesVoteCount-noVoteCount > 1)
            result += (double)newton(agents-yesVoteCount-noVoteCount,2) / nOver2;

        return matrixCompatibleFactory.createWithNominator(result);
    }

    private MatrixCompatible yesAndUndecidedAgentChosenCase(int agents, int yesVoteCount, int noVoteCount){
        return matrixCompatibleFactory.createWithNominator(undecidedAgentChosenCalculation(agents,yesVoteCount,noVoteCount,yesVoteCount));
    }

    private MatrixCompatible noAndUndecidedAgentChosenCase(int agents, int yesVoteCount, int noVoteCount) {
        return matrixCompatibleFactory.createWithNominator(undecidedAgentChosenCalculation(agents,yesVoteCount,noVoteCount,noVoteCount));
    }

    private double undecidedAgentChosenCalculation(int agents, int yesVoteCount, int noVoteCount, int conditionValue){
        double result = 0.0;

        if(conditionValue > 0 && agents - yesVoteCount - noVoteCount > 0)
            result+= (double)(conditionValue * (agents-yesVoteCount-noVoteCount))/nOver2;

        return result;
    }

    private MatrixCompatible yesAndNoAgentChosenCase(int yesVoteCount, int noVoteCount){

        double result = 0.0;

        if(noVoteCount > 0 && yesVoteCount > 0)
            result += (double)(noVoteCount * yesVoteCount)/nOver2;

        return matrixCompatibleFactory.createWithNominator(result);

    }




    private void generateKeys(){
        indexToKey = new HashMap<>();

        int k = 0;
        for(int i=0; i<=agentsCount; i++){
            for(int j =0; j<=agentsCount-i; j++){
                indexToKey.put(k++,new Case(i,j));
            }
        }
    }

    private static long newton(long n, long k){
        if (k > n) { return 0; }
        if (n == k) { return 1; } // only one way to chose when n == k
        if (k > n - k) { k = n - k; } // Everything is symmetric around n-k, so it is quicker to iterate over a smaller k than a larger one.
        if (k == 1) { return n; }
        long c = 1;
        for (long i = 1; i <= k; i++)
        {
            c *= n--;
            c /= i;
        }
        return c;
    }




}

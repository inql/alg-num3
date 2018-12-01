package ug.pprotocols.matrix;

import ug.pprotocols.datatypes.DataType;
import ug.pprotocols.datatypes.DoubleComp;
import ug.pprotocols.datatypes.MatrixCompatible;
import ug.pprotocols.datatypes.MatrixCompatibleFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatrixGenerator {

    public int agentsCount;
    public List<String> keys;
    public Map<String, Integer> keyToIndex;
    public Map<Integer, String> indexToKey;
    public MatrixCompatibleFactory matrixCompatibleFactory;

    double nOver2;

    public int numberOfEquations;

    public MatrixGenerator(int n){

        this.matrixCompatibleFactory = new MatrixCompatibleFactory(DataType.DOUBLE);
        this.agentsCount = n;
        generateKeys();
        generateMaps();
        this.numberOfEquations = keys.size();

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
        String currentCaseRow = indexToKey.get(i);
        String[] countsRow = currentCaseRow.split(",");

        int yesCountsRow = Integer.parseInt(countsRow[0]);
        int noCountsRow = Integer.parseInt(countsRow[1]);

        String currentCaseColumn = indexToKey.get(j);
        String[] countsColumn = currentCaseColumn.split(",");

        int yesCountsColumn = Integer.parseInt(countsColumn[0]);
        int noCountsColumn = Integer.parseInt(countsColumn[1]);

        if(yesCountsRow == agentsCount && noCountsRow ==0 && i == j)
            return matrixCompatibleFactory.createWithNominator(1D);
        else if(yesCountsRow == 0 && noCountsRow == agentsCount && i == j)
            return matrixCompatibleFactory.createWithNominator(1D);
        else if(yesCountsRow == 0 && noCountsRow == 0 && i == j)
            return matrixCompatibleFactory.createWithNominator(1D);
        else if (yesCountsRow == yesCountsColumn && noCountsRow == noCountsColumn && (yesCountsRow > 1 || noCountsRow > 1 || agentsCount - yesCountsRow - noCountsRow > 1)) // Przypadek, gdy nic się nie zmienia (wylosowana została para agentów o tym samym zdaniu)
            return getProbabilityCase1(agentsCount, yesCountsRow, noCountsRow);
        else if (yesCountsRow + 1 == yesCountsColumn && noCountsRow == noCountsColumn && yesCountsRow > 0 && yesCountsRow + noCountsRow < agentsCount) //Przypadek, gdy liczba agentów na "tak" zwiększa się o 1 (wylosowano 1 agenta na "tak" i jednego niezdecydowanego)
            return getProbabilityCase2(agentsCount, yesCountsRow, noCountsRow);
        else if (yesCountsRow == yesCountsColumn && noCountsRow + 1 == noCountsColumn && noCountsRow > 0 && yesCountsRow + noCountsRow < agentsCount) //Przypadek, gdy liczba agentów na "nie" zwiększa się o 1 (wylosowano 1 agenta na "nie" i jednego niezdecydowanego)
            return getProbabilityCase3(agentsCount, yesCountsRow, noCountsRow);
        else if (yesCountsRow - 1 == yesCountsColumn && noCountsRow - 1 == noCountsColumn && yesCountsRow > 0 && noCountsRow > 0) //Przypadek, gdy liczba agentów niezdecydowanych rośnie o 2, Czyli gdy wylosowano jednego na "tak" i jednego na "nie"
            return getProbabilityCase4(agentsCount, yesCountsRow, noCountsRow);
        else if (i == j)
            return matrixCompatibleFactory.createWithNominator(-1D);
        else
            return matrixCompatibleFactory.createWithNominator(0D);
    }



    private MatrixCompatible getProbabilityCase1(int agents, int yesVoteCount, int noVoteCount){

        double result = -1.0;

        if(yesVoteCount>1)
            result += (double)newton(yesVoteCount,2)/nOver2;

        if(noVoteCount > 1)
            result += (double)newton(noVoteCount,2)/nOver2;

        if(agents-yesVoteCount-noVoteCount > 1)
            result += (double)newton(agents-yesVoteCount-noVoteCount,2) / nOver2;

        return matrixCompatibleFactory.createWithNominator(result);
    }

    private MatrixCompatible getProbabilityCase2(int agents, int yesVoteCount, int noVoteCount){
        double result = 0.0;

        if(yesVoteCount > 0 && agents - yesVoteCount - noVoteCount > 0){
            result+= (double)(yesVoteCount * (agents - yesVoteCount - noVoteCount))/nOver2;
        }

        return matrixCompatibleFactory.createWithNominator(result);
    }

    private MatrixCompatible getProbabilityCase3(int agents, int yesVoteCount, int noVoteCount) {

        double result = 0.0;

        if(noVoteCount > 0 && agents - yesVoteCount - noVoteCount > 0){
            result+=(double)(noVoteCount * (agents - yesVoteCount - noVoteCount))/nOver2;
        }

        return matrixCompatibleFactory.createWithNominator(result);
    }

    private MatrixCompatible getProbabilityCase4(int agents, int yesVoteCount, int noVoteCount){

        double result = 0.0;

        if(noVoteCount > 0 && yesVoteCount > 0)
            result += (double)(noVoteCount * yesVoteCount)/nOver2;

        return matrixCompatibleFactory.createWithNominator(result);

    }




    private void generateKeys(){
        keys = new ArrayList<>();

        for(int i=0; i<=agentsCount; i++){
            for(int j =0; j<=agentsCount-i; j++){
                keys.add(i+","+j);
            }
        }
    }
    
    private void generateMaps(){
        keyToIndex = new HashMap<>();
        indexToKey = new HashMap<>();
        
        int i =0;
        for (String key :
                keys) {
            keyToIndex.put(key, i);
            indexToKey.put(i,key);
            i++;
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

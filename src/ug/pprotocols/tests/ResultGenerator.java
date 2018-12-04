package ug.pprotocols.tests;

import ug.pprotocols.Type;
import ug.pprotocols.algorithm.Mcarlo;
import ug.pprotocols.datatypes.DataType;
import ug.pprotocols.datatypes.MatrixCompatible;
import ug.pprotocols.datatypes.MatrixCompatibleFactory;
import ug.pprotocols.matrix.Case;
import ug.pprotocols.matrix.Equation;
import ug.pprotocols.matrix.MatrixGenerator;

import java.util.HashMap;
import java.util.Map;

public class ResultGenerator {

    private Map<Integer,Integer> testScope;
    private final MatrixCompatibleFactory matrixCompatibleFactory = new MatrixCompatibleFactory(DataType.DOUBLE);

    public ResultGenerator(Map<Integer, Integer> testScope) {
        this.testScope = testScope;
    }

    public Map<Type,Map<Integer,AggregatedResults>> doTests(){
        Map<Type,Map<Integer,AggregatedResults>> testsResults = new HashMap<>();

        testsResults.put(Type.GAUSS,new HashMap<>());
        testsResults.put(Type.GAUSS_SPARSE,new HashMap<>());
        testsResults.put(Type.GAUSS_SEIDEL,new HashMap<>());
        testsResults.put(Type.JACOBIAN,new HashMap<>());
        long start,stop;
        double timeInMiliSeconds;
        MatrixGenerator matrixGenerator;

        for (Integer agentsNumber :
                testScope.keySet()) {
            matrixGenerator = new MatrixGenerator(new Case(0,0,agentsNumber)); //yes\no voters doesnt matter in that case
            double[] monteCarloValues = new Mcarlo(1000000).getAllProbabilities(matrixGenerator.generateKeys());
            for (Type type :
                        testsResults.keySet()) {
                    for(int i = 0; i< testScope.get(agentsNumber); i++) {
                        Equation equationToSolve = matrixGenerator.generateEquation();
                        start = System.nanoTime();




                        stop = System.nanoTime();
                        timeInMiliSeconds = ((stop-start)/1000000D);



                    }

                }





        }



        for (Type type :
                testsResults.keySet()) {
        }






        return testsResults;
    }



    @SuppressWarnings("unchecked")
    private Double calculateAbsoluteError(MatrixCompatible[] goldenVector, MatrixCompatible[] calculatedVector){
        MatrixCompatible absoluteError = matrixCompatibleFactory.createWithNominator(BigInteger.ZERO);
        for(int i = 0; i<goldenVector.length; i++){
            absoluteError = dataOperation.add(absoluteError,dataOperation.subtract(goldenVector[i],calculatedVector[myMatrix.rows[i]]));
        }
        return Math.abs(absoluteError.getDoubleValue())/ (double) goldenVector.length;
    }

}

package ug.pprotocols;

import ug.pprotocols.algorithm.Mcarlo;
import ug.pprotocols.matrix.Case;
import ug.pprotocols.matrix.Equation;
import ug.pprotocols.matrix.MatrixGenerator;
import ug.pprotocols.tests.AggregatedResults;
import ug.pprotocols.tests.ResultGenerator;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {




//        test(new Case(1,1,2));
//        test(new Case(2,1,5));
//        test(new Case(3,3,6));
//        test(new Case(7,1,10));
//        test(new Case(6,7,15));
//        test(new Case(7,4,20));

        Map<Type,Map<Integer,AggregatedResults>> results = generateCsv();
        System.out.println(results);
    }

    public static Map<Type,Map<Integer, AggregatedResults>> generateCsv(){

        Map<Integer,Integer> testScope = new HashMap<Integer, Integer>(){{
            put(10,1);
//            put(20,50);
//            put(30,25);
//            put(40,12);
//            put(50,6);
//            put(60,3);
//            put(70,2);
//            put(80,1);
        }};

        ResultGenerator resultGenerator = new ResultGenerator(testScope);
        return resultGenerator.doTests();
    }

    public static void test(Case caseToAnalise){

        System.out.println(caseToAnalise);
        MatrixGenerator matrixGenerator = new MatrixGenerator(caseToAnalise);
        Equation equation = matrixGenerator.generateEquation();
        System.out.println("Skonczylem generowac lul");

        long start = System.nanoTime();
        System.out.println("GAUSS "+matrixGenerator.getSolution(Type.GAUSS,equation));
        long result = System.nanoTime() - start;
        System.out.println(result);

        start = System.nanoTime();
        System.out.println("GAUSS SPARSE "+matrixGenerator.getSolution(Type.GAUSS_SPARSE,equation));
        result = System.nanoTime() - start;
        System.out.println(result);

        start = System.nanoTime();
        System.out.println("DŻEJKOBI "+matrixGenerator.getSolution(Type.JACOBIAN,equation));
        result = System.nanoTime() - start;
        System.out.println(result);

        start = System.nanoTime();
        System.out.println("GAUSIK Z SEJDELEM RYJU "+matrixGenerator.getSolution(Type.GAUSS_SEIDEL,equation));
        result = System.nanoTime() - start;
        System.out.println(result);

//        Mcarlo mcarlo = new Mcarlo(1000000);
//        System.out.println(mcarlo.countProbability(caseToAnalise));



    }
}

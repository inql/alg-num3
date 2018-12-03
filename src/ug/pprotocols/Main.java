package ug.pprotocols;

import ug.pprotocols.algorithm.GaussImpl;
import ug.pprotocols.algorithm.Mcarlo;
import ug.pprotocols.datatypes.DataType;
import ug.pprotocols.datatypes.DoubleComp;
import ug.pprotocols.datatypes.MatrixCompatible;
import ug.pprotocols.datatypes.MatrixCompatibleFactory;
import ug.pprotocols.matrix.Case;
import ug.pprotocols.matrix.Equation;
import ug.pprotocols.matrix.MatrixGenerator;
import ug.pprotocols.matrix.MyMatrix;
import ug.pprotocols.operations.DoubleOperation;

public class Main {

    public static void main(String[] args) {

//        DoubleComp[][] doubleComps = {{new DoubleComp(10),new DoubleComp(-1),new DoubleComp(2),new DoubleComp(0)},
//                                    {new DoubleComp(-1),new DoubleComp(11),new DoubleComp(-1),new DoubleComp(3)},
//                                    {new DoubleComp(2),new DoubleComp(-1),new DoubleComp(10),new DoubleComp(-1)},
//                                    {new DoubleComp(0),new DoubleComp(3),new DoubleComp(-1),new DoubleComp(8)}};
//
//        MyMatrix<DoubleComp> compMyMatrix = new MyMatrix<>(doubleComps);
//        GaussImpl gi = new GaussImpl(compMyMatrix,new MatrixCompatibleFactory(DataType.DOUBLE), new DoubleOperation(), ChoiceType.PARTIAL);
//
//        DoubleComp[] vectorX = {new DoubleComp(6),new DoubleComp(25),new DoubleComp(-11),new DoubleComp(15)};
//
//        MatrixCompatible[] mc = gi.jacobian(vectorX);
//
//       for (int i = 0; i < mc.length; i++)
//       {
//           System.out.println(mc[i]);
//       }


        test(new Case(32,11,55));
    }

    public static void test(Case caseToAnalise){

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
        System.out.println(matrixGenerator.getSolution(Type.JACOBIAN,equation));
        Mcarlo mcarlo = new Mcarlo(100000);
        System.out.println(mcarlo.countProbability(caseToAnalise));



    }
}

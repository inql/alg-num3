package ug.pprotocols;

import ug.pprotocols.algorithm.GaussImpl;
import ug.pprotocols.algorithm.Mcarlo;
import ug.pprotocols.datatypes.DataType;
import ug.pprotocols.datatypes.DoubleComp;
import ug.pprotocols.datatypes.MatrixCompatible;
import ug.pprotocols.datatypes.MatrixCompatibleFactory;
import ug.pprotocols.matrix.Case;
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


        test(new Case(8,4,15));
    }

    public static void test(Case caseToAnalise){

        MatrixGenerator matrixGenerator = new MatrixGenerator(caseToAnalise);
        System.out.println(matrixGenerator.getSolution(matrixGenerator.generateEquation(),Type.GAUSS));
        System.out.println(matrixGenerator.getSolution(matrixGenerator.generateEquation(),Type.JACOBIAN));
        Mcarlo mcarlo = new Mcarlo(1000000);
        System.out.println(mcarlo.countProbability(caseToAnalise));



    }
}

package ug.pprotocols;

import ug.pprotocols.algorithm.Mcarlo;
import ug.pprotocols.matrix.Case;
import ug.pprotocols.matrix.Equation;
import ug.pprotocols.matrix.MatrixGenerator;
import ug.pprotocols.tests.AggregatedResults;
import ug.pprotocols.tests.ResultGenerator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {


//        test(new Case(1,1,2));
//        test(new Case(2,1,5));
//        test(new Case(3,3,6));
//        test(new Case(7,1,10));
//        test(new Case(6,7,15));
//        test(new Case(7,4,20));

        Map<Type, Map<Integer, AggregatedResults>> results = generateCsv();

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("wyniki.csv"));
            for (Type type :
                    results.keySet()) {

                bufferedWriter.write(type.toString() + "\n\n");
                bufferedWriter.write("liczba agentów;błąd;czas wykonania;ilość wykonań;\n");
                for (Integer agentsNum :
                        new TreeSet<>(results.get(type).keySet())) {
                    bufferedWriter.write("\n"+agentsNum + ";" + results.get(type).get(agentsNum).toString());
                    bufferedWriter.write("\n");
                }
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<Type, Map<Integer, AggregatedResults>> generateCsv() {

        Map<Integer, Integer> testScope = new HashMap<Integer, Integer>() {{
            put(5,1);
            put(6, 1);
            put(7, 1);
//            put(30, 1);
//            put(40, 1);
//            put(50, 6);
//            put(60, 3);
//            put(70, 2);
//            put(80, 1);
        }};

        List<Type> testCases = Arrays.asList(Type.values());

        ResultGenerator resultGenerator = new ResultGenerator(testScope);
        return resultGenerator.doTests(testCases,100000);
    }
}

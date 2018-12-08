package ug.pprotocols;

import ug.pprotocols.tests.AggregatedResults;
import ug.pprotocols.tests.ResultGenerator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Map<Type, Map<Integer, AggregatedResults>> results = generateCsv();

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("wyniki.csv"));
            for (Type type :
                    results.keySet()) {
                bufferedWriter.write("\n\n");
                bufferedWriter.write(type.toString() + "\n");
                bufferedWriter.write("liczba agentów,błąd bezwgledny(max),błąd bezwzględny(średnia),roznica do MonteCarlo,czas wykonania,ilość wykonań,\n");
                for (Integer agentsNum :
                        new TreeSet<>(results.get(type).keySet())) {
                    bufferedWriter.write("\n"+agentsNum + "," + results.get(type).get(agentsNum).toString());
                }

            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<Type, Map<Integer, AggregatedResults>> generateCsv() {

        Map<Integer, Integer> testScope = new HashMap<Integer, Integer>() {{
//            put (3,1);
//            put(4,1);
//            put(5, 1);
//            put(6,1);
//            put(7,1);
//            put(8,1);
//            put(9,1);
//            put(10,1);
////            put(11, 3);
////            put(12, 2);
////            put(15, 1);
////            put(18, 1);
////            put(20, 1);
        }};
        for(int i =3; i<=25; i++){
            testScope.put(i,200);
        }

        Type[] types = Type.values();
        Arrays.sort(types);
        List<Type> testCases = Arrays.asList(types);
//
//        List<Type> testCases = new ArrayList<Type>(){{
//            add(Type.GAUSS);
//            add(Type.GAUSS_SPARSE);
//        }};

        ResultGenerator resultGenerator = new ResultGenerator(testScope);
        return resultGenerator.doTests(testCases,100000);
    }
}

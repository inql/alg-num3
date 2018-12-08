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
                bufferedWriter.write("liczba agentów;błąd bezwgledny;roznica do MonteCarlo;czas wykonania;ilość wykonań;\n");
                for (Integer agentsNum :
                        new TreeSet<>(results.get(type).keySet())) {
                    bufferedWriter.write("\n"+agentsNum + ";" + results.get(type).get(agentsNum).toString());
                }

            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<Type, Map<Integer, AggregatedResults>> generateCsv() {

        Map<Integer, Integer> testScope = new HashMap<Integer, Integer>() {{
            put(5, 8);
            put(6, 8);
            put(7, 7);
            put(8, 6);
            put(9, 5);
            put(10, 4);
            put(11, 3);
            put(12, 2);
            put(15, 1);
            put(18, 1);
            put(20, 1);


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

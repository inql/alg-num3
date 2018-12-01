package ug.pprotocols.matrix;

import ug.pprotocols.datatypes.MatrixCompatible;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatrixGenerator {

    public int agentsCount;
    public List<String> keys;
    public Map<String, Integer> keyToIndex;
    public Map<Integer, String> indexToKey;

    double nOver2;

    public int numberOfEquations;

    public MatrixGenerator(int n){

        this.agentsCount = n;
        generateKeys();
        generateMaps();
        this.numberOfEquations = keys.size();

        this.nOver2 = (double)newton((long)n,2L);
    }








    private void generateKeys(){
        keys = new ArrayList<>();

        for(int i=0; i<agentsCount; i++){
            for(int j =0; j<agentsCount; j++){
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

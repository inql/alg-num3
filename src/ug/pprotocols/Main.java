package ug.pprotocols;

import ug.pprotocols.algorithm.Mcarlo;

public class Main {

    public static void main(String[] args) {

        Mcarlo mc = new Mcarlo(10000);
        System.out.println(mc.countProbability(30,18));

    }
}

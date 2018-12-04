package ug.pprotocols.tests;

public class Results {

    double absoluteError;
    double executionTime;

    public Results(double absoluteError, double executionTime) {
        this.absoluteError = absoluteError;
        this.executionTime = executionTime;
    }

    @Override
    public String toString() {
        return absoluteError+";"+executionTime+";";
    }




}

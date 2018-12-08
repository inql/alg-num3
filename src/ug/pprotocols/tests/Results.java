package ug.pprotocols.tests;

public class Results {

    double absoluteError;
    double executionTime;
    double differenceFromMonteCarlo;

    public Results(double absoluteError, double executionTime, double differenceFromMonteCarlo) {
        this.absoluteError = absoluteError;
        this.executionTime = executionTime;
        this.differenceFromMonteCarlo = differenceFromMonteCarlo;
    }

    @Override
    public String toString() {
        return absoluteError+";"+differenceFromMonteCarlo+";"+executionTime+";";
    }




}

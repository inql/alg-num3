package ug.pprotocols.matrix;

public class Case {

    final int yesVoters;
    final int noVoters;
    final int totalVoters;

    public Case(int yesVoters, int noVoters, int totalVoters) {
        this.yesVoters = yesVoters;
        this.noVoters = noVoters;
        this.totalVoters = totalVoters;
    }

    public int getNoVoters() {
        return noVoters;
    }

    public int getTotalVoters() {
        return totalVoters;
    }

    public int getYesVoters() {
        return yesVoters;
    }
}

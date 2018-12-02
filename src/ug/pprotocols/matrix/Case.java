package ug.pprotocols.matrix;

public class Case {

    private final int yesVoters;
    private final int noVoters;
    private final int totalVoters;
    private final int undecidedVoters;

    public Case(int yesVoters, int noVoters, int totalVoters) {
        this.yesVoters = yesVoters;
        this.noVoters = noVoters;
        this.totalVoters = totalVoters;
        this.undecidedVoters = totalVoters-yesVoters-noVoters;
        if(undecidedVoters<0)
            throw new IllegalArgumentException("Wrong number of voters!");
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

    public int getUndecidedVoters() {
        return undecidedVoters;
    }
}

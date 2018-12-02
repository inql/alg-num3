package ug.pprotocols.algorithm;

import ug.pprotocols.matrix.Case;

import java.util.Random;

public class Mcarlo {

    final Random random;
    final int numOfIterations;


    public Mcarlo(int numOfIterations)
    {
        random = new Random();
        this.numOfIterations = numOfIterations;
    }

    public State[] createArrayOfAgents(int total, int Y, int N)
    {
        State[] allAgents = new State[total];
        for (int i = 0; i < Y; i++)
        {
            allAgents[i] = State.YES;
        }
        for (int i = Y; i < Y+N; i++)
        {
            allAgents[i] = State.NO;
        }
        for (int i = N+Y; i < total; i++)
        {
            allAgents[i] = State.UNDECIDED;
        }


        return allAgents;

    }


    public double countProbability(Case caseOfProgram) {

        State[] motherArray = createArrayOfAgents(caseOfProgram.getTotalVoters(),caseOfProgram.getYesVoters(), caseOfProgram.getNoVoters());
        int countYes = 0;

        for (int i = 0; i < numOfIterations; i++)
        {
            State[] allAgents = motherArray.clone();

            while (!areSameStatesArray(allAgents))
            {
                shuffleArray(allAgents);

                allAgents[0] = changePair(allAgents[0],allAgents[1]);
                allAgents[1] = changePair(allAgents[1],allAgents[0]);

            }
            if (allAgents[0] == State.YES)
                countYes++;

        }


        return (double)countYes/numOfIterations;

    }

    public State randomUndecidedOrNo()
    {
        if (random.nextInt() % 2 == 1)
            return State.UNDECIDED;
        else return State.NO;
    }

    public State changePair(State o1, State o2)
    {
        if ((o1 == State.YES && o2 == State.UNDECIDED) || (o2 == State.YES && o1 == State.UNDECIDED))
            return State.YES;
        else if ((o1 == State.YES && o2 == State.NO) || (o2 == State.YES && o1 == State.NO))
            return State.UNDECIDED;
        else if ((o1 == State.UNDECIDED && o2 == State.NO) || (o2 == State.UNDECIDED && o1 == State.NO))
            return State.NO;

        return o1;
    }



    public void shuffleArray(State[] ar)
    {
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = random.nextInt(i + 1);
            State a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    public boolean areSameStatesArray(State[] a){
        for(int i=1; i<a.length; i++){
            if(a[0] != a[i]){
                return false;
            }
        }

        return true;
    }




}

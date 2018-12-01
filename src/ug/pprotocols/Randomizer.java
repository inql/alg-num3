package ug.pprotocols;

import java.util.Random;

public class Randomizer {

    Random random;

    public Randomizer(int seed)
    {
        random = new Random(seed);
    }
    public Randomizer()
    {
        random = new Random();
    }

    public int randomNumber()
    {
        return random.ints(-65536,(65536)).findFirst().getAsInt();
    }

}

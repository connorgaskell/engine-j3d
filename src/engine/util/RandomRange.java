package engine.util;

import java.util.Random;

public class RandomRange {

    public float randomFloat(float min, float max) {
        Random r = new Random();
        return min + r.nextFloat() * (max - min);
    }
    
    public int randomInteger(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
    
}

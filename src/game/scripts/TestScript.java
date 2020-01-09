package game.scripts;

import engine.script.StandardScript;

public class TestScript extends StandardScript {

    @Override
    public void start() {
        System.out.println("This message will appear once when the program starts.");
    }

    @Override
    public void update() {
        System.out.println("This message will appear 1ms");
    }

    @Override
    public void frameUpdate() {
        System.out.println("This message will appear every frame.");
    }

    @Override
    public void lateUpdate() {

    }
    
}

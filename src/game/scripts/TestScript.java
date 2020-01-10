package game.scripts;

import engine.game.Game;
import engine.objects.primitives.*;
import engine.script.StandardScript;
import java.awt.Color;
import javax.vecmath.*;

public class TestScript extends StandardScript {

    @Override
    public void start() {
        System.out.println("This message will appear once when the program starts.");
        for(int x = -20; x < 20; x++) {
            for(int z = -20; z < 20; z++) {
                if(x % 2 == 0) {
                    if(z % 2 == 0) {
                        new Plane(new Vector3d(x * 20, 0, z * 20), new Quat4d(180, 0, 0, 0), 10, 10, new Color3f(Color.WHITE), Game.worldGroup, "Test");
                    } else {
                        new Plane(new Vector3d(x * 20, 0, z * 20), new Quat4d(180, 0, 0, 0), 10, 10, new Color3f(Color.GRAY), Game.worldGroup, "Test");
                    }
                } else {
                    if(z % 2 == 0) {
                        new Plane(new Vector3d(x * 20, 0, z * 20), new Quat4d(180, 0, 0, 0), 10, 10, new Color3f(Color.GRAY), Game.worldGroup, "Test");
                    } else {
                        new Plane(new Vector3d(x * 20, 0, z * 20), new Quat4d(180, 0, 0, 0), 10, 10, new Color3f(Color.WHITE), Game.worldGroup, "Test");
                    }
                }
            }
        }
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

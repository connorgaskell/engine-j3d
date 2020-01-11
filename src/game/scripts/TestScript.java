package game.scripts;

import engine.core.Engine;
import engine.objects.Camera;
import engine.objects.primitives.*;
import engine.script.StandardScript;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.vecmath.*;

public class TestScript extends StandardScript {

    private Camera camera;
    
    @Override
    public void start() {
        camera = new Camera();
        
        System.out.println(camera.getPosition());
        System.out.println("This message will appear once when the program starts.");
        for(int x = -20; x < 20; x++) {
            for(int z = -20; z < 20; z++) {
                if(x % 2 == 0) {
                    if(z % 2 == 0) {
                        new Plane(new Vector3d(x * 20, 0, z * 20), new Quat4d(180, 0, 0, 0), 10, 10, new Color3f(Color.WHITE), Engine.worldGroup, "Test");
                    } else {
                        new Plane(new Vector3d(x * 20, 0, z * 20), new Quat4d(180, 0, 0, 0), 10, 10, new Color3f(Color.GRAY), Engine.worldGroup, "Test");
                    }
                } else {
                    if(z % 2 == 0) {
                        new Plane(new Vector3d(x * 20, 0, z * 20), new Quat4d(180, 0, 0, 0), 10, 10, new Color3f(Color.GRAY), Engine.worldGroup, "Test");
                    } else {
                        new Plane(new Vector3d(x * 20, 0, z * 20), new Quat4d(180, 0, 0, 0), 10, 10, new Color3f(Color.WHITE), Engine.worldGroup, "Test");
                    }
                }
            }
        }

    }

    @Override
    public void update() {
        System.out.println("This message will appear every 1ms");
    }

    @Override
    public void frameUpdate() {
        //System.out.println("This message will appear every frame.");
    }

    @Override
    public void lateUpdate() {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
                camera.setPosition(new Vector3f(camera.getPosition().x, camera.getPosition().y, camera.getPosition().z - 4f));
                break;
                
            case KeyEvent.VK_S:
                camera.setPosition(new Vector3f(camera.getPosition().x, camera.getPosition().y, camera.getPosition().z + 4f));
                break;
                
            case KeyEvent.VK_A:
                camera.setPosition(new Vector3f(camera.getPosition().x - 4f, camera.getPosition().y, camera.getPosition().z));
                break;
            
            case KeyEvent.VK_D:
                camera.setPosition(new Vector3f(camera.getPosition().x + 4f, camera.getPosition().y, camera.getPosition().z));
                break;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

}

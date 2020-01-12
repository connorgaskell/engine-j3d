package game.scripts;

import engine.core.Engine;
import engine.objects.Camera;
import engine.objects.primitives.*;
import engine.script.StandardScript;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.*;
import javax.vecmath.*;

public class TestScript extends StandardScript {

    private Camera camera;
    private final Set<Character> pressed = new HashSet<Character>();
    
    @Override
    public void start() {
        camera = new Camera();
        posX = camera.getPosition().x;
        posY = camera.getPosition().y;
        posZ = camera.getPosition().z;
        
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
        //camera.lookAt(new Point3d(rotX, rotY, rotZ));
        //camera.setPosition(new Vector3f(posX, posY, posZ));
    }

    @Override
    public void lateUpdate() {

    }

    @Override
    public synchronized void keyTyped(KeyEvent e) {
        
    }

    float rotX, rotY, rotZ = 0;
    float posX, posY, posZ = 0;
    float rotSpeed = 4;
    float translateSpeed = 4;
    
    @Override
    public synchronized void keyPressed(KeyEvent e) {
        pressed.add(e.getKeyChar());
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
                camera.translate(new Vector3f(0, 0, -translateSpeed));
                posZ -= translateSpeed;
                break;
                
            case KeyEvent.VK_S:
                camera.translate(new Vector3f(0, 0, translateSpeed));
                posZ += translateSpeed;
                break;
                
            case KeyEvent.VK_A:
                camera.translate(new Vector3f(-translateSpeed, 0, 0));
                posX -= translateSpeed;
                break;
            
            case KeyEvent.VK_D:
                camera.translate(new Vector3f(translateSpeed, 0, 0));
                posX += translateSpeed;
                break;
                
            case KeyEvent.VK_UP:
                rotY += rotSpeed;
                break;
                
            case KeyEvent.VK_DOWN:
                rotY -= rotSpeed;
                break;
                
            case KeyEvent.VK_LEFT:
                rotX -= rotSpeed;
                break;
                
            case KeyEvent.VK_RIGHT:
                rotX += rotSpeed;
                break;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressed.remove(e.getKeyChar());
    }

}

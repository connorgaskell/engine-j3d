package game.scripts;

import engine.behaviors.Pick;
import engine.materials.ObjectMaterial;
import engine.objects.Camera;
import engine.objects.GameObject;
import engine.objects.PrimitiveType;
import engine.objects.light.LightType;
import engine.objects.primitives.Text3D;
import engine.script.StandardScript;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.media.j3d.Fog;
import javax.media.j3d.Light;
import javax.vecmath.*;

public class TestScript extends StandardScript {

    private Camera camera;
    private Pick mouse;
    private GameObject cube, humanoid;
    private Text3D text, mousePosText;
    private int count = 0;
    private final Set<Character> pressed = new HashSet<Character>();
    
    @Override
    public void start() {
        camera = new Camera();
        
        ArrayList<String> layers = new ArrayList<>();
        layers.add("Floor");
        
        mouse = enableMouse(layers);
        posX = camera.getPosition().x;
        posY = camera.getPosition().y;
        posZ = camera.getPosition().z;
        camera.setPosition(new Vector3f(posX, posY + 50, posZ + 50));
        
        System.out.println(camera.getPosition());
        System.out.println("This message will appear once when the program starts.");
        
        System.out.println(randomFloat(0, 10));
        System.out.println(randomInt(0, 10));
        
        Fog expFog = fog(new Color3f(Color.WHITE), 0.0025f);
        expFog.setColor(new Color3f(Color.GRAY));
        
        //Fog linearFog = fog(new Color3f(Color.WHITE), 50.0f, 200.5f);
        
        sky("./res/example_sky.jpg");
   
        Light ambientLight = light(LightType.AMBIENT, new Point3f(5, 20, 5f), new Color3f(0.25f, 0.25f, 0.25f));
        Light directionalLight1 = light(LightType.DIRECTIONAL, new Point3f(0f, -1f, 0f), new Color3f(1f, 1f, 1f));
        Light directionalLight2 = light(LightType.DIRECTIONAL, new Point3f(-2f, -1f, -1f), new Color3f(1f, 1f, 1f));
        
        ObjectMaterial texturedMaterial = new ObjectMaterial(new Color3f(0.7f, .15f, .15f), new Color3f(0.0f, 0.0f, 0.0f), new Color3f(0.7f, .15f, .15f), new Color3f(0.0f, 0.0f, 0.0f), 1.0f, "./res/wrapping_paper.jpg");
        ObjectMaterial grassTexture = new ObjectMaterial(new Color3f(1.0f, 1.0f, 1.0f), new Color3f(1.0f, 1.0f, 1.0f), new Color3f(1.0f, 1.0f, 1.0f), new Color3f(0.0f, 0.0f, 0.0f), 1.0f, "./res/grass.png");
        ObjectMaterial stoneTexture = new ObjectMaterial(new Color3f(1.0f, 1.0f, 1.0f), new Color3f(1.0f, 1.0f, 1.0f), new Color3f(1.0f, 1.0f, 1.0f), new Color3f(0.0f, 0.0f, 0.0f), 1.0f, "./res/stone.jpg");
        
        ObjectMaterial redMaterial = new ObjectMaterial(new Color3f(0.7f, .15f, .15f), new Color3f(0.0f, 0.0f, 0.0f), new Color3f(0.7f, .15f, .15f), new Color3f(0.0f, 0.0f, 0.0f), 1.0f);
        ObjectMaterial grayMaterial = new ObjectMaterial(new Color3f(Color.BLACK), new Color3f(0.0f, 0.0f, 0.0f), new Color3f(Color.BLACK), new Color3f(Color.GRAY), 1.0f);
        ObjectMaterial whiteMaterial = new ObjectMaterial(new Color3f(Color.BLACK), new Color3f(Color.WHITE), new Color3f(Color.WHITE), new Color3f(Color.WHITE), 1.0f);
        ObjectMaterial blueMaterial = new ObjectMaterial(new Color3f(Color.BLUE), new Color3f(Color.BLUE), new Color3f(Color.BLUE), new Color3f(Color.BLUE), 1.0f);
        
        cube = instantiate(PrimitiveType.CUBE, new Vector3f(0, 10, 0), new Quat4d(180, 0, 0, 0), new Vector3f(10, 10, 10), texturedMaterial);
        cube.setPosition(new Vector3f(20, 10, -20));
        GameObject sphere = instantiate(PrimitiveType.SPHERE, new Vector3f(20, 10, 0), new Quat4d(180, 0, 0, 0), new Vector3f(10, 10, 10), texturedMaterial);
        GameObject cone = instantiate(PrimitiveType.CONE, new Vector3f(40, 10, 0), new Quat4d(180, 0, 0, 0), new Vector3f(10, 50, 10), texturedMaterial);
        GameObject cyl = instantiate(PrimitiveType.CYLINDER, new Vector3f(60, 10, 0), new Quat4d(180, 0, 0, 0), new Vector3f(10, 50, 10), texturedMaterial);
        
        humanoid = instantiate("./res/humanoid_mesh.obj", new Vector3f(60, 10, 0), new Quat4d(180, 0, 0, 0), new Vector3f(25, 25, 25), texturedMaterial);
        humanoid.setPosition(new Vector3f(100, 25, 0));
        humanoid.setPickable(false);
        
        text = text3d("Hello, world!", new Vector3f(0, 5, -100), blueMaterial);
        mousePosText = text3d("", new Vector3f(-200, 5, -100), blueMaterial);
        
        for(int x = -20; x < 20; x++) {
            for(int z = -20; z < 20; z++) {
                if(x % 2 == 0) {
                    if(z % 2 == 0) {
                        instantiate(PrimitiveType.PLANE, new Vector3f(x * 10, 0, z * 10), new Quat4d(180, 0, 0, 0), new Vector3f(10, 10, 10), stoneTexture);
                    } else {
                        instantiate(PrimitiveType.PLANE, new Vector3f(x * 10, 0, z * 10), new Quat4d(180, 0, 0, 0), new Vector3f(10, 10, 10), grassTexture);
                    }
                } else {
                    if(z % 2 == 0) {
                        instantiate(PrimitiveType.PLANE, new Vector3f(x * 10, 0, z * 10), new Quat4d(180, 0, 0, 0), new Vector3f(10, 10, 10), grassTexture);
                    } else {
                        instantiate(PrimitiveType.PLANE, new Vector3f(x * 10, 0, z * 10), new Quat4d(180, 0, 0, 0), new Vector3f(10, 10, 10), stoneTexture);
                    }
                }
            }
        }
        
        //instantiate("Path/To/File", new Vector3f(0, 0, 0), new Quat4d(0, 0, 0, 0), new Vector3f(1, 1, 1), Engine.worldGroup);
    }

    @Override
    public void update() {
        //System.out.println("This message will appear every 1ms");
        text.setText("Elapsed Time: " + Integer.toString((count++) / 60) + "s");
        mousePosText.setText("Mouse: " + Integer.toString((int)Math.floor(mousePosition(mouse).x)) + ", " + Integer.toString((int)Math.floor(mousePosition(mouse).y)) + ", " + Integer.toString((int)Math.floor(mousePosition(mouse).z)));
        //text.setText(Integer.toString((int)Math.floor(mousePosition().x)));
    }

    @Override
    public void frameUpdate() {
        //camera.lookAt(new Point3d(rotX, rotY, rotZ));
        //camera.setPosition(new Vector3f(posX, posY, posZ));
        //cube.setPosition(new Vector3f(cube.getPosition().x, cube.getPosition().y, cube.getPosition().z + 0.1f));
        //cube.setPosition(new Vector3f(mousePosition().x, 0, mousePosition().z));
        cube.setRotation(new Quat4d(0, cube.getRotation().y + 0.1f, 0, 1));
        //System.out.println(mousePosition().x);
        //System.out.println(mousePosition());
        
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

    @Override
    public void onMousePress(MouseEvent evt) {
        System.out.println(evt);
        humanoid.setPosition(new Vector3f((float)mousePosition(mouse).x, humanoid.getPosition().y, (float)mousePosition(mouse).z));
    }
    
    @Override
    public void onMouseRelease(MouseEvent evt) {
        System.out.println(evt);
    }

    @Override
    public void onMouseMove(MouseEvent evt) {

    }

}

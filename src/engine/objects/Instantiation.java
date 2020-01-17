package engine.objects;

import engine.core.Engine;
import engine.objects.light.Ambient;
import engine.objects.light.LightType;
import engine.objects.light.Point;
import engine.objects.primitives.Ball;
import engine.objects.primitives.Cube;
import engine.objects.primitives.Plane;
import javax.media.j3d.BranchGroup;
import javax.vecmath.*;

/*
 * The Instantiation class takes a Prefab object and creates a GameObject.
 * The initial position and rotation are also defined here.
 */

public class Instantiation {
    
    public void add(Prefab prefab, Vector3f pos, Quat4d rot, Vector3f scale, BranchGroup branchGroup) {
        /*
         * Perform a check to see if the prefab contains a Mesh or is a primitive type.
         * If it's a primitive, then call the other add() method.
         */
        
        prefab.setPosition(pos);
        prefab.setRotation(rot);
        
        BranchGroup gameObjectGroup = new BranchGroup();
        gameObjectGroup.addChild(prefab.gameObject);
        branchGroup.addChild(gameObjectGroup);
    }
    
    public void add(PrimitiveType primitive, Vector3f pos, Quat4d rot, Vector3f scale, Color3f col) {
        switch(primitive) {
            case PLANE:
                new Plane(pos, rot, scale.x, scale.z, col, Engine.worldGroup, "");
                break;
            case CUBE:
                new Cube(pos, rot, scale, col, Engine.worldGroup, "");
                break;
            case SPHERE:
                new Ball(pos, rot, scale.x, col, Engine.worldGroup, "");
                break;
        }
    }
    
    public void add(LightType light, Point3f pos, Color3f col) {
        switch(light) {
            case AMBIENT:
                new Ambient(col);
                break;
            case DIRECTIONAL:
                
                break;
            case POINT:
                new Point(pos, col);
                break;
        }
    }
    
}

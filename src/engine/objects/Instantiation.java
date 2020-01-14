package engine.objects;

import engine.core.Engine;
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
    
    public void add(PrimitiveType primitive, Vector3d pos, Quat4d rot, Vector3f scale, Color3f col) {
        switch(primitive) {
            case PLANE:
                new Plane(pos, rot, scale.x, scale.z, col, Engine.worldGroup, "");
                break;
        }
    }
    
}

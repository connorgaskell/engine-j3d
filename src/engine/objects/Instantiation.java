package engine.objects;

import javax.media.j3d.BranchGroup;
import javax.vecmath.*;

/*
 * The Instantiation class takes a Prefab object and creates a GameObject.
 * The initial position and rotation are also defined here.
 */

public class Instantiation {
    
    public void add(Prefab prefab, Vector3f pos, Quat4d rot, Vector3f scale, BranchGroup branchGroup) {
        prefab.setPosition(pos);
        prefab.setRotation(rot);
        
        BranchGroup gameObjectGroup = new BranchGroup();
        gameObjectGroup.addChild(prefab.gameObject);
        branchGroup.addChild(gameObjectGroup);
    }
    
}

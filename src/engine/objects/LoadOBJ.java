package engine.objects;

import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.vecmath.Quat4d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

public class LoadOBJ extends GameObject {
    
    public Shape3D shape;
    
    public LoadOBJ(String path, Vector3f pos, Quat4d rot, Vector3f scale, BranchGroup branchGroup) {
        ObjectFile objLoader = new ObjectFile(ObjectFile.RESIZE);
        Scene scene = null;
        
        try {
            /*
             * A Prefab file may include a mesh or a defined primitive object.
             * Location of the mesh should be parsed from the Prefab.
             */
            File file = new File(path);
            scene = objLoader.load(file.toURI().toURL());
        } catch (ParsingErrorException | MalformedURLException | FileNotFoundException e) { }
        
        BranchGroup tempGroup = scene.getSceneGroup();
        tempGroup.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
        tempGroup.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        tempGroup.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
        tempGroup.setCapability(BranchGroup.ALLOW_DETACH);
        
        shape = (Shape3D)tempGroup.getChild(0);
        
        Transform3D scaleTransform = new Transform3D();
        /*
         * Scale is to be parsed from the Prefab.
         */
        scaleTransform.setScale(new Vector3d(scale.x, scale.y, scale.z));
        bounds.setTransform(scaleTransform);
        bounds.addChild(shape.getParent());
      
        setPosition(pos);
        //setRotation(rot);
        
        BranchGroup gameObjectGroup = new BranchGroup();
        gameObjectGroup.addChild(gameObject);
        branchGroup.addChild(gameObjectGroup);
    }
    
}

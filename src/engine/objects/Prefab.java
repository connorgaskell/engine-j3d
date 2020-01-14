package engine.objects;

import com.sun.j3d.loaders.*;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import java.io.*;
import java.net.*;
import javax.media.j3d.*;

public class Prefab extends GameObject {
    
    public Shape3D shape;
    
    public Prefab(String prefabFile) {
        ObjectFile objLoader = new ObjectFile(ObjectFile.RESIZE);
        Scene scene = null;
        
        try {
            /*
             * A Prefab file may include a mesh or a defined primitive object.
             * Location of the mesh should be parsed from the Prefab.
             */
            File file = new File(".MeshPath");
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
        scaleTransform.setScale(1.0);
        bounds.setTransform(scaleTransform);
        bounds.addChild(shape.getParent());
    }
    
}

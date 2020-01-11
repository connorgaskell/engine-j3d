package engine.script;

import engine.core.Engine;
import javax.media.j3d.*;

/*
 * This class will likely be replaced by some kind of drag and drop functionality, where the developer
 * can drag a Script onto a GameObject, the Script(Behavior) will be added as a child of that object.
 *
 * TODO: Load script names and the Node the script should be attached to from Scripts.json.
 *       getNode() should use some other method of identification (UID's for example) as some Nodes (such as GameObjects) will likely share the same name.
 */

public class ScriptLoader {
    
    /*
     * The ScriptLoader class adds Scripts to Nodes in the Scene based on data in Scripts.json
     * @throws ClassNotFound Exception
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public ScriptLoader() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        StandardScript script = (StandardScript)Class.forName("game.scripts.TestScript").newInstance();
        ((BranchGroup)getNode("worldGroup")).addChild(script);
        Engine.scene.getCanvas().addKeyListener(script);
    }

    /*
     * Gets a Node from all nodes present in the scene (nodeList) from the name of the node.
     * @param nodeName The name of the Node the Script will be attached to.
     */
    private Node getNode(String nodeName) {
        for (Node node : Engine.nodeList) {
            if (nodeName.equals(node.getName())) {
                return node;
            }
        }
        return Engine.rootGroup;
    }
    
}

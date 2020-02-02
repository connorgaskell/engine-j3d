package engine.behaviors;

import engine.Settings;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.picking.*;
import java.util.ArrayList;

public class Pick extends PickMouseBehavior {

    private Point3d intercept;
    
    public Point3d get3DIntercept() {
        return intercept;
    }
    
    public Pick(Canvas3D canvas3D, BranchGroup branchGroup, Bounds bounds, ArrayList<String> layers) {
        super(canvas3D, branchGroup, bounds, layers);
        setSchedulingBounds(Settings.INFINITE_BOUNDS);
        pickCanvas.setMode(PickTool.GEOMETRY_INTERSECT_INFO);
    }

    @Override
    public void updateScene(int xPos, int yPos, ArrayList<String> layers) {
        pickCanvas.setShapeLocation(xPos, yPos);

        Point3d eyePos = pickCanvas.getStartPosition();

        PickResult[] pickResult;
        pickResult = pickCanvas.pickAllSorted();
        PickIntersection pickIntersection;

        if(pickResult != null) {
            for (PickResult result : pickResult) {
                try {
                    if (layers.contains(result.getObject().getName())) {
                        pickIntersection = result.getClosestIntersection(eyePos);
                        intercept = pickIntersection.getPointCoordinatesVW();
                    }
                } catch(NullPointerException e) { }
            }
        }
    }
    
}

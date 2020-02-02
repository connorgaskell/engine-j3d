package engine.behaviors;

import engine.Settings;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.picking.*;

public class Pick extends PickMouseBehavior {

    private Point3d intercept;
    
    public Point3d get3DIntercept() {
        return intercept;
    }
    
    public Pick(Canvas3D canvas3D, BranchGroup branchGroup, Bounds bounds) {
        super(canvas3D, branchGroup, bounds);
        setSchedulingBounds(Settings.INFINITE_BOUNDS);
        pickCanvas.setMode(PickTool.GEOMETRY_INTERSECT_INFO);
    }

    @Override
    public void updateScene(int xPos, int yPos) {
        pickCanvas.setShapeLocation(xPos, yPos);

        Point3d eyePos = pickCanvas.getStartPosition();

        PickResult pickResult;
        pickResult = pickCanvas.pickClosest();

        if(pickResult != null) {
            PickIntersection pickIntersection = pickResult.getClosestIntersection(eyePos);
            intercept = pickIntersection.getPointCoordinatesVW();
        }
    }
    
}

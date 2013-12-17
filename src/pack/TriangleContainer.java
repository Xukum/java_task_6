package pack;

import secondName.Point3D;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: salerat
 * Date: 12/16/13
 * Time: 6:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class TriangleContainer {
    public Point3D leftAnglePoint;
    public Point3D topAnglePoint;
    public Point3D rightAnglePoint;
    public Point3D middleTrianglePoint;
    public double[] normal;
    public Color currentTriangleColor;
    public double[] Kd;
    public double[] Kf;

    public TriangleContainer clone(){
        TriangleContainer ret = new TriangleContainer();
        if (leftAnglePoint!=null) ret.leftAnglePoint = new Point3D(leftAnglePoint);
        if (topAnglePoint!=null) ret.topAnglePoint = new Point3D(topAnglePoint);
        if (rightAnglePoint!=null) ret.rightAnglePoint = new Point3D(rightAnglePoint);
        if (middleTrianglePoint!=null) ret.middleTrianglePoint = new Point3D(middleTrianglePoint);
        if (normal!=null) ret.normal = normal.clone();
        if (currentTriangleColor!=null) ret.currentTriangleColor = new Color(currentTriangleColor.getRGB());
        if (Kd!=null) ret.Kd = Kd.clone();
        if (Kf!=null) ret.Kf = Kf.clone();
        return ret;

    }

    public Point3D[] getAllPoints(){
        Point3D[] ret = new Point3D[3];
        ret[0] = leftAnglePoint;
        ret[1] = topAnglePoint;
        ret[2] = rightAnglePoint;
//        ret[3] = middleTrianglePoint;
        return ret;
    }
}

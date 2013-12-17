package secondName;

/**
 * Created with IntelliJ IDEA.
 * User: salerat
 * Date: 12/9/13
 * Time: 12:32 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;

public class BsplineModel {
    public List<Point3D> pts =  new ArrayList<Point3D>();

    public static double a = 0;
    public static double b = 100;
    public static double c = 0;
    public static double d = 100;

    public double xCof;
    public double yCof;
    public double r;


    public double k = 40.0;
    // the basis function for a cubic B spline
    double b( int i, double t ) {
        switch( i ) {
            case -2:
                return (((-t + 3) * t - 3) * t + 1) / 6;
            case -1:
                return (((3 * t - 6) * t) * t + 4) / 6;
            case 0:
                return (((-3 * t + 3) * t + 3) * t + 1) / 6;
            case 1:
                return (t * t * t) / 6;
        }
        return 0; // we only get here if an invalid i is specified
    }

    // evaluate a point on the B spline
    private Point3D p( int i, double t ) {
        double px = 0;
        double py = 0;

        for( int j = -2; j <= 1; j++ ) {
            Point3D coordinate = pts.get(i + j);
            px += b(j, t) * coordinate.getX();
            py += b(j, t) * coordinate.getY();
        }
        return new Point3D(px*xCof, py*yCof, r);
    }
    public void sortDots() {
        Collections.sort(pts, new PointCompare());
    }
    public Point3D[] getInterpolated(double With, double Height, double radius) {

        xCof = 1;
        yCof = 1;
        r = radius;

        List<Point3D> interpolatedCoordinates = new ArrayList<Point3D>();
        Point3D q = p(2, 0);
        interpolatedCoordinates.add(q);
        for( int i = 2; i < pts.size() - 1; i++ ) {
            for( int j = 1; j <= k; j++ ) {
                q = p(i, j / k);
                interpolatedCoordinates.add(q);
            }
        }
        Point3D[] pt = new Point3D[interpolatedCoordinates.size()];
        pt=interpolatedCoordinates.toArray(pt);
        return pt;
    }
}

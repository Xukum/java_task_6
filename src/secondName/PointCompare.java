package secondName;

/**
 * Created with IntelliJ IDEA.
 * User: salerat
 * Date: 12/9/13
 * Time: 3:31 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.Comparator;


public class PointCompare implements Comparator<Point3D> {
    public int compare(Point3D a, Point3D b) {
        if (a.getX() < b.getX()) {
            return -1;
        }
        else if (a.getX() > b.getX()) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
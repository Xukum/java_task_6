package secondName;



/**
 * Created with IntelliJ IDEA.
 * User: afonia
 * Date: 12/9/13
 * Time: 12:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class MatrixArifmetikModel {

    public static Point3D RotateByX(Point3D p, double angle){
        double x = p.getX();
        double y = p.getY()*Math.cos(angle) - p.getZ()*Math.sin(angle);
        double z = p.getY()*Math.sin(angle) + p.getZ()*Math.cos(angle);
        return new Point3D(x,y,z);
    }

    public static Point3D RotateByY(Point3D p, double angle){
        double x = p.getX()*Math.cos(angle) + p.getZ()*Math.sin(angle);
        double y = p.getY();
        double z = -p.getX()*Math.sin(angle) + p.getZ()*Math.cos(angle);
        return new Point3D(x,y,z);
    }

    public static Point3D RotateByZ(Point3D p, double angle){
        double x = p.getX()*Math.cos(angle) - p.getY()*Math.sin(angle);
        double y = p.getX()*Math.sin(angle) + p.getY()*Math.cos(angle);
        double z = p.getZ();
        return new Point3D(x,y,z);
    }

    public static void MovePoints(Point3D[] p, Point3D pmove){
        for(Point3D p0:p){
            MovePoint(p0,pmove);
        }
    }

    public static void MovePoint(Point3D p, Point3D pmove){
        p.x+=pmove.x;
        p.y+=pmove.y;
        p.z+=pmove.z;
    }

}

package secondName;


import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: afonia
 * Date: 12/9/13
 * Time: 12:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class SegmentModel {
    public Point3D[] points;
    public Color color;
    public SegmentModel(){

    }
    public SegmentModel(Point3D[] points){
        this.points = points;
    }

    public static SegmentModel RotateSegmentByX(SegmentModel segment, double angle){
        SegmentModel ret = new SegmentModel();
        ret.points = new Point3D[segment.points.length];
        for (int i = 0; i<ret.points.length; i++){
             ret.points[i] = MatrixArifmetikModel.RotateByX(segment.points[i], angle);
        }
        return ret;
    }

    public static SegmentModel RotateSegmentByY(SegmentModel segment, double angle){
        SegmentModel ret = new SegmentModel();
        ret.points = new Point3D[segment.points.length];
        for (int i = 0; i<ret.points.length; i++){
            ret.points[i] = MatrixArifmetikModel.RotateByY(segment.points[i], angle);
        }
        return ret;
    }

    public static SegmentModel RotateSegmentByZ(SegmentModel segment, double angle){
        SegmentModel ret = new SegmentModel();
        ret.points = new Point3D[segment.points.length];
        for (int i = 0; i<ret.points.length; i++){
            ret.points[i] = MatrixArifmetikModel.RotateByZ(segment.points[i], angle);
        }
        return ret;
    }

    public void move(Point3D pOffset) {
        MatrixArifmetikModel.MovePoints(points,pOffset);
    }

    public void fit(){

        double sizeX = 2;
        double sizeY = 2;
        double sizeZ = 1;

        double maxX = points[0].x;
        double minX = points[0].x;
        double maxY = points[0].y;
        double minY = points[0].y;
        double maxZ = points[0].z;
        double minZ = points[0].z;
        for (Point3D p : points){
            if(maxX<p.x) maxX = p.x;
            if(minX>p.x) minX = p.x;
            if(maxY<p.x) maxY = p.y;
            if(minY>p.x) minY = p.y;
            if(maxZ<p.x) maxZ = p.z;
            if(minZ>p.x) minZ = p.z;
            ////System.out.println(p.x + " " + p.y +" " + p.z );
        }

        double xCoff = 1;
        double yCoff = 1;
        double zCoff = 1;

        if(maxX>minX)xCoff = sizeX/(maxX - minX);
        if(maxY>minY)yCoff = sizeY/(maxY - minY);
        if(maxZ>minZ)zCoff = sizeZ/(maxZ - minZ);

        for (Point3D p: points){
            p.x = (p.x - minX)*xCoff - 1;
            p.y = (p.y - minY)*yCoff - 1;
            p.z = (p.y + minZ)*zCoff;

            //System.out.println(p.x + " " + p.y +" " + p.z);
            //System.out.println(xCoff + " " +yCoff +" " +zCoff);
        }

    }

    public SegmentModel getCopy(){
        Point3D[] ret = new Point3D[points.length];
        Point3D t;
        for (int i = 0; i<ret.length;i++){
            t = points[i];
            ret[i] = new Point3D(t.x,t.y,t.z);
        }
        SegmentModel sm = new SegmentModel(ret) ;
        sm.color = this.color;
        return sm;
    }

}

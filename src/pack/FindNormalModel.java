package pack;
import secondName.Point3D;
/**
 * Created with IntelliJ IDEA.
 * User: salerat
 * Date: 12/16/13
 * Time: 2:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class FindNormalModel {
    Point3D point1;
    Point3D point2;
    Point3D point3;
    double normalNorm[] = null;

    public FindNormalModel(Point3D point1, Point3D point2, Point3D point3) {
        this.point1=point1;
        this.point2=point2;
        this.point3=point3;
    }

    public double[] getNorm() {
        double[] vec1= {point2.x-point1.x, point2.y-point1.y,point2.z-point1.z};
        double[] vec2= {point2.x-point3.x, point2.y-point3.y,point2.z-point3.z};
        double normal[] = {vec1[1]*vec2[2] - vec1[2]*vec2[1], vec1[2]*vec2[0] - vec1[0]*vec2[2], vec1[0]*vec2[1]-vec1[1]*vec2[0]};
        double normalLenth = Math.sqrt(normal[0] * normal[0] + normal[1] * normal[1] + normal[2] * normal[2]);
        double normalN[] = {normal[0]/normalLenth, normal[1]/normalLenth, normal[2]/normalLenth};
        normalNorm=normalN;
        return normalN;
    }
}

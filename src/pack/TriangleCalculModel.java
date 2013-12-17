package pack;
import secondName.Point3D;
/**
 * Created with IntelliJ IDEA.
 * User: salerat
 * Date: 12/16/13
 * Time: 2:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class TriangleCalculModel {
    Point3D point1;
    Point3D point2;
    Point3D point3;
    double normalNorm[] = null;

    public TriangleCalculModel(Point3D point1, Point3D point2, Point3D point3) {
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

    public java.util.List<TriangleContainer> findMiddle(java.util.List<TriangleContainer> triangleContainer) {
        for(TriangleContainer tr : triangleContainer)  {
            double perimetr = Math.sqrt( (tr.leftAnglePoint.x - tr.topAnglePoint.x) * (tr.leftAnglePoint.x - tr.topAnglePoint.x) + (tr.leftAnglePoint.y - tr.topAnglePoint.y) * (tr.leftAnglePoint.y - tr.topAnglePoint.y) + (tr.leftAnglePoint.z - tr.topAnglePoint.z) * (tr.leftAnglePoint.z - tr.topAnglePoint.z))  +
                    Math.sqrt( (tr.topAnglePoint.x - tr.rightAnglePoint.x)*(tr.topAnglePoint.x - tr.rightAnglePoint.x) + (tr.topAnglePoint.y - tr.rightAnglePoint.y) * (tr.topAnglePoint.y - tr.rightAnglePoint.y) + (tr.topAnglePoint.z - tr.rightAnglePoint.z)* (tr.topAnglePoint.z - tr.rightAnglePoint.z) ) +
                    Math.sqrt( (tr.leftAnglePoint.x - tr.rightAnglePoint.x)*(tr.leftAnglePoint.x - tr.rightAnglePoint.x) + (tr.leftAnglePoint.y - tr.rightAnglePoint.y) * (tr.leftAnglePoint.y - tr.rightAnglePoint.y) + (tr.leftAnglePoint.z - tr.rightAnglePoint.z) * (tr.leftAnglePoint.z - tr.rightAnglePoint.z));
            tr.middleTrianglePoint.x = (tr.leftAnglePoint.x + tr.topAnglePoint.x + tr.rightAnglePoint.x) /  perimetr;
            tr.middleTrianglePoint.y = (tr.leftAnglePoint.y + tr.topAnglePoint.y + tr.rightAnglePoint.y) /  perimetr;
            tr.middleTrianglePoint.z = (tr.leftAnglePoint.z + tr.topAnglePoint.z + tr.rightAnglePoint.z) /  perimetr;
        }
        return triangleContainer;
    }
}

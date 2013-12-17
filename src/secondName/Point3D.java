package secondName;



/**
 * Created with IntelliJ IDEA.
 * User: afonia
 * Date: 12/10/13
 * Time: 3:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class  Point3D {
    public double x;
    public double y;
    public double z;

    public Point3D(double x,double y,double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3D(Point3D p){
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double getZ(){
        return z;
    }

}

package secondName;

/**
 * Created with IntelliJ IDEA.
 * User: afonia
 * Date: 12/10/13
 * Time: 4:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class CameraContainer {
    public boolean isClipering = false;
    public double zf = 10;
    public double zn = 8;
    public double sh = 200;
    public double sw = 200;

    public Point3D c = new Point3D(-10,0,0);
    public Point3D v = new Point3D(10,0,0);
    public Point3D u = new Point3D(0,1,0);

    //    double distanse;
    double distanseX;
    double distanseY;
    double distanseZ;

    public double uNormal[];
    public double vNormal[];
    public double wNormal[];

    public CameraContainer(){
        double distanse = this.c.x*this.c.x + this.c.y*this.c.y + this.c.z*this.c.z;

        distanseX = Math.sqrt(distanse-this.c.x*this.c.x);
        distanseY = Math.sqrt(distanse-this.c.y*this.c.y);
        distanseZ = Math.sqrt(distanse-this.c.z*this.c.z);
//        System.out.println(distanse+" " + camera.c.x*camera.c.x);
        double u[] = { this.c.x-this.u.x, this.c.y-this.u.y,this.c.z-this.u.z};
        double v[] = { this.c.x-this.v.x, this.c.y-this.v.y,this.c.z-this.v.z};
        double w[] = {u[1]*v[2] - u[2]*v[1], u[2]*v[0] - u[0]*v[2], u[0]*v[1]-u[1]*v[0]};

        double vNew[] = {u[1]*w[2] - u[2]*w[1], u[2]*w[0] - u[0]*w[2], u[0]*w[1]-u[1]*w[0]};


        double uNorm = Math.sqrt(u[0]*u[0]+u[1]*u[1]+u[2]*u[2]);
        double vNorm = Math.sqrt(vNew[0]*vNew[0]+vNew[1]*vNew[1]+vNew[2]*vNew[2]);
        double wNorm = Math.sqrt(w[0]*w[0]+w[1]*w[1]+w[2]*w[2]);
        // System.out.println(wNorm);
        uNormal = new double[]{u[0] / uNorm, u[1] / uNorm, u[2] / uNorm};
        vNormal = new double[]{vNew[0]/vNorm, vNew[1]/vNorm, vNew[2]/vNorm};
        wNormal = new double[]{w[0]/wNorm, w[1]/wNorm, w[2]/wNorm};
    }

    public void Rotate(Point3D p){
        RotateVectot(vNormal,p);
        RotateVectot(uNormal,p);
        RotateVectot(wNormal,p);
        normalize();

//        Point3D vp = new Point3D(vNormal[0],vNormal[1],vNormal[2]);
//        vp = MatrixArifmetikModel.RotateByX(vp,p.x/100);
//        vNormal[0] = vp.x;
//        vNormal[1] = vp.y;
//        vNormal[2] = vp.z;
//            u.x += p.y/10;
//            u.y += p.x/10;
//            u.z += p.x/10;
//            v.x += p.x;
//            v.y += p.x;
//            v.z += p.x/100;
//            c.x += p.x/10;
//            c.y += p.x/10;
//            c.z += p.y/100;

//            System.out.println(c.x + " " + c.y + " " + c.z);
//            MatrixArifmetikModel.RotateByX(c, 12);
//            MatrixArifmetikModel.RotateByX(v, 1);
//            MatrixArifmetikModel.RotateByX(u, 1);

    }

    public void MoveCamera(Point3D p){
        double k = 10;

        distanseX += p.x/k;
        distanseY += p.y/k;
        distanseZ += p.z/k;
//
//        MatrixArifmetikModel.MovePoint(c,p);
//        MatrixArifmetikModel.MovePoint(u,p);
//        MatrixArifmetikModel.MovePoint(v,p);

    }

    private void MoveVector(double[] v, Point3D p){
        double k = 100;
        p.x = p.x/k;
        p.y = p.y/k;
        p.z = p.z/k;
        System.out.println(p.z);

        Point3D vp = new Point3D(v[0],v[1],v[2]);
        MatrixArifmetikModel.MovePoint(vp,p);

        v[0] = vp.x;
        v[1] = vp.y;
        v[2] = vp.z;
    }

    private void RotateVectot(double[] v,Point3D p){
        double k = 100;
        Point3D vp = new Point3D(v[0],v[1],v[2]);
        vp = MatrixArifmetikModel.RotateByX(vp,p.y/k);
        vp = MatrixArifmetikModel.RotateByY(vp,-p.x/k);
//        vp = MatrixArifmetikModel.RotateByZ(vp,p.x/k);

        v[0] = vp.x;
        v[1] = vp.y;
        v[2] = vp.z;
    }

    private void normalize(){
        double u[] = uNormal;
        double v[] = vNormal;
        double w[] = wNormal;

        double vNew[] = {u[1]*w[2] - u[2]*w[1], u[2]*w[0] - u[0]*w[2], u[0]*w[1]-u[1]*w[0]};


        double uNorm = Math.sqrt(u[0]*u[0]+u[1]*u[1]+u[2]*u[2]);
        double vNorm = Math.sqrt(vNew[0]*vNew[0]+vNew[1]*vNew[1]+vNew[2]*vNew[2]);
        double wNorm = Math.sqrt(w[0]*w[0]+w[1]*w[1]+w[2]*w[2]);

        uNormal = new double[]{u[0] / uNorm, u[1] / uNorm, u[2] / uNorm};
        vNormal = new double[]{vNew[0]/vNorm, vNew[1]/vNorm, vNew[2]/vNorm};
        wNormal = new double[]{w[0]/wNorm, w[1]/wNorm, w[2]/wNorm};
    }

}
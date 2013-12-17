package secondName;


import pack.TriangleContainer;

import java.awt.*;
import java.awt.List;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: afonia
 * Date: 12/9/13
 * Time: 12:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class NetModel {
    public int n;  //сетка
    public int m;  //сетка
    public int k;  //гладкость

    public Color color;
    public double x,y,z;
    public BsplineModel base;

    public SegmentModel[] segments;
    public SegmentModel[] circles;

    public java.util.List<TriangleContainer> triangleContainer = new ArrayList<TriangleContainer>();

    public NetModel(BsplineModel base,int n, int m,int k,double With,double Height,double radius){
//        k = 40;
        this.k = k;
        Random rand = new Random();
        color = new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat());
        this.base = base;
        this.base.k = (k+n);
        this.m = m+1;
        this.n = n;
        initSegments(With,Height,radius);
        fitByZ();
//        fit();
    }

    private void initSegments(double With,double Height,double radius) {
        SegmentModel bsegments = new SegmentModel(base.getInterpolated(With,Height,radius));

        segments = new SegmentModel[n];
        double angle = 2*Math.PI/n;
        for (int i = 0; i<segments.length;i++){
            segments[i] = SegmentModel.RotateSegmentByX(bsegments,angle*i);
            segments[i].color  = this.color;
        }
        circles  = new SegmentModel[m+1];
        SegmentModel sm;
        double step = (double)(bsegments.points.length)/m;
        for (int i = 0; i<circles.length; i++){
            sm = new SegmentModel();
            sm.points = new Point3D[(segments.length + 1)*k];
            circles[i] = sm;
            sm.color = this.color;
        }
        Point3D origin;
        ArrayList<Point3D> origins = new ArrayList<Point3D>();
        double cirStep = 2*Math.PI/circles[0].points.length;
        for (int i = 0; i<circles.length;i++){
//            origin = new Point3D(segments[0].points[(int)Math.round(step*i)]);
            for (int j = 0; j<segments.length;j++){
                if(step*i<segments[j].points.length) origin = segments[j].points[(int)Math.round(step*i)];
                else  origin = segments[j].points[segments[j].points.length-1];
                origins.add(origin);
                for (int z = 0; z<=k*2-1;z++){
                    circles[i].points[k*j + z] = MatrixArifmetikModel.RotateByX(origin,angle/k*z);
                }
//                circles[i].points[j] = MatrixArifmetikModel.RotateByX(origin,cirStep*j);
            }
        }
        TriangleContainer tc;
        double[] kd = new double[3];
        kd[0] = 100.1;
        kd[1] = 100.1;
        kd[2] = 100.1;

        for (int i = 0; i<origins.size() - segments.length-1;i++){
            tc = new TriangleContainer();
            tc.leftAnglePoint = origins.get(i);
            tc.rightAnglePoint =origins.get(i+segments.length);
            tc.topAnglePoint = origins.get(i+segments.length+1);
            tc.Kd = kd;
            tc.Kf = kd;
            tc.currentTriangleColor = new Color(125,125,125);
            triangleContainer.add(tc);

            tc = new TriangleContainer();
            tc.leftAnglePoint = origins.get(i);
            tc.rightAnglePoint = origins.get(i+1);
            tc.topAnglePoint = origins.get(i+segments.length+1);
            tc.Kd = kd;
            tc.Kf = kd;
            tc.currentTriangleColor = new Color(125,125,125);
            triangleContainer.add(tc);
        }
//        for(int i = 0; i<circles.length; i++){
//            for (int j = 0; j<circles[i].points.length; j++){
//                if(i<circles.length-1){
//                    if(j != circles[i].points.length-1) circles[i].points[j] = segments[j].points[(int)Math.round(step*i)];
//                    else circles[i].points[j] = circles[i].points[0];
//                }else {
//                    if(j != circles[i].points.length-1) circles[i].points[j] = segments[j].points[bsegments.points.length-1];
//                    else circles[i].points[j] = circles[i].points[0];
//                }
//
//            }
//        }

    }

    public void move(Point3D pOffset) {
        for(SegmentModel s : segments){
            s.move(pOffset);
        }
        for (SegmentModel c : circles){
            c.move(pOffset);
        }
    }

    public void fit(double zAdd){

        double sizeX = 2;
        double sizeY = 2;
        double sizeZ = 1;

        double maxX = segments[0].points[0].x;
        double minX = segments[0].points[0].x;
        double maxY = segments[0].points[0].y;
        double minY = segments[0].points[0].y;
        double maxZ = segments[0].points[0].z;
        double minZ = segments[0].points[0].z;

        for (SegmentModel sm:segments ){

            for (Point3D p : sm.points){
                if(maxX<p.x) maxX = p.x;
                if(minX>p.x) minX = p.x;
                if(maxY<p.y) maxY = p.y;
                if(minY>p.y) minY = p.y;
                if(maxZ<p.z) maxZ = p.z;
                if(minZ>p.z) minZ = p.z;
                //System.out.println(p.x + " " + p.y +" " + p.z );
            }
        }
        double xCoff = 1;
        double yCoff = 1;
        double zCoff = 1;

        if(maxX>minX)xCoff = sizeX/(maxX - minX);
        if(maxY>minY)yCoff = sizeY/(maxY - minY);
        if(maxZ>minZ)zCoff = sizeZ/(maxZ - minZ);

        for (SegmentModel sm:segments ){

            for (Point3D p: sm.points){
            p.x = (p.x - minX)*xCoff - 1;
            p.y = (p.y - minY)*yCoff - 1;
            p.z = (p.z - minZ)*zCoff + zAdd;
//            System.out.println(p.x + " " + p.y +" " + p.z );
        }
        }
        for (SegmentModel sm:circles ){

            for (Point3D p: sm.points){
                p.x = (p.x - minX)*xCoff - 1;
                p.y = (p.y - minY)*yCoff - 1;
                p.z = (p.z - minZ)*zCoff + zAdd;
//            System.out.println(p.x + " " + p.y +" " + p.z );
            }
        }
    }

    public void fitByZ(){
        double min = 0;
        for (SegmentModel sm:segments){
            for (Point3D p:sm.points){
                if(p.z<min) min = p.z;
            }
        }

        for (SegmentModel sm: segments){
            sm.move(new Point3D(0,0,-min));
        }
        for (SegmentModel sm: circles){
            sm.move(new Point3D(0,0,-min));
        }
    }


}

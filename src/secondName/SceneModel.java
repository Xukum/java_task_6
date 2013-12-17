package secondName;

/**
 * Created with IntelliJ IDEA.
 * User: afonia
 * Date: 12/10/13
 * Time: 9:32 AM
 * To change this template use File | Settings | File Templates.
 */

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class SceneModel {

    public List<NetModel> nets = new ArrayList<NetModel>();

    public SceneModel() {
    	BsplineModel bs = new BsplineModel();

    	bs.pts.add(new Point3D(0, 0, 10));
    	bs.pts.add( new Point3D(100, 100, 10));
    	bs.pts.add(new Point3D(200, 100, 10));
    	bs.pts.add(new Point3D(300, 0, 10));

    	NetModel nm = new NetModel(bs, 10, 10, 1, 300, 100, 10);
    	addNet(nm);
    }
    double offset = 2;
    public void addNet(NetModel net){
        int num = nets.size()+1;
        net.fit(0.5);

        Point3D pOffset = new Point3D(0,num*offset,0);
        net.move(pOffset);
        
        nets.add(net);
    }

    public void removeNet(int i){
        if(i<nets.size()){
            nets.remove(i);
            for (int j = i;j<nets.size();j++){
                Point3D pOffset = new Point3D(0,-offset,0);
                nets.get(j).move(pOffset);
            }
        }
    }

    public ArrayList<SegmentModel> convertToHalfSquare(CameraContainer camera) {

        ArrayList<SegmentModel> ret = getCopy();
        ret = ConvertCam(ret,camera);
        ret = MadeHalfSquere(ret,camera);
        if(camera.isClipering) ret = Clipering(ret,camera);

        return ret;
    }

    private ArrayList<SegmentModel> MadeHalfSquere(ArrayList<SegmentModel> ret,CameraContainer camera) {
        for (SegmentModel sm: ret){
            for(Point3D p:sm.points){
                p.x = p.x * 2 * camera.zn  / ( camera.sw * p.z);
                p.y = p.y * 2 * camera.zn  / ( camera.sh * p.z);
//                System.out.println(p.x + " " + p.y+ " " + p.z);
//                p.x = (p.x+300) * 2 * camera.zn * 600 / ( camera.sw * (p.z+300));
//                p.y = (p.y+300) * 2 * camera.zn * 600 / ( camera.sh * (p.z+300));
                //p.z = 0;
            }
        }
        return ret;
    }

    private ArrayList<SegmentModel> getCopy() {
        ArrayList<SegmentModel> ret = new ArrayList<SegmentModel>();

        for (NetModel net: nets){
            for (SegmentModel sm: net.segments){
                ret.add(sm.getCopy());
            }

            for (SegmentModel sm: net.circles){
                ret.add(sm.getCopy());
            }
        }
        return ret;
    }

    private ArrayList<SegmentModel> Clipering(ArrayList<SegmentModel> ret,CameraContainer camera) {
        double MinX = -1;
        double MaxX = 1;
        double MinY = -1;
        double MaxY = 1;
        double MinZ = camera.zn;
        double MaxZ = camera.zf;

        int deleted;

        for (SegmentModel sm:ret){
            deleted = 0;

            for (int i = 0; i<sm.points.length; i++){
//                System.out.println(sm.points[i].x + " " + sm.points[i].y+ " " + sm.points[i].z);

                if(sm.points[i].x<MinX || sm.points[i].x>MaxX){
                    sm.points[i] = null;
                    deleted++;
                    continue;
                }
                if(sm.points[i].y<MinY || sm.points[i].y>MaxY){
                    sm.points[i] = null;
                    deleted++;
                    continue;
                }
                if(sm.points[i].z<MinZ || sm.points[i].z>MaxZ){
                    sm.points[i] = null;
                    deleted++;
                    continue;
                }
            }
            Point3D[] newPoints = new Point3D[sm.points.length - deleted];
            int j = 0;
            for (int i = 0; i<newPoints.length; i++){
                if(sm.points[i+j]==null){
                    i--;
                    j++;
                }else {
                    newPoints[i] = sm.points[i+j];
                }

            }
            sm.points = newPoints;
//            for (Point3D p : newPoints){
//                System.out.println(p.x + " " + p.y+ " " + p.z);
//            }

        }
        return  ret;
    }

    public ArrayList<SegmentModel> ConvertCam(ArrayList<SegmentModel> ret,CameraContainer camera) {
//        double distanse = camera.c.x*camera.c.x + camera.c.y*camera.c.y + camera.c.z*camera.c.z;
//
//        double distanseX = Math.sqrt(distanse-camera.c.x*camera.c.x);
//        double distanseY = Math.sqrt(distanse-camera.c.y*camera.c.y);
//        double distanseZ = Math.sqrt(distanse-camera.c.z*camera.c.z);
////        System.out.println(distanse+" " + camera.c.x*camera.c.x);
//        double u[] = { camera.c.x-camera.u.x, camera.c.y-camera.u.y,camera.c.z-camera.u.z};
//        double v[] = { camera.c.x-camera.v.x, camera.c.y-camera.v.y,camera.c.z-camera.v.z};
//        double w[] = {u[1]*v[2] - u[2]*v[1], u[2]*v[0] - u[0]*v[2], u[0]*v[1]-u[1]*v[0]};
//
//        double vNew[] = {u[1]*w[2] - u[2]*w[1], u[2]*w[0] - u[0]*w[2], u[0]*w[1]-u[1]*w[0]};
//
//
//        double uNorm = Math.sqrt(u[0]*u[0]+u[1]*u[1]+u[2]*u[2]);
//        double vNorm = Math.sqrt(vNew[0]*vNew[0]+vNew[1]*vNew[1]+vNew[2]*vNew[2]);
//        double wNorm = Math.sqrt(w[0]*w[0]+w[1]*w[1]+w[2]*w[2]);
//       // System.out.println(wNorm);
//        double uNormal[] = {u[0]/uNorm, u[1]/uNorm, u[2]/uNorm};
//        double vNormal[] = {vNew[0]/vNorm, vNew[1]/vNorm, vNew[2]/vNorm};
//        double wNormal[] = {w[0]/wNorm, w[1]/wNorm, w[2]/wNorm};
//        System.out.println(uNormal[0] + " " + uNormal[1] + " "+uNormal[2]+ " / " +vNormal[0] + " " + vNormal[1] + " "+vNormal[2]+" / "+wNormal[0] + " " + wNormal[1] + " "+wNormal[2]);
        for (SegmentModel sm: ret){
            for (Point3D point: sm.points){
                point.x = point.x * camera.uNormal[0] + point.y * camera.uNormal[1] + point.z * camera.uNormal[2] + camera.distanseX;
                point.y = point.x * camera.vNormal[0] + point.y * camera.vNormal[1] + point.z * camera.vNormal[2] + camera.distanseY;
                point.z = point.x * camera.wNormal[0] + point.y * camera.wNormal[1] + point.z * camera.wNormal[2] + camera.distanseZ;
//                System.out.println(point.x + " " + point.y+ " " + point.z);
            }
        }
        return  ret;
    }

}


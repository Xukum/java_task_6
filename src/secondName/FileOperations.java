package secondName;

/**
 * Created with IntelliJ IDEA.
 * User: salerat
 * Date: 12/16/13
 * Time: 1:23 PM
 * To change this template use File | Settings | File Templates.
 */

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FileOperations {
    public double n;
    public double m;
    public double k;
    public double a;
    public double b;
    public double c;
    public double d;
    public double zn;
    public double zf;
    public double sw;
    public double sh;
    public Integer figCount = 0;
    public List<Point3D[]> points = new ArrayList<Point3D[]>();

    public void onLoad(JFileChooser fileChooser) {
        File file = fileChooser.getSelectedFile();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            Integer line = 0;
            Integer pElement = 0;
            String[] dots;
            Point3D[] currentPointsArray = null;
            while ((text = reader.readLine()) != null) {
                text = text.replaceAll("\t", " ");
                line++;
                if (line == 1) {
                    dots = text.split("\\s+");
                    n=Double.parseDouble(dots[0]);
                    m=Double.parseDouble(dots[1]);
                    k=Double.parseDouble(dots[2]);
                    a=Double.parseDouble(dots[3]);
                    b=Double.parseDouble(dots[4]);
                    c=Double.parseDouble(dots[5]);
                    d=Double.parseDouble(dots[6]);
                    zn=Double.parseDouble(dots[7]);
                    zf=Double.parseDouble(dots[8]);
                    sw=Double.parseDouble(dots[9]);
                    sh=Double.parseDouble(dots[10]);
                }
                if(line == 2) {
                    figCount=Integer.parseInt(text);
                }
                if(line > 2) {
                    dots = text.split("\\s+");
                    if(dots.length == 1) {
                        currentPointsArray=new Point3D[Integer.parseInt(text)-1];
                        pElement=0;
                        points.add(currentPointsArray);
                    } else {
                        currentPointsArray[pElement] = new Point3D(Double.parseDouble(dots[0]),Double.parseDouble(dots[1]),0);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }

    }

    public void UpdateParams(){
        BsplineModel.a = a;
        BsplineModel.b = b;
        BsplineModel.c = c;
        BsplineModel.d = d;

        MyPanel.camera.zf = zf;
        MyPanel.camera.zn = zn;
        MyPanel.camera.sw = sw;
        MyPanel.camera.sh = sh;

        for (int i = 0; i< figCount;i++){
            BsplineModel bs = new BsplineModel();
            Collections.addAll(bs.pts,points.get(i));
            NetModel nm = new NetModel(bs,(int)n , (int)m, (int)k, 300, 100, 1);
            MyPanel.scene.addNet(nm);
        }

    }

    public void GetAllData(){
        a = BsplineModel.a;
        b = BsplineModel.b;
        c = BsplineModel.c;
        d = BsplineModel.d;

        zf = MyPanel.camera.zf ;
        zn = MyPanel.camera.zn ;
        sw = MyPanel.camera.sw ;
        sh = MyPanel.camera.sh ;

        NetModel nm = MyPanel.scene.nets.get(0);
        n = nm.n;
        m = nm.m;
        k = nm.k;

        figCount = MyPanel.scene.nets.size();
        points = new ArrayList<Point3D[]>();
        for (int i = 0; i< figCount;i++){
            Point3D[] pts = new Point3D[MyPanel.scene.nets.get(i).base.pts.size()];
            pts = MyPanel.scene.nets.get(i).base.pts.toArray(pts);
            points.add(pts);
        }
    }


}

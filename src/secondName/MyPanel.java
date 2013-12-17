package secondName;



import pack.TriangleContainer;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Panel for drowing lines
 *
 * @author Atuchin
 *
 */
public class MyPanel extends JPanel {
    
    public static CameraContainer camera = new CameraContainer();
    public static SceneModel scene = new SceneModel();
    public Point3D clickP;
    public boolean isMove = false;
    public BsplineModel m = new BsplineModel();
	{

		addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	clickP = new Point3D(e.getX(), e.getY(), 0);
//                if (e.getButton() == e.BUTTON1) {
//                	
//                }
//                if(e.getButton() == e.BUTTON3){
//                    
//                    repaint();
//                }
                repaint();
            }

            public void mousePressed(MouseEvent e) {
                if(e.getButton() == e.BUTTON1){
                    isMove = false;
                }
                if(e.getButton() == e.BUTTON3){
                    isMove = true;
                }

                clickP = new Point3D(e.getX(), e.getY(), 0);
            }

            public void mouseReleased(MouseEvent e) {
                clickP = null;
            }
        });
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {

			}

			public void mouseDragged(MouseEvent e) {
                if(clickP!=null){
                	Point3D p = new Point3D(clickP.x - e.getX(), clickP.y - e.getY(), 0);
                	if(!isMove){
                        camera.Rotate(p);
//                        //System.out.println("rot");
                    }
                    if(isMove){
                        camera.MoveCamera(p);
//                        //System.out.println("mov");
                    }
                    clickP = new Point3D(e.getX(), e.getY(), 0);
                	repaint();
                }
            }
		});
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                camera.MoveCamera(new Point3D(0,0,e.getUnitsToScroll()));
//                //System.out.println(e.getUnitsToScroll());
                repaint();
            }
        });

	}

	public void paint(Graphics g) {
		super.paint(g);
            DrawScene(g,scene);
	}

//    public void DrawNet(Graphics g,NetModel net){
//        g.setColor(Color.BLUE);
//        int offset = 400;
//        SegmentModel sm;
//        for (int i = 0; i<net.segments.length;i++){
//            sm = net.segments[i];
//            for (int j = 0;j<net.segments[i].points.length-1;j++){
//                //System.out.println(sm.points[j].x + " " + sm.points[j].y+ " " + sm.points[j].z);
//                g.drawLine(offset+(int)sm.points[j].getX(),offset+(int)sm.points[j].getY(),
//                        offset+(int)sm.points[j+1].getX(),offset+(int)sm.points[j+1].getY());
//            }
//        }
//        for (int i = 0; i<net.circles.length;i++){
//            sm = net.circles[i];
//            for (int j = 0;j<net.circles[i].points.length-1;j++){
//                //System.out.println(sm.points[j].x + " " + sm.points[j].y+ " " + sm.points[j].z);
//                g.drawLine(offset+(int)sm.points[j].getX(),offset+(int)sm.points[j].getY(),
//                        offset+(int)sm.points[j+1].getX(),offset+(int)sm.points[j+1].getY());
//            }
//        }
//    }

    public void DrawScene(Graphics g, SceneModel scene){
        ArrayList<SegmentModel> lines = scene.convertToHalfSquare(camera);
        ArrayList<TriangleContainer> tr = scene.convertToHalfSquareTriangle(camera);
        int offsety = getHeight()/4;
        int offsetx = getWidth()/2;
        double scaleX = 5000;
        double scaleY = 1000;


        int[] x = new int[3];
        int[] y = new int[3];

        for(TriangleContainer triangle : tr){
//            //System.out.println(offsetx+triangle.topAnglePoint.x*scaleX + " " +
//                    offsety+triangle.topAnglePoint.y*scaleY+ " " + triangle.topAnglePoint.z);

            x[0] = (int)(offsetx+triangle.topAnglePoint.x*scaleX);
            x[1] = (int)(offsetx+triangle.leftAnglePoint.x*scaleX);
            x[2] = (int)(offsetx+triangle.rightAnglePoint.x*scaleX);

            y[0] = (int)(offsety+triangle.topAnglePoint.y*scaleY);
            y[1] = (int)(offsety+triangle.leftAnglePoint.y*scaleY);
            y[2] = (int)(offsety+triangle.rightAnglePoint.y*scaleY);
            g.setColor(triangle.currentTriangleColor);
//            //System.out.println(triangle.currentTriangleColor.getGreen());
            g.fillPolygon(x,y,x.length);
        }


//        for (SegmentModel sm: lines){
//            for (int i = 0; i<sm.points.length - 1;i++){
//                g.setColor(sm.color);
//
//                //System.out.println(offsetx+sm.points[i].x*scaleX + " " + offsety+sm.points[i].y*scaleY+ " " + sm.points[i].z);
//                g.drawLine(offsetx+(int)(sm.points[i].x*scaleX),
//                        offsety+(int)(sm.points[i].y*scaleY),
//                        offsetx+(int)(sm.points[i+1].x*scaleX),
//                        offsety+ (int)(sm.points[i+1].y*scaleY));
//
//            }
//        }
    }
}


package secondName;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JPanel;

public class BSplinePanel extends JPanel {
    public int circleD = 10;
    public Point3D currenP = null;
    public BsplineModel m = new BsplineModel();

    public BSplinePanel() {
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == e.BUTTON1) {
                    if (currenP == null) {
                        m.pts.add(new Point3D(e.getX(), e.getY(), 0));
                        m.sortDots();
                    }
                }
                repaint();
            }

            public void mousePressed(MouseEvent e) {
                for (Point3D point : m.pts) {
                    if (Math.pow(e.getX() - circleD / 2 - point.getX(), 2) + Math.pow(e.getY() + circleD / 2 - point.getY(), 2) < Math.pow(circleD * 2, 2)) {
                        currenP = point;
                        break;
                    }
                }
                repaint();
            }

            public void mouseReleased(MouseEvent e) {
                currenP = null;
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {

            }

            public void mouseDragged(MouseEvent e) {
                if (currenP != null) {
                    currenP.x = e.getX();
                    currenP.y = e.getY();
                    m.sortDots();
                    repaint();
                }
            }
        });

    }

    public void paint(Graphics g) {
        super.paint(g);
        if (m.pts.size() > 3) {
            Point3D lp = null;
            for (Point3D point : m.getInterpolated((double) this.getWidth(), (double) this.getHeight(), (double) this.getHeight() / 2)) {
                if (lp != null) g.drawLine((int) lp.getX(), (int) lp.getY(), (int) point.getX(), (int) point.getY());
                lp = point;
            }
        }
        if (m.pts != null) {

            for (Point3D point : m.pts) {
                g.setColor(new Color(255, 0, 0));
                g.fillOval((int) point.getX(), (int) point.getY(), circleD, circleD);
            }
        }
    }
}

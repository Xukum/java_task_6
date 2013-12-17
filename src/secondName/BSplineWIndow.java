package secondName;
import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class BSplineWIndow extends javax.swing.JFrame {
	public BSplinePanel MyPanel;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JTextField MText;
	private JTextField KText;
	private JTextField Rtext;
	private JTextField NText;
	private JLabel jLabel1;
	private JButton Create;
	
	public BSplineWIndow() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				Create = new JButton();
				getContentPane().add(Create, new AnchorConstraint(588, 324, 1001, 1, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				Create.setLayout(null);
				Create.setText("CreateNet");
				Create.setPreferredSize(new java.awt.Dimension(124, 108));
				Create.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						CreateMouseClicked(evt);
					}
				});
			}
			{
				MyPanel = new BSplinePanel();
				getContentPane().add(MyPanel, new AnchorConstraint(0, 1, 0, 129, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_ABS));
				MyPanel.setPreferredSize(new java.awt.Dimension(254, 261));
				MyPanel.setBackground(new java.awt.Color(192,192,192));
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1, new AnchorConstraint(21, 45, 82, 1, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel1.setText("N=");
				jLabel1.setPreferredSize(new java.awt.Dimension(17, 16));
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2, new AnchorConstraint(139, 50, 201, 1, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel2.setText("M=");
				jLabel2.setPreferredSize(new java.awt.Dimension(19, 16));
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3, new AnchorConstraint(270, 66, 331, 1, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel3.setText("K=");
				jLabel3.setPreferredSize(new java.awt.Dimension(25, 16));
			}
			{
				jLabel4 = new JLabel();
				getContentPane().add(jLabel4, new AnchorConstraint(388, 92, 450, 1, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jLabel4.setText("Radius");
				jLabel4.setPreferredSize(new java.awt.Dimension(35, 16));
			}
			{
				NText = new JTextField();
				getContentPane().add(NText, new AnchorConstraint(9, 274, 97, 113, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				NText.setText("10");
				NText.setPreferredSize(new java.awt.Dimension(62, 23));
			}
			{
				Rtext = new JTextField();
				getContentPane().add(Rtext, new AnchorConstraint(377, 274, 465, 113, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				Rtext.setText("10");
				Rtext.setPreferredSize(new java.awt.Dimension(62, 23));
			}
			{
				KText = new JTextField();
				getContentPane().add(KText, new AnchorConstraint(258, 274, 346, 113, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				KText.setText("1");
				KText.setPreferredSize(new java.awt.Dimension(62, 23));
			}
			{
				MText = new JTextField();
				getContentPane().add(MText, new AnchorConstraint(143, 274, 231, 113, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				MText.setText("10");
				MText.setPreferredSize(new java.awt.Dimension(62, 23));
			}
			pack();
			setSize(400, 300);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void CreateMouseClicked(MouseEvent evt) {

        if(this.MyPanel.m.pts.size()>3){
            int n = Integer.parseInt(this.NText.getText());
            int m = Integer.parseInt(this.MText.getText());
            int k = Integer.parseInt(this.KText.getText());
            int r = Integer.parseInt(this.Rtext.getText());

            NetModel ret = new NetModel(this.MyPanel.m, n, m, k, this.MyPanel.getWidth(), this.MyPanel.getHeight(), r);
            secondName.MyPanel.scene.addNet(ret);
            this.dispose();

        }
	}

}

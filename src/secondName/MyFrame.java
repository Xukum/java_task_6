/**
 * 
 */
package secondName;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.*;

/**
 * MainWindow Class
 * 
 * @author Atuchin
 * 
 */
public class MyFrame extends JFrame {
	MyPanel curMyPanel = null;
    private JTextField NumberNet;

	/**
	 * Default constructor to create main window
	 */
	public MyFrame (String string) {
		this.setTitle(string);
		init();
	}

	/**
	 * init main window
	 */
	public void init() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(100, 100));
		curMyPanel = new MyPanel();
		this.add(new ScrollPane().add(curMyPanel));
		this.add(initToolBar(), BorderLayout.PAGE_START);
		this.setSize(400, 400);
		this.setVisible(true);
	}

	/**
	 * add tool Bar
	 * 
	 * @return Tool bar
	 */
	public JToolBar initToolBar() {
		JToolBar ret = new JToolBar();
		setPreferredSize(new Dimension(450, 130));

		ImageIcon icon = new ImageIcon("src/resorces//add.gif");
		JButton button = new JButton(icon);
		button.setToolTipText("openSettings");
		button.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						BSplineWIndow inst = new BSplineWIndow();
						inst.setLocationRelativeTo(null);
						inst.setVisible(true);
					}
				});
			}
		});
		ret.add(button);
		
		ret.addSeparator();
		icon = new ImageIcon("src/resorces//About.gif");
		button = new JButton(icon);
		button.setToolTipText("show about");
		button.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
                onLoad() ;
				//showAbout();
			}
		});
		ret.add(button);

        ret.addSeparator();
        ret.addSeparator();

//        for(int i = 0; i<MyPanel.scene.nets.size();i++){
        button = new JButton("Редактировать:");
        button.setToolTipText("show about");
        button.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                //System.out.println(NumberNet.getText());
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        BSplineWIndow inst = new BSplineWIndow();
                        inst.setLocationRelativeTo(null);
                        inst.MyPanel.m = MyPanel.scene.nets.get(Integer.parseInt(NumberNet.getText())).base;
                        MyPanel.scene.removeNet(Integer.parseInt(NumberNet.getText()));
                        curMyPanel.repaint();
                        inst.setVisible(true);
                    }
                });
            }

        });
        ret.add(button);
        NumberNet = new JFormattedTextField("1");
        NumberNet.setMaximumSize(new Dimension(50,50));
        ret.add(NumberNet);

        ret.addSeparator();
        ret.addSeparator();
        icon = new ImageIcon("src/resorces//About.gif");
        button = new JButton(icon);
        button.setToolTipText("show about");
        button.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                getJDialog1().setVisible(true);
//                MyPanel.camera.isClipering = !MyPanel.camera.isClipering;
                //showAbout();
            }
        });
        ret.add(button);

		return ret;

	}


    public void onLoad() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(getComponent(0)) == JFileChooser.APPROVE_OPTION) {
            FileOperations file = new FileOperations();
            file.onLoad(fileChooser);
        }
    }
	/**
	 * show about window
	 */
	public void showAbout() {
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(
					"About.txt"));

			String text = in.readLine();
			while (in.ready()) {
				text = text + "\n" + in.readLine();
			}
			in.close();
			JOptionPane.showMessageDialog(this, text, "About Init",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

    private JTextField jTextField10;
    private JTextField jTextField9;
    private JTextField jTextField8;
    private JTextField jTextField7;
    private JTextField jTextField6;
    private JTextField jTextField5;
    private JTextField jTextField4;
    private JTextField jTextField3;
    private JTextField jTextField2;
    private JButton Ok;
    private JTextField jTextField1;
    private JDialog jDialog1;



    private JDialog getJDialog1() {
        if(jDialog1 == null) {
            jDialog1 = new JDialog(this);
            jDialog1.getContentPane().setLayout(null);
            jDialog1.setPreferredSize(new Dimension(406, 281));
            jDialog1.getContentPane().add(getOk(), "East");
            jDialog1.getContentPane().add(getJTextField1());
            jDialog1.getContentPane().add(getJTextField2());
            jDialog1.getContentPane().add(getJTextField3());
            jDialog1.getContentPane().add(getJTextField4());
            jDialog1.getContentPane().add(getJTextField5());
            jDialog1.getContentPane().add(getJTextField6());
            jDialog1.getContentPane().add(getJTextField7());
            jDialog1.getContentPane().add(getJTextField8());
            jDialog1.getContentPane().add(getJTextField9());
            jDialog1.getContentPane().add(getJTextField10());
            jDialog1.setSize(406, 281);
        }
        return jDialog1;
    }

    private JButton getOk() {
        if(Ok == null) {
            Ok = new JButton();
            Ok.setText("Ok");
            Ok.setBounds(50, 164, 62, 68);

            Ok.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    jDialog1.setVisible(false);
                    FileOperations fo = new FileOperations();
                    fo.GetAllData();

                    fo.n = Integer.parseInt(jTextField1.getText());
                    fo.m = Integer.parseInt(jTextField2.getText());
                    fo.k = Integer.parseInt(jTextField3.getText());
                    fo.a = Integer.parseInt(jTextField4.getText());
                    fo.b = Integer.parseInt(jTextField5.getText());
                    fo.c = Integer.parseInt(jTextField6.getText());
                    fo.zf = Integer.parseInt(jTextField7.getText());
                    fo.sh = Integer.parseInt(jTextField8.getText());
                    fo.zn = Integer.parseInt(jTextField9.getText());
                    fo.sw = Integer.parseInt(jTextField10.getText());
                    MyPanel.scene.nets.clear();
                    fo.UpdateParams();

                }
            });
        }
        return Ok;
    }


    private JTextField getJTextField1() {
        if(jTextField1 == null) {
            jTextField1 = new JTextField();
            jTextField1.setText("n");
            jTextField1.setBounds(50, 12, 62, 23);
        }
        return jTextField1;
    }

    private JTextField getJTextField2() {
        if(jTextField2 == null) {
            jTextField2 = new JTextField();
            jTextField2.setText("m");
            jTextField2.setBounds(159, 12, 62, 23);
        }
        return jTextField2;
    }

    private JTextField getJTextField3() {
        if(jTextField3 == null) {
            jTextField3 = new JTextField();
            jTextField3.setText("k");
            jTextField3.setBounds(268, 12, 62, 23);
        }
        return jTextField3;
    }

    private JTextField getJTextField4() {
        if(jTextField4 == null) {
            jTextField4 = new JTextField();
            jTextField4.setText("a");
            jTextField4.setBounds(159, 66, 62, 23);
        }
        return jTextField4;
    }
    private JTextField getJTextField5() {
        if(jTextField5 == null) {
            jTextField5 = new JTextField();
            jTextField5.setText("b");
            jTextField5.setBounds(268, 66, 62, 23);
        }
        return jTextField5;
    }

    private JTextField getJTextField6() {
        if(jTextField6 == null) {
            jTextField6 = new JTextField();
            jTextField6.setText("c");
            jTextField6.setBounds(50, 66, 62, 23);
        }
        return jTextField6;
    }

    private JTextField getJTextField7() {
        if(jTextField7 == null) {
            jTextField7 = new JTextField();
            jTextField7.setText("zf");
            jTextField7.setBounds(159, 118, 62, 23);
        }
        return jTextField7;
    }

    private JTextField getJTextField8() {
        if(jTextField8 == null) {
            jTextField8 = new JTextField();
            jTextField8.setText("sh");
            jTextField8.setBounds(268, 118, 62, 23);
        }
        return jTextField8;
    }

    private JTextField getJTextField9() {
        if(jTextField9 == null) {
            jTextField9 = new JTextField();
            jTextField9.setText("zn");
            jTextField9.setBounds(50, 118, 62, 23);
        }
        return jTextField9;
    }

    private JTextField getJTextField10() {
        if(jTextField10 == null) {
            jTextField10 = new JTextField();
            jTextField10.setText("sw");
            jTextField10.setBounds(268, 164, 62, 23);
        }
        return jTextField10;
    }

}

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class BuilderFrame extends JFrame implements ActionListener
{
  private final int buttonSize = 53;

  private final int reefFaceButtonSize = 35;
  private final int reefHoriMidPos;
  private final int reefCenterHoriOffset = 18;
  private final int reefInnerHoriOffset = 65;
  private final int reefOuterHoriOffset = 87;

  private final int reefVertMidOffset = 735;
  private final int reefVertMidPos;
  private final int reefCenterVertOffset = 85;
  private final int reefInnerVertOffset = 32;
  private final int reefOuterVertOffset = 70;

  private final int stationVertPos = 512;
  private final int stationHoriPos = 143;
  private final double stationOffsetScalar = 0.74;
  private final int stationOffsetDist = (int)(buttonSize * stationOffsetScalar);

  private final int bargeVertOffset = 50;
  private final int cageSpacing = 90;
  
  private JButton cl1;
  private JButton cl2;
  private JButton cl3;
  private JButton cr1;
  private JButton cr2;
  private JButton cr3;
  private JButton b1;
  private JButton b2;
  private JButton b3;
  private JButton p;
  
  private ButtonGroup reefFaces;
  private JToggleButton ra;
  private JToggleButton rb;
  private JToggleButton rc;
  private JToggleButton rd;
  private JToggleButton re;
  private JToggleButton rf;
  private JToggleButton rg;
  private JToggleButton rh;
  private JToggleButton ri;
  private JToggleButton rj;
  private JToggleButton rk;
  private JToggleButton rl;

  private JComboBox<JButton> reefLevel;

  private BufferedImage fieldImage;
  private JLabel imageLabel;

  private JTextField autoOutput;
  private JButton copy;

  private AutoBuilder builder;

  private List<JButton> simpleButtonList;
  
  public BuilderFrame()
  {
    builder = new AutoBuilder();
    
    initSimpleButtons();
    
    initFaceButtons();
    
    initOutputText();
    
    initFrame();

    initFieldImage();

    reefHoriMidPos = (getWidth() - reefFaceButtonSize) / 2;
    reefVertMidPos = getHeight() - reefVertMidOffset;
    
    paint(getGraphics());
  }

  private void initFieldImage()
  {
    try 
    {
      fieldImage = ImageIO.read(new File("src\\2025_REEFSCAPE.png"));
    } 
    catch (Exception e) 
    {
      // TODO: handle exception
    }
    imageLabel = new JLabel(new ImageIcon(fieldImage.getScaledInstance(945, 1800, Image.SCALE_DEFAULT)));
    imageLabel.setLayout(new FlowLayout());
    add(imageLabel);
  }

  private void initSimpleButtons()
  {
    cl1 = new JButton("cl1");
    cl1.addActionListener(this);
    add(cl1);
    
    cl2 = new JButton("cl2");
    cl2.addActionListener(this);
    add(cl2);
    
    cl3 = new JButton("cl3");
    cl3.addActionListener(this);
    add(cl3);
    
    cr1 = new JButton("cr1");
    cr1.addActionListener(this);
    add(cr1);
    
    cr2 = new JButton("cr2");
    cr2.addActionListener(this);
    add(cr2);
    
    cr3 = new JButton("cr3");
    cr3.addActionListener(this);
    add(cr3);

    b1 = new JButton("b1");
    b1.addActionListener(this);
    add(b1);
    
    b2 = new JButton("b2");
    b2.addActionListener(this);
    add(b2);
    
    b3 = new JButton("b3");
    b3.addActionListener(this);
    add(b3);
    
    p = new JButton("p");
    p.addActionListener(this);
    add(p);

    simpleButtonList = List.of(cl1, cl2, cl3, cr1, cr2, cr3, p, b1, b2, b3);
  }

  private void initFrame()
  {
    setTitle("Auto Builder");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(945, 1080);
    setLocationRelativeTo(null);
    setVisible(true);
    setResizable(false);
    setLayout(new FlowLayout());
  }

  private void initFaceButtons()
  {
    ra = new JToggleButton("ra");
    ra.addActionListener(this);
    add(ra);
    
    rb = new JToggleButton("rb");
    rb.addActionListener(this);
    add(rb);

    rc = new JToggleButton("rc");
    rc.addActionListener(this);
    add(rc);

    rd = new JToggleButton("rd");
    rd.addActionListener(this);
    add(rd);

    re = new JToggleButton("re");
    re.addActionListener(this);
    add(re);

    rf = new JToggleButton("rf");
    rf.addActionListener(this);
    add(rf);

    rg = new JToggleButton("rg");
    rg.addActionListener(this);
    add(rg);

    rh = new JToggleButton("rh");
    rh.addActionListener(this);
    add(rh);

    ri = new JToggleButton("ri");
    ri.addActionListener(this);
    add(ri);

    rj = new JToggleButton("rj");
    rj.addActionListener(this);
    add(rj);

    rk = new JToggleButton("rk");
    rk.addActionListener(this);
    add(rk);

    rl = new JToggleButton("rl");
    rl.addActionListener(this);
    add(rl);

    reefFaces = new ButtonGroup();
    reefFaces.add(ra);
    reefFaces.add(rb);
    reefFaces.add(rc);
    reefFaces.add(rd);
    reefFaces.add(re);
    reefFaces.add(rf);
    reefFaces.add(rg);
    reefFaces.add(rh);
    reefFaces.add(ri);
    reefFaces.add(rj);
    reefFaces.add(rk);
    reefFaces.add(rl);
  }

  private void initOutputText()
  {
    autoOutput = new JTextField("");
    autoOutput.setEditable(false);
    add(autoOutput);

    copy = new JButton("Copy Auto String");
    copy.addActionListener(this);
    add(copy);
  }

  @Override
  public void paint(Graphics g) 
  {
    Insets reefInsets = new Insets(0, 0, 0, 0);

    imageLabel.setBounds(0, 0 - 900, 945, 1800);
    p.setBounds(getWidth() - 137 - buttonSize, 0 + 125, buttonSize, buttonSize);

    cl1.setBounds(0 + stationHoriPos, getHeight() - stationVertPos, buttonSize, buttonSize);
    cl2.setBounds(0 + stationHoriPos + stationOffsetDist, getHeight() - stationVertPos + buttonSize, buttonSize, buttonSize);
    cl3.setBounds(0 + stationHoriPos + (stationOffsetDist * 2), getHeight() - stationVertPos + (buttonSize * 2), buttonSize, buttonSize);
    cr3.setBounds(getWidth() - stationHoriPos - buttonSize, getHeight() - stationVertPos, buttonSize, buttonSize);
    cr2.setBounds(getWidth() - stationHoriPos - stationOffsetDist - buttonSize, getHeight() - stationVertPos + buttonSize, buttonSize, buttonSize);
    cr1.setBounds(getWidth() - stationHoriPos - (stationOffsetDist * 2) - buttonSize, getHeight() - stationVertPos + (buttonSize * 2), buttonSize, buttonSize);

    b1.setBounds(0 + 183, 0 + bargeVertOffset, buttonSize, buttonSize);
    b2.setBounds(0 + 183 + cageSpacing, 0 + bargeVertOffset, buttonSize, buttonSize);
    b3.setBounds(0 + 183 + (cageSpacing * 2), 0 + bargeVertOffset, buttonSize, buttonSize);

    // Face 1
    ra.setBounds(reefHoriMidPos - reefCenterHoriOffset, reefVertMidPos + reefCenterVertOffset, reefFaceButtonSize, reefFaceButtonSize);
    ra.setMargin(reefInsets);
    rb.setBounds(reefHoriMidPos + reefCenterHoriOffset, reefVertMidPos + reefCenterVertOffset, reefFaceButtonSize, reefFaceButtonSize);
    rb.setMargin(reefInsets);

    // Face 2
    rc.setBounds(reefHoriMidPos + reefInnerHoriOffset, reefVertMidPos + reefOuterVertOffset, reefFaceButtonSize, reefFaceButtonSize);
    rc.setMargin(reefInsets);
    rd.setBounds(reefHoriMidPos + reefOuterHoriOffset, reefVertMidPos + reefInnerVertOffset, reefFaceButtonSize, reefFaceButtonSize);
    rd.setMargin(reefInsets);

    // Face 3
    re.setBounds(reefHoriMidPos + reefOuterHoriOffset, reefVertMidPos - reefInnerVertOffset, reefFaceButtonSize, reefFaceButtonSize);
    re.setMargin(reefInsets);
    rf.setBounds(reefHoriMidPos + reefInnerHoriOffset, reefVertMidPos - reefOuterVertOffset, reefFaceButtonSize, reefFaceButtonSize);
    rf.setMargin(reefInsets);

    // Face 4
    rg.setBounds(reefHoriMidPos + reefCenterHoriOffset, reefVertMidPos - reefCenterVertOffset, reefFaceButtonSize, reefFaceButtonSize);
    rg.setMargin(reefInsets);
    rh.setBounds(reefHoriMidPos - reefCenterHoriOffset, reefVertMidPos - reefCenterVertOffset, reefFaceButtonSize, reefFaceButtonSize);
    rh.setMargin(reefInsets);

    // Face 5
    ri.setBounds(reefHoriMidPos - reefInnerHoriOffset, reefVertMidPos - reefOuterVertOffset, reefFaceButtonSize, reefFaceButtonSize);
    ri.setMargin(reefInsets);
    rj.setBounds(reefHoriMidPos - reefOuterHoriOffset, reefVertMidPos - reefInnerVertOffset, reefFaceButtonSize, reefFaceButtonSize);
    rj.setMargin(reefInsets);

    // Face 6
    rk.setBounds(reefHoriMidPos - reefOuterHoriOffset, reefVertMidPos + reefInnerVertOffset, reefFaceButtonSize, reefFaceButtonSize);
    rk.setMargin(reefInsets);
    rl.setBounds(reefHoriMidPos - reefInnerHoriOffset, reefVertMidPos + reefOuterVertOffset, reefFaceButtonSize, reefFaceButtonSize);
    rl.setMargin(reefInsets);

    autoOutput.setFont(autoOutput.getFont().deriveFont(40f));
    autoOutput.setSize(945, autoOutput.getHeight());
    autoOutput.setLocation(0, getHeight() - (autoOutput.getHeight() * 3));
    autoOutput.setText(builder.getString());

    copy.setMargin(new Insets(1, 1, 1, 1));
    copy.setLocation((getWidth() - copy.getWidth()) / 2, autoOutput.getY() - copy.getHeight());

    super.paint(g);
  }

  @Override
  public void actionPerformed(ActionEvent e) 
  {
    if (e.getSource() == copy)
    {
      Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(autoOutput.getText()), null);
    }
    else if (e.getSource() instanceof JButton button) 
    {
      if (simpleButtonList.stream().anyMatch(simpleButton -> e.getSource() == simpleButton)) 
      {
        builder.addToString(button.getText());
      }
    }

    repaint();
  }
}

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class BuilderFrame extends JFrame implements ActionListener, MouseListener
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

  private final int timeButtonInwardOffset = 250;
  private final int timeButtonUpOffset = 275;
  
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
  private JButton e;
  
  private ButtonGroup reefFaces;
  private JRadioButton ra;
  private JRadioButton rb;
  private JRadioButton rc;
  private JRadioButton rd;
  private JRadioButton re;
  private JRadioButton rf;
  private JRadioButton rg;
  private JRadioButton rh;
  private JRadioButton ri;
  private JRadioButton rj;
  private JRadioButton rk;
  private JRadioButton rl;

  private JButton a;
  private JButton c1;
  private JButton c2;
  private JButton c3;
  private JButton c4;

  private JButton w;
  private JButton t;
  private JSlider timeSlider;

  private BufferedImage fieldImage;
  private JLabel imageLabel;

  private JTextField autoOutput;
  private JButton copy;

  private JCheckBox pickPos;

  private AutoBuilder builder;

  private List<JButton> simpleButtonList;
  private List<JButton> levelButtonList;
  private List<JButton> timeButtonList;

  private JButton delete;
  
  public BuilderFrame()
  {
    builder = new AutoBuilder();

    addWindowListener
    (
      new WindowAdapter() 
      {
        public void windowClosing(WindowEvent e) 
        {
          builder.writeToSave();
        };
      }
    );

    initSimpleButtons();
    
    initFaceButtons();

    initLevelButtons();
    
    initOutputText();

    initTimeInput();

    pickPos = new JCheckBox("Pick Position", false);
    pickPos.addActionListener(this);
    add(pickPos);

    initFieldImage();
    
    initFrame();

    reefHoriMidPos = (getWidth() - reefFaceButtonSize) / 2;
    reefVertMidPos = getHeight() - reefVertMidOffset;

    repaint();
    paint(getGraphics());
  }

  private void initFieldImage()
  {
    try 
    {
      fieldImage = ImageIO.read(getClass().getResource("2025_REEFSCAPE.png"));
    } 
    catch (Exception e) 
    {
      // TODO: handle exception
    }
    imageLabel = new JLabel(new ImageIcon(fieldImage.getScaledInstance(945, 1800, Image.SCALE_DEFAULT)));
    imageLabel.addMouseListener(this);
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

    e = new JButton("e");
    e.addActionListener(this);
    add(e);

    simpleButtonList = List.of(cl1, cl2, cl3, cr1, cr2, cr3, p, b1, b2, b3, e);
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
    ra = new JRadioButton("ra");
    ra.addActionListener(this);
    ra.setActionCommand("ra");
    add(ra);
    
    rb = new JRadioButton("rb");
    rb.addActionListener(this);
    rb.setActionCommand("rb");
    add(rb);

    rc = new JRadioButton("rc");
    rc.addActionListener(this);
    rc.setActionCommand("rc");
    add(rc);

    rd = new JRadioButton("rd");
    rd.addActionListener(this);
    rd.setActionCommand("rd");
    add(rd);

    re = new JRadioButton("re");
    re.addActionListener(this);
    re.setActionCommand("re");
    add(re);

    rf = new JRadioButton("rf");
    rf.addActionListener(this);
    rf.setActionCommand("rf");
    add(rf);

    rg = new JRadioButton("rg");
    rg.addActionListener(this);
    rg.setActionCommand("rg");
    add(rg);

    rh = new JRadioButton("rh");
    rh.addActionListener(this);
    rh.setActionCommand("rh");
    add(rh);

    ri = new JRadioButton("ri");
    ri.addActionListener(this);
    ri.setActionCommand("ri");
    add(ri);

    rj = new JRadioButton("rj");
    rj.addActionListener(this);
    rj.setActionCommand("rj");
    add(rj);

    rk = new JRadioButton("rk");
    rk.addActionListener(this);
    rk.setActionCommand("rk");
    add(rk);

    rl = new JRadioButton("rl");
    rl.addActionListener(this);
    rl.setActionCommand("rl");
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

    ra.setSelected(true);
  }

  private void initLevelButtons()
  {
    a = new JButton("a");
    a.addActionListener(this);
    a.setActionCommand("a");
    add(a);

    c1 = new JButton("c1");
    c1.addActionListener(this);
    c1.setActionCommand("1");
    add(c1);

    c2 = new JButton("c2");
    c2.addActionListener(this);
    c2.setActionCommand("2");
    add(c2);

    c3 = new JButton("c3");
    c3.addActionListener(this);
    c3.setActionCommand("3");
    add(c3);

    c4 = new JButton("c4");
    c4.addActionListener(this);
    c4.setActionCommand("4");
    add(c4);

    levelButtonList = List.of(a, c1, c2, c3, c4);
  }

  private void initOutputText()
  {
    autoOutput = new JTextField("");
    autoOutput.setEditable(false);
    add(autoOutput);

    copy = new JButton("Copy Auto String");
    copy.addActionListener(this);
    add(copy);

    delete = new JButton("Delete Last Item");
    delete.addActionListener(this);
    add(delete);
  }

  private void initTimeInput()
  {
    w = new JButton("w");
    w.addActionListener(this);
    add(w);

    t = new JButton("t");
    t.addActionListener(this);
    add(t);

    timeSlider = new JSlider(0, 15, 5);
    timeSlider.setLabelTable(timeSlider.createStandardLabels(1));
    timeSlider.setPaintLabels(true);
    add(timeSlider);

    timeButtonList = List.of(t, w);
  }

  @Override
  public void paint(Graphics g) 
  {
    Insets reefInsets = new Insets(0, 0, 0, 0);

    super.paint(g);

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

    e.setBounds((getWidth() - e.getWidth()) / 2, reefVertMidPos - reefCenterVertOffset - 65, buttonSize, buttonSize);

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

    a.setBounds(getWidth() - 350, 300, buttonSize, a.getHeight());
    c1.setBounds(getWidth() - 350, 300 + c1.getHeight(), buttonSize, c1.getHeight());
    c2.setBounds(getWidth() - 350, 300 + (c1.getHeight() * 2), buttonSize, c2.getHeight());
    c3.setBounds(getWidth() - 350, 300 + (c1.getHeight() * 3), buttonSize, c3.getHeight());
    c4.setBounds(getWidth() - 350, 300 + (c1.getHeight() * 4), buttonSize, c4.getHeight());

    w.setBounds(timeButtonInwardOffset, getHeight() - timeButtonUpOffset, buttonSize, buttonSize);
    t.setBounds(getWidth() - timeButtonInwardOffset - t.getWidth(), getHeight() - timeButtonUpOffset, buttonSize, buttonSize);
    timeSlider.setSize(250, timeSlider.getHeight());
    timeSlider.setLocation((getWidth() - timeSlider.getWidth()) / 2, getHeight() - timeButtonUpOffset);

    pickPos.setLocation(75, getHeight() - 275);

    autoOutput.setFont(autoOutput.getFont().deriveFont(30f));
    autoOutput.setSize(945, autoOutput.getHeight());
    autoOutput.setLocation(0, getHeight() - (autoOutput.getHeight() * 3) - 30);
    autoOutput.setText(builder.getString());

    copy.setMargin(new Insets(1, 1, 1, 1));
    copy.setLocation(getWidth() / 2, autoOutput.getY() - copy.getHeight());

    delete.setMargin(new Insets(1, 1, 1, 1));
    delete.setLocation((getWidth() - (delete.getWidth() * 2)) / 2, autoOutput.getY() - delete.getHeight());
  }

  @Override
  public void actionPerformed(ActionEvent e) 
  {
    repaint();

    if (e.getSource() == copy)
    {
      Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(autoOutput.getText()), null);
    }
    else if (e.getSource() == delete)
    {
      builder.removeLastItem();
    }
    else if (e.getSource() instanceof JButton button) 
    {
      if (timeButtonList.stream().anyMatch(simpleButton -> e.getSource() == simpleButton))
      {
        builder.addToString(button.getText() + timeSlider.getValue());
      }
      else if (simpleButtonList.stream().anyMatch(simpleButton -> e.getSource() == simpleButton)) 
      {
        builder.addToString(button.getText());
      }
      else if (levelButtonList.stream().anyMatch(simpleButton -> e.getSource() == simpleButton)) 
      {
        if (button.getText() == "a")
        {
          int faceNumber = 
          switch (reefFaces.getSelection().getActionCommand())
          {
            case "ra", "rb" -> 1;
            case "rc", "rd" -> 2;
            case "re", "rf" -> 3;
            case "rg", "rh" -> 4;
            case "ri", "rj" -> 5;
            case "rk", "rl" -> 6;
            default -> 1;
          };

          builder.addToString(button.getText() + faceNumber);
        }
        else
        {
          builder.addToString(reefFaces.getSelection().getActionCommand() + button.getActionCommand());
        }
      }
    }
    
    repaint();
  }

  @Override
  public void mousePressed(MouseEvent e) 
  {
    if (pickPos.isSelected())
    {
      DecimalFormat formatter = new DecimalFormat("#.##");
      formatter.setRoundingMode(RoundingMode.DOWN);

      String x = formatter.format(clamp(Math.abs((e.getY() - 900) - 730) * 0.0120191780822, 0.5, 8.274));
      String y = formatter.format(clamp((Math.abs(e.getX() - 945) * 0.0119274074074) - 1.61, 0.5, 7.551));

      builder.addToString("g" + x + ":" + y);

      repaint();
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {}

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}

  @Override
  public void mouseClicked(MouseEvent e) {}

  public static double clamp(double value, double low, double high)
    {return Math.max(low, Math.min(value, high));}
}

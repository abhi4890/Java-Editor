import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class MyEditor extends JFrame
{
  JFrame frame;
  JTextArea textarea,input;
  JMenuBar menubar;
  JMenu menu1,menu2,menu3;
  JMenuItem newfile,open_file,close,save,cut,copy,paste,compile,run,save_as;
  JScrollPane scroll,scroll_in,scroll_out;
  JPopupMenu options;
  JTextPane statusbar,output;
  MyEditor()
  {
    Utilities.setNativeLookAndFeel();
    frame=new JFrame("MyEditor");
    Working working=new Working(this);
    DoEvent work=new DoEvent(this,working);
      frame.setLayout(null);
      Font f1=new Font("Constantia",Font.PLAIN,24);
        textarea=new JTextArea();
        textarea.setBackground(new Color(255,255,200));
        textarea.setFont(f1);
          menubar=new JMenuBar();
           menu1=new JMenu("File");
           menu1.setOpaque(true);
           menu2=new JMenu("Edit");
           menu2.setOpaque(true);
           menu3=new JMenu("Operations");
           menu3.setOpaque(true);
          menu1.setMnemonic(KeyEvent.VK_F);
          menu2.setMnemonic(KeyEvent.VK_E);
          menu3.setMnemonic(KeyEvent.VK_P);
          menu1.addMouseListener(work);
          menu2.addMouseListener(work);
          menu3.addMouseListener(work);
          ImageIcon open=new ImageIcon(getClass().getResource("images/open2.png"));
          ImageIcon edit=new ImageIcon(getClass().getResource("images/edit.png"));
          ImageIcon operations=new ImageIcon(getClass().getResource("images/operations.png"));
          menu1.setIcon(open);
          menu2.setIcon(edit);
          menu3.setIcon(operations);
          newfile=new JMenuItem("New File");
            newfile.setIcon(new ImageIcon(getClass().getResource("images/new.png")));
            newfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
            newfile.addActionListener(work);
          close=new JMenuItem("Close");
            close.setIcon(new ImageIcon(getClass().getResource("images/close.png")));
            close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
            close.addActionListener(work);
          compile=new JMenuItem("Compile");
              compile.setIcon(new ImageIcon(getClass().getResource("images/compile.png")));
              compile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,ActionEvent.CTRL_MASK));
              compile.addActionListener(work);
          run=new JMenuItem("Run");
              run.setIcon(new ImageIcon(getClass().getResource("images/run.png")));
              run.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK));
              run.addActionListener(work);
          save=new JMenuItem("Save");
              save.setIcon(new ImageIcon(getClass().getResource("images/save.png")));
              save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
              save.addActionListener(work);
              save_as=new JMenuItem("Save As");
                  save_as.setIcon(new ImageIcon(getClass().getResource("images/save_as.png")));
                  save_as.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.ALT_MASK));
                  save_as.addActionListener(work);
          cut=new JMenuItem("Cut");
            cut.setIcon(new ImageIcon(getClass().getResource("images/cut.png")));
            cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
            cut.addActionListener(work);
            menu2.add(cut);
            menu2.addSeparator();
          copy=new JMenuItem("Copy");
            copy.setIcon(new ImageIcon(getClass().getResource("images/copy.png")));
            copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
            copy.addActionListener(work);
            menu2.add(copy);
            menu2.addSeparator();
          paste=new JMenuItem("Paste");
            paste.setIcon(new ImageIcon(getClass().getResource("images/paste.png")));
            paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
            paste.addActionListener(work);
            menu2.add(paste);
          open_file=new JMenuItem("Open");
            open_file.setIcon(new ImageIcon(getClass().getResource("images/open.png")));
            open_file.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
            open_file.addActionListener(work);
          menu1.add(newfile);
          menu1.addSeparator();
          menu1.add(open_file);
          menu1.addSeparator();
          menu1.add(close);
          menu3.add(save);
          menu3.addSeparator();
          menu3.add(save_as);
          menu3.addSeparator();
          menu3.add(compile);
          menu3.addSeparator();
          menu3.add(run);
          menubar.add(menu1);
          menubar.add(menu2);
          menubar.add(menu3);

          frame.setJMenuBar(menubar);
          //adding the textarea
          scroll=new JScrollPane(textarea);
          scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
          scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
          scroll.setBounds(20,45,1350,780);
          frame.add(scroll);

          //adding the standard input ui
          input=new JTextArea();
          input.setFont(new Font("Monospaced",Font.BOLD,18));
          JScrollPane scroll_in=new JScrollPane(input);
          scroll_in.setBounds(1375,45,540,430);
          scroll_in.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
          scroll_in.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
          frame.add(scroll_in);
          //adding the standard output ui
           output=new JTextPane();
          output.setEditable(false);
          output.setFont(new Font("Monospaced",Font.BOLD,18));
          JScrollPane scroll_out=new JScrollPane(output);
          scroll_out.setBounds(1375,520,540,420);
          scroll_out.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
          scroll_out.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
          frame.add(scroll_out);
          //adding the label of standard input and standard output
          JLabel stdin=new JLabel();
          JLabel stdout=new JLabel();

          stdin.setBounds(1380,20,540,20);
          stdin.setText("\tStandard Input :");
          stdin.setFont(new Font("Arial",Font.BOLD,14));
          frame.add(stdin);
          stdout.setBounds(1380,490,540,20);
          stdout.setText("\tStandard Output :");
          stdout.setFont(new Font("Arial",Font.BOLD,14));
          frame.add(stdout);
          //adding the status bar;
          JLabel statusbarname=new JLabel();
          statusbarname.setBounds(20,830,1350,30);
          statusbarname.setFont(new Font("Arial",Font.BOLD,14));
          statusbarname.setText("\tStatus Bar :");
          frame.add(statusbarname);
          statusbar=new JTextPane();
          statusbar.setEditable(false);
          statusbar.setText("Enter your java code");
          statusbar.setBounds(20,870,1350,60);
          statusbar.setFont(new Font("Arial",Font.BOLD,20));
          frame.add(statusbar);
          //adding the textarea name
          JLabel textareaname=new JLabel();
          textareaname.setBounds(20,15,1350,20);
          textareaname.setFont(new Font("Arial",Font.BOLD,14));
          textareaname.setText(" Enter your java code here :");
          frame.add(textareaname);
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
         frame.getContentPane().setBackground(new Color(240,230,255));
           frame.pack();
          frame.setVisible(true);
  }
  public static void main(String args[])
  {
    new MyEditor();
  }
}

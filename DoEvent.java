import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
class DoEvent extends MouseAdapter implements ActionListener,MouseListener
{
    MyEditor editor;
    Color c;            //default color of menubar
    Working working;
    DoEvent(MyEditor editor,Working working)
    {
      this.editor=editor;
      this.working=working;
    }
    public void actionPerformed(ActionEvent event)
    {
     if(event.getSource()==editor.close)               //for closing
      {
        System.exit(1);
      }
      else if(event.getSource()==editor.open_file)
      {
        try{
          working.open_file();
        }catch(IOException e){}

      }
      else if(event.getSource()==editor.save)           //for saving
      {
          working.save();
      }
      else if(event.getSource()==editor.compile)                  //for compiling
      {
        boolean is_file_compiled=working.compile();
        if(is_file_compiled==true)
        {
          System.out.println("File compiled successfully");
        }
      }
      else if(event.getSource()==editor.run)
      {
        try{
          working.run_code();
        }catch(IOException e){}
          catch(ClassNotFoundException e){}
      }
      else if(event.getSource()==editor.copy)                     //to copy
      {
        editor.textarea.copy();
      }
      else if(event.getSource()==editor.cut)                      //to cut
      {
          editor.textarea.cut();
      }
      else if(event.getSource()==editor.paste)                  //to paste
      {
        editor.textarea.paste();
      }
      else if(event.getSource()==editor.newfile)
      {
        working.newfile();
      }
      else if(event.getSource()==editor.save_as)
      {
        working.save_as();
      }
    }
    public void mouseEntered(MouseEvent mevent)
    {
      if(mevent.getSource()==editor.menu1||mevent.getSource()==editor.menu2||mevent.getSource()==editor.menu3)
      {
        this.c=editor.menu1.getBackground();
          JMenu item=(JMenu)mevent.getSource();
          item.setBackground(new Color(150,150,180));
      }
    }
    //------------------------------------------------
    public void mouseExited(MouseEvent mevent)
    {
      if(mevent.getSource()==editor.menu1 || mevent.getSource()==editor.menu2 ||mevent.getSource()==editor.menu3)
      {
        JMenu item=(JMenu)mevent.getSource();
          item.setBackground(this.c);
      }
    }
    //-------------------------------------------------

    //-------------------------------------------------------

}

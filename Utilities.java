import java.awt.*;
import javax.swing.*;
import java.io.*;
class Utilities
{
    public static void setNativeLookAndFeel()
    {
      try
      {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      }
      catch(Exception e)
      {
        e.printStackTrace();
      }
    }
    //--------------------------------------------
}

import java.io.*;
class MakeFiles extends Working
{
      public void make_files()
      {
          File class=new File(class_name);
          FileOutputStream fout=new FileOutputStream("input.txt");
              String str=editor.input.getText();
              System.out.println(str);
      }
}

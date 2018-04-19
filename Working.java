import java.io.*;
import javax.tools.*;
import javax.swing.*;
import java.awt.*;
class Working
{
  MyEditor editor;
  boolean saved=false;
  static String file_name="";
  static String class_name="";
  boolean compiled=false;
  File input_file,class_file;
  Working(MyEditor editor)
  {
    this.editor=editor;
  }
  //------------------------------------------------------------
  public void newfile()
  {
        editor.textarea.setText("");
        editor.statusbar.setText("Enter your java code");
        editor.input.setText("");
        editor.output.setText("");
        file_name="";
        saved=false;
        compiled=false;
  }
  //------------------------------------------------------------
  public void save()
  {
      if(file_name=="")
      { save_as();
        return;
      }
      try
      {
            FileOutputStream fout=new FileOutputStream(file_name);
                fout.write(editor.textarea.getText().getBytes());
            editor.statusbar.setText("File written succesfully \t\t File Name : "+file_name);
              saved=true;
              fout.close();
      }
      catch(IOException e)
      {
        System.out.println(e);
        editor.statusbar.setText("File could not be saved");
      }
  }
  //-------------------------------------------------------------------
  public void open_file()throws IOException
  {
        FileDialog file_chooser=new FileDialog(new JFrame(),"Open File",FileDialog.LOAD);
                  file_chooser.setVisible(true);
            String file=file_chooser.getFile();
          if(file!=null)
             {
               file_name=file_chooser.getDirectory()+file;
                  InputStreamReader ir=new InputStreamReader(new FileInputStream(file_name));
                  BufferedReader br=new BufferedReader(ir);
                  String text="";
                  int s;
                  while((s=br.read())!=-1)
                    text=text+(char)s;
                  editor.textarea.setText(text);
                  editor.statusbar.setText("File Opened Successfully\t\t File Name : "+file_name);
                  br.close();
                  ir.close();
                  saved=true;
             }
        else {
          System.out.println("Please select a valid file to open");
        }
  }
  //-------------------------------------------------------------------
  public void save_as()
  {
    System.out.println("Save as event fired");
          FileDialog file_saver=new FileDialog(new JFrame(),"File Save",FileDialog.SAVE);
                    file_saver.setVisible(true);
              String file=file_saver.getFile();
              System.out.println(file);
                if(file!=null)
                {
                    if(file.indexOf('.')==-1)
                      file+=".java";
                  file=file_saver.getDirectory()+file;
                  System.out.println(file);
                  try{
                  FileOutputStream fout=new FileOutputStream(file);
                        fout.write(editor.textarea.getText().getBytes());
                        fout.close();
                        file_name=file;
                        editor.statusbar.setText("File Created Successfully\t\tFile Name : "+file);
                      }catch(FileNotFoundException e){
                        System.out.println("File Not found exception occured");
                      }
                        catch(IOException e){
                          System.out.println("IOException occured");
                        }
                }
                else {
                  System.out.println("Please select properly");
                }
  }
  //---------------------------------------------------------------------
  public boolean compile()
  {
    editor.output.setText("");
    boolean state=true;
    if(!saved)
      {
          JOptionPane.showMessageDialog(null,"Please save the file first");
          editor.statusbar.setText("Please save the file first");
          System.out.println("Please save the file first");
          return false;
      }
    try
      {
          String command="javac "+file_name;
          Process compilation=Runtime.getRuntime().exec(command);
          InputStreamReader ir1=new InputStreamReader(compilation.getInputStream());
          BufferedReader output=new BufferedReader(ir1);
          InputStreamReader ir2=new InputStreamReader(compilation.getErrorStream());
          BufferedReader error=new BufferedReader(ir2);
          int err,out;
          err=error.read();
          String message="",status="";
          if(err!=-1)
          { char ch;
              message="";
              do {  ch=(char)err;
                      message=message+ch;
                      err=error.read();
              } while (err!=-1);
              state=false;
          }
          out=output.read();
         if(out!=-1)
          {
            char ch;
                message="";
                do {  ch=(char)out;
                        message=message+ch;
                        out=output.read();
                } while (out!=-1);
                state=true;
          }
          System.out.println(message);
          editor.output.setText(message);
          if(state==true)
            {
              class_name=file_name.substring(file_name.lastIndexOf('\\')+1,file_name.lastIndexOf('.'));
              System.out.println("Class name is\n"+class_name);
              status="File Compiled Successfully\t\tFile Name :"+file_name;
            }
          else status="File could not be compiled .......\t\tFile Name : "+file_name;
          compiled=state;
          editor.statusbar.setText(status);
          ir1.close();
          ir2.close();
          compilation.destroy();
          //---------------------------------------------making files
          /*FileOutputStream fout=new FileOutputStream("input.txt");
              String s=editor.input.getText();
              int i;
              byte ch[]=s.getBytes();
                 fout.write(ch);
          input_file=new File("input.txt");*/
          class_file=new File(class_name+".class");
      }
      catch(NullPointerException e1)
        {
          System.out.println("NullPointerException occured");
        }
      catch(IOException e2)
         {
           System.out.println("IOException occured");
         }
         System.out.println("File name is"+file_name);
         return state;
  }
  //------------------------------------------------------------------
  public void run_code()throws IOException,ClassNotFoundException
  {
      String class_path=file_name.substring(0,file_name.lastIndexOf('\\'));
      System.out.println("java -cp "+class_path+" "+class_name);
        Process running=Runtime.getRuntime().exec("java -cp "+class_path+" "+class_name);
        System.out.println("java "+class_name);
        InputStreamReader ir1=new InputStreamReader(running.getInputStream());    //connected to process outputstream
        BufferedReader output_reader=new BufferedReader(ir1);
        InputStreamReader ir2=new InputStreamReader(running.getErrorStream());
        BufferedReader error_reader=new BufferedReader(ir2);
        OutputStreamWriter or=new OutputStreamWriter(running.getOutputStream());      //connected to process InputStream
        BufferedWriter input_writer=new BufferedWriter(or);

        String in=editor.input.getText();
        int i;
        //--------writing the input
             System.out.print(in);
             input_writer.write(in);
             input_writer.close();
             or.close();
        //------------error reading
          String message="";
              i=error_reader.read();
              while(i!=-1)
              {
                message+=(char)i;
                System.out.println("error");
                    i=error_reader.read();
              }
          System.out.println(message);
          editor.output.setText(message);
          //------------output reading
          i=output_reader.read();
          message="";
          while(i!=-1)
          {
                message+=(char)i;
                i=output_reader.read();
            System.out.println("output");
          }
      System.out.println(message);
      System.out.println(running.exitValue());
      editor.output.setText(message);
      running.destroy();
      //--------------------------
  }
}

import java.io.*;
class Classic
{
  public static void main(String args[])throws IOException,FileNotFoundException
    {
          FileOutputStream fout=new FileOutputStream("abc.java");
            String s="India is my country";
            byte b[]=s.getBytes();
            fout.write(b);
            File f=new File("abc.java");
            //System.out.println(f.getCanonicalPath());
            System.out.println(System.getProperty("user.home"));
    }
}

package Main.CalendarApp;

import javax.swing.*;
import java.io.*;

public class Diary implements Serializable {

    /**
     * 数据域
     */
    //私有变量
    private String filename;
    private String theme;
    private String content;
    //含参构造方法
    public Diary(String filename,String theme,String content)
    {
        if(theme.length()==0)
        {
            JOptionPane.showMessageDialog(null, "无论心情如何，总得有个主题吧！");
        }
        else if(content.length()==0)
        {
            JOptionPane.showMessageDialog(null, "把开心的不开心的都尽情写下吧！");
        }
        else
        {
            File CalendarDiaryFile=new File("C:/Calendar/Diary");
            if(!CalendarDiaryFile.exists())
            {
                CalendarDiaryFile.mkdirs();
            }
            this.filename="C:/Calendar/Diary/"+filename+".dat";
            this.theme=theme;
            this.content=content;
        }
    }
    public void write() throws Exception
    {
        File file=new File(filename);
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.close();
    }
    public Diary read() throws Exception
    {
        File file=new File(filename);
        FileInputStream fis=new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Diary d=(Diary) ois.readObject();
        ois.close();
        return d;
    }
    /**
     * 变量get()方法
     * @return
     */
    public String getTheme()
    {
        return this.theme;
    }
    public String getContent()
    {
        return this.content;
    }
    /**
     * 变量set()方法
     * @param comment
     */
}
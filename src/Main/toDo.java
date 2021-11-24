package Main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class toDo {

    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<String>();

        final int WIDTH = 1080;
        final int HEIGHT = 600;
        //获取屏幕尺寸
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame jf = new JFrame("待办");
        //设置居中和大小
        jf.setBounds((int)(screenSize.getWidth()-WIDTH)/2,(int)(screenSize.getHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        //设置icon
        jf.setIconImage(new ImageIcon("src/image/note.png").getImage());

        JPanel jp = new JPanel();
        JLabel title = new JLabel("NotePad");

        title.setFont(new java.awt.Font("黑体",1,26));

        title.setForeground(new Color(32,191,107));


        jp.add(title);
        jf.add(jp);



        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}

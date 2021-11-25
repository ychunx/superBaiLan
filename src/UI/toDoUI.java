package UI;

import javax.swing.*;
import java.awt.*;

public class toDoUI {

    public static JPanel init(){

        //待办
        JPanel jp = new JPanel();
        jp.setLayout(null);

        //标题logo
        JLabel title = new JLabel("NotePad");
        title.setFont(new java.awt.Font("黑体",1,30));
        title.setForeground(new Color(32,191,107));
        title.setBounds(20,20,1070,30);
        jp.add(title);

        //事项输入框
        JPanel inText = new JPanel();
        inText.setLayout(null);
        inText.setBounds(0,60,1080,50);

        JTextArea inJA = new JTextArea();
        inJA.setBounds(20,0,800,50);

        JButton inB = new JButton("添加");
        inB.setBounds(900,1,100,48);
        inB.setBackground(new Color(202,204,209));

        inText.add(inJA);
        inText.add(inB);
        jp.add(inText);

        //事项展示外框
        JScrollPane sList = new JScrollPane();
        sList.setLayout(null);
        sList.setBounds(0,120,1080,470);
        jp.add(sList);

        JPanel List = new JPanel();
        List.setLayout(null);
        List.setBounds(0,0,1080,470);
        sList.add(List);

        //事项展示内框1
        JPanel List1 = new JPanel();
        List1.setBounds(20,10,1020,40);
        List1.setLayout(null);
        List.add(List1);

        JLabel list1 = new JLabel("1.第一个事项");
        list1.setBounds(0,0,800,40);
        list1.setFont(new java.awt.Font("黑体",1,18));
        List1.add(list1);

        JButton list1B1 = new JButton("完成");
        JButton list1B2 = new JButton("删除");
        list1B1.setBounds(860,5,60,30);
        list1B2.setBounds(940,5,60,30);
        list1B1.setBackground(new Color(202,204,209));
        list1B2.setBackground(new Color(202,204,209));
        List1.add(list1B1);
        List1.add(list1B2);

        //事项展示内框2
        JPanel List2 = new JPanel();
        List2.setBounds(20,60,1020,40);
        List2.setLayout(null);
        List.add(List2);

        JLabel list2 = new JLabel("2.第二个事项");
        list2.setBounds(0,0,800,40);
        list2.setFont(new java.awt.Font("黑体",1,18));
        List2.add(list2);

        JButton list2B1 = new JButton("完成");
        JButton list2B2 = new JButton("删除");
        list2B1.setBounds(860,5,60,30);
        list2B2.setBounds(940,5,60,30);
        list2B1.setBackground(new Color(202,204,209));
        list2B2.setBackground(new Color(202,204,209));
        List2.add(list2B1);
        List2.add(list2B2);

        //脚部
        JPanel footer = new JPanel();
        footer.setLayout(null);
        footer.setBounds(0,590,1080,50);
        jp.add(footer);

        JLabel footerLL = new JLabel("总共x项");
        footerLL.setBounds(20,0,500,50);
        footerLL.setFont(new java.awt.Font("黑体",1,18));
        footer.add(footerLL);

        JButton footerRB = new JButton("清除全部");
        footerRB.setBounds(900,10,100,30);
        footerRB.setBackground(new Color(202,204,209));
        footer.add(footerRB);

        return jp;
    }
}

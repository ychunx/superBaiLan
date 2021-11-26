package UI;

import Main.toDo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

public class toDoUI {

    static String[] list = toDo.read();
    static JPanel List = new JPanel();

    public static JPanel init(){


        list = toDo.read();

        //待办
        JPanel jp = new JPanel();
        jp.setLayout(null);

        //标题logo
        JLabel title = new JLabel("NotePad");
        title.setFont(new java.awt.Font("黑体",1,40));
        title.setForeground(new Color(32,191,107));
        title.setBounds(20,20,1070,40);
        jp.add(title);

        //事项输入框
        JPanel inText = new JPanel();
        inText.setLayout(null);
        inText.setBounds(0,70,1080,30);

        JTextArea inJA = new JTextArea();
        inJA.setBounds(20,0,880,30);
        inJA.setFont(new java.awt.Font("Dilog",1,20));

        JButton inB = new JButton("添加事项");
        inB.setBounds(900,0,100,30);
        inB.setBackground(new Color(202,204,209));

        inB.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String one = inJA.getText();
                int num = list.length + 1;
                toDo.add(one + "\n");
                inJA.setText("");
                init();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        inText.add(inJA);
        inText.add(inB);
        jp.add(inText);

        //事项展示外框
        JScrollPane sList = new JScrollPane();
        sList.setLayout(null);
        sList.setBounds(0,120,1080,470);
        jp.add(sList);

        //JPanel List = new JPanel();
        List.setLayout(null);
        List.setBounds(0,0,1080,470);
        sList.add(List);

        //事项展示内框
        setJL();
        /***
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
        ***/

        //脚部
        JPanel footer = new JPanel();
        footer.setLayout(null);
        footer.setBounds(0,590,1080,50);
        jp.add(footer);

        int l = list.length;
        JLabel footerLL = new JLabel("总共"+l+"项");
        footerLL.setBounds(20,0,500,50);
        footerLL.setFont(new java.awt.Font("黑体",1,18));
        footer.add(footerLL);

        JButton footerRB = new JButton("清除全部");
        footerRB.setBounds(900,10,100,30);
        footerRB.setBackground(new Color(202,204,209));
        footer.add(footerRB);

        footerRB.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toDo.clean();
                init();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        merge.jsp.setRightComponent(jp);
        merge.jsp.setDividerLocation(120);

        return jp;
    }

    public static void setJL(){

        List.removeAll();

        for(int i=0;i<list.length;i++){
            final int num = i;

            JPanel[] jp = new JPanel[list.length];
            jp[i] = new JPanel();
            jp[i].setBounds(20,(10+(i*40)),1020,40);
            jp[i].setLayout(null);
            List.add(jp[i]);

            JLabel list1 = new JLabel((i+1) + "." + list[i]);
            list1.setBounds(0,0,800,40);
            list1.setFont(new Font("黑体",1,18));
            jp[i].add(list1);

            JButton list1B = new JButton("删除");
            list1B.setBounds(900,5,60,30);
            list1B.setBackground(new Color(202,204,209));
            jp[i].add(list1B);

            list1B.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    toDo.delete(num,list);
                    init();
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }
    }
}

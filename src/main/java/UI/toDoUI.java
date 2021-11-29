package UI;

import Main.toDo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class toDoUI {

    static String[] list = toDo.read();
    static JPanel List = new JPanel();

    public static JPanel init(){

        //每次初始化都要读取事项列表
        list = toDo.read();

        //待办面板
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
        //事项输入框 > 事项文本输入框
        final JTextArea inJA = new JTextArea();
        inJA.setBounds(20,0,880,30);
        inJA.setFont(new java.awt.Font("Dilog",1,20));
        //事项输入框 > 添加事项按钮
        JButton inB = new JButton("添加事项");
        inB.setBounds(900,0,100,30);
        inB.setBackground(new Color(202,204,209));

        inJA.addKeyListener(new KeyAdapter() {
            /**
             * Invoked when a key has been pressed.
             *
             * @param e
             */
            @Override
            //ctrl+enter 添加事件
            public void keyPressed(KeyEvent e) {

                    int keyCode = e.getKeyCode();
                if(e.getKeyChar() == KeyEvent.VK_ENTER && e.isControlDown() ) {
                        String one = inJA.getText();    //获取用户输入内容
                        if(!one.equals("")){            //当用户没输入时点击添加按钮无效
                            int num = list.length + 1;      //计算当前已存在事项+1得该事项序号
                            toDo.add(one + "\n");           //将内容添加进数据文件
                            inJA.setText("");               //重置文本输入框
                            init(); //刷新
                        }

                }
            }
        });
        inB.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String one = inJA.getText();    //获取用户输入内容
                if(!one.equals("")){            //当用户没输入时点击添加按钮无效
                    int num = list.length + 1;      //计算当前已存在事项+1得该事项序号
                    toDo.add(one + "\n");           //将内容添加进数据文件
                    inJA.setText("");               //重置文本输入框
                    init(); //刷新
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
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

        //事项展示外框 > 事项展示内框
        setJL();    //调用构造函数

        //脚部
        JPanel footer = new JPanel();
        footer.setLayout(null);
        footer.setBounds(0,590,1080,50);
        jp.add(footer);
        //脚部 > 左下角数量提醒
        int l = list.length;
        JLabel footerLL = new JLabel("总共"+l+"项");
        footerLL.setBounds(20,0,500,50);
        footerLL.setFont(new java.awt.Font("黑体",1,18));
        footer.add(footerLL);
        //脚部 > 清除全部事项按钮
        JButton footerRB = new JButton("清除全部");
        footerRB.setBounds(900,10,100,30);
        footerRB.setBackground(new Color(202,204,209));
        footer.add(footerRB);

        footerRB.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toDo.clean();   //调用清除全部方法
                init();     //刷新
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });

        //每次重新调用init()都要在右分割面板重新填充内容，才会刷新面板
        merge.jsp.setRightComponent(jp);
        merge.jsp.setDividerLocation(120);

        //提供给打开程序时初始化面板
        return jp;
    }

    //事项展示函数
    public static void setJL(){

        List.removeAll();   //先清除原列表内的所有组件


        for(int i=0;i<list.length;i++){
            final int num = i;

            //整体一个事项的组件容器
            JPanel[] jp = new JPanel[list.length];
            jp[i] = new JPanel();
            jp[i].setBounds(20,(10+(i*40)),1020,40);
            jp[i].setLayout(null);
            List.add(jp[i]);
            //显示事项内容的组件
            JLabel list1 = new JLabel((i+1) + "." + list[i]);
            list1.setBounds(0,0,800,40);
            list1.setFont(new Font("黑体",1,18));
            jp[i].add(list1);
            //删除按钮
            JButton list1B = new JButton("删除");
            list1B.setBounds(900,5,60,30);
            list1B.setBackground(new Color(202,204,209));
            jp[i].add(list1B);

            list1B.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    toDo.delete(num,list);  //调用删除方法
                    init();     //刷新
                }
                @Override
                public void mousePressed(MouseEvent e) {}
                @Override
                public void mouseReleased(MouseEvent e) {}
                @Override
                public void mouseEntered(MouseEvent e) {}
                @Override
                public void mouseExited(MouseEvent e) {}
            });
        }
    }
}

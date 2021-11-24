package UI;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class merge {

    //窗口属性
    final int WIDTH = 1200;
    final int HEIGHT = 700;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    JFrame jf = new JFrame("团队作业");

    //组装视图
    public void init(){

        jf.setBounds((int)(screenSize.getWidth()-WIDTH)/2,(int)(screenSize.getHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        jf.setIconImage(new ImageIcon("src/image/note.png").getImage());

        //菜单栏
        JMenuBar jmb = new JMenuBar();
        JMenu jm = new JMenu("设置");
        JMenuItem jm1 = new JMenuItem("退出程序");

        jm1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        jm.add(jm1);
        jmb.add(jm);
        jf.setJMenuBar(jmb);

        //分割面板
        JSplitPane jsp = new JSplitPane();

        //连续布局
        jsp.setContinuousLayout(true);
        jsp.setDividerLocation(120);
        jsp.setDividerSize(7);

        jf.add(jsp);

        //左侧内容
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("团队作业");
        DefaultMutableTreeNode notePad = new DefaultMutableTreeNode("记事本");
        DefaultMutableTreeNode note = new DefaultMutableTreeNode("错题本");
        DefaultMutableTreeNode toDo = new DefaultMutableTreeNode("待办");
        DefaultMutableTreeNode monney = new DefaultMutableTreeNode("记账本");
        root.add(notePad);
        root.add(note);
        root.add(toDo);
        root.add(monney);
        JTree tree = new JTree(root);

        jsp.setLeftComponent(tree);

        //设置页面
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override   //当选中时执行
            public void valueChanged(TreeSelectionEvent e) {
                Object path = e.getNewLeadSelectionPath().getLastPathComponent();

                if(notePad.equals(path)){
                    jsp.setRightComponent(new JLabel("记事本"));
                    jsp.setDividerLocation(120);
                } else if(note.equals(path)){
                    jsp.setRightComponent(new JLabel("错题本"));
                    jsp.setDividerLocation(120);
                } else if(toDo.equals(path)){

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

                    JButton footerRB = new JButton("清楚全部");
                    footerRB.setBounds(900,10,100,30);
                    footerRB.setBackground(new Color(202,204,209));
                    footer.add(footerRB);

                    jsp.setRightComponent(jp);
                    jsp.setDividerLocation(120);
                } else if(monney.equals(path)){
                    jsp.setRightComponent(new JLabel("记账本"));
                    jsp.setDividerLocation(120);
                }
            }
        });

        tree.setSelectionRow(1);//默认选择记事本

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    public static void main(String[] args){

        try {
            new merge().init();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
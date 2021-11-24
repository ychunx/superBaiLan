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
                    jsp.setRightComponent(new JLabel("待办"));
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
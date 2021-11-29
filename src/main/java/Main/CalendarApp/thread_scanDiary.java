package Main.CalendarApp;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class thread_scanDiary extends JFrame implements Runnable {
    /**
     * 声明变量区
     */
    private static final long serialVersionUID = 1L;
    //日历数据库存放路径
    private String path="./Diary";
    //日历总个数
    private static int num;
    //声明日历文件
    private File file;
    private File[] Diary;
    //声明JTable模型
    private JTable jtable;
    //声明格言面板及标签及内容等
    private JPanel jpMotto=new JPanel();
    private JLabel jlblMotto=new JLabel();
    private Font font=new Font("宋体", Font.ITALIC,20);
    private String wish="欢迎光临superbailan日记本";
    //增加弹出式菜单2015-4-26
    private JPopupMenu jPopupMenu1=new JPopupMenu();
    //声明菜单
    private JMenuItem jmiScan=new JMenuItem("查看");
    private JMenuItem jmiDelete=new JMenuItem("删除");

    @Override
    public void run() {
        //尝试弹出式菜单增加子菜单
        jmiScan.setForeground(Color.RED);
        jmiDelete.setForeground(Color.RED);

        jPopupMenu1.add(jmiScan);
        jPopupMenu1.addSeparator();
        jPopupMenu1.add(jmiDelete);
        jPopupMenu1.addSeparator();

        /**
         * 智能获取文件列表
         */
        file = new File(path);
        Diary=file.listFiles();
        num=Diary.length;
        String[] head={"  时间  ","   主题   "};
        Object[][] diary=new Object[num][2];
        for(int i=0;i<num;i++)
        {
            try{
                String time=Diary[i].getName().replaceFirst(".dat",  "");
                FileInputStream fis=new FileInputStream(Diary[i]);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Main.CalendarApp.Diary d=(Main.CalendarApp.Diary) ois.readObject();
                ois.close();
                String theme=d.getTheme();
                diary[i][0]=time;
                diary[i][1]=theme;
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        /**
         * 格言面板取
         */
        jlblMotto.setText(wish);
        jlblMotto.setFont(font);
        jlblMotto.setForeground(Color.RED);
        jpMotto.add(jlblMotto);
        jpMotto.setBackground(Color.WHITE);
        /**
         * 日历列表面板区
         */
        final DefaultTableModel tableModel=new DefaultTableModel(diary,head);
        jtable=new JTable(tableModel);
        jtable.setBackground(Color.WHITE);
        jtable.setRowHeight(30);
        jtable.setDoubleBuffered(false);
        jtable.setComponentPopupMenu(jPopupMenu1);
        jtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
        tcr.setHorizontalAlignment(SwingConstants.CENTER);// 这句和上句作用一样
        jtable.setDefaultRenderer(Object.class, tcr);
        JScrollPane jsp=new JScrollPane(jtable);

        /**
         * 弹出式菜单事件监听器编写
         */
        //查看菜单
        jmiScan.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jtable.getSelectedRow()>=0)
                {
                    int index=jtable.getSelectedRow();
                    String filename="./Diary/"+Diary[index].getName();
                    File file=new File(filename);
                    try
                    {
                        FileInputStream fis=new FileInputStream(file);
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        Main.CalendarApp.Diary d=(Main.CalendarApp.Diary) ois.readObject();
                        ois.close();
                        JFrame jf=new JFrame();
                        JTextArea jta=new JTextArea();
                        JLabel jlblTitle=new JLabel("主题");
                        JTextField jtfTitle=new JTextField(16);
                        JPanel jpTitle=new JPanel();
                        jpTitle.setLayout(new BorderLayout());
                        jpTitle.add(jlblTitle, BorderLayout.WEST);
                        jpTitle.add(jtfTitle, BorderLayout.CENTER);
                        jta.setLineWrap(true);
                        jta.setWrapStyleWord(true);
                        JScrollPane jsp=new JScrollPane(jta);
                        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                        jtfTitle.setText(d.getTheme());
                        jta.setText(d.getContent());
                        jtfTitle.setEditable(false);
                        jta.setEditable(false);
                        jf.setTitle("日记 "+Diary[index].getName().replaceFirst(".dat",  ""));
                        jf.add(jsp, BorderLayout.CENTER);
                        jf.add(jpTitle, BorderLayout.NORTH);
                        jf.setSize(400,400);
                        jf.setLocationRelativeTo(null);
                        jf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                        jf.setVisible(true);
                    }
                    catch(Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "请先选中一个日记！");
                }
            }
        });
        //删除菜单
        jmiDelete.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jtable.getSelectedRow()>=0)
                {
                    int index=jtable.getSelectedRow();
                    String filename="C:/Calendar/Diary/"+Diary[index].getName();
                    File file=new File(filename);
                    int option= JOptionPane.showConfirmDialog(null, "你确定要删除日记吗"+Diary[index].getName()+"？");
                    if(option== JOptionPane.YES_OPTION)
                    {
                        file.delete();
                        tableModel.removeRow(index);
                        SwingUtilities.updateComponentTreeUI(jtable);
                        JOptionPane.showMessageDialog(null, "删除成功！");
                    }
                    else
                    {
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "请先选中一个日记！");
                }
            }

        });
        /**
         * 主框架布局
         */
        this.add(jsp, BorderLayout.CENTER);
        this.add(jpMotto, BorderLayout.SOUTH);
        this.setSize(600, 500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("日记列表");
        this.setVisible(true);
        new Thread(new thread_mottoSparkle(jlblMotto)).start();
    }
}
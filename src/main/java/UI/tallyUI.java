package UI;

import Main.tally;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class tallyUI {

    private static JComboBox typeCom = new JComboBox();
    private static JComboBox typeCom2 = new JComboBox();
    private static JTextField moneyText = new JTextField();
    private static JTextField stateText = new JTextField("无");
    private static JTextField tjText = new JTextField();
    private static JTextField kehuText = new JTextField();
    private static JTextField jishuText = new JTextField();
    private static JTextField jsmoneyText = new JTextField();
    private static JTextField jsfenchengText = new JTextField();
    private static JTextField yingliText = new JTextField();
    private static JTextField wanchengText = new JTextField();
    private static JTable table = new JTable();
    private static DefaultTableModel dtm = new DefaultTableModel();
    private static Vector colName = new Vector();

    public static JSplitPane init(){
        JScrollPane p1 = new JScrollPane(setTable());
        final JSplitPane p2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,setInfo(),p1);
        p2.addComponentListener(new ComponentListener(){
            @Override
            public void componentResized(ComponentEvent arg0) {
                p2.setDividerLocation(0.4);
            }
            @Override
            public void componentHidden(ComponentEvent arg0) {}
            @Override
            public void componentMoved(ComponentEvent arg0) {}
            @Override
            public void componentShown(ComponentEvent arg0) {}
        });

        //重新设置右分割面板使能够刷新
        merge.jsp.setRightComponent(p2);
        merge.jsp.setDividerLocation(120);
        //返回给初始化面板时使用
        return p2;
    }

    //布局页面
    public static JPanel setInfo(){
        JPanel jp = new JPanel();
        jp.setLayout(null);
        JLabel label1 = new JLabel("类型");
        label1.setBounds(10, 50, 30, 30);
        jp.add(label1);
        typeCom.removeAllItems();
        typeCom.addItem("支出");
        typeCom.addItem("收入");
        typeCom.setBounds(50, 50, 60, 30);
        jp.add(typeCom);
        JLabel label2 = new JLabel("用户");
        label2.setBounds(130, 50, 60, 30);
        jp.add(label2);
        kehuText.setBounds(160, 50, 60, 30);
        jp.add(kehuText);
        JLabel jishulabel = new JLabel("  日期");
        jishulabel.setBounds(240, 50, 60, 30);
        jp.add(jishulabel);
        jishuText.setBounds(280, 50, 60, 30);
        jp.add(jishuText);
        JLabel dingdanlabel = new JLabel("  地点");
        dingdanlabel.setBounds(360, 50, 60, 30);
        jp.add(dingdanlabel);
        moneyText.setBounds(400, 50, 60, 30);
        jp.add(moneyText);
        JLabel jsjiagelabel = new JLabel("  金额");
        jsjiagelabel.setBounds(480, 50, 60, 30);
        jp.add(jsjiagelabel);
        jsmoneyText.setBounds(520, 50, 60, 30);
        jp.add(jsmoneyText);
        JLabel jsfenchenglabel = new JLabel("支付方式");
        jsfenchenglabel.setBounds(590, 50, 60, 30);
        jp.add(jsfenchenglabel);
        jsfenchengText.setBounds(640, 50, 60, 30);
        jp.add(jsfenchengText);
        JLabel yinglilabel = new JLabel("  余额");
        yinglilabel.setBounds(700, 50, 40, 30);
        jp.add(yinglilabel);
        yingliText.setBounds(740, 50, 60, 30);
        jp.add(yingliText);
        JLabel wanchenglabel = new JLabel("  类型");
        wanchenglabel.setBounds(820, 50, 60, 30);
        jp.add(wanchenglabel);
        wanchengText.setBounds(860, 50, 60, 30);
        jp.add(wanchengText);
        JLabel statelabel4 = new JLabel("  备注");
        statelabel4.setBounds(920, 50, 60, 30);
        stateText.setBounds(960, 50, 60, 30);
        jp.add(statelabel4);
        jp.add(stateText);
        JButton addBut = new JButton("添加");
        addBut.setBounds(400, 100, 60, 30);
        addBut.setBackground(new Color(202,204,209));
        jp.add(addBut);

        addBut.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent arg0) {}
            @Override
            public void mousePressed(MouseEvent arg0) {}
            @Override
            public void mouseExited(MouseEvent arg0) {}
            @Override
            public void mouseEntered(MouseEvent arg0) {}
            @Override
            public void mouseClicked(MouseEvent arg0) {
                String type = (String)typeCom.getSelectedItem();//返回当前所选项
                String kehuname=kehuText.getText();
                kehuText.setText("");
                String jishuname=jishuText.getText();
                jishuText.setText("");
                String dingdanmoney=moneyText.getText();
                moneyText.setText("");
                String jishumoney=jsmoneyText.getText();
                jsmoneyText.setText("");
                String jishufencheng=jsfenchengText.getText();
                jsfenchengText.setText("");
                String yingli=yingliText.getText();
                yingliText.setText("");
                String finnishdata=wanchengText.getText();
                wanchengText.setText("");
                String remark = stateText.getText();
                stateText.setText("无");
                String kaishitime = tally.getTime();
                int id = tally.getNewID();
                String data = id + "," + type + "," + kehuname + "," + jishuname + "," +dingdanmoney + ","+jishumoney + ","+jishufencheng + ","+yingli + ","+kaishitime+","+finnishdata+","+remark +"\n";
                tally.add(data);
                select();
            }
        });

        JButton deleteBut = new JButton("删除");
        deleteBut.setBounds(600, 100, 60, 30);
        deleteBut.setBackground(new Color(202,204,209));
        jp.add(deleteBut);
        deleteBut.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = table.getSelectedRow();
                dtm.removeRow(i);
                String data = changeTableData();
                tally.delete(data);
            }
        });

        JLabel chaxunlabel = new JLabel("查询收入/支出");
        chaxunlabel.setBounds(280, 200, 100, 30);
        jp.add(chaxunlabel);
        typeCom2.removeAllItems();
        typeCom2.addItem("收入");
        typeCom2.addItem("支出");
        typeCom2.setBounds(380, 200, 100, 30);
        jp.add(typeCom2);
        JButton selectBut = new JButton("查询");
        selectBut.setBounds(500, 200, 60, 30);
        selectBut.setBackground(new Color(202,204,209));
        jp.add(selectBut);

        selectBut.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseClicked(MouseEvent e) {
                String type = (String) typeCom2.getSelectedItem();
                String remark = tjText.getText();
                Vector data = tally.select(type,remark);
                dtm.setDataVector(data, colName);
                table.setModel(dtm);
            }
        });



        KeyListener key_Listener = new KeyListener(){

            /**
             * Invoked when a key has been typed.
             * See the class description for {@link KeyEvent} for a definition of
             * a key typed event.
             *
             * @param e
             */
            @Override
            public void keyTyped(KeyEvent e) {

            }

            /**
             * Invoked when a key has been pressed.
             * See the class description for {@link KeyEvent} for a definition of
             * a key pressed event.
             *
             * @param e
             */
            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
               //cter+enter 保存
                if(e.getKeyChar() == KeyEvent.VK_ENTER && e.isControlDown() )
                {
                    String type = (String)typeCom.getSelectedItem();//返回当前所选项
                    String kehuname=kehuText.getText();
                    kehuText.setText("");
                    String jishuname=jishuText.getText();
                    jishuText.setText("");
                    String dingdanmoney=moneyText.getText();
                    moneyText.setText("");
                    String jishumoney=jsmoneyText.getText();
                    jsmoneyText.setText("");
                    String jishufencheng=jsfenchengText.getText();
                    jsfenchengText.setText("");
                    String yingli=yingliText.getText();
                    yingliText.setText("");
                    String finnishdata=wanchengText.getText();
                    wanchengText.setText("");
                    String remark = stateText.getText();
                    stateText.setText("无");
                    String kaishitime = tally.getTime();
                    int id = tally.getNewID();
                    String data = id + "," + type + "," + kehuname + "," + jishuname + "," +dingdanmoney + ","+jishumoney + ","+jishufencheng + ","+yingli + ","+kaishitime+","+finnishdata+","+remark +"\n";
                    tally.add(data);
                    select();
                }

            }
        };
        typeCom.addKeyListener(key_Listener);
        kehuText.addKeyListener(key_Listener);
        jishuText.addKeyListener(key_Listener);
        moneyText.addKeyListener(key_Listener);
        jsmoneyText.addKeyListener(key_Listener);
        jsfenchengText.addKeyListener(key_Listener);
        yingliText.addKeyListener(key_Listener);
        wanchengText.addKeyListener(key_Listener);
        stateText.addKeyListener(key_Listener);




        return jp;
    }

    //设置表格
    public static JTable setTable(){

        table.removeAll();
        //清除全部，防止重叠
        colName.clear();

        colName.add("记录号");
        colName.add("类型");
        colName.add("客户名");
        colName.add("日期");
        colName.add("地点");
        colName.add("金额");
        colName.add("方式");
        colName.add("余额");
        colName.add("记录时间");
        colName.add("类型");
        colName.add("备注");

        Vector data = tally.select();
        dtm.setDataVector(data, colName);
        table.setModel(dtm);
        return table;
    }

    //从文件中重新读取一遍数据相当于刷新
    public static void select(){
        Vector data = tally.select();
        dtm.setDataVector(data, colName);
        table.setModel(dtm);
    }

    //获取表格内容
    public static String changeTableData(){
        String data = "";
        int row = table.getRowCount();
        for(int i=0;i<row;i++){
            String line = "";
            for(int j=0;j<11;j++){
                line += dtm.getValueAt(i, j)+",";
            }
            line += "\n";
            data += line;
        }
        return data;
    }
}

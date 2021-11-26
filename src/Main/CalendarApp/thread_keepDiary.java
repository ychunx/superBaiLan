package Main.CalendarApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class thread_keepDiary extends JFrame implements Runnable {

    private JFrame jf=this;
    private JTextArea jta=new JTextArea();
    private JButton jbtSave=new JButton("保存");
    private JLabel jlblTitle=new JLabel("主题");
    private JTextField jtfTitle=new JTextField(16);
    private String id;
    public thread_keepDiary(String time)
    {

        JPanel jpTitle=new JPanel();
        jpTitle.setLayout(new BorderLayout());
        jpTitle.add(jlblTitle, BorderLayout.WEST);
        jpTitle.add(jtfTitle,BorderLayout.CENTER);
        jta.setLineWrap(true);
        jta.setWrapStyleWord(true);
        JScrollPane jsp=new JScrollPane(jta);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.id=time;
        jbtSave.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub

                String theme=jtfTitle.getText().trim();
                String content=jta.getText();

                Diary d_today=new Diary(id,theme,content);
                try
                {
                    d_today.write();
                    JOptionPane.showMessageDialog(null, "保存成功！");
                    jf.dispose();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        this.setTitle("我的日记本"+id);
        this.add(jsp,BorderLayout.CENTER);
        this.add(jpTitle, BorderLayout.NORTH);
        this.add(jbtSave, BorderLayout.SOUTH);
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }
    @Override
    public void run() {
        this.setVisible(true);
    }

}
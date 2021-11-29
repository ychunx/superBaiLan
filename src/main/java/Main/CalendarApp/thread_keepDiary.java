package Main.CalendarApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
        jpTitle.add(jtfTitle, BorderLayout.CENTER);
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
        this.add(jsp, BorderLayout.CENTER);
        this.add(jpTitle, BorderLayout.NORTH);
        this.add(jbtSave, BorderLayout.SOUTH);
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        KeyListener key_Listener = new KeyListener() {
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

            /**
             * Invoked when a key has been released.
             * See the class description for {@link KeyEvent} for a definition of
             * a key released event.
             *
             * @param e
             */
            @Override
            //ctrl+enter 保存日记
            public void keyReleased(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER && e.isControlDown() ){

                    String theme=jtfTitle.getText().trim();
                    String content=jta.getText();

                    Diary d_today=new Diary(id,theme,content);

                    try {
                        d_today.write();
                        JOptionPane.showMessageDialog(null, "保存成功！");
                        jf.dispose();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };
        jtfTitle.addKeyListener(key_Listener);
        jta.addKeyListener(key_Listener);
    }

    @Override
    public void run() {
        this.setVisible(true);
    }

}
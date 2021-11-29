package Main.CalendarApp;

import UI.merge;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Calendar extends JFrame {

    private static CalendarPanel calendarPanel=new CalendarPanel();
    private static String versionID="CopyRight By Superbailan";
    private static JButton jbtPrior=new JButton("向左翻页查看日历");
    private static JButton jbtNext=new JButton("向右翻页查看日历");
    private static JButton jbtDiary=new JButton("开始写日记");
    private static JButton jbtScanDiary=new JButton("点击查看日记");
    private static int year;

    public static JPanel init()
    {
        year=calendarPanel.getYear();
        calendarPanel.setBackground(Color.WHITE);
        /**
         * 添加功能性按钮到日历面板下方
         */
        JPanel jpButtons=new JPanel(new FlowLayout());
        //设置按钮背景色为白色
        jbtPrior.setBackground(Color.WHITE);
        jbtNext.setBackground(Color.WHITE);
        jbtDiary.setBackground(Color.WHITE);
        jbtScanDiary.setBackground(Color.WHITE);
        /*
         * 给四个按钮添加鼠标事件，使其更加炫酷
         */
        //一、后退按钮
        jbtPrior.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                // TODO Auto-generated method stub
                jbtPrior.setForeground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                // TODO Auto-generated method stub
                jbtPrior.setForeground(Color.BLACK);
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

        });
        //二、前进按钮
        jbtNext.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                // TODO Auto-generated method stub
                jbtNext.setForeground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                // TODO Auto-generated method stub
                jbtNext.setForeground(Color.BLACK);
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

        });
        //三、写日记按钮
        jbtDiary.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                // TODO Auto-generated method stub
                jbtDiary.setForeground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                // TODO Auto-generated method stub
                jbtDiary.setForeground(Color.BLACK);
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

        });
        //四、看日记按钮
        jbtScanDiary.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                // TODO Auto-generated method stub
                jbtScanDiary.setForeground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                // TODO Auto-generated method stub
                jbtScanDiary.setForeground(Color.BLACK);
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

        });
        jpButtons.add(jbtPrior);
        jpButtons.add(jbtNext);
        jpButtons.add(jbtDiary);
        jpButtons.add(jbtScanDiary);
        jpButtons.setBackground(Color.WHITE);
        /**
         * 添加日历主要组件
         */
        JPanel jpCalendar=new JPanel(new BorderLayout());
        jpCalendar.add(calendarPanel, BorderLayout.CENTER);
        jpCalendar.add(jpButtons, BorderLayout.SOUTH);

        jbtScanDiary.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new thread_scanDiary()).start();
            }
        });
        jbtDiary.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                //获取本地系统时间
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");//设置日期格式
                String time=df.format(new Date());
                new Thread(new thread_keepDiary(time)).start();
            }
        });

        jbtPrior.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                int currentMonth=calendarPanel.getMonth();
                if(currentMonth==0)
                {
                    calendarPanel.setYear(year);
                    year--;
                }
                calendarPanel.setMonth((currentMonth-1)%12);
            }
        });

        jbtNext.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                int currentMonth=calendarPanel.getMonth();
                if(currentMonth==11)
                {
                    calendarPanel.setYear(++year);
                }
                calendarPanel.setMonth((currentMonth+1)%12);
            }
        });


        merge.jsp.setRightComponent(jpCalendar);
        merge.jsp.setDividerLocation(120);

        return jpCalendar;
    }
}

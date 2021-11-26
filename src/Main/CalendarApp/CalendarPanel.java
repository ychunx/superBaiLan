package Main.CalendarApp;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CalendarPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JLabel jlblHeader=new JLabel(" ",JLabel.CENTER);
    private JLabel[] jlblDay=new JLabel[49];
    private java.util.Calendar calendar=new GregorianCalendar();
    private int year0=calendar.get(java.util.Calendar.YEAR);
    private int month0=calendar.get(java.util.Calendar.MONTH);
    private int day0=calendar.get(java.util.Calendar.DAY_OF_MONTH);
    private int month;
    private int year;
    private int day;
    private JPanel jpDays=new JPanel(new GridLayout(0,7));
    Font font1=new Font("宋体",Font.ITALIC,20);
    Font font2=new Font("宋体",Font.BOLD,26);
    Font font3=new Font("宋体",Font.BOLD,30);
    public CalendarPanel()
    {
        //设置日历头部件以及日期标签的背景色为白色
        jlblHeader.setBackground(Color.WHITE);
        jpDays.setBackground(Color.WHITE);
        //声明每个标签
        for(int i=0;i<49;i++)
        {
            jlblDay[i]=new JLabel();
            jlblDay[i].setBorder(new LineBorder(Color.LIGHT_GRAY,1));
            jlblDay[i].setHorizontalAlignment(JLabel.RIGHT);
            jlblDay[i].setVerticalAlignment(JLabel.TOP);
        }
        calendar=new GregorianCalendar();
        month=calendar.get(java.util.Calendar.MONTH);
        year=calendar.get(java.util.Calendar.YEAR);
        day=calendar.get(java.util.Calendar.DATE);
        //更新日历
        updateCalendar();
        showHeader();
        showDays();
        //添加到主面板
        this.setLayout(new BorderLayout());
        this.add(jlblHeader, BorderLayout.NORTH);
        this.add(jpDays, BorderLayout.CENTER);
    }
    private void showHeader()
    {
        SimpleDateFormat sdf=new SimpleDateFormat("MMMM yyyy", Locale.CHINA);
        String header=sdf.format(calendar.getTime());
        jlblHeader.setText(header);
        jlblHeader.setForeground(Color.BLUE);
        jlblHeader.setFont(font3);
    }
    private void showDayNames()
    {
        DateFormatSymbols dfs=new DateFormatSymbols(Locale.CHINA);
        String dayNames[]=dfs.getWeekdays();
        for(int i=0;i<7;i++)
        {
            jlblDay[i].setText(dayNames[i+1]);
            jlblDay[i].setForeground(Color.BLUE);
            jlblDay[i].setHorizontalAlignment(JLabel.CENTER);
            jlblDay[i].setFont(font2);
            jpDays.add(jlblDay[i]);
        }
    }
    public void showDays()
    {
        jpDays.removeAll();
        showDayNames();
        int startingDayOfMonth=calendar.get(java.util.Calendar.DAY_OF_WEEK);
        java.util.Calendar cloneCalendar=(java.util.Calendar)calendar.clone();
        cloneCalendar.add(java.util.Calendar.DATE, -1);
        int daysInPrecedingMonth=cloneCalendar.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
        for(int i=0;i<startingDayOfMonth-1;i++)
        {
            jlblDay[i+7].setForeground(Color.LIGHT_GRAY);
            jlblDay[i+7].setHorizontalAlignment(JLabel.CENTER);
            jlblDay[i+7].setText(daysInPrecedingMonth-startingDayOfMonth+2+i+"");
            jlblDay[i+7].setFont(font1);
            jpDays.add(jlblDay[i+7]);
        }
        int daysInCurrentMonth=calendar.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
        for(int i=1;i<=daysInCurrentMonth;i++)
        {
            if(i==day0&&year==year0&&month==month0)
            {
                jlblDay[i-2+startingDayOfMonth+7].setForeground(Color.red);
                jlblDay[i-2+startingDayOfMonth+7].setHorizontalAlignment(JLabel.CENTER);
                jlblDay[i-2+startingDayOfMonth+7].setText(i+"");
                jlblDay[i-2+startingDayOfMonth+7].setFont(font2);
                jpDays.add(jlblDay[i-2+startingDayOfMonth+7]);
            }
            else
            {
                jlblDay[i-2+startingDayOfMonth+7].setForeground(Color.darkGray);
                jlblDay[i-2+startingDayOfMonth+7].setHorizontalAlignment(JLabel.CENTER);
                jlblDay[i-2+startingDayOfMonth+7].setText(i+"");
                jlblDay[i-2+startingDayOfMonth+7].setFont(font1);
                jpDays.add(jlblDay[i-2+startingDayOfMonth+7]);
            }
        }
        int j=1;
        for(int i=daysInCurrentMonth-1+startingDayOfMonth+7;i%7!=0;i++)
        {
            jlblDay[i].setForeground(Color.LIGHT_GRAY);
            jlblDay[i].setHorizontalAlignment(JLabel.CENTER);
            jlblDay[i].setText(j++ +"");
            jlblDay[i].setFont(font1);
            jpDays.add(jlblDay[i]);
        }
        jpDays.repaint();
    }
    public void updateCalendar()
    {
        calendar.set(java.util.Calendar.YEAR, year);
        calendar.set(java.util.Calendar.MONTH, month);
        calendar.set(java.util.Calendar.DATE, 1);
        month=calendar.get(java.util.Calendar.MONTH);
        year=calendar.get(java.util.Calendar.YEAR);
        day=calendar.get(Calendar.DATE);
    }
    public int getMonth()
    {
        return month;
    }
    public int getYear()
    {
        return year;
    }
    public void setMonth(int month)
    {
        this.month=month;
        updateCalendar();
        showHeader();
        showDays();
    }
    public void setYear(int year)
    {
        this.year=year;
        updateCalendar();
        showHeader();
        showDays();
    }
}
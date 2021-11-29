package Main.CalendarApp;

import javax.swing.*;

public class thread_mottoSparkle implements Runnable {
    private JLabel jlbl;
    public thread_mottoSparkle(JLabel jlbl)
    {
        this.jlbl=jlbl;
    }
    @Override
    public void run() {
        String content=jlbl.getText();
        int L=content.length();
        int index=0;
        while(true)
        {
            jlbl.setText(content.substring(0, index));
            try
            {
                Thread.sleep(250);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            index++;
            if(index==(L+1)) {
                index=0;
            }
        }
    }

}
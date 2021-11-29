package Main;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class tally {

    private static String filename = "tally.txt";

    public void FileIO() {
        File file = new File(filename);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void write(String data, boolean mode) {
        try {
            FileWriter fw = new FileWriter(filename,mode);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data);
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String read() {
        FileReader fr;
        String line = "";
        String fileStr = "";
        try {
            fr = new FileReader(filename);//不能一行行的读
            BufferedReader br = new BufferedReader(fr);//可以一行行的读效率高
            while((line = br.readLine()) != null) {
                fileStr += line + "\n";
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileStr;
    }

    //获取当前时间
    public static String getTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sdf.format(date);
        return str;
    }

    //将String转化成Vector类型
    public static Vector StringToVec(String data) {
        Vector bigVec = new Vector();
        if(data != null && !data.equals("")) {
            String[] array = data.split("\n");
            for(int i=0;i< array.length;i++) {
                String[] a = array[i].split(",");
                Vector smallVec = new Vector();
                smallVec.add(a[0]);
                smallVec.add(a[1]);
                smallVec.add(a[2]);
                smallVec.add(a[3]);
                smallVec.add(a[4]);
                smallVec.add(a[5]);
                smallVec.add(a[6]);
                smallVec.add(a[7]);
                smallVec.add(a[8]);
                smallVec.add(a[9]);
                smallVec.add(a[10]);
                bigVec.add(smallVec);
            }
        }
        return bigVec;
    }

    //获取下一个流水账号
    public static int getNewID() {
        int id = 1;
        String data = read();
        if(data != null && !data.equals("")) {
            Vector bigVec = StringToVec(data);
            Vector smallVecLast = (Vector)bigVec.get(bigVec.size()-1);
            String str = (String)smallVecLast.get(0);
            id = Integer.parseInt(str) + 1;
        }
        return id;
    }

    public static void add(String data) {
        write(data, true);
    }

    public static void delete(String data) {
        write(data, false);
    }

    //全部查询
    public static Vector select() {
        String str = read();
        return StringToVec(str);
    }

    //条件查询
    public static Vector select(String type, String remark) {
        Vector vecData = new Vector();
        Vector vecAll = select();
        for(int i = 0;i < vecAll.size();i++) {
            Vector smallVec = (Vector)vecAll.get(i);
            boolean select = (smallVec.get(1).toString().equals(type) || type.equals(""))
                    &&
                    (smallVec.get(4).toString().equals(remark) || remark.equals(""));
            if(select) {
                vecData.add(smallVec);
            }
        }
        return vecData;
    }
}

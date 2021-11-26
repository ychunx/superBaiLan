package Main;

import java.io.*;
import java.util.ArrayList;

public class toDo{

    private static String filename = "toDo.txt";    //文件路径名

    public static void write(String data,boolean mode) {
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

    //读取文件内容，返回字符串数组
    public static String[] read() {
        FileReader fr;
        String line = "";
        ArrayList listArr = new ArrayList();
        try {
            fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            while((line = br.readLine()) != null) {
                listArr.add(line + "\n");
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            //查询异常可能是路径不存在，所以直接新创一个数据文件
            File file = new File(filename);
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        //将listArr转换成字符串数组返回
        String[] r = new String[listArr.size()];
        for (int i=0;i<listArr.size();i++){
            r[i] = listArr.get(i).toString();
        }
        return r;
    }

    //添加方法
    public static void add(String data){ write(data,true); }
    //清除全部方法
    public static void clean(){ write("",false); }
    //删除方法，根据提供索引号复制给暂时新数组实现
    public static void delete(int num,String[] list){
        int j = 0;
        String[] temp = new String[list.length-1];
        for(int i=0;i< list.length;i++){
            if(i == num){
                continue;
            }
            temp[j++] = list[i];
        }
        list = temp;
        //清除数据文件中的所有内容
        clean();
        //添加内容回数据文件
        for (String s : list){
            add(s);
        }
    }
}
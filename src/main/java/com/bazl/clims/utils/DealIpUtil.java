package com.bazl.clims.utils;

public class DealIpUtil {

    /*
    * 根据参数的ip把小数点后不满三位以0补全
    * 如1.1.1 结果 001.001.001
    */
    public static String addZero(String ip){
        String result=""; //用保存处理后的结果
        String []array=ip.split("\\."); //这个函数将传入的字符串根据这个小点来分解，因为这个点式一个转义字符，所以需要写成"\\."
        for(int i=0;i<array.length;i++){
            while(array[i].length()<3){ //一共被分成了四个字符串，字符串里已经没有了小点，如果一个字符串的长度小于三，那么就在前面加零
                array[i]="0"+array[i];
            }
            if(i!=array.length-1){ //加完后，再继续在后面加上一个小点，因为最后一个不需要加，所以用if控制一下
                array[i]=array[i]+".";
            }
        }
        for(int i=0;i<array.length;i++){
            result+=array[i]; //将处理好的四个字符串连起来，这四个分别是 062. 004. 022. 115
        }
        return result;
    }

    public static String addZeroqudian(String ip){
        String result="";
        String []array=ip.split("\\.");
        for(int i=0;i<array.length;i++){
            while(array[i].length()<3){
                array[i]="0"+array[i];
            }
            if(i!=array.length-1){
                array[i]=array[i]+".";
            }
        }
        for(int i=0;i<array.length;i++){
            result+=array[i];
        }
        return result.replace(".", "");
    }
    public static void main(String args[]){
       System.out.println(DealIpUtil.addZeroqudian("1.1.1.1"));
    }
}

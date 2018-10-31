package common;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.UUID;


public class ThisSystemUtil {

	public static int parseInt(String target,int defaultValue){
    	try{
    		return Integer.parseInt(target.trim()); //trim()将输入的对象target去掉两端空格，parseInt将String类的target转为Int类型
    	}catch(Exception e){
    		return defaultValue; //如果输入存在异常则自动返回一个默认值defaultValue(默认值在调用方法时设定在方法参数中)
    	}
    	
    	
    /*
     * public static  String  md5(String inputStr){
        BigInteger bigInteger=null;
        try {
         MessageDigest md = MessageDigest.getInstance("MD5");   
         byte[] inputData = inputStr.getBytes(); 
         md.update(inputData);   
         bigInteger = new BigInteger(md.digest());
         bigInteger=bigInteger.abs();
        } catch (Exception e) {e.printStackTrace();}
        return bigInteger.toString(16);
    }
    	
    }
    public static String uuid(){
    	String uuid=UUID.randomUUID().toString();
    	char[] cs=new char[32];
    	char c=0;
    	for(int i=uuid.length(),j=0;i-->0;){
    		if((c=uuid.charAt(i))!='-'){
    			cs[j++]=c;
    		}
    	}
    	return new String(cs);
    }
    public static void main(String[] args) {
    	for(int i=10;i-->0;){
    		String u=uuid();
    		System.out.println(u);
    	}
	}
	*/
}
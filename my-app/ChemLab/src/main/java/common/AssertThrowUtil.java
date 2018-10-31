package common;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.time.DateUtils;

/**
 * Created by Administrator on 2017/3/15 0015.
 */
public class AssertThrowUtil {
	
    public static String assertNotBlank(String errorMessage,String input){
        if(input==null||(input=input.trim()).length()==0){
            throw new ThisSystemException(errorMessage);
        }
        return input;
    }
    //判定输入不能为空
    
    //把assertNotBlank方法简化为aNB
    public static String aNB(String errorMessage,String input){
        return assertNotBlank(errorMessage,input);
    } 
    
    /*
     * 方法使用：
     * 登录
     * account = AssertThrowUtil.assertNotBlank("输入不能为空!", input);
     * password = AssertThrowUtil.assertNotBlank("输入不能为空!", input);
     */
    
    public static void assertTrue(String message,boolean b){
    	if(!b){
    		throw new ThisSystemException(message);
    	}
    	
    }
    //如果某一判定为假，抛出错误异常
    public static void assertFalse(String message,boolean b){
    	if(b){
    		throw new ThisSystemException(message);
    	}
    }
  //如果某一判定为真，抛出错误异常
    
    public static void assertPatternMatch(String message,String reg,String target){
    	Pattern pattern=Pattern.compile(reg);
    	Matcher matcher = pattern.matcher(target);
    	if(!matcher.matches()){
    		throw new ThisSystemException(message);
    	}
    }
    //判断输入变量target格式是否符合reg标识的数据格式
      
    public static Date assertDatePatternAndParse(String message,String reg,String target,String [] datePattern){
    	if(target!=null||(target=target.trim()).length()!=0) {
    		Pattern stringPattern=Pattern.compile(reg);
        	Matcher matcher = stringPattern.matcher(target.trim());
        	if(!matcher.matches()){
        		throw new ThisSystemException(message);
        	}
        	Date date = DateUtils.parseDate(target.trim(), datePattern);
        	return date;
    	}
    }
  //在日期栏输入部位非空时判断输入日期格式是否符合数据库要求，并将输入的String类target对象转为规定格式的date类型并返回
    /*
     * 方法使用：
    Date yearOfGraduation = AssertThrowUtil.assertDatePattern("Illegal input!","\\d{4}",staffManagementArgumentObject.getYearOfGraduation(),new String[]{"yyyy"});
     */
    
    public static Integer assertIntPatternAndParse(String message,String reg,String target){
    	if(target!=null||(target=target.trim()).length()!=0) {
    		Pattern stringPattern=Pattern.compile(reg);
        	Matcher matcher = stringPattern.matcher(target.trim());
        	if(!matcher.matches()){
        		throw new ThisSystemException(message);
        	}
        	Integer input = Integer.parseInt(target.trim());
        	return input;
    	}
    	
    /*
    public static void assertNotNull(String message,Object o){
    	if(o==null){
    		throw new ThisSystemException(message);
    	}
    }
    public static void assertNull(String message,Object o){
    	if(o!=null){
    		throw new ThisSystemException(message);
    	}
    }
    public static void assertEquals(String message,Object a,Object b){
    	if(a==null?a!=b:!a.equals(b)){
    		throw new ThisSystemException(message);
    	}
    }
    public static void assertNotEquals(String message,Object a,Object b){
    	if(a==null?a==b:a.equals(b)){
    		throw new ThisSystemException(message);
    	}
    }
    
    public static void main(String[] args) {
    	
    	Pattern pattern=Pattern.compile("[\u4e00-\u9fa5]{2,}");
    	Matcher matcher = pattern.matcher("张飞");
    	if(matcher.matches()){
    		System.out.println("ddd");
    		
    	}
	}
	*/
}

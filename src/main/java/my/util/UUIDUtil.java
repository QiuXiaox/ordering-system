package my.util;

import java.util.UUID;

public class UUIDUtil {

	
    /** 
     * 获得一个UUID 
     * @return String UUID 
     */ 
    public static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
    } 
   
    
    /** 
     * 获得特定UUID 
     * USER_XR3Q_041211
     * @return String UUID 
     */ 
    public static String getUUID(String fix){ 
    	String s = UUID.randomUUID().toString(); 
    	String num = System.currentTimeMillis()+"";
        return fix+"_"+s.substring(9,13)+"_"+num.substring(6,12); 
    } 
   
    /** 
     * 获得短型特定UUID 
     * XR3Q_041211
     * @return String UUID 
     */ 
    public static String getSortUUID(){ 
    	String s = UUID.randomUUID().toString(); 
    	String num = System.currentTimeMillis()+"";
        return s.substring(9,13)+"_"+num.substring(6,12); 
    } 
    
    /** 
     * 获得短型无下划线特定UUID 
     * XR3Q041211
     * @return String UUID 
     */ 
    public static String getNoLineSortUUID(){ 
    	String s = UUID.randomUUID().toString(); 
    	String num = System.currentTimeMillis()+"";
        return s.substring(9,13)+num.substring(6,12); 
    }
    
    /** 
     * 获得一个UUID 
     * @return String UUID 
     */ 
    public static Long getNumUUID(){ 
        return System.currentTimeMillis(); 
    } 
    
    /** 
     * 获得特定UUID 
     * USER_XR3Q
     * @return String UUID 
     */ 
    public static String getUUID(String fix , int length){ 
    	String s = UUID.randomUUID().toString(); 
    	String id = s.substring(9,13);
        return fix+"_"+id; 
    } 
    
    public static void main(String[] args) {
		System.out.println(UUIDUtil.getUUID());
	}
}

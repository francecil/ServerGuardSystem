package com.france.serverGuard.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class KeepAlive {

	private static final long serialVersionUID = -2813120366138988480L;  
	  
    /* 覆盖该方法，仅用于测试使用。 
     * @see java.lang.Object#toString() 
     */  
    @Override  
    public String toString() {  
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\t维持连接包";  
    }  
}

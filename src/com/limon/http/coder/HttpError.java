/**   
 * @FileName: HttpError.java 
 * @Package:com.gzsoft.server.http.coder 
 * @Description: TODO
 * @author: GZSOFT m_lm@163.com  
 * @date:2013-9-27 下午03:36:28 
 * @version V1.0 
 * Copyright (c) 2013 GZSOFT,Inc. All Rights Reserved.
 */
package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

/**
 * @ClassName: HttpError 
 * @Description: 
 * @author: GZSOFT gqf
 * @date:2013-9-27 下午03:36:28 
 */
@XmlNode
public class HttpError
{
    @XmlNode(name="errorCode")
    private String errorCode="";
    
    @XmlNode(name="message")
    private String message="";

    public String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
    
    
}

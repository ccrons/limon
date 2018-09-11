/**   
 * @FileName: RegisterReq.java 
 * @Package:com.gzsoft.server.http.coder 
 * @Description: TODO
 * @author: GZSOFT m_lm@163.com  
 * @date:2013-9-25 下午02:23:52 
 * @version V1.0 
 * Copyright (c) 2013 GZSOFT,Inc. All Rights Reserved.
 */
package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

/**
 * @ClassName: RegisterReq 
 * @Description: 
 * @author: gqf
 * @date:2013-9-25 下午02:23:52 
 */
@XmlNode
public class HttpContent
{
    @XmlNode(name="MessageType")
    private String MessageType="";
    
    @XmlNode(name="MessageTimestamp")
    private String MessageTimestamp="";
    
    @XmlNode(name="MessageContent")
    private String MessageContent="";

    public String getMessageType()
    {
        return MessageType;
    }

    public void setMessageType(String messageType)
    {
        MessageType = messageType;
    }

    public String getMessageTimestamp()
    {
        return MessageTimestamp;
    }

    public void setMessageTimestamp(String messageTimestamp)
    {
        MessageTimestamp = messageTimestamp;
    }

    public String getMessageContent()
    {
        return MessageContent;
    }

    public void setMessageContent(String messageContent)
    {
        MessageContent = messageContent;
    }   
}
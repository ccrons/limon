package com.limon.http.bussiness;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.limon.http.coder.GetMessageReq;
import com.limon.http.coder.GetMessageRsp;
import com.limon.http.model.Message;
import com.limon.http.model.MessageList;
import com.limon.http.service.GetMessageService;
import com.limon.util.JSONUtil;

public class GetMessageHandler {
	private static final Logger log = Logger.getLogger(GetMessageHandler.class);   
	private GetMessageService service;
	private GetMessageRsp rsp=new GetMessageRsp();
	
	public GetMessageHandler(GetMessageService service){
		this.service=service;
	}

    public GetMessageRsp execute(GetMessageReq req,HttpServletRequest request){    	
        try{
        	Map<String,Object> map=new HashMap<String,Object>();
        	map.put("pageStart", (req.getPage()-1)*req.getPageNum());
        	map.put("pageSize", req.getPageNum());
        	List<Message> list=service.getMessageList(map);
        	
        	Integer recordNum=service.getMessageListCount(map);
        	MessageList msglist=new MessageList();
        	msglist.setList(list);
        	rsp.setResult("1");
            rsp.setMessageList(JSON.toJSONString(msglist,JSONUtil.features));
            rsp.setErrorMsg("");
            rsp.setRecordNum(recordNum+"");
        }catch(Exception e){            
            rsp.setResult("0");
            rsp.setMessageList("");
            rsp.setRecordNum("0");
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
}

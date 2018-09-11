package com.limon.http.bussiness;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import com.limon.http.coder.SetHeadImgReq;
import com.limon.http.coder.SetHeadImgRsp;
import com.limon.http.service.SetHeadImgService;
import com.limon.http.util.ByteUtil;
import com.limon.http.util.HttpDateUtil;

public class SetHeadImgHandler {
	private static final Logger log = Logger.getLogger(SetHeadImgHandler.class);   
	private SetHeadImgService service;
	private SetHeadImgRsp rsp=new SetHeadImgRsp();
	
	public SetHeadImgHandler(SetHeadImgService service){
		this.service=service;
	}

    public SetHeadImgRsp execute(SetHeadImgReq req,HttpServletRequest request){    	
        try{
        	String headimg="";
        	String filePath= request.getSession().getServletContext().getRealPath("upload")+"\\headimg"; 
        	byte[] img=ByteUtil.Hex2Bytes(req.getImg());
        	//保存头像到upload/headimg目录下
        	headimg=saveImg(img,filePath);
        	Map<String,Object> map=new HashMap<String,Object>();
        	map.put("uid",req.getUid());
        	map.put("headimg",headimg);
        	service.updatePerInfo(map);
        	rsp.setResult("1");
        	rsp.setErrorMsg("");
        }catch(Exception e){            
            rsp.setResult("0");
            rsp.setErrorMsg(e.getMessage());
            log.error("失败 - " + e.getMessage());
            e.printStackTrace();
        }         
        return rsp;
    }
    
    public String saveImg(byte[] bfile,String filePath){
    	String savepath="";
    	BufferedOutputStream bos = null;  
        FileOutputStream fos = null;  
        File file = null;  
        String fileName=HttpDateUtil.getNowDateString()+".jpg";
        try {  
            File dir = new File(filePath);  
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在  
                dir.mkdirs();  
            }  
            file = new File(filePath+"\\"+fileName);  
            fos = new FileOutputStream(file);  
            bos = new BufferedOutputStream(fos);  
            bos.write(bfile);  
            savepath="upload/headimg/"+fileName;
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (bos != null) {  
                try {  
                    bos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
            if (fos != null) {  
                try {  
                    fos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
        }  
        return savepath;
    }
}

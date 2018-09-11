package com.limon.wx.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xml.sax.SAXException;

import com.limon.base.controller.BaseController;
import com.limon.http.model.Order;
import com.limon.http.model.OrderProductGet;
import com.limon.http.service.PayOrderService;
import com.limon.util.TicketUtil;
import com.limon.util.WXAppPayUtil;
import com.limon.util.XMLParser;
import com.limon.wx.model.PayResult;
import com.limon.wx.model.PaySingInfo;
import com.limon.wx.model.UserInfo;
import com.limon.wx.service.ShopService;

/**
 * @author gqf
 *
 * 2017-2-9 下午4:32:56
 */
@Controller
@RequestMapping("/wx")
public class PayController extends BaseController{
	@Autowired
	private PayOrderService payOrderService;
	@Autowired
	private ShopService shopService;
	/**
	 * @Description:获取微信支付参数
	 * @return
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@RequestMapping("/getPayOrder")
	public String getPayOrder(){
		System.out.println("******getPayParams***微信支付**");
		UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
		String orderno = request.getParameter("orderno");
		String total_fee = request.getParameter("total_fee");
		System.out.println("orderno="+orderno+"==total_fee=="+total_fee);
		//订单金额单位转化，微信支付单位（分）
		Integer fee = (int)(Double.parseDouble(total_fee)*100);
		if(orderno != null && orderno.length()>0){
			//统一下单接口
			String result= WXAppPayUtil.getPayInfo(user.getUserOpenid(), "榴莲购订单", orderno, fee,"JSAPI",getIp(request));
			//解析统一下单的返回结果
			Map<String, String> map1;
			//支付参数
			String prepay_id = "";
			try {
				map1 = XMLParser.getMapFromXML(result);
				//通信标识
				if("SUCCESS".equals(map1.get("return_code"))){
					//交易是否成功需要查看result_code来判断
					if("SUCCESS".equals(map1.get("result_code"))){
						prepay_id = map1.get("prepay_id");
						//更新订单prepay_id
						shopService.updateOrderPrepay_id(orderno);
					}else{
						if("ORDERPAID".equals(map1.get("err_code"))){
							prepay_id = shopService.getOrderPrepay_id(orderno);
						}else{
							//失败
							request.setAttribute("err_code_des", map1.get("err_code_des"));
							request.setAttribute("orderno", orderno);
							return "/wx/payfail";
						}
					}
				}else{
					request.setAttribute("err_code_des","支付失败");
					request.setAttribute("orderno", orderno);
					return "/wx/payfail";
				}
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
			
			//签名
			PaySingInfo paySign = TicketUtil.paySign(prepay_id);
			
			String packageStr =paySign.getPackageStr();
			packageStr = packageStr.replaceAll("/", "%20");
			packageStr = packageStr.replaceAll("&amp;", "&");
			paySign.setPackageStr(packageStr);
			//System.out.println("支付签名信息"+paySign.getPackageStr());
			
			
			request.setAttribute("paySign", paySign);
			request.setAttribute("orderno", orderno);
			request.setAttribute("total_fee", total_fee);
			System.out.println("去支付 ==orderno="+orderno+"==fee="+total_fee);
			
			return "/wx/paysubmit";
		}else{
			return "/wx/ordererror";
		}
		
	}
	
	/**
	 * @Description:微信公众号支付成功通知 异步
	 * @return
	 */
	@RequestMapping("/payNotifyAsyn")
	public void payNotifyAsyn(){
		System.out.println("支付成功 异步通知******************************");
		try {
			request.setCharacterEncoding("UTF-8");  
		    response.setCharacterEncoding("UTF-8");  
		    response.setContentType("text/html;charset=UTF-8");  
		    response.setHeader("Access-Control-Allow-Origin", "*");   
		    InputStream in=request.getInputStream();  
		    
	    	ByteArrayOutputStream out=new ByteArrayOutputStream();  
		    byte[] buffer =new byte[1024];  
		    int len=0;  
			while((len=in.read(buffer))!=-1){  
			    out.write(buffer, 0, len);  
			}
		    out.close();  
		    in.close();  
		    String msgxml=new String(out.toByteArray(),"utf-8");//xml数据  
		    System.out.println(msgxml);   
		    if(msgxml!=null&&!msgxml.equals("")){
			    Map map = XMLParser.getMapFromXML(msgxml);
			    String result_code	=	(String) map.get("result_code");  
			    String out_trade_no	=	(String) map.get("out_trade_no");  
			    String total_fee	=	(String) map.get("total_fee"); 
			    String wx_orderno =	(String) map.get("transaction_id"); 
			    String trade_state=(String) map.get("trade_state");
			    
			    if("SUCCESS".equals(trade_state)){
			    	//支付成功后业务处理
			    	Order order=shopService.getOrderByOrderno(out_trade_no);
			    	if(order.getStatus().equals("0")){
				    	Map<String,Object> pmap=new HashMap<String,Object>();
				    	pmap.put("id", order.getId());
				    	pmap.put("payno",wx_orderno);
				    	pmap.put("paytype",2);
				    	pmap.put("status",1);
			        	//更新订单状态
				    	payOrderService.updateOrderStatus(pmap);
			        	//查询订单信息
			        	List<OrderProductGet> plist=payOrderService.getProductListByOid(order.getId());
			        	//减少商家库存
			        	Map<String,Object> smap=new HashMap<String,Object>();
			        	smap.put("storeid", order.getSid());
			        	for(OrderProductGet op:plist){
			        		smap.put("pnum",op.getCount());
			        		smap.put("productid",op.getGid());
			        		payOrderService.updateStoreSaleNum(smap);
			        	}
			    	}
			    	String result = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";  
				    response.getWriter().write(result);    //告诉微信已经收到通知了  
			    }
		    }
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 微信公众号支付成功通知 同步
	 * @return
	 */
	@RequestMapping("/payNotifySyn")
	public String payNotifySyn(){
		System.out.println("支付成功同步通知返回页面");
		String orderno = request.getParameter("orderno");
		//支付成功后业务处理
		Order order=shopService.getOrderByOrderno(orderno);
		PayResult pr=WXAppPayUtil.queryOrder(orderno);
		if(pr!=null&&pr.getTrade_state().equals("SUCCESS")&&order.getStatus().equals("0")){
			//支付成功修改状态
			Map<String,Object> pmap=new HashMap<String,Object>();
	    	pmap.put("id", order.getId());
	    	pmap.put("payno",pr.getTransaction_id());
	    	pmap.put("paytype",2);
	    	pmap.put("status",1);
	    	//更新订单状态
	    	payOrderService.updateOrderStatus(pmap);
	    	//查询订单信息
	    	List<OrderProductGet> plist=payOrderService.getProductListByOid(order.getId());
	    	//减少商家库存
	    	Map<String,Object> smap=new HashMap<String,Object>();
	    	smap.put("storeid", order.getSid());
	    	for(OrderProductGet op:plist){
	    		smap.put("pnum",op.getCount());
	    		smap.put("productid",op.getGid());
	    		payOrderService.updateStoreSaleNum(smap);
	    	}
		}
		
    	return "redirect:orderdetail?oid="+order.getId();
	}
	
	public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }
}

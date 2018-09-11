package com.limon.base.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.limon.base.model.AUser;
import com.limon.base.model.SysLog;
import com.limon.base.model.SysMenu;
import com.limon.base.model.SysUser;
import com.limon.base.service.SysConfigService;
import com.limon.base.service.SysLogService;
import com.limon.base.service.SysMenuService;
import com.limon.http.model.GoodsLimit;
import com.limon.http.model.ShopMyGoodsIndex;
import com.limon.http.service.FavGoodsService;
import com.limon.http.service.GetGoodsInfoService;
import com.limon.http.service.GetMyGoodsByShopService;
import com.limon.manage.model.Recruit;
import com.limon.manage.model.StoreAdProduct;
import com.limon.manage.model.StoreAdProductInfo;
import com.limon.manage.model.TimeLimit;
import com.limon.store.service.OrderInfoService;
import com.limon.store.service.StoreAdService;
import com.limon.util.ConfigUtil;
import com.limon.util.DateUtil;
import com.limon.wx.model.UserInfo;

/**
 * @author gqf
 *
 * 主页相关流程
 * 2015-2-10 上午10:32:56
 */
@Controller
public class MainController extends BaseController{
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SysLogService sysLogService;
	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private StoreAdService storeAdService;
	@Autowired
	private SysConfigService sysConfigService;
	@Autowired
	private GetGoodsInfoService getGoodsInfoService;
	@Autowired
	private GetMyGoodsByShopService getMyGoodsByShopService;
	@Autowired
	private FavGoodsService favGoodsService;
	
	@RequestMapping("/main")
    public String index(HttpServletRequest request, HttpServletResponse response){
		SysUser user=this.getLoginUser();
		List<SysMenu> menulist=sysMenuService.getRoleMenuList(user.getRoleid());
		String logtype=(String) request.getSession().getAttribute("logtype");
		// 登录类型
		request.setAttribute("logtype",logtype);
		//当前登录用户
		request.setAttribute("loginUser",user);
		//根据用户角色取有权限的菜单列表
		request.setAttribute("menulist",menulist);
        return "/index";
    }
	
	@RequestMapping("/welcome")
    public String welcome(HttpServletRequest request, HttpServletResponse response){
		SysUser user=(SysUser)request.getSession().getAttribute("loginUser");
		String logtype=(String)request.getSession().getAttribute("logtype");
		if(logtype.equals("1")){
			logtype="3";
		}
		if(logtype.equals("0")){
			logtype="4";
		}
		Integer logtimes=sysLogService.getLogTimes(user.getUsername(),logtype);
		SysLog lastlog=sysLogService.getLastLogInfo(user.getUsername(),logtype);
		
		//当前用户登录次数
		request.setAttribute("logtimes",logtimes);
		//用户最近一次登录信息
		request.setAttribute("lastlog",lastlog);
        return "/welcome";
    }
	
	@RequestMapping("/getmsg")
    public String getmsg(HttpServletRequest request, HttpServletResponse response){
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			Integer uid=this.getLoginUser().getId();
			map.put("storeid", uid);
			map.put("nowday",DateUtil.getToday());
			Integer neworder=orderInfoService.getNewOrderListCount(map);
			if(neworder>0){
				response.getWriter().write("您有"+neworder+"笔新订单，点击查看");
			}else{
				response.getWriter().write("");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }
	
	@RequestMapping("/rec")
    public String rec(HttpServletRequest request, HttpServletResponse response){
		Integer sid=this.getParaInteger("sid");
		String recpic=ConfigUtil.getConfig("recpic").getConfig_value();
		try {
			Recruit rec=sysConfigService.getRec();
			request.setAttribute("recpic",recpic);
			request.setAttribute("rec",rec);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("sid", sid);
		return "wap/rec";
    }
	
	/**
	 * 0元购列表
	 * @return
	 */
	@RequestMapping("/zero")
    public String zero(HttpServletRequest request, HttpServletResponse response){
		UserInfo user = (UserInfo)request.getSession().getAttribute("loginUser");
		if(user == null){
			return "redirect:wxindex";
		}
		Integer sid=this.getParaInteger("sid");
		String fpic=ConfigUtil.getConfig("fpic").getConfig_value();
		try {
			List<ShopMyGoodsIndex> saplist=getMyGoodsByShopService.getMyGoodsFirstByShop(sid);
			for(ShopMyGoodsIndex sap:saplist){
				sap.setInfourl(allPath+"spinfo?sid="+sid+"&pid="+sap.getId());
				//活动验证
    			StoreAdProduct ap = storeAdService.getStoreAdProductByProductId(sap.getId()+"");
    			if(ap!=null){
    				sap.setAdid(ap.getAdid()+"");
    				sap.setAdprice(ap.getAdprice());
    			}
    			TimeLimit tl = storeAdService.getTimeLimitByProductId(sap.getId()+"");
    			if(tl!=null){
    				sap.setTltime(tl.getTltime());
    				sap.setTlprice(tl.getTlprice());
    			}
			}
			request.setAttribute("fpic",fpic);
			request.setAttribute("saplist",saplist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("sid", sid);
		Integer cartnum=favGoodsService.getCartNum(user.getId());
    	request.setAttribute("cartnum", cartnum);
		return "wap/zero";
    }
	
	/**
	 * 抽奖转盘
	 * @return
	 */
	@RequestMapping("/luck")
    public String luck(HttpServletRequest request, HttpServletResponse response){
		Integer sid=this.getParaInteger("sid");
		Integer uid=this.getParaInteger("uid");
		AUser au=sysConfigService.getAppUserById(uid);
		try {
			List<StoreAdProductInfo> saplist=sysConfigService.getLuck(sid);
			request.setAttribute("saplist",saplist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("sid", sid);
		request.setAttribute("uid", uid);
		request.setAttribute("hasluck", au.getHasluck());
		return "wap/luck";
    }
	
	/**
	 * 更改抽奖状态
	 */
	@RequestMapping("/cluck")
    public void cluck(HttpServletRequest request, HttpServletResponse response){
		Integer rs=0;
		Integer uid=this.getParaInteger("uid");
		
		try {
			sysConfigService.changeLuck(uid);
			rs=1;
			response.getWriter().write(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	/**
	 * 限时抢购详情
	 * @return
	 */
	@RequestMapping("/timelimit")
    public String timelimit(HttpServletRequest request, HttpServletResponse response){
		Integer sid=this.getParaInteger("sid");
		Integer uid=this.getParaInteger("uid");
		String isstart="0";
		try {
			String tlpic=ConfigUtil.getConfig("tlpic").getConfig_value();
			String fstime1=ConfigUtil.getConfig("fstime1").getConfig_value();
			String fetime1=ConfigUtil.getConfig("fetime1").getConfig_value();
			String fstime2=ConfigUtil.getConfig("fstime2").getConfig_value();
			String fetime2=ConfigUtil.getConfig("fetime2").getConfig_value();
			String fstime3=ConfigUtil.getConfig("fstime3").getConfig_value();
			String fetime3=ConfigUtil.getConfig("fetime3").getConfig_value();
			request.setAttribute("stime1",fstime1);
			request.setAttribute("stime2",fstime2);
			request.setAttribute("stime3",fstime3);
			request.setAttribute("etime1",fetime1);
			request.setAttribute("etime2",fetime2);
			request.setAttribute("etime3",fetime3);
			//时间处理
			SimpleDateFormat datesdf=new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat datetimesdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d=new Date();//当前时间
			String fstime="";
			String fetime="";
			String tltime="1,";
			if(fstime1!=null&&!fstime1.equals("")&&fetime1!=null&&!fetime1.equals("")){
				//抢购1开始时间
				Date stime=datetimesdf.parse(datesdf.format(d)+" "+fstime1+":00");
				//抢购1结束时间
				Date etime=datetimesdf.parse(datesdf.format(d)+" "+fetime1+":00");
				System.out.println(datetimesdf.format(d)+"=========="+datetimesdf.format(stime)+"=========="+datetimesdf.format(etime));
				//如果当前时间小于抢购1结束时间  显示抢购1的产品列表
				if(d.getTime()<etime.getTime()){
					tltime="1,";
				}
				if(d.getTime()<etime.getTime()&&d.getTime()>stime.getTime()){
					isstart="1";
				}
				if(d.getTime()>etime.getTime()){
					isstart="4";
				}
				fstime1=datetimesdf.format(stime);
				fetime1=datetimesdf.format(etime);
				fstime=fstime1;
				fetime=fetime1;
			}
			if(fstime2!=null&&!fstime2.equals("")&&fetime2!=null&&!fetime2.equals("")){
				//抢购2开始时间
				Date stime=datetimesdf.parse(datesdf.format(d)+" "+fstime2+":00");
				//抢购2结束时间
				Date etime=datetimesdf.parse(datesdf.format(d)+" "+fetime2+":00");
				System.out.println(datetimesdf.format(d)+"=========="+datetimesdf.format(stime)+"=========="+datetimesdf.format(etime));
				//如果当前时间小于抢购1结束时间  显示抢购1的产品列表
				if(d.getTime()<etime.getTime()&&d.getTime()>datetimesdf.parse(fetime).getTime()){
					tltime="2,";
				}
				if(d.getTime()<etime.getTime()&&d.getTime()>stime.getTime()){
					isstart="2";
				}
				if(d.getTime()>etime.getTime()){
					isstart="5";
				}
				fstime2=datetimesdf.format(stime);
				fetime2=datetimesdf.format(etime);
				fstime=fstime2;
				fetime=fetime2;
			}
			if(fstime3!=null&&!fstime3.equals("")&&fetime3!=null&&!fetime3.equals("")){
				//抢购2开始时间
				Date stime=datetimesdf.parse(datesdf.format(d)+" "+fstime3+":00");
				//抢购2结束时间
				Date etime=datetimesdf.parse(datesdf.format(d)+" "+fetime3+":00");
				System.out.println(datetimesdf.format(d)+"=========="+datetimesdf.format(stime)+"=========="+datetimesdf.format(etime));
				//如果当前时间小于抢购1结束时间  显示抢购1的产品列表
				if(d.getTime()<etime.getTime()&&d.getTime()>datetimesdf.parse(fetime).getTime()){
					tltime="3,";
				}
				if(d.getTime()<etime.getTime()&&d.getTime()>stime.getTime()){
					isstart="3";
				}
				if(d.getTime()>etime.getTime()){
					isstart="6";
				}
				fstime3=datetimesdf.format(stime);
				fetime3=datetimesdf.format(etime);
				fstime=fstime3;
				fetime=fetime3;
			}
		
			List<StoreAdProductInfo> saplist=sysConfigService.getTLimit(sid,tltime);
			
			for(StoreAdProductInfo sap:saplist){
				Map<String,Object> m=new HashMap<String,Object>();
            	m.put("uid",uid);
            	m.put("pid",sap.getId());
            	m.put("sid",sid);
    			m.put("stime",fstime);
    			m.put("etime",fetime);
    			GoodsLimit gla=sysConfigService.getBuyNum(m);
            	GoodsLimit glt=sysConfigService.getLimitNum(m);
            	if(gla!=null){
            		if(glt.getBuynum()==null){
            			glt.setBuynum(0);
            		}
            		sap.setBuynum(gla.getBuynum()+"");
            	}else{
            		sap.setBuynum("0");
            	}
            	if(glt!=null){
            		if(glt.getLimitnum()==null){
            			glt.setLimitnum(0);
            		}
            		if(glt.getLimitnum()>0){
            			sap.setLimitnum(glt.getLimitnum()+"");
            			sap.setIslimit("0");
            		}else{
            			sap.setLimitnum("0");
            			sap.setIslimit("1");
            		}
            	}else{
            		sap.setLimitnum("0");
            		sap.setIslimit("1");
            	}
            	
            	//活动验证
    			StoreAdProduct ap = storeAdService.getStoreAdProductByProductId(sap.getId()+"");
    			if(ap!=null){
    				sap.setAdid(ap.getAdid()+"");
    				sap.setAdprice(ap.getAdprice());
    			}
    			TimeLimit tl = storeAdService.getTimeLimitByProductId(sap.getId()+"");
    			if(tl!=null){
    				sap.setTltime(tl.getTltime());
    				sap.setTlprice(tl.getTlprice());
    			}
			}
			
			
			request.setAttribute("tlpic",tlpic);
			request.setAttribute("saplist",saplist);
			request.setAttribute("fstime1",fstime1);
			request.setAttribute("fstime2",fstime2);
			request.setAttribute("fstime3",fstime3);
			request.setAttribute("fetime1",fetime1);
			request.setAttribute("fetime2",fetime2);
			request.setAttribute("fetime3",fetime3);
			request.setAttribute("tltime", tltime);
			request.setAttribute("isstart", isstart);
			Calendar c=Calendar.getInstance();
			String s=datetimesdf.format(c.getTime());
			String nowtime=s.replaceAll("-","/").replaceAll(" ",",");
			request.setAttribute("nowtime", nowtime);
			request.setAttribute("sid", sid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("sid", sid);
		return "wap/timelimit";
    }
	
	/**
	 * 广告商品详情
	 * @return
	 */
	@RequestMapping("/pinfo")
    public String pinfo(HttpServletRequest request, HttpServletResponse response){
		Integer pid=this.getParaInteger("pid");
		Integer sid=this.getParaInteger("sid");
		request.setAttribute("sid",sid);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("pid",pid);
		map.put("sid",sid);
		StoreAdProductInfo sapi=storeAdService.getStoreAdProductByPId(map);
		
		try {
			
			//活动验证
			StoreAdProduct ap = storeAdService.getStoreAdProductByProductId(pid+"");
			if(ap!=null){
				sapi.setAdid(ap.getAdid()+"");
				sapi.setAdprice(ap.getAdprice());
			}
			TimeLimit tl = storeAdService.getTimeLimitByProductId(pid+"");
			if(tl!=null){
				sapi.setTltime(tl.getTltime());
				sapi.setTlprice(tl.getTlprice());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("sapi", sapi);
		return "wap/pinfo";
    }
	
	/**
	 * 正常的商品详情
	 * @return
	 */
	@RequestMapping("/spinfo")
    public String spinfo(HttpServletRequest request, HttpServletResponse response){
		UserInfo user = (UserInfo)request.getSession().getAttribute("loginUser");
		Integer pid=this.getParaInteger("pid");
		Integer sid=this.getParaInteger("sid");
		request.setAttribute("sid",sid);
		StoreAdProductInfo sapi=sysConfigService.getStoreProductByPId(pid,sid);
		
		try {
			Map<String,String> map=new HashMap<String,String>();
        	map.put("storeid", sid+"");
        	map.put("productid", pid+"");
			map.put("userid",user.getId()+"");
        	Integer s=getGoodsInfoService.getIsFav(map);
        	if(s>0){
        		sapi.setIsfav(1);
        	}else{
        		sapi.setIsfav(0);
        	}
			//活动验证
			StoreAdProduct ap = storeAdService.getStoreAdProductByProductId(pid+"");
			if(ap!=null){
				//System.out.println(ap.getAdid()+"==="+ap.getAdprice());
				sapi.setAdid(ap.getAdid()+"");
				sapi.setAdprice(ap.getAdprice());
			}
			
			List<String> tltimes=storeAdService.getTimeLimitProduct(pid);
			if(tltimes!=null&&tltimes.size()>0){
				String tl=tltimes.get(0);
				//时间处理
    			SimpleDateFormat datesdf=new SimpleDateFormat("yyyy-MM-dd");
    			SimpleDateFormat datetimesdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    			Date d=new Date();//当前时间
    			
    			String fstime1=ConfigUtil.getConfig("fstime1").getConfig_value();
				String fetime1=ConfigUtil.getConfig("fetime1").getConfig_value();
    			Date stime1=datetimesdf.parse(datesdf.format(d)+" "+fstime1+":00");
				Date etime1=datetimesdf.parse(datesdf.format(d)+" "+fetime1+":00");
				
				String fstime2=ConfigUtil.getConfig("fstime2").getConfig_value();
				String fetime2=ConfigUtil.getConfig("fetime2").getConfig_value();
    			Date stime2=datetimesdf.parse(datesdf.format(d)+" "+fstime2+":00");
				Date etime2=datetimesdf.parse(datesdf.format(d)+" "+fetime2+":00");
				
				String fstime3=ConfigUtil.getConfig("fstime3").getConfig_value();
				String fetime3=ConfigUtil.getConfig("fetime3").getConfig_value();
    			Date stime3=datetimesdf.parse(datesdf.format(d)+" "+fstime3+":00");
				Date etime3=datetimesdf.parse(datesdf.format(d)+" "+fetime3+":00");
				if(tl.indexOf("1,")!=-1&&d.getTime()>stime1.getTime()&&d.getTime()<etime1.getTime()){
					//活动验证
	    			TimeLimit timel = storeAdService.getTimeLimitByProductId(pid+"");
					if(timel!=null){
						sapi.setTltime(timel.getTltime());
						sapi.setTlprice(timel.getTlprice());
					}
				}else if(tl.indexOf("2,")!=-1&&d.getTime()>stime2.getTime()&&d.getTime()<etime2.getTime()){
					//活动验证
	    			TimeLimit timel = storeAdService.getTimeLimitByProductId(pid+"");
					if(timel!=null){
						sapi.setTltime(timel.getTltime());
						sapi.setTlprice(timel.getTlprice());
					}
				}else if(tl.indexOf("3,")!=-1&&d.getTime()>stime3.getTime()&&d.getTime()<etime3.getTime()){
					//活动验证
	    			TimeLimit timel = storeAdService.getTimeLimitByProductId(pid+"");
					if(timel!=null){
						sapi.setTltime(timel.getTltime());
						sapi.setTlprice(timel.getTlprice());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("sapi", sapi);
		return "wap/spinfo";
    }
	
	/**
	 * 0元购商品详情
	 * @return
	 */
	@RequestMapping("/zpinfo")
    public String zpinfo(HttpServletRequest request, HttpServletResponse response){
		Integer pid=this.getParaInteger("pid");
		Integer sid=this.getParaInteger("sid");
		request.setAttribute("sid",sid);
		StoreAdProductInfo sapi=sysConfigService.getZeroProductByPId(pid,sid);
		
		try {
			//活动验证
			StoreAdProduct ap = storeAdService.getStoreAdProductByProductId(pid+"");
			if(ap!=null){
				sapi.setAdid(ap.getAdid()+"");
				sapi.setAdprice(ap.getAdprice());
			}
			TimeLimit tl = storeAdService.getTimeLimitByProductId(pid+"");
			if(tl!=null){
				sapi.setTltime(tl.getTltime());
				sapi.setTlprice(tl.getTlprice());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("sapi",sapi);
		return "wap/zpinfo";
    }
	
	/**
	 * 限时抢购详情
	 * @return
	 */
	@RequestMapping("/tpinfo")
    public String tpinfo(HttpServletRequest request, HttpServletResponse response){
		Integer pid=this.getParaInteger("pid");
		Integer sid=this.getParaInteger("sid");
		Integer islimit=this.getParaInteger("islimit");
		Integer buynum=this.getParaInteger("buynum");
		Integer limitnum=this.getParaInteger("limitnum");
		request.setAttribute("sid",sid);
		request.setAttribute("islimit",islimit);
		request.setAttribute("buynum",buynum);
		request.setAttribute("limitnum",limitnum);
		StoreAdProductInfo sapi=sysConfigService.getTLimitProductByPId(pid,sid);
		
		try {
			//活动验证
			StoreAdProduct ap = storeAdService.getStoreAdProductByProductId(pid+"");
			if(ap!=null){
				sapi.setAdid(ap.getAdid()+"");
				sapi.setAdprice(ap.getAdprice());
			}
			TimeLimit tl = storeAdService.getTimeLimitByProductId(pid+"");
			if(tl!=null){
				sapi.setTltime(tl.getTltime());
				sapi.setTlprice(tl.getTlprice());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("sapi",sapi);
		
		return "wap/tpinfo";
    }
	
	
	@RequestMapping("/getnow")
    public void getNowTime(HttpServletRequest request, HttpServletResponse response){
		try {
			SimpleDateFormat datetimesdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar c=Calendar.getInstance();
			String s=datetimesdf.format(c.getTime());
			s=s.replaceAll("-","/").replaceAll(" ",",");
			System.out.println(s+"-----------------");
			response.getWriter().write(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}

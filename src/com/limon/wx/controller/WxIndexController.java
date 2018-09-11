package com.limon.wx.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.chainsaw.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.limon.base.controller.BaseController;
import com.limon.http.model.Address;
import com.limon.http.model.CarGoods;
import com.limon.http.model.City;
import com.limon.http.model.District;
import com.limon.http.model.FavGoods;
import com.limon.http.model.Goods;
import com.limon.http.model.GoodsLimit;
import com.limon.http.model.Order;
import com.limon.http.model.OrderProductGet;
import com.limon.http.model.OrderProductInfo;
import com.limon.http.model.ProductType;
import com.limon.http.model.Province;
import com.limon.http.model.Shop;
import com.limon.http.model.ShopAd;
import com.limon.http.model.ShopMyGoodsIndex;
import com.limon.http.model.ShoppingCart;
import com.limon.http.service.AddAddressService;
import com.limon.http.service.AddCarService;
import com.limon.http.service.DelAddressService;
import com.limon.http.service.DelCarService;
import com.limon.http.service.DelFavGoodsService;
import com.limon.http.service.FavGoodsService;
import com.limon.http.service.FeedBackService;
import com.limon.http.service.GetAddressService;
import com.limon.http.service.GetAllAddressService;
import com.limon.http.service.GetCarGoodsService;
import com.limon.http.service.GetCityService;
import com.limon.http.service.GetDistrictService;
import com.limon.http.service.GetFavGoodsService;
import com.limon.http.service.GetGoodsByTypeService;
import com.limon.http.service.GetGoodsFTypeService;
import com.limon.http.service.GetMyGoodsByShopService;
import com.limon.http.service.GetNearShopService;
import com.limon.http.service.GetProvinceService;
import com.limon.http.service.GetShopAdService;
import com.limon.http.service.GetShopGoodsBuyNumService;
import com.limon.http.service.GetShopInfoService;
import com.limon.http.service.SubmitOrderService;
import com.limon.http.service.UpdateAddressService;
import com.limon.http.service.UpdateCarService;
import com.limon.http.service.UpdateOrderService;
import com.limon.manage.model.ProductInfo;
import com.limon.manage.model.StoreAdProduct;
import com.limon.manage.model.TimeLimit;
import com.limon.store.service.StoreAdService;
import com.limon.util.CommonUtil;
import com.limon.util.ConfigUtil;
import com.limon.util.DateUtil;
import com.limon.util.TicketUtil;
import com.limon.wx.model.SignInfo;
import com.limon.wx.model.UserInfo;
import com.limon.wx.service.ShopService;

/**
 * @author gqf
 *
 * 首页
 * 2015-2-10 上午10:32:56
 */
@Controller
@RequestMapping("/wx")
public class WxIndexController extends BaseController{	
	@Autowired
	private GetNearShopService getNearShopService;
	@Autowired
	private GetShopInfoService getShopInfoService;
	@Autowired
	private GetShopAdService getShopAdService;
	@Autowired
	private GetMyGoodsByShopService getMyGoodsByShopService;
	@Autowired
	private GetGoodsFTypeService getGoodsFTypeService;
	@Autowired
	private GetGoodsByTypeService getGoodsByTypeService;
	@Autowired
	private StoreAdService storeAdService;
	@Autowired
	private FavGoodsService favGoodsService;
	@Autowired
	private DelFavGoodsService delFavGoodsService;
	@Autowired
	private AddCarService addCarService;
	@Autowired
	private GetCarGoodsService getCarGoodsService;
	@Autowired
	private GetShopGoodsBuyNumService getShopGoodsBuyNumService;
	@Autowired
	private DelCarService delCarService;
	@Autowired
	private UpdateCarService updateCarService; 
	@Autowired
	private GetAddressService getAddressService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private GetProvinceService getProvinceService;
	@Autowired
	private GetCityService getCityService;
	@Autowired
	private GetDistrictService getDistrictService;
	@Autowired
	private AddAddressService addAddressService;
	@Autowired
	private GetAllAddressService getAllAddressService;
	@Autowired
	private SubmitOrderService submitOrderService;
	@Autowired
	private GetFavGoodsService getFavGoodsService;
	@Autowired
	private FeedBackService feedBackService;
	@Autowired
	private UpdateAddressService updateAddressService;
	@Autowired
	private DelAddressService delAddressService;
	@Autowired
	private UpdateOrderService updateOrderService;
	/**
	 * 获取定位信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response){
		//微信签名获取
		UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
		if(user == null){
			return "redirect:wxindex";
		}
		SignInfo sign=TicketUtil.getSign("http://shop.duriantimes.com/wx/index");
		request.setAttribute("sign",sign);
        //购物车商品数量
    	Integer cartnum=favGoodsService.getCartNum(user.getId());
    	request.setAttribute("cartnum", cartnum);
		//首页数据处理
		System.out.println(user.getNickname()+"访问首页......");
		return "/wx/index";
	}
	
	/**
	 * 首页banner
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/ajaxbanner")
	public void ajaxbanner(HttpServletRequest request, HttpServletResponse response){
		try{
			UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
			String str="";
			String lat=request.getParameter("lat");
			String lng=request.getParameter("lng");
			Map<String,String> map=new HashMap<String,String>();
			map.put("userlat",lat);
			map.put("userlng",lng);
			//为IOS审核设置大范围搜索2000米
			map.put("radius", "3000000");
			List<Shop> shoplist=getNearShopService.getNearShop(map);
			Shop s=null;
			if(shoplist.size()>0){
				s=shoplist.get(0);
				System.out.println("最近店铺");
			}else{
				//默认店铺
				s=getShopInfoService.getShopById(24);
				System.out.println("默认店铺");
			}
			//店铺保存session
			request.getSession().setAttribute("shop", s);
			
			//首页banner获取
			List<ShopAd> adlist=getShopAdService.getShopAdBySid(s.getId());
			String allPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+request.getContextPath()+"/";

			int i=0;
			for(ShopAd ad:adlist){
				ad.setAdurl(allPath+"storead/show?id="+ad.getId()+"&sid="+s.getId());
				if(ad.getImgurl()!=null&&!ad.getImgurl().equals("")){
					ad.setImgurl(allPath+ad.getImgurl());
				}
				str+="<li data-target=\"#carousel-example-generic\" data-slide-to=\""+i+"\"";
				if(i==0){
					str+=" class=\"active\"";
				}
				str+="></li>";
				i++;
			}
			str+="|";
			int j=0;
			for(ShopAd ad:adlist){
				str+="<div class=\"item ";
				if(j==0){
					str+="active";
				}
				str+=" aa\">";
				str+="<a href=\""+ad.getAdurl()+"\"><img src=\""+ad.getImgurl()+"\" alt=\"...\" style=\"height:200px\"></a>";
				str+="</div>";
				j++;
			}
			str+="|/timelimit?sid="+s.getId()+"&uid="+user.getId();
			str+="|/zero?sid="+s.getId();
			response.getWriter().write(str);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 首页banner
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/ajaxindexgoods")
	public void ajaxindexgoods(HttpServletRequest request, HttpServletResponse response){
		try{
			//店铺获取
			String lat=request.getParameter("lat");
			String lng=request.getParameter("lng");
			Map<String,String> map=new HashMap<String,String>();
			map.put("userlat",lat);
			map.put("userlng",lng);
			//为IOS审核设置大范围搜索2000米
			map.put("radius", "5000");
			List<Shop> shoplist=getNearShopService.getNearShop(map);
			Shop s=null;
			if(shoplist.size()>0){
				s=shoplist.get(0);
				System.out.println("最近店铺");
			}else{
				//默认店铺
				s=getShopInfoService.getShopById(24);
				System.out.println("默认店铺");
			}
			
			String str="";
			//首页明星单品
	        List<ShopMyGoodsIndex> slist=getMyGoodsByShopService.getMyGoodsRecByShop(s.getId());
	        int i=0;
	        str+="<tr>";
	    	for(ShopMyGoodsIndex goods:slist){
	    		if(goods.getImgurl()!=null&&!goods.getImgurl().equals("")){
	    			goods.setImgurl(allPath+goods.getImgurl());
	        	}else{
	        		goods.setImgurl("");
	        	}
	    		goods.setInfourl(allPath+"spinfo?sid="+s.getId()+"&pid="+goods.getId());
	    		str+="<td>";
	    		str+="<a href=\""+goods.getInfourl()+"\"  style=\"text-decoration:none;color:#000000;\">";
	    		str+="<img src=\""+goods.getImgurl()+"\" alt=\"\" style=\"width: 160px;\">";
	    		str+="<p>"+goods.getName()+"</p>";
	    		str+="<p class=\"tj-p\"><i>￥"+goods.getPrice()+"</i>元</p>";
	    		str+="</td>";
	    		if((i+1)%2==0){
	    			str+="</tr>";
	    			if((i+1)<slist.size()){
	    				str+="<tr>";
	    			}
	    		}
	    		i++;
	    	}
	    	str+="|";
	    	
	    	//首页超值特价
	    	List<ShopMyGoodsIndex> glist=getMyGoodsByShopService.getMyGoodsFirstByShop(s.getId());
	    	int j=0;
	    	str+="<tr>";
	    	for(ShopMyGoodsIndex goods:glist){
	    		if(goods.getImgurl()!=null&&!goods.getImgurl().equals("")){
	    			goods.setImgurl(allPath+goods.getImgurl());
	        	}else{
	        		goods.setImgurl("");
	        	}
	    		goods.setInfourl(allPath+"spinfo?sid="+s.getId()+"&pid="+goods.getId());
	    		str+="<td>";
	    		str+="<a href=\""+goods.getInfourl()+"\"  style=\"text-decoration:none;color:#000000;\">";
	    		str+="<img src=\""+goods.getImgurl()+"\" alt=\"\" style=\"width: 160px;\">";
	    		str+="<p>"+goods.getName()+"</p>";
	    		str+="<p class=\"tj-p\"><i>￥"+goods.getPrice()+"</i>元</p>";
	    		str+="</td>";
	    		if((j+1)%2==0){
	    			str+="</tr>";
	    			if((j+1)<glist.size()){
	    				str+="<tr>";
	    			}
	    		}
	    		j++;
	    	}
	    	str+="|";
	    	
	    	//首页新品上架
	    	List<ShopMyGoodsIndex> nlist=getMyGoodsByShopService.getMyGoodsLuckByShop(s.getId());
	    	int a=0;
	    	str+="<tr>";
	    	for(ShopMyGoodsIndex goods:nlist){
	    		if(goods.getImgurl()!=null&&!goods.getImgurl().equals("")){
	    			goods.setImgurl(allPath+goods.getImgurl());
	        	}else{
	        		goods.setImgurl("");
	        	}
	    		goods.setInfourl(allPath+"spinfo?sid="+s.getId()+"&pid="+goods.getId());
	    		str+="<td>";
	    		str+="<a href=\""+goods.getInfourl()+"\"  style=\"text-decoration:none;color:#000000;\">";
	    		str+="<img src=\""+goods.getImgurl()+"\" alt=\"\" style=\"width: 160px;\">";
	    		str+="<p>"+goods.getName()+"</p>";
	    		str+="<p class=\"tj-p\"><i>￥"+goods.getPrice()+"</i>元</p>";
	    		str+="</td>";
	    		if((a+1)%2==0){
	    			str+="</tr>";
	    			if((a+1)<nlist.size()){
	    				str+="<tr>";
	    			}
	    		}
	    		a++;
	    	}
	    	System.out.println(str);
			response.getWriter().write(str);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 收藏/取消收藏
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/favgoods")
	public void favgoods(HttpServletRequest request, HttpServletResponse response){
		try {
			UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
			Integer pid=this.getParaInteger("pid");
			Shop s=(Shop)request.getSession().getAttribute("shop");
			Map<String,Object> fmap=new HashMap<String,Object>();
			fmap.put("userid",user.getId());
			fmap.put("storeid",s.getId());
			fmap.put("productid",pid);
	    	Integer isFav=favGoodsService.getFavGoods(fmap);
	    	if(isFav>0){
	    		delFavGoodsService.delFavGoods(fmap);
				response.getWriter().write("0");
	    	}else{
	    		favGoodsService.favGoods(fmap);
				response.getWriter().write("1");
	    	}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 活动专区
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/activity")
	public String activity(HttpServletRequest request, HttpServletResponse response){
		//微信签名获取
		UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
		if(user == null){
			return "redirect:wxindex";
		}
		//首页数据处理
		System.out.println(user.getNickname()+"访问订单......");
		return "/wx/activity";
	}
	
	
	/**
	 * 订单信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/orderlist")
	public String orderlist(HttpServletRequest request, HttpServletResponse response){
		//微信签名获取
		UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
		if(user == null){
			return "redirect:wxindex";
		}
		List<Order> olist=new ArrayList<Order>();
		Integer ostatus=9;
		if(request.getParameter("ostatus")!=null&&!request.getParameter("ostatus").equals("")){
			ostatus=Integer.parseInt(request.getParameter("ostatus"));
		}

		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userid", user.getId());
		map.put("pageStart", 0);
		map.put("pageSize", 1000);
		if(ostatus==9){
			olist=shopService.getAllOrder(map);
		}else if(ostatus==0){
			olist=shopService.getWPayOrder(map);
		}else if(ostatus==1){
			olist=shopService.getWSendOrder(map);
		}else if(ostatus==2){
			olist=shopService.getWReceiveOrder(map);
		}else if(ostatus==3){
			olist=shopService.getOverOrder(map);
		}
		for(Order o:olist){
			List<OrderProductGet> plist=shopService.getProductListByOid(o.getId());
			o.setList(plist);
		}
		request.setAttribute("olist", olist);
		request.setAttribute("ostatus", ostatus);
		//首页数据处理
		System.out.println(user.getNickname()+"访问订单列表......");
		return "/wx/orderlist";
	}
	
	/**
	 * 个人中心
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/person")
	public String person(HttpServletRequest request, HttpServletResponse response){
		//微信签名获取
		UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
		if(user == null){
			return "redirect:wxindex";
		}
		//未支付订单数量
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userid", user.getId());
		map.put("pageStart", 0);
		map.put("pageSize", 1000);
		Integer wpaycount=shopService.getWPayOrderCount(map);
		Integer wsendcount=shopService.getWSendOrderCount(map);
		Integer wreceivecount=shopService.getWReceiveOrderCount(map);
		Integer overcount=shopService.getOverOrderCount(map);
		Integer cancelcount=shopService.getCancelOrderCount(map);
		//购物车商品数量
    	Integer cartnum=favGoodsService.getCartNum(user.getId());
    	request.setAttribute("user", user);
    	request.setAttribute("cartnum", cartnum);
    	request.setAttribute("wpaycount", wpaycount);
    	request.setAttribute("wsendcount", wsendcount);
    	request.setAttribute("wreceivecount", wreceivecount);
    	request.setAttribute("overcount", overcount);
    	request.setAttribute("cancelcount", cancelcount);
		//首页数据处理
		System.out.println(user.getNickname()+"访问个人中心......");
		return "/wx/person";
	}
	
	/**
	 * 商品分类列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/goodslist")
	public String goodslist(HttpServletRequest request, HttpServletResponse response){
		//微信签名获取
		UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
		if(user == null){
			return "redirect:wxindex";
		}
		//店铺获取
		Shop s=(Shop)request.getSession().getAttribute("shop");
		String searchkey=(String)request.getParameter("searchkey");
		request.setAttribute("searchkey",searchkey);
		List<ProductType> newptlist=new ArrayList<ProductType>();
		if(searchkey!=null&&!searchkey.equals("")){
			ProductType p=new ProductType();
	    	p.setId(0);
	    	p.setTypename("搜索结果");
	    	newptlist.add(0, p);
	    	//首页明星单品
	    	Map<String,Object> map=new HashMap<String,Object>();
	    	map.put("sid", s.getId());
	    	map.put("searchkey", searchkey);
	        List<ShopMyGoodsIndex> slist=getMyGoodsByShopService.getMyGoodsSearchByShop(map);
	    	for(ShopMyGoodsIndex goods:slist){
	    		//System.out.println(goods.getName()+"==="+goods.getIsrec());
	    		if(goods.getImgurl()!=null&&!goods.getImgurl().equals("")){
	    			goods.setImgurl(allPath+goods.getImgurl());
	        	}else{
	        		goods.setImgurl("");
	        	}
	    		goods.setInfourl(allPath+"spinfo?sid="+s.getId()+"&pid="+goods.getId());
	    	}
	    	request.setAttribute("slist", slist);
		}else{
			Map<String,String> map=new HashMap<String,String>();
	    	map.put("storeid", s.getId()+"");
	    	List<ProductType> ptlist=getGoodsFTypeService.getStoreProductType(map);
	    	
	    	ProductType p=new ProductType();
	    	p.setId(0);
	    	p.setTypename("热门推荐");
	    	newptlist.add(0, p);
	    	for(ProductType pt:ptlist){
	    		newptlist.add(pt);
	    	}
	    	
	    	//首页明星单品
	        List<ShopMyGoodsIndex> slist=getMyGoodsByShopService.getMyGoodsRecByShop(s.getId());
	    	for(ShopMyGoodsIndex goods:slist){
	    		//System.out.println(goods.getName()+"==="+goods.getIsrec());
	    		if(goods.getImgurl()!=null&&!goods.getImgurl().equals("")){
	    			goods.setImgurl(allPath+goods.getImgurl());
	        	}else{
	        		goods.setImgurl("");
	        	}
	    		goods.setInfourl(allPath+"spinfo?sid="+s.getId()+"&pid="+goods.getId());
	    	}
	    	request.setAttribute("slist", slist);
		}

    	request.setAttribute("ptlist", newptlist);
    	//购物车商品数量
    	Integer cartnum=favGoodsService.getCartNum(user.getId());
    	request.setAttribute("cartnum", cartnum);
		//首页数据处理
		System.out.println(user.getNickname()+"访问商品列表......");
		return "/wx/goodslist";
	}
	
	/**
	 * ajax商品分类列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/ajaxgoodslist")
	public void ajaxgoodslist(HttpServletRequest request, HttpServletResponse response){
		try{
			//店铺获取
			Shop s=(Shop)request.getSession().getAttribute("shop");
			Integer typeid=Integer.parseInt(request.getParameter("typeid"));
			String str="";
			if(typeid==0){
				List<ShopMyGoodsIndex> slist=getMyGoodsByShopService.getMyGoodsRecByShop(s.getId());
				int i=0;
				str+="<table class=\"r-taba\">";
				str+="<tr>";
		    	for(ShopMyGoodsIndex goods:slist){
		    		str+="<td>";
		    		if(goods.getImgurl()!=null&&!goods.getImgurl().equals("")){
		    			goods.setImgurl(allPath+goods.getImgurl());
		        	}else{
		        		goods.setImgurl("");
		        	}
		    		goods.setInfourl(allPath+"spinfo?sid="+s.getId()+"&pid="+goods.getId());
		    		str+="<a href=\""+goods.getInfourl()+"\"  style=\"text-decoration:none;color:#000000;\">";
		    		str+="<img src=\""+goods.getImgurl()+"\" alt=\"\">";
		    		str+="<p>"+goods.getName()+"</p>";
		    		str+="<p><span class=\" hong\"><i>￥</i>"+goods.getPrice()+"</span> <span>规格："+goods.getSpecifications()+"</span></p>";
		    		str+="</a>";
		    		str+="</td>";
		    		if((i+1)%2==0){
		    			str+="</tr>";
		    		}
		    		i++;
		    	}
		    	str+="</table>";
			}else{
				String allPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+request.getContextPath()+"/"; 
	
	        	Map<String,Object> map=new HashMap<String,Object>();
	        	map.put("storeid", s.getId());
	        	map.put("ftypeid", typeid);
	        	map.put("pageStart",0);
	        	map.put("pageSize",100);
	        	
	        	int i=0;
	        	str+="<table class=\"r-taba\">";
				str+="<tr>";
	        	List<Goods> glist=getGoodsByTypeService.getGoodsByType(map);
	        	for(Goods goods:glist){
	        		str+="<td>";
	        		if(goods.getImgurl()!=null&&!goods.getImgurl().equals("")){
	        			goods.setImgurl(allPath+goods.getImgurl());
	            	}else{
	            		goods.setImgurl("");
	            	}
	        		goods.setInfourl(allPath+"spinfo?sid="+s.getId()+"&pid="+goods.getId());
	        		str+="<a href=\""+goods.getInfourl()+"\"  style=\"text-decoration:none;color:#000000;\">";
	        		str+="<img src=\""+goods.getImgurl()+"\" alt=\"\">";
		    		str+="<p>"+goods.getName()+"</p>";
		    		str+="<p><span class=\" hong\"><i>￥</i>"+goods.getPrice()+"</span> <span>规格："+goods.getSpecifications()+"</span></p>";
		    		str+="</a>";
		    		str+="</td>";
		    		if((i+1)%2==0){
		    			str+="</tr>";
		    		}
		    		i++;
		    		
	        		goods.setType(goods.getIsfirst());
	        		map.put("pid",goods.getId());
	        		GoodsLimit gla=getGoodsByTypeService.getAdProduct(map);
	        		if(gla!=null&&gla.getAdnum()>0){
	        			goods.setIsad("0");
	        		}else{
	        			goods.setIsad("1");
	        		}
	        		GoodsLimit glt=getGoodsByTypeService.getTimelimit(map);
	        		if(glt!=null&&glt.getLimitnum()>0){
	        			goods.setIslimit("0");
	        		}else{
	        			goods.setIslimit("1");
	        		}
	        		
	        		StoreAdProduct ap = storeAdService.getStoreAdProductByProductId(goods.getId()+"");
	    			if(ap!=null){
	    				goods.setPrice(ap.getAdprice()+"");
	    			}
	    			
	    			List<String> tltimes=getGoodsByTypeService.getTimeLimitProduct(goods.getId());
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
	    	    			TimeLimit timel = storeAdService.getTimeLimitByProductId(goods.getId()+"");
	    					if(timel!=null){
	    						goods.setPrice(timel.getTlprice()+"");
	    					}
	    				}else if(tl.indexOf("2,")!=-1&&d.getTime()>stime2.getTime()&&d.getTime()<etime2.getTime()){
	    					//活动验证
	    	    			TimeLimit timel = storeAdService.getTimeLimitByProductId(goods.getId()+"");
	    					if(timel!=null){
	    						goods.setPrice(timel.getTlprice()+"");
	    					}
	    				}else if(tl.indexOf("3,")!=-1&&d.getTime()>stime3.getTime()&&d.getTime()<etime3.getTime()){
	    					//活动验证
	    	    			TimeLimit timel = storeAdService.getTimeLimitByProductId(goods.getId()+"");
	    					if(timel!=null){
	    						goods.setPrice(timel.getTlprice()+"");
	    					}
	    				}
	    			}
	        	}
	        	str+="</table>";
			}
	
			response.getWriter().write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 加入购物车
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addcart")
	public void addcart(HttpServletRequest request, HttpServletResponse response){
		try {
			//店铺获取
			UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
			Shop s=(Shop)request.getSession().getAttribute("shop");
			Integer pid=Integer.parseInt(request.getParameter("pid"));
			String price=this.getParaString("price");
			Integer num=1;
			ShoppingCart cart=new ShoppingCart();
	    	cart.setStoreid(s.getId());
	    	cart.setProductid(pid);
	    	cart.setUserid(user.getId());
	    	cart.setCount(num);
	    	cart.setPrice(price);
	    	cart.setCreatetime(DateUtil.getTodayTime());
	    	ShoppingCart hasCar=addCarService.getCar(cart);
	    	if(hasCar!=null){
	    		cart.setCount(num+hasCar.getCount());
	    		addCarService.updateCarNum(cart);
	    	}else{
	    		addCarService.addCar(cart);
	    	}
	    	
			response.getWriter().write("1");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 加入购物车并跳转页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addcartpage")
	public String addcartpage(HttpServletRequest request, HttpServletResponse response){
		try {
			//店铺获取
			UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
			Shop s=(Shop)request.getSession().getAttribute("shop");
			Integer pid=Integer.parseInt(request.getParameter("pid"));
			String price=this.getParaString("price");
			Integer num=1;
			ShoppingCart cart=new ShoppingCart();
	    	cart.setStoreid(s.getId());
	    	cart.setProductid(pid);
	    	cart.setUserid(user.getId());
	    	cart.setCount(num);
	    	cart.setPrice(price);
	    	cart.setCreatetime(DateUtil.getTodayTime());
	    	ShoppingCart hasCar=addCarService.getCar(cart);
	    	if(hasCar!=null){
	    		cart.setCount(num+hasCar.getCount());
	    		addCarService.updateCarNum(cart);
	    	}else{
	    		addCarService.addCar(cart);
	    	}
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:cartlist";
	}
	
	/**
	 * 删除购物车
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/delcart")
	public void delcart(HttpServletRequest request, HttpServletResponse response){
		try {
			UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
			Integer sid=Integer.parseInt(request.getParameter("sid"));
			Integer pid=Integer.parseInt(request.getParameter("pid"));
			Map<String,Object> map=new HashMap<String,Object>();
        	map.put("userid",user.getId());
        	map.put("storeid",sid);
        	map.put("productid",pid);
        	delCarService.delCar(map);
			response.getWriter().write("1");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改购物车数量
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/updatecart")
	public void updatecart(HttpServletRequest request, HttpServletResponse response){
		try {
			//店铺获取
			UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
			Integer sid=Integer.parseInt(request.getParameter("sid"));
			Integer pid=Integer.parseInt(request.getParameter("pid"));
			Integer num=Integer.parseInt(request.getParameter("num"));
			ShoppingCart cart=new ShoppingCart();
        	cart.setStoreid(sid);
        	cart.setProductid(pid);
        	cart.setUserid(user.getId());
        	cart.setCount(num);
        	updateCarService.updateCar(cart);
			response.getWriter().write("1");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 购物车
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/cartlist")
	public String cartlist(HttpServletRequest request, HttpServletResponse response){
		UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
		if(user == null){
			return "redirect:wxindex";
		}
		Shop s=(Shop)request.getSession().getAttribute("shop");
		String allPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+request.getContextPath()+"/"; 
       
    	/**限时抢购时间段处理***********************************/
    	String fstime1=ConfigUtil.getConfig("fstime1").getConfig_value();
		String fetime1=ConfigUtil.getConfig("fetime1").getConfig_value();
		String fstime2=ConfigUtil.getConfig("fstime2").getConfig_value();
		String fetime2=ConfigUtil.getConfig("fetime2").getConfig_value();
		String fstime3=ConfigUtil.getConfig("fstime3").getConfig_value();
		String fetime3=ConfigUtil.getConfig("fetime3").getConfig_value();
		
		//时间处理
		SimpleDateFormat datesdf=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat datetimesdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d=new Date();//当前时间
		String fstime="";
		String fetime="";
		String tltime="";
		try{
			if(fstime1!=null&&!fstime1.equals("")&&fetime1!=null&&!fetime1.equals("")){
				//抢购1开始时间
				Date stime=datetimesdf.parse(datesdf.format(d)+" "+fstime1+":00");
				//抢购1结束时间
				Date etime=datetimesdf.parse(datesdf.format(d)+" "+fetime1+":00");
				//System.out.println(d.toLocaleString()+"=========="+stime.toLocaleString()+"=========="+etime.toLocaleString());
				//如果当前时间小于抢购1结束时间  显示抢购1的产品列表
				if(d.getTime()<etime.getTime()){
					tltime="1,";
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
				//System.out.println(d.toLocaleString()+"=========="+stime.toLocaleString()+"=========="+etime.toLocaleString());
				//如果当前时间小于抢购1结束时间  显示抢购1的产品列表
				if(d.getTime()<etime.getTime()&&d.getTime()>stime.getTime()){
					tltime="2,";
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
				//System.out.println(d.toLocaleString()+"=========="+stime.toLocaleString()+"=========="+etime.toLocaleString());
				//如果当前时间小于抢购1结束时间  显示抢购1的产品列表
				if(d.getTime()<etime.getTime()&&d.getTime()>stime.getTime()){
					tltime="3,";
				}
				fstime3=datetimesdf.format(stime);
				fetime3=datetimesdf.format(etime);
				fstime=fstime3;
				fetime=fetime3;
			}
		
		
	    	Map<String,Object> map=new HashMap<String,Object>();
	    	map.put("userid",user.getId());
	    	map.put("pageStart",0);
	    	map.put("pageSize",100);
	    	List<CarGoods> list=getCarGoodsService.getCarGoods(map);
	    	for(CarGoods goods:list){
	    		if(goods.getImgurl()!=null&&!goods.getImgurl().equals("")){
	    			goods.setImgurl(allPath+goods.getImgurl());
	        	}else{
	        		goods.setImgurl("");
	        	}
	    		
	    		Map<String,Object> m=new HashMap<String,Object>();
	        	m.put("uid",user.getId());
	        	m.put("pid",goods.getId());
	        	m.put("sid",goods.getSid());
				m.put("stime",fstime);
				m.put("etime",fetime);
				GoodsLimit gla=getShopGoodsBuyNumService.getBuyNum(m);
	        	GoodsLimit glt=getShopGoodsBuyNumService.getLimitNum(m);
	        	if(gla!=null){
	        		goods.setBuynum(gla.getBuynum()+"");
	        	}else{
	        		goods.setBuynum("0");
	        	}
	        	if(glt!=null){
	        		if(glt.getLimitnum()!=null&&glt.getLimitnum()>0){
	        			goods.setLimitnum(glt.getLimitnum()+"");
	        			goods.setIslimit("0");
	        		}else{
	        			goods.setLimitnum("0");
	        			goods.setIslimit("1");
	        		}
	        	}else{
	        		goods.setLimitnum("0");
	    			goods.setIslimit("1");
	        	}
	        	
	        	//获取原价
	        	Map<String,Object> mp=new HashMap<String,Object>();
				mp.put("sid",goods.getSid());
				mp.put("pid",goods.getId());
				String price=storeAdService.getGoodsSalePrice(mp);
	        	
	        	List<String> tltimes=storeAdService.getTimeLimitProduct(goods.getId());
				if(tltimes!=null&&tltimes.size()>0){
					String tl=tltimes.get(0);
	    			Date stime1=datetimesdf.parse(fstime1);
					Date etime1=datetimesdf.parse(fetime1);
	    			Date stime2=datetimesdf.parse(fstime2);
					Date etime2=datetimesdf.parse(fetime2);
	    			Date stime3=datetimesdf.parse(fstime3);
					Date etime3=datetimesdf.parse(fetime3);
					
					
					//活动验证
					TimeLimit timel = storeAdService.getTimeLimitByProductId(goods.getId()+"");
					if(tl.indexOf("1,")!=-1&&d.getTime()>stime1.getTime()&&d.getTime()<etime1.getTime()){
						if(timel==null){
			    			goods.setPrice(price);
						}
					}else if(tl.indexOf("2,")!=-1&&d.getTime()>stime2.getTime()&&d.getTime()<etime2.getTime()){
						if(timel==null){
							goods.setPrice(price);
						}
					}else if(tl.indexOf("3,")!=-1&&d.getTime()>stime3.getTime()&&d.getTime()<etime3.getTime()){
						if(timel==null){
							goods.setPrice(price);
						}
					}else{
						goods.setPrice(price);
					}
					
				}else{
					//活动验证
	    			StoreAdProduct ap = storeAdService.getStoreAdProductByProductId(goods.getId()+"");
	    			//System.out.println(ap+"===================");
	    			if(ap==null){
	    				//System.out.println(price+"==========================");
	    				goods.setPrice(price);
	    			}
				}
	    	}
	    	request.setAttribute("cartlist", list);
		}catch(Exception e){
			e.printStackTrace();
		}
		//购物车商品数量
    	Integer cartnum=favGoodsService.getCartNum(user.getId());
    	request.setAttribute("cartnum", cartnum);
		//首页数据处理
		System.out.println(user.getNickname()+"访问购物车......");

		return "/wx/cartlist";
	}
	
	/**
	 * 订单确认
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/makeorder")
	public String makeorder(HttpServletRequest request, HttpServletResponse response){
		//微信签名获取
		UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
		if(user == null){
			return "redirect:wxindex";
		}
		String pinfo=request.getParameter("pinfo");
		String[] ps=pinfo.split("_");
		List<ProductInfo> opilist=new ArrayList<ProductInfo>();
		Integer allweight=0;//商品总重量
		Integer startweight=Integer.parseInt(ConfigUtil.getConfig("startweight").getConfig_value());
		Integer startcost=Integer.parseInt(ConfigUtil.getConfig("startcost").getConfig_value());
		Integer addcost=Integer.parseInt(ConfigUtil.getConfig("startcost").getConfig_value());
		Integer zeropost=Integer.parseInt(ConfigUtil.getConfig("zeropost").getConfig_value());
		Double oprice=0.0;
		for(String str:ps){
			String[] info=str.split(";");
			Integer pid=Integer.parseInt(info[0]);
			String count=info[1];
			String price=info[2];
			ProductInfo pi=shopService.getGoosById(pid);
			pi.setId(pid);
			pi.setOrdernum(Integer.parseInt(count));
			pi.setPrice(Double.parseDouble(price));
			String allPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+request.getContextPath()+"/"; 
			pi.setImgurl(allPath+pi.getImgurl());
			opilist.add(pi);
			allweight+=pi.getWeight();
			oprice+=pi.getPrice();
		}
		Integer post=0;//运费
		if(oprice>=zeropost){
			//满足包邮条件运费为0
			post=0;
		}else{
			if(allweight<=startweight){
				post=startcost;
			}else{
				Integer n=(allweight-startweight)/1000+1;
				post=startcost+addcost*n;
			}
		}
		
		Shop s=(Shop)request.getSession().getAttribute("shop");
		Integer aid=0;
		if(request.getParameter("aid")!=null&&!request.getParameter("aid").equals("")){
			try{
				aid=Integer.parseInt(request.getParameter("aid").trim());
			}catch(NumberFormatException e){
				e.printStackTrace();
			}
		}
		Address addr=null;
		//System.out.println(aid+"================");
		if(aid!=0){
			addr=getAddressService.getAddressNameByAid(aid);
		}else{
			addr=getAddressService.getDefaultAddress(user.getId());
		}
		//System.out.println(addr+"================");
		//订单确认处理
		System.out.println(user.getNickname()+"访问订单确认......");
		request.setAttribute("shop", s);
		request.setAttribute("addr",addr);
		request.setAttribute("pinfo",pinfo);
		request.setAttribute("opilist", opilist);
		request.setAttribute("post",post);
		return "/wx/makeorder";
	}
	
	/**
	 * 添加地址
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addaddr")
	public String addaddr(HttpServletRequest request, HttpServletResponse response){
		//微信签名获取
		UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
		if(user == null){
			return "redirect:wxindex";
		}
		String pinfo=request.getParameter("pinfo");
		List<Province> plist=getProvinceService.getAllProvince();
		List<City> clist=getCityService.getCityByProvince(plist.get(0).getId());
		List<District> colist=getDistrictService.getDistrictByCity(clist.get(0).getId());
		request.setAttribute("plist", plist);
		request.setAttribute("clist", clist);
		request.setAttribute("colist", colist);
		request.setAttribute("pinfo", pinfo);
		//首页数据处理
		System.out.println(user.getNickname()+"访问添加地址......");
		return "/wx/addaddr";
	}
	
	/**
	 * 编辑地址
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/editaddr")
	public String editaddr(HttpServletRequest request, HttpServletResponse response){
		//微信签名获取
		UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
		if(user == null){
			return "redirect:wxindex";
		}
		String addrid=request.getParameter("aid");
		Address addr=shopService.getAddressById(Integer.parseInt(addrid));
		List<Province> plist=getProvinceService.getAllProvince();
		List<City> clist=getCityService.getCityByProvince(plist.get(0).getId());
		List<District> colist=getDistrictService.getDistrictByCity(clist.get(0).getId());
		request.setAttribute("plist", plist);
		request.setAttribute("clist", clist);
		request.setAttribute("colist", colist);
		request.setAttribute("addr", addr);
		//首页数据处理
		System.out.println(user.getNickname()+"访问编辑地址......");
		return "/wx/editaddr";
	}
	
	/**
	 * 修改地址
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/updateaddr")
	public String updateaddr(HttpServletRequest request, HttpServletResponse response){
		//微信签名获取
		UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
		if(user == null){
			return "redirect:wxindex";
		}
		String name=request.getParameter("cname");
		String tel=request.getParameter("ctel");
		String cpcc=request.getParameter("provname");
		String caddr=request.getParameter("caddr");
		String aid=request.getParameter("aid");
		String[] cs=cpcc.split(",");
		Address addr=new Address();
    	addr.setUserid(user.getId());
    	addr.setAddress(caddr);
    	addr.setCity(cs[1]);
    	addr.setContact(name);
    	addr.setCounty(cs[2]);
    	addr.setMobile(tel);
    	addr.setProvince(cs[0]);
    	addr.setId(Integer.parseInt(aid));
    	updateAddressService.updateUserAddress(addr);
		//首页数据处理
		System.out.println(user.getNickname()+"访问修改地址提交......");
		return "redirect:addrlist";
	}
	
	/**
	 * 删除地址
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/deladdr")
	public String deladdr(HttpServletRequest request, HttpServletResponse response){
		//微信签名获取
		UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
		if(user == null){
			return "redirect:wxindex";
		}
		String aid=request.getParameter("aid");
		delAddressService.delUserAddr(Integer.parseInt(aid));
		Integer dnum=shopService.getDefaultNum(user.getId());
		if(dnum==0){
			Address a=getAddressService.getDefaultAddress(user.getId());
			shopService.setDefault(a.getId());
		}
		//首页数据处理
		System.out.println(user.getNickname()+"访问删除地址......");
		return "redirect:addrlist";
	}
	
	/**
	 * 设置默认地址
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/defaultaddr")
	public String defaultaddr(HttpServletRequest request, HttpServletResponse response){
		//微信签名获取
		UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
		if(user == null){
			return "redirect:wxindex";
		}
		String aid=request.getParameter("aid");
		shopService.cleanDefault(user.getId());
		shopService.setDefault(Integer.parseInt(aid));
		//首页数据处理
		System.out.println(user.getNickname()+"访问删除地址......");
		return "redirect:addrlist";
	}
	
	/**
	 * 地址管理添加地址
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addaddrm")
	public String addaddrm(HttpServletRequest request, HttpServletResponse response){
		//微信签名获取
		UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
		if(user == null){
			return "redirect:wxindex";
		}
		
		List<Province> plist=getProvinceService.getAllProvince();
		List<City> clist=getCityService.getCityByProvince(plist.get(0).getId());
		List<District> colist=getDistrictService.getDistrictByCity(clist.get(0).getId());
		request.setAttribute("plist", plist);
		request.setAttribute("clist", clist);
		request.setAttribute("colist", colist);
		//首页数据处理
		System.out.println(user.getNickname()+"访问管理添加地址......");
		return "/wx/addaddrm";
	}
	
	/**
	 * 添加地址
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/saveaddrm")
	public String saveaddrm(HttpServletRequest request, HttpServletResponse response){
		//微信签名获取
		UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
		if(user == null){
			return "redirect:wxindex";
		}
		String name=request.getParameter("cname");
		String tel=request.getParameter("ctel");
		String cpcc=request.getParameter("provname");
		String caddr=request.getParameter("caddr");
		String[] cs=cpcc.split(",");
		Address addr=new Address();
    	addr.setUserid(user.getId());
    	addr.setAddress(caddr);
    	addr.setCity(cs[1]);
    	addr.setContact(name);
    	addr.setCounty(cs[2]);
    	addr.setMobile(tel);
    	addr.setProvince(cs[0]);
    	addr.setCreatetime(DateUtil.getTodayTime());
    	addAddressService.saveUserAddress(addr);
		//首页数据处理
		System.out.println(user.getNickname()+"访问添加地址......");
		return "redirect:addrlist";
	}
	
	/**
	 * 意见反馈
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/feedback")
	public String feedback(HttpServletRequest request, HttpServletResponse response){
		//微信签名获取
		UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
		if(user == null){
			return "redirect:wxindex";
		}
		//首页数据处理
		request.setAttribute("isres",0);
		System.out.println(user.getNickname()+"访问意见反馈......");
		return "/wx/feedback";
	}
	
	/**
	 * 意见反馈保存
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/savefeedback")
	public String savefeedback(HttpServletRequest request, HttpServletResponse response){
		//微信签名获取
		UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
		if(user == null){
			return "redirect:wxindex";
		}
		Integer isres=0;
		String content=(String)request.getParameter("content");
		System.out.println(content+"------");
		Map<String,Object> map=new HashMap<String,Object>();
    	map.put("id",0);
    	map.put("userid",user.getId());
    	map.put("content",content);
    	map.put("createtime",DateUtil.getTodayTime());
    	feedBackService.saveFeedBack(map);
    	if((Integer)(map.get("id"))>0){
    		isres=1;
    	}
    	request.setAttribute("isres",isres);
		//首页数据处理
		System.out.println(user.getNickname()+"保存意见反馈......");
		return "/wx/feedback";
	}
	
	/**
	 * 订单详情
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/orderdetail")
	public String orderdetail(HttpServletRequest request, HttpServletResponse response){
		//微信签名获取
		UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
		if(user == null){
			return "redirect:wxindex";
		}
		Integer oid=Integer.parseInt(request.getParameter("oid"));
		Order o=shopService.getOrderById(oid);
		List<OrderProductGet> plist=shopService.getProductListByOid(o.getId());
		o.setList(plist);
		request.setAttribute("order", o);
		//首页数据处理
		System.out.println(user.getNickname()+"访问订单详情......");
		return "/wx/orderdetail";
	}
	
	/**
	 * 取消订单
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/cancelorder")
	public String cancelorder(HttpServletRequest request, HttpServletResponse response){
		//微信签名获取
		UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
		if(user == null){
			return "redirect:wxindex";
		}
		String ostatus=request.getParameter("ostatus");
		String oid=request.getParameter("oid");
		Map<String,Object> map=new HashMap<String,Object>();
    	map.put("id",oid);
    	map.put("status",4);
    	map.put("reason","");
		updateOrderService.updateOrderStatus(map);
		//首页数据处理
		System.out.println(user.getNickname()+"访问取消订单......");
		return "redirect:orderlist?ostatus="+ostatus;
	}
	
	/**
	 * 申请退款
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/returnorder")
	public String returnorder(HttpServletRequest request, HttpServletResponse response){
		//微信签名获取
		UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
		if(user == null){
			return "redirect:wxindex";
		}
		String oid=request.getParameter("oid");
		String ostatus=request.getParameter("ostatus");
		String reason=request.getParameter("reason");
		Map<String,Object> map=new HashMap<String,Object>();
    	map.put("id",oid);
    	map.put("status",5);
    	map.put("reason",reason);
		updateOrderService.updateOrderStatus(map);
		//首页数据处理
		System.out.println(user.getNickname()+"访问申请退款......");
		return "redirect:orderlist?ostatus="+ostatus;
	}
	
	/**
	 * 申请退款页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toreturn")
	public String toreturn(HttpServletRequest request, HttpServletResponse response){
		//微信签名获取
		UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
		if(user == null){
			return "redirect:wxindex";
		}
		String oid=request.getParameter("oid");
		String ostatus=request.getParameter("ostatus");
		//首页数据处理
		request.setAttribute("oid",oid);
		request.setAttribute("ostatus",ostatus);
		System.out.println(user.getNickname()+"访问退款原因......");
		return "/wx/toreturn";
	}
	
	/**
	 * 确认收货
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/okorder")
	public String okorder(HttpServletRequest request, HttpServletResponse response){
		//微信签名获取
		UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
		if(user == null){
			return "redirect:wxindex";
		}
		String oid=request.getParameter("oid");
		String ostatus=request.getParameter("ostatus");
		Map<String,Object> map=new HashMap<String,Object>();
    	map.put("id",oid);
    	map.put("status",3);
    	map.put("reason","");
		updateOrderService.updateOrderStatus(map);
		//首页数据处理
		System.out.println(user.getNickname()+"访问确认收货......");
		return "redirect:orderlist?ostatus="+ostatus;
	}
	
	/**
	 * 添加地址
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/saveaddr")
	public String saveaddr(HttpServletRequest request, HttpServletResponse response){
		//微信签名获取
		UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
		if(user == null){
			return "redirect:wxindex";
		}
		String pinfo=request.getParameter("pinfo");
		String name=request.getParameter("cname");
		String tel=request.getParameter("ctel");
		String cpcc=request.getParameter("provname");
		String caddr=request.getParameter("caddr");
		String[] cs=cpcc.split(",");
		Address addr=new Address();
    	addr.setUserid(user.getId());
    	addr.setAddress(caddr);
    	addr.setCity(cs[1]);
    	addr.setContact(name);
    	addr.setCounty(cs[2]);
    	addr.setMobile(tel);
    	addr.setProvince(cs[0]);
    	addr.setCreatetime(DateUtil.getTodayTime());
    	addAddressService.saveUserAddress(addr);
		//首页数据处理
		System.out.println(user.getNickname()+"访问添加地址......");
		return "redirect:makeorder?pinfo="+pinfo+"&aid="+addr.getId();
	}
	
	/**
	 * ajaxgetcity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/ajaxgetcity")
	public void ajaxgetcity(HttpServletRequest request, HttpServletResponse response){
		try {
			String provcode=request.getParameter("provcode");
			String str="[";
			List<City> clist=getCityService.getCityByProvince(Integer.parseInt(provcode));
			for(int i=0;i<clist.size();i++){
				if(i==0){
					str+="{text:'"+clist.get(i).getName()+"',value:'"+clist.get(i).getId()+"'}";
				}else{
					str+=",{text:'"+clist.get(i).getName()+"',value:'"+clist.get(i).getId()+"'}";
				}
			}
			str+="]";
			if(str.equals("[]")){
				str="[{text:'',value:'0'}]";
			}
			response.getWriter().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ajaxgetcounty
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/ajaxgetcounty")
	public void ajaxgetcounty(HttpServletRequest request, HttpServletResponse response){
		try {
			String citycode=request.getParameter("citycode");
			String str="[";
			List<District> clist=getDistrictService.getDistrictByCity(Integer.parseInt(citycode));
			for(int i=0;i<clist.size();i++){
				if(i==0){
					str+="{text:'"+clist.get(i).getName()+"',value:'"+clist.get(i).getId()+"'}";
				}else{
					str+=",{text:'"+clist.get(i).getName()+"',value:'"+clist.get(i).getId()+"'}";
				}
			}
			str+="]";
			if(str.equals("[]")){
				str="[{text:'',value:'0'}]";
			}
			response.getWriter().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 地址列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/selectaddr")
	public String addrlist(HttpServletRequest request, HttpServletResponse response){
		//微信签名获取
		UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
		if(user == null){
			return "redirect:wxindex";
		}
		String pinfo=request.getParameter("pinfo");
		String aid=request.getParameter("aid");
		List<Address> alist=getAllAddressService.getAllAddressName(user.getId());
		request.setAttribute("alist",alist);
		request.setAttribute("pinfo",pinfo);
		request.setAttribute("aid",aid);
		//首页数据处理
		System.out.println(user.getNickname()+"访问地址列表......");
		return "/wx/selectaddr";
	}
	
	/**
	 * 地址管理
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addrlist")
	public String addrlistmanage(HttpServletRequest request, HttpServletResponse response){
		//微信签名获取
		UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
		if(user == null){
			return "redirect:wxindex";
		}
		String pinfo=request.getParameter("pinfo");
		List<Address> alist=getAllAddressService.getAllAddressName(user.getId());
		request.setAttribute("alist",alist);
		request.setAttribute("pinfo",pinfo);
		//首页数据处理
		System.out.println(user.getNickname()+"访问地址列表......");
		return "/wx/addrlist";
	}
	
	/**
	 * 收藏列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/favlist")
	public String favlist(HttpServletRequest request, HttpServletResponse response){
		//微信签名获取
		UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
		if(user == null){
			return "redirect:wxindex";
		}
		Shop s=(Shop)request.getSession().getAttribute("shop");
		Map<String,Object> map=new HashMap<String,Object>();
    	map.put("userid",user.getId());
    	map.put("pageStart", 0);
    	map.put("pageSize", 999);
    	List<FavGoods> list=getFavGoodsService.getFavGoods(map);
    	String allPath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+request.getContextPath()+"/"; 
    	for(FavGoods goods:list){
    		if(goods.getImgurl()!=null&&!goods.getImgurl().equals("")){
    			goods.setImgurl(allPath+goods.getImgurl());
        	}else{
        		goods.setImgurl("");
        	}
    	}
    	Integer recordNum=getFavGoodsService.getFavGoodsCount(map);
    	request.setAttribute("flist",list);
    	request.setAttribute("recordNum", recordNum);
    	request.setAttribute("shop",s);
		//首页数据处理
		System.out.println(user.getNickname()+"访问收藏列表......");
		return "/wx/favlist";
	}
	
	/**
	 * 创建订单
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/submitorder")
	public String submitorder(HttpServletRequest request, HttpServletResponse response){
		String res="/wx/ordersuccess";
		try{
			UserInfo user = (UserInfo)request.getSession().getAttribute("wxloginUser");
			Shop s=(Shop)request.getSession().getAttribute("shop");
        	String orderno=CommonUtil.getDateFormatMillisecond().format(new Date());
        	String payno="";//支付流水号
        	Integer status=0;//订单状态0-未支付1-已支付(待确认)/新订单2-待收货/派送中3-已完成4-已取消（未付款）5-已取消（已付款，退款）
        	Integer userid=user.getId();
        	Integer storeid=s.getId();
        	String orderprice=request.getParameter("totalprice");//订单金额
        	String sendprice=request.getParameter("sendprice");//配送费
        	String remark="";//订单备注
        	Integer addrid=Integer.parseInt(request.getParameter("addrid"));
        	String createtime=DateUtil.getTodayTime();
        	String sendtime=DateUtil.getTodayTime();
        	String reason="";
        	String goodscode=CommonUtil.getRandom8()+"";
        	Map<String,Object> map=new HashMap<String,Object>();
        	map.put("id",0);
        	map.put("orderno",orderno);
        	map.put("payno",payno);
        	map.put("status",status);
        	map.put("userid",userid);
        	map.put("storeid",storeid);
        	map.put("orderprice",orderprice);
        	map.put("sendprice",sendprice);
        	map.put("remark",remark);
        	map.put("addrid", addrid);
        	map.put("sendtime",sendtime);
        	map.put("goodscode",goodscode);
        	map.put("createtime",createtime);
        	map.put("reason",reason);
        	Address address=getAddressService.getAddressNameByAid(addrid);
        	map.put("province",address.getProvince());
        	map.put("city",address.getCity());
        	map.put("county",address.getCounty());
        	map.put("address",address.getAddress());
        	map.put("acceptname",address.getContact());
        	map.put("accepttel",address.getMobile());
        	
        	Integer hasad=0;
        	String pinfo=request.getParameter("pinfo");
        	System.out.println(pinfo);
        	String[] ps=pinfo.split("_");
        	List<OrderProductInfo> list=new ArrayList<OrderProductInfo>();
        	for(String str:ps){
        		String[] gs=str.split(",");
        		OrderProductInfo opi=new OrderProductInfo();
        		opi.setId(Integer.parseInt(gs[0]));
        		opi.setCount(gs[1]);
        		opi.setPrice(gs[2]);
        		list.add(opi);
        	}
        	
        	//验证商品活动信息
    		for(OrderProductInfo product:list){
	        	//活动验证
				StoreAdProduct ap = storeAdService.getStoreAdProductByProductId(product.getId()+"");
				if(ap!=null){
					hasad=1;
				}
    		}
        	map.put("hasad", hasad);
        	submitOrderService.saveOrder(map);

        	Integer id=(Integer)map.get("id");
        	if(id>0){
        		for(OrderProductInfo product:list){
        			Map<String,Object> omap=new HashMap<String,Object>();
        			omap.put("orderid", id);
        			omap.put("productid",product.getId());
        			omap.put("ordernum",product.getCount());
        			omap.put("opprice", product.getPrice());
        			submitOrderService.saveOrderProduct(omap);
        			
        			Map<String,Object> smap=new HashMap<String,Object>();
        			smap.put("sid", storeid);
        			smap.put("pid",product.getId());
        			//更新店铺库存
        			Integer pnum=submitOrderService.getProductNum(smap);
        			if(pnum>0){
        				submitOrderService.updateProductNum(smap);
        			}
        			//更新抢购数量
        			Integer tnum=submitOrderService.getTLimitNum(smap);
        			if(tnum!=null&&tnum>0){
        				submitOrderService.updateTLimitNum(map);
        			}
        		}
        		//创建订单成功    跳转到支付页面
        		res="redirect:getPayOrder?orderno="+orderno+"&total_fee="+orderprice;
        	}else{ 
        		//创建订单失败    跳转到失败页面
        		res="/wx/ordererror";
        	}
        }catch(Exception e){ 
            e.printStackTrace();
            res="/wx/ordererror";
        }
		return res;     
	}
}

package com.limon.http.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.limon.base.controller.BaseController;
import com.limon.http.bussiness.AddAddressHandler;
import com.limon.http.bussiness.AddCarHandler;
import com.limon.http.bussiness.AppUpdateCheckHandler;
import com.limon.http.bussiness.DelAddressHandler;
import com.limon.http.bussiness.DelCarAllHandler;
import com.limon.http.bussiness.DelCarHandler;
import com.limon.http.bussiness.DelFavGoodsHandler;
import com.limon.http.bussiness.DelOrderHandler;
import com.limon.http.bussiness.FavGoodsHandler;
import com.limon.http.bussiness.FeedBackHandler;
import com.limon.http.bussiness.GetAddressHandler;
import com.limon.http.bussiness.GetAllAddressHandler;
import com.limon.http.bussiness.GetAllGoodsByShopHandler;
import com.limon.http.bussiness.GetCarGoodsHandler;
import com.limon.http.bussiness.GetCityHandler;
import com.limon.http.bussiness.GetDistrictHandler;
import com.limon.http.bussiness.GetFavGoodsHandler;
import com.limon.http.bussiness.GetGoodsByTypeHandler;
import com.limon.http.bussiness.GetGoodsFTypeHandler;
import com.limon.http.bussiness.GetGoodsInfoHandler;
import com.limon.http.bussiness.GetGoodsTypeHandler;
import com.limon.http.bussiness.GetHistoryOrderHandler;
import com.limon.http.bussiness.GetHpInfoHandler;
import com.limon.http.bussiness.GetMessageHandler;
import com.limon.http.bussiness.GetMyGoodsByShopHandler;
import com.limon.http.bussiness.GetNearShopHandler;
import com.limon.http.bussiness.GetPerInfoHandler;
import com.limon.http.bussiness.GetProvinceHandler;
import com.limon.http.bussiness.GetSendedOrderHandler;
import com.limon.http.bussiness.GetSendingOrderHandler;
import com.limon.http.bussiness.GetShopAdHandler;
import com.limon.http.bussiness.GetShopAddressHandler;
import com.limon.http.bussiness.GetShopGoodsBuyNumHandler;
import com.limon.http.bussiness.GetShopGoodsInfoHandler;
import com.limon.http.bussiness.GetShopHandler;
import com.limon.http.bussiness.GetShopInfoHandler;
import com.limon.http.bussiness.GetTotalOrderHandler;
import com.limon.http.bussiness.GetUserOrderNumHandler;
import com.limon.http.bussiness.GetWPayOrderHandler;
import com.limon.http.bussiness.GetWReceiveOrderHandler;
import com.limon.http.bussiness.GetWSendOrderHandler;
import com.limon.http.bussiness.LoginHandler;
import com.limon.http.bussiness.NmSelfShopHandler;
import com.limon.http.bussiness.PayOrderHandler;
import com.limon.http.bussiness.RegisterHandler;
import com.limon.http.bussiness.RetrievePwdHandler;
import com.limon.http.bussiness.SearchGoodsHandler;
import com.limon.http.bussiness.SearchShopHandler;
import com.limon.http.bussiness.SendCheckCodeHandler;
import com.limon.http.bussiness.SetHeadImgHandler;
import com.limon.http.bussiness.SetPerInfoHandler;
import com.limon.http.bussiness.SetShopAddressHandler;
import com.limon.http.bussiness.ShopGoodsDownHandler;
import com.limon.http.bussiness.ShopGoodsUpHandler;
import com.limon.http.bussiness.ShopGoodsUpdateHandler;
import com.limon.http.bussiness.ShopLoginHandler;
import com.limon.http.bussiness.ShopUpdateOrderHandler;
import com.limon.http.bussiness.ShopUpdatePwdHandler;
import com.limon.http.bussiness.SubmitOrderHandler;
import com.limon.http.bussiness.UpdateAddressHandler;
import com.limon.http.bussiness.UpdateCarHandler;
import com.limon.http.bussiness.UpdateOrderHandler;
import com.limon.http.bussiness.UpdatePwdHandler;
import com.limon.http.coder.*;
import com.limon.http.service.AddAddressService;
import com.limon.http.service.AddCarService;
import com.limon.http.service.AppUpdateCheckService;
import com.limon.http.service.DelAddressService;
import com.limon.http.service.DelCarAllService;
import com.limon.http.service.DelCarService;
import com.limon.http.service.DelFavGoodsService;
import com.limon.http.service.DelOrderService;
import com.limon.http.service.FavGoodsService;
import com.limon.http.service.FeedBackService;
import com.limon.http.service.GetAddressService;
import com.limon.http.service.GetAllAddressService;
import com.limon.http.service.GetAllGoodsByShopService;
import com.limon.http.service.GetCarGoodsService;
import com.limon.http.service.GetCityService;
import com.limon.http.service.GetDistrictService;
import com.limon.http.service.GetFavGoodsService;
import com.limon.http.service.GetGoodsByTypeService;
import com.limon.http.service.GetGoodsFTypeService;
import com.limon.http.service.GetGoodsInfoService;
import com.limon.http.service.GetGoodsTypeService;
import com.limon.http.service.GetHistoryOrderService;
import com.limon.http.service.GetHpInfoService;
import com.limon.http.service.GetMessageService;
import com.limon.http.service.GetMyGoodsByShopService;
import com.limon.http.service.GetNearShopService;
import com.limon.http.service.GetPerInfoService;
import com.limon.http.service.GetProvinceService;
import com.limon.http.service.GetSendedOrderService;
import com.limon.http.service.GetSendingOrderService;
import com.limon.http.service.GetShopAdService;
import com.limon.http.service.GetShopAddressService;
import com.limon.http.service.GetShopGoodsBuyNumService;
import com.limon.http.service.GetShopGoodsInfoService;
import com.limon.http.service.GetShopInfoService;
import com.limon.http.service.GetShopService;
import com.limon.http.service.GetTotalOrderService;
import com.limon.http.service.GetUserOrderNumService;
import com.limon.http.service.GetWPayOrderService;
import com.limon.http.service.GetWReceiveOrderService;
import com.limon.http.service.GetWSendOrderService;
import com.limon.http.service.LoginService;
import com.limon.http.service.NmSelfShopService;
import com.limon.http.service.PayOrderService;
import com.limon.http.service.RegisterService;
import com.limon.http.service.RetrievePwdService;
import com.limon.http.service.SearchGoodsService;
import com.limon.http.service.SearchShopService;
import com.limon.http.service.SetHeadImgService;
import com.limon.http.service.SetPerInfoService;
import com.limon.http.service.SetShopAddressService;
import com.limon.http.service.ShopGoodsDownService;
import com.limon.http.service.ShopGoodsUpService;
import com.limon.http.service.ShopGoodsUpdateService;
import com.limon.http.service.ShopLoginService;
import com.limon.http.service.ShopUpdateOrderService;
import com.limon.http.service.ShopUpdatePwdService;
import com.limon.http.service.SubmitOrderService;
import com.limon.http.service.UpdateAddressService;
import com.limon.http.service.UpdateCarService;
import com.limon.http.service.UpdateOrderService;
import com.limon.http.service.UpdatePwdService;
import com.limon.http.util.HttpDateUtil;
import com.limon.http.xml.XmlCoder;
import com.limon.store.service.StoreAdService;
import com.limon.util.Des3;

/**
 * @author gqf
 * 
 *         http接口 2015-2-27 上午10:52:19
 */
@Controller
public class HttpReqController extends BaseController {
	@Autowired
	private LoginService loginService;
	@Autowired
	private RegisterService registerService;
	@Autowired
	private AppUpdateCheckService appUpdateCheckService;
	@Autowired
	private UpdatePwdService updatePwdService;
	@Autowired
	private AddAddressService addAddressService;
	@Autowired
	private UpdateAddressService updateAddressService;
	@Autowired
	private DelAddressService delAddressService;
	@Autowired
	private GetAllAddressService getAllAddressService;
	@Autowired
	private GetAddressService getAddressService;
	@Autowired
	private GetNearShopService getNearShopService;
	@Autowired
	private SearchShopService searchShopService;
	@Autowired
	private GetGoodsTypeService getGoodsTypeService;
	@Autowired
	private GetGoodsByTypeService getGoodsByTypeService;
	@Autowired
	private GetGoodsInfoService getGoodsInfoService;
	@Autowired
	private AddCarService addCarService;
	@Autowired
	private DelCarService delCarService;
	@Autowired
	private GetCarGoodsService getCarGoodsService;
	@Autowired
	private SubmitOrderService submitOrderService;
	@Autowired
	private DelOrderService delOrderService;
	@Autowired
	private PayOrderService payOrderService;
	@Autowired
	private GetWPayOrderService getWPayOrderService;
	@Autowired
	private GetWReceiveOrderService getWReceiveOrderService;
	@Autowired
	private GetHistoryOrderService getHistoryOrderService;
	@Autowired
	private FavGoodsService favGoodsService;
	@Autowired
	private GetFavGoodsService getFavGoodsService;
	@Autowired
	private DelFavGoodsService delFavGoodsService;
	@Autowired
	private NmSelfShopService nmSelfShopService;
	@Autowired
	private UpdateCarService updateCarService;
	@Autowired
	private UpdateOrderService updateOrderService;
	@Autowired
	private GetMessageService getMessageService;
	@Autowired
	private FeedBackService feedBackService;
	@Autowired
	private SearchGoodsService searchGoodsService;
	@Autowired
	private RetrievePwdService retrievePwdService;
	@Autowired
	private SetShopAddressService setShopAddressService;
	@Autowired
	private GetShopAddressService getShopAddressService;
	@Autowired
	private GetShopAdService getShopAdService;
	@Autowired
	private GetShopInfoService getShopInfoService;
	@Autowired
	private GetAllGoodsByShopService getAllGoodsByShopService;
	@Autowired
	private GetMyGoodsByShopService getMyGoodsByShopService;
	@Autowired
	private GetSendingOrderService getSendingOrderService;
	@Autowired
	private GetSendedOrderService getSendedOrderService;
	@Autowired
	private GetTotalOrderService getTotalOrderService;
	@Autowired
	private GetWSendOrderService getWSendOrderService;
	@Autowired
	private ShopGoodsDownService shopGoodsDownService;
	@Autowired
	private ShopGoodsUpService shopGoodsUpService;
	@Autowired
	private ShopGoodsUpdateService shopGoodsUpdateService;
	@Autowired
	private ShopLoginService shopLoginService;
	@Autowired
	private ShopUpdateOrderService shopUpdateOrderService;
	@Autowired
	private ShopUpdatePwdService shopUpdatePwdService;
	@Autowired
	private GetShopGoodsInfoService getShopGoodsInfoService;
	@Autowired
	private GetDistrictService getDistrictService;
	@Autowired
	private GetProvinceService getProvinceService;
	@Autowired
	private GetCityService getCityService;
	@Autowired
	private GetShopService getShopService;
	@Autowired
	private GetGoodsFTypeService getGoodsFTypeService;
	@Autowired
	private GetPerInfoService getPerInfoService;
	@Autowired
	private SetPerInfoService setPerInfoService;
	@Autowired
	private SetHeadImgService setHeadImgService;
	@Autowired
	private DelCarAllService delCarAllService;
	@Autowired
	private GetHpInfoService getHpInfoService;
	@Autowired
	private GetUserOrderNumService getUserOrderNumService;
	@Autowired
	private GetShopGoodsBuyNumService getShopGoodsBuyNumService;
	@Autowired
	private StoreAdService storeAdService;
	
	private static final Logger log = Logger.getLogger(HttpReqController.class);
	public XmlCoder coder = new XmlCoder();
	HttpContent rspxml = new HttpContent();
	HttpError rsperror = new HttpError();
	String rspStr = "";
	String rspEnStr = "";

	@RequestMapping("/HttpService")
	public String index(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String contentType = request.getContentType();
		if (contentType != null && !contentType.equals("")&& request.getContentLength() > 0) {
			try {
				ServletInputStream inStream = request.getInputStream();
				 
				// 接收数据
				/*
				BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
				String lines = "";
				StringBuffer sb = new StringBuffer("");
				while ((lines = reader.readLine()) != null) {
					lines = new String(lines.getBytes(), "utf-8");
					sb.append(lines);
				} 
				*/
				
				byte[] reqdata= IOUtils.toByteArray(inStream);

				/*3DES解密*/
				String reqData=new String(Des3.decode(reqdata));
				/*URLDecoder解码*/
				reqData=URLDecoder.decode(reqData, "utf-8");
				// 处理数据
				if (reqData != null && !reqData.equals("")) {
					// 请求数据
					log.info("接收数据：" + reqData);

					// 解析数据 包
					HttpContent body = coder.decode(reqData, HttpContent.class);
					//log.info("访问接口：" + body.getMessageType());
					//log.info("时间戳：" + body.getMessageTimestamp());
					//log.info("包体内容：" + body.getMessageContent());

					// 1、登录 LoginReq
					if (body.getMessageType().equals(MessageType.LoginReq)) {
						LoginReq req = coder.decode(body.getMessageContent(),LoginReq.class);
						LoginHandler handler = new LoginHandler(loginService);
						LoginRsp rsp = handler.execute(req,request,response);

						rspxml.setMessageType(MessageType.LoginRsp);
						rspStr = coder.encode(rsp);
					} 
					// 2、注册 RegisterReq
					else if (body.getMessageType().equals(MessageType.RegisterReq)) {
						RegisterReq req = coder.decode(body.getMessageContent(),RegisterReq.class);
						RegisterHandler handler = new RegisterHandler(registerService);
						RegisterRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.RegisterRsp);
						rspStr = coder.encode(rsp);
					} 
					// 3、app更新检测 AppUpdateCheckReq
					else if (body.getMessageType().equals(MessageType.AppUpdateCheckReq)) {
						AppUpdateCheckReq req = coder.decode(body.getMessageContent(),AppUpdateCheckReq.class);
						AppUpdateCheckHandler handler = new AppUpdateCheckHandler(appUpdateCheckService);
						AppUpdateCheckRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.AppUpdateCheckRsp);
						rspStr = coder.encode(rsp);
					} 
					// 4、修改密码 UpdatePwdReq
					else if (body.getMessageType().equals(MessageType.UpdatePwdReq)) {
						UpdatePwdReq req = coder.decode(body.getMessageContent(),UpdatePwdReq.class);
						UpdatePwdHandler handler = new UpdatePwdHandler(updatePwdService);
						UpdatePwdRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.UpdatePwdRsp);
						rspStr = coder.encode(rsp);
					} 
					// 1、新增收货地址 AddAddressReq
					else if (body.getMessageType().equals(MessageType.AddAddressReq)) {
						AddAddressReq req = coder.decode(body.getMessageContent(),AddAddressReq.class);
						AddAddressHandler handler = new AddAddressHandler(addAddressService);
						AddAddressRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.AddAddressRsp);
						rspStr = coder.encode(rsp);
					} 
					// 2、修改收货地址 UpdateAddressReq
					else if (body.getMessageType().equals(MessageType.UpdateAddressReq)) {
						UpdateAddressReq req = coder.decode(body.getMessageContent(),UpdateAddressReq.class);
						UpdateAddressHandler handler = new UpdateAddressHandler(updateAddressService);
						UpdateAddressRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.UpdateAddressRsp);
						rspStr = coder.encode(rsp);
					} 
					// 3、修改收货地址 DelAddressReq
					else if (body.getMessageType().equals(MessageType.DelAddressReq)) {
						DelAddressReq req = coder.decode(body.getMessageContent(),DelAddressReq.class);
						DelAddressHandler handler = new DelAddressHandler(delAddressService);
						DelAddressRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.DelAddressRsp);
						rspStr = coder.encode(rsp);
					} 
					// 4、获取全部收货地址 GetAllAddressReq
					else if (body.getMessageType().equals(MessageType.GetAllAddressReq)) {
						GetAllAddressReq req = coder.decode(body.getMessageContent(),GetAllAddressReq.class);
						GetAllAddressHandler handler = new GetAllAddressHandler(getAllAddressService);
						GetAllAddressRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.GetAllAddressRsp);
						rspStr = coder.encode(rsp);
					} 
					// 5、获取指定收货地址 GetAddressReq
					else if (body.getMessageType().equals(MessageType.GetAddressReq)) {
						GetAddressReq req = coder.decode(body.getMessageContent(),GetAddressReq.class);
						GetAddressHandler handler = new GetAddressHandler(getAddressService);
						GetAddressRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.GetAddressRsp);
						rspStr = coder.encode(rsp);
					} 
					// 6、获取附近店铺 GetNearShopReq
					else if (body.getMessageType().equals(MessageType.GetNearShopReq)) {
						GetNearShopReq req = coder.decode(body.getMessageContent(),GetNearShopReq.class);
						GetNearShopHandler handler = new GetNearShopHandler(getNearShopService);
						GetNearShopRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.GetNearShopRsp);
						rspStr = coder.encode(rsp);
					}
					// 7、搜索店铺 SearchShopReq
					else if (body.getMessageType().equals(MessageType.SearchShopReq)) {
						SearchShopReq req = coder.decode(body.getMessageContent(),SearchShopReq.class);
						SearchShopHandler handler = new SearchShopHandler(searchShopService);
						SearchShopRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.SearchShopRsp);
						rspStr = coder.encode(rsp);
					}
					// 8、获取指定店铺商品类型 GetGoodsTypeReq
					else if (body.getMessageType().equals(MessageType.GetGoodsTypeReq)) {
						GetGoodsTypeReq req = coder.decode(body.getMessageContent(),GetGoodsTypeReq.class);
						GetGoodsTypeHandler handler = new GetGoodsTypeHandler(getGoodsTypeService);
						GetGoodsTypeRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.GetGoodsTypeRsp);
						rspStr = coder.encode(rsp);
					}
					// 9、根据类型获取指定店铺商品 GetGoodsByTypeReq
					else if (body.getMessageType().equals(MessageType.GetGoodsByTypeReq)) {
						GetGoodsByTypeReq req = coder.decode(body.getMessageContent(),GetGoodsByTypeReq.class);
						GetGoodsByTypeHandler handler = new GetGoodsByTypeHandler(getGoodsByTypeService,storeAdService);
						GetGoodsByTypeRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.GetGoodsByTypeRsp);
						rspStr = coder.encode(rsp);
					}
					// 10、获取商品详细信息 GetGoodsInfoReq
					else if (body.getMessageType().equals(MessageType.GetGoodsInfoReq)) {
						GetGoodsInfoReq req = coder.decode(body.getMessageContent(),GetGoodsInfoReq.class);
						GetGoodsInfoHandler handler = new GetGoodsInfoHandler(getGoodsInfoService,storeAdService);
						GetGoodsInfoRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.GetGoodsInfoRsp);
						rspStr = coder.encode(rsp);
					}
					// 11、添加商品到购物车 AddCarReq
					else if (body.getMessageType().equals(MessageType.AddCarReq)) {
						AddCarReq req = coder.decode(body.getMessageContent(),AddCarReq.class);
						AddCarHandler handler = new AddCarHandler(addCarService);
						AddCarRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.AddCarRsp);
						rspStr = coder.encode(rsp);
					}
					// 12、删除购物车商品 DelCarReq
					else if (body.getMessageType().equals(MessageType.DelCarReq)) {
						DelCarReq req = coder.decode(body.getMessageContent(),DelCarReq.class);
						DelCarHandler handler = new DelCarHandler(delCarService);
						DelCarRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.DelCarRsp);
						rspStr = coder.encode(rsp);
					}
					// 13、获取购物车商品 GetCarGoodsReq
					else if (body.getMessageType().equals(MessageType.GetCarGoodsReq)) {
						GetCarGoodsReq req = coder.decode(body.getMessageContent(),GetCarGoodsReq.class);
						GetCarGoodsHandler handler = new GetCarGoodsHandler(getCarGoodsService,getShopGoodsBuyNumService,storeAdService);
						GetCarGoodsRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.GetCarGoodsRsp);
						rspStr = coder.encode(rsp);
					}
					// 14、提交订单 SubmitOrderReq
					else if (body.getMessageType().equals(MessageType.SubmitOrderReq)) {
						SubmitOrderReq req = coder.decode(body.getMessageContent(),SubmitOrderReq.class);
						SubmitOrderHandler handler = new SubmitOrderHandler(submitOrderService,storeAdService);
						SubmitOrderRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.SubmitOrderRsp);
						rspStr = coder.encode(rsp);
					}
					// 15、删除订单 DelOrderReq
					else if (body.getMessageType().equals(MessageType.DelOrderReq)) {
						DelOrderReq req = coder.decode(body.getMessageContent(),DelOrderReq.class);
						DelOrderHandler handler = new DelOrderHandler(delOrderService);
						DelOrderRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.DelOrderRsp);
						rspStr = coder.encode(rsp);
					}
					// 16、订单支付成功通知 PayOrderReq
					else if (body.getMessageType().equals(MessageType.PayOrderReq)) {
						PayOrderReq req = coder.decode(body.getMessageContent(),PayOrderReq.class);
						PayOrderHandler handler = new PayOrderHandler(payOrderService);
						PayOrderRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.PayOrderRsp);
						rspStr = coder.encode(rsp);
					}
					// 17、获取待付款订单 GetWPayOrderReq
					else if (body.getMessageType().equals(MessageType.GetWPayOrderReq)) {
						GetWPayOrderReq req = coder.decode(body.getMessageContent(),GetWPayOrderReq.class);
						GetWPayOrderHandler handler = new GetWPayOrderHandler(getWPayOrderService,storeAdService);
						GetWPayOrderRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.GetWPayOrderRsp);
						rspStr = coder.encode(rsp);
					}
					// 18、获取待收货订单 GetWReceiveOrderReq
					else if (body.getMessageType().equals(MessageType.GetWReceiveOrderReq)) {
						GetWReceiveOrderReq req = coder.decode(body.getMessageContent(),GetWReceiveOrderReq.class);
						GetWReceiveOrderHandler handler = new GetWReceiveOrderHandler(getWReceiveOrderService);
						GetWReceiveOrderRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.GetWReceiveOrderRsp);
						rspStr = coder.encode(rsp);
					}
					// 19、获取历史订单 GetHistoryOrderReq
					else if (body.getMessageType().equals(MessageType.GetHistoryOrderReq)) {
						GetHistoryOrderReq req = coder.decode(body.getMessageContent(),GetHistoryOrderReq.class);
						GetHistoryOrderHandler handler = new GetHistoryOrderHandler(getHistoryOrderService);
						GetHistoryOrderRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.GetHistoryOrderRsp);
						rspStr = coder.encode(rsp);
					}
					// 20、收藏商品 FavGoodsReq
					else if (body.getMessageType().equals(MessageType.FavGoodsReq)) {
						FavGoodsReq req = coder.decode(body.getMessageContent(),FavGoodsReq.class);
						FavGoodsHandler handler = new FavGoodsHandler(favGoodsService);
						FavGoodsRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.FavGoodsRsp);
						rspStr = coder.encode(rsp);
					}
					// 21、获取收藏商品 GetFavGoodsReq
					else if (body.getMessageType().equals(MessageType.GetFavGoodsReq)) {
						GetFavGoodsReq req = coder.decode(body.getMessageContent(),GetFavGoodsReq.class);
						GetFavGoodsHandler handler = new GetFavGoodsHandler(getFavGoodsService);
						GetFavGoodsRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.GetFavGoodsRsp);
						rspStr = coder.encode(rsp);
					}
					// 22、删除收藏商品 DelFavGoodsReq
					else if (body.getMessageType().equals(MessageType.DelFavGoodsReq)) {
						DelFavGoodsReq req = coder.decode(body.getMessageContent(),DelFavGoodsReq.class);
						DelFavGoodsHandler handler = new DelFavGoodsHandler(delFavGoodsService);
						DelFavGoodsRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.DelFavGoodsRsp);
						rspStr = coder.encode(rsp);
					}
					// 23、获取自营店信息 NmSelfShopReq
					else if (body.getMessageType().equals(MessageType.NmSelfShopReq)) {
						NmSelfShopReq req = coder.decode(body.getMessageContent(),NmSelfShopReq.class);
						NmSelfShopHandler handler = new NmSelfShopHandler(nmSelfShopService);
						NmSelfShopRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.NmSelfShopRsp);
						rspStr = coder.encode(rsp);
					}
					// 24、修改购物车商品数量 UpdateCarReq
					else if (body.getMessageType().equals(MessageType.UpdateCarReq)) {
						UpdateCarReq req = coder.decode(body.getMessageContent(),UpdateCarReq.class);
						UpdateCarHandler handler = new UpdateCarHandler(updateCarService);
						UpdateCarRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.UpdateCarRsp);
						rspStr = coder.encode(rsp);
					}
					// 25、修改订单状态 UpdateCarReq
					else if (body.getMessageType().equals(MessageType.UpdateOrderReq)) {
						UpdateOrderReq req = coder.decode(body.getMessageContent(),UpdateOrderReq.class);
						UpdateOrderHandler handler = new UpdateOrderHandler(updateOrderService);
						UpdateOrderRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.UpdateOrderRsp);
						rspStr = coder.encode(rsp);
					}
					// 26、获取消息推送列表
					else if (body.getMessageType().equals(MessageType.GetMessageReq)) {
						GetMessageReq req = coder.decode(body.getMessageContent(),GetMessageReq.class);
						GetMessageHandler handler = new GetMessageHandler(getMessageService);
						GetMessageRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.GetMessageRsp);
						rspStr = coder.encode(rsp);
					}
					// 27、提交意见反馈
					else if (body.getMessageType().equals(MessageType.FeedBackReq)) {
						FeedBackReq req = coder.decode(body.getMessageContent(),FeedBackReq.class);
						FeedBackHandler handler = new FeedBackHandler(feedBackService);
						FeedBackRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.FeedBackRsp);
						rspStr = coder.encode(rsp);
					}// 28、搜索商品
					else if (body.getMessageType().equals(MessageType.SearchGoodsReq)) {
						SearchGoodsReq req = coder.decode(body.getMessageContent(),SearchGoodsReq.class);
						SearchGoodsHandler handler = new SearchGoodsHandler(searchGoodsService,storeAdService);
						SearchGoodsRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.SearchGoodsRsp);
						rspStr = coder.encode(rsp);
					}
					// 29、发送验证码
					else if (body.getMessageType().equals(MessageType.SendCheckCodeReq)) {
						SendCheckCodeReq req = coder.decode(body.getMessageContent(),SendCheckCodeReq.class);
						SendCheckCodeHandler handler = new SendCheckCodeHandler();
						SendCheckCodeRsp rsp = handler.execute(req,request);

						rspxml.setMessageType(MessageType.SendCheckCodeRsp);
						rspStr = coder.encode(rsp);
					}
					// 30、找回密码
					else if(body.getMessageType().equals(MessageType.RetrievePwdReq)){
						RetrievePwdReq req = coder.decode(body.getMessageContent(),RetrievePwdReq.class);
						RetrievePwdHandler handler = new RetrievePwdHandler(retrievePwdService);
						RetrievePwdRsp rsp = handler.execute(req, request);
						
						rspxml.setMessageType(MessageType.RetrievePwdRsp);
						rspStr = coder.encode(rsp);
					}
					// 31、设置社区店铺收货地址
					else if(body.getMessageType().equals(MessageType.SetShopAddressReq)){
						SetShopAddressReq req = coder.decode(body.getMessageContent(),SetShopAddressReq.class);
						SetShopAddressHandler handler = new SetShopAddressHandler(setShopAddressService);
						SetShopAddressRsp rsp = handler.execute(req, request);
						
						rspxml.setMessageType(MessageType.SetShopAddressRsp);
						rspStr = coder.encode(rsp);
					}
					// 32、获取社区店铺收货地址
					else if(body.getMessageType().equals(MessageType.GetShopAddressReq)){
						GetShopAddressReq req = coder.decode(body.getMessageContent(),GetShopAddressReq.class);
						GetShopAddressHandler handler = new GetShopAddressHandler(getShopAddressService);
						GetShopAddressRsp rsp = handler.execute(req, request);
						
						rspxml.setMessageType(MessageType.GetShopAddressRsp);
						rspStr = coder.encode(rsp);
					}
					// 33、获取便利店广告信息
					else if(body.getMessageType().equals(MessageType.GetShopAdReq)){
						GetShopAdReq req = coder.decode(body.getMessageContent(),GetShopAdReq.class);
						GetShopAdHandler handler = new GetShopAdHandler(getShopAdService);
						GetShopAdRsp rsp = handler.execute(req, request);
						
						rspxml.setMessageType(MessageType.GetShopAdRsp);
						rspStr = coder.encode(rsp);
					}
					// 34、获取店铺信息
					else if(body.getMessageType().equals(MessageType.GetShopInfoReq)){
						GetShopInfoReq req = coder.decode(body.getMessageContent(),GetShopInfoReq.class);
						GetShopInfoHandler handler = new GetShopInfoHandler(getShopInfoService);
						GetShopInfoRsp rsp = handler.execute(req, request);
						
						rspxml.setMessageType(MessageType.GetShopInfoRsp);
						rspStr = coder.encode(rsp);
					}
					// 35、获取省
					else if(body.getMessageType().equals(MessageType.GetProvinceReq)){
						GetProvinceReq req = coder.decode(body.getMessageContent(),GetProvinceReq.class);
						GetProvinceHandler handler = new GetProvinceHandler(getProvinceService);
						GetProvinceRsp rsp = handler.execute(req, request);
						
						rspxml.setMessageType(MessageType.GetProvinceRsp);
						rspStr = coder.encode(rsp);
					}
					// 36、获取市
					else if(body.getMessageType().equals(MessageType.GetCityReq)){
						GetCityReq req = coder.decode(body.getMessageContent(),GetCityReq.class);
						GetCityHandler handler = new GetCityHandler(getCityService);
						GetCityRsp rsp = handler.execute(req, request);
						
						rspxml.setMessageType(MessageType.GetCityRsp);
						rspStr = coder.encode(rsp);
					}
					// 37、获取区
					else if(body.getMessageType().equals(MessageType.GetDistrictReq)){
						GetDistrictReq req = coder.decode(body.getMessageContent(),GetDistrictReq.class);
						GetDistrictHandler handler = new GetDistrictHandler(getDistrictService);
						GetDistrictRsp rsp = handler.execute(req, request);
						
						rspxml.setMessageType(MessageType.GetDistrictRsp);
						rspStr = coder.encode(rsp);
					}
					// 38、获取指定市辖区店铺列表
					else if(body.getMessageType().equals(MessageType.GetShopReq)){
						GetShopReq req = coder.decode(body.getMessageContent(),GetShopReq.class);
						GetShopHandler handler = new GetShopHandler(getShopService);
						GetShopRsp rsp = handler.execute(req, request);
						
						rspxml.setMessageType(MessageType.GetShopRsp);
						rspStr = coder.encode(rsp);
					}
					// 39、获取指定店铺商品类型一级列表
					else if(body.getMessageType().equals(MessageType.GetGoodsFTypeReq)){
						GetGoodsFTypeReq req = coder.decode(body.getMessageContent(),GetGoodsFTypeReq.class);
						GetGoodsFTypeHandler handler = new GetGoodsFTypeHandler(getGoodsFTypeService);
						GetGoodsFTypeRsp rsp = handler.execute(req, request);
						
						rspxml.setMessageType(MessageType.GetGoodsFTypeRsp);
						rspStr = coder.encode(rsp);
					}
					// 40、获取个人资料
					else if(body.getMessageType().equals(MessageType.GetPerInfoReq)){
						GetPerInfoReq req = coder.decode(body.getMessageContent(),GetPerInfoReq.class);
						GetPerInfoHandler handler = new GetPerInfoHandler(getPerInfoService);
						GetPerInfoRsp rsp = handler.execute(req, request);
						
						rspxml.setMessageType(MessageType.GetPerInfoRsp);
						rspStr = coder.encode(rsp);
					}
					// 41、设置个人资料
					else if(body.getMessageType().equals(MessageType.SetPerInfoReq)){
						SetPerInfoReq req = coder.decode(body.getMessageContent(),SetPerInfoReq.class);
						SetPerInfoHandler handler = new SetPerInfoHandler(setPerInfoService);
						SetPerInfoRsp rsp = handler.execute(req, request);
						
						rspxml.setMessageType(MessageType.SetPerInfoRsp);
						rspStr = coder.encode(rsp);
					}
					// 42、设置个人头像
					else if(body.getMessageType().equals(MessageType.SetHeadImgReq)){
						SetHeadImgReq req = coder.decode(body.getMessageContent(),SetHeadImgReq.class);
						SetHeadImgHandler handler = new SetHeadImgHandler(setHeadImgService);
						SetHeadImgRsp rsp = handler.execute(req, request);
						
						rspxml.setMessageType(MessageType.SetHeadImgRsp);
						rspStr = coder.encode(rsp);
					}
					// 43、清空购物车
					else if(body.getMessageType().equals(MessageType.DelCarAllReq)){
						DelCarAllReq req = coder.decode(body.getMessageContent(),DelCarAllReq.class);
						DelCarAllHandler handler = new DelCarAllHandler(delCarAllService);
						DelCarAllRsp rsp = handler.execute(req, request);
						
						rspxml.setMessageType(MessageType.DelCarAllRsp);
						rspStr = coder.encode(rsp);
					}
					// 44、 获取首页招募令、0元购、限时抢购信息
					else if(body.getMessageType().equals(MessageType.GetHpInfoReq)){
						GetHpInfoReq req = coder.decode(body.getMessageContent(),GetHpInfoReq.class);
						GetHpInfoHandler handler = new GetHpInfoHandler(getHpInfoService);
						GetHpInfoRsp rsp = handler.execute(req, request);
						
						rspxml.setMessageType(MessageType.GetHpInfoRsp);
						rspStr = coder.encode(rsp);
					}
					// 45、 获取用户已支付订单数
					else if(body.getMessageType().equals(MessageType.GetUserOrderNumReq)){
						GetUserOrderNumReq req = coder.decode(body.getMessageContent(),GetUserOrderNumReq.class);
						GetUserOrderNumHandler handler = new GetUserOrderNumHandler(getUserOrderNumService);
						GetUserOrderNumRsp rsp = handler.execute(req, request);
						
						rspxml.setMessageType(MessageType.GetHpInfoRsp);
						rspStr = coder.encode(rsp);
					}
					// 46、 获取店铺限时抢购商品购买数
					else if(body.getMessageType().equals(MessageType.GetShopGoodsBuyNumReq)){
						GetShopGoodsBuyNumReq req = coder.decode(body.getMessageContent(),GetShopGoodsBuyNumReq.class);
						GetShopGoodsBuyNumHandler handler = new GetShopGoodsBuyNumHandler(getShopGoodsBuyNumService);
						GetShopGoodsBuyNumRsp rsp = handler.execute(req, request);
						
						rspxml.setMessageType(MessageType.GetShopGoodsBuyNumRsp);
						rspStr = coder.encode(rsp);
					}
					/**********************************商家版接口********************************************/
					
					// 1、店铺登录
					else if(body.getMessageType().equals(MessageType.ShopLoginReq)){
						ShopLoginReq req = coder.decode(body.getMessageContent(),ShopLoginReq.class);
						ShopLoginHandler handler = new ShopLoginHandler(shopLoginService);
						ShopLoginRsp rsp = handler.execute(req, request);
						
						rspxml.setMessageType(MessageType.ShopLoginRsp);
						rspStr = coder.encode(rsp);
					}
					// 2、商家修改密码
					else if(body.getMessageType().equals(MessageType.ShopUpdatePwdReq)){
						ShopUpdatePwdReq req = coder.decode(body.getMessageContent(),ShopUpdatePwdReq.class);
						ShopUpdatePwdHandler handler = new ShopUpdatePwdHandler(shopUpdatePwdService);
						ShopUpdatePwdRsp rsp = handler.execute(req, request);
						
						rspxml.setMessageType(MessageType.ShopUpdatePwdRsp);
						rspStr = coder.encode(rsp);
					}
					// 3、获取全部商品列表
					else if(body.getMessageType().equals(MessageType.GetAllGoodsByShopReq)){
						GetAllGoodsByShopReq req = coder.decode(body.getMessageContent(),GetAllGoodsByShopReq.class);
						GetAllGoodsByShopHandler handler = new GetAllGoodsByShopHandler(getAllGoodsByShopService);
						GetAllGoodsByShopRsp rsp = handler.execute(req, request);
						
						rspxml.setMessageType(MessageType.GetAllGoodsByShopRsp);
						rspStr = coder.encode(rsp);
					}
					// 4、获取已上架商品列表
					else if(body.getMessageType().equals(MessageType.GetMyGoodsByShopReq)){
						GetMyGoodsByShopReq req = coder.decode(body.getMessageContent(),GetMyGoodsByShopReq.class);
						GetMyGoodsByShopHandler handler = new GetMyGoodsByShopHandler(getMyGoodsByShopService);
						GetMyGoodsByShopRsp rsp = handler.execute(req, request);
						
						rspxml.setMessageType(MessageType.GetMyGoodsByShopRsp);
						rspStr = coder.encode(rsp);
					}
					// 5、商品批量上架
					else if(body.getMessageType().equals(MessageType.ShopGoodsUpReq)){
						ShopGoodsUpReq req = coder.decode(body.getMessageContent(),ShopGoodsUpReq.class);
						ShopGoodsUpHandler handler = new ShopGoodsUpHandler(shopGoodsUpService);
						ShopGoodsUpRsp rsp = handler.execute(req, request);
						
						rspxml.setMessageType(MessageType.ShopGoodsUpRsp);
						rspStr = coder.encode(rsp);
					}
					// 6、商品批量下架
					else if(body.getMessageType().equals(MessageType.ShopGoodsDownReq)){
						ShopGoodsDownReq req = coder.decode(body.getMessageContent(),ShopGoodsDownReq.class);
						ShopGoodsDownHandler handler = new ShopGoodsDownHandler(shopGoodsDownService);
						ShopGoodsDownRsp rsp = handler.execute(req, request);
						
						rspxml.setMessageType(MessageType.ShopGoodsDownRsp);
						rspStr = coder.encode(rsp);
					}
					// 7、上架商品价格、库存修改
					else if(body.getMessageType().equals(MessageType.ShopGoodsUpdateReq)){
						ShopGoodsUpdateReq req = coder.decode(body.getMessageContent(),ShopGoodsUpdateReq.class);
						ShopGoodsUpdateHandler handler = new ShopGoodsUpdateHandler(shopGoodsUpdateService);
						ShopGoodsUpdateRsp rsp = handler.execute(req, request);
						
						rspxml.setMessageType(MessageType.ShopGoodsUpdateRsp);
						rspStr = coder.encode(rsp);
					}
					// 8、获取待处理订单列表
					else if(body.getMessageType().equals(MessageType.GetWSendOrderReq)){
						GetWSendOrderReq req = coder.decode(body.getMessageContent(),GetWSendOrderReq.class);
						GetWSendOrderHandler handler = new GetWSendOrderHandler(getWSendOrderService);
						GetWSendOrderRsp rsp = handler.execute(req, request);
						
						rspxml.setMessageType(MessageType.GetWSendOrderRsp);
						rspStr = coder.encode(rsp);
					}
					// 9、获取派送中订单列表
					else if(body.getMessageType().equals(MessageType.GetSendingOrderReq)){
						GetSendingOrderReq req = coder.decode(body.getMessageContent(),GetSendingOrderReq.class);
						GetSendingOrderHandler handler = new GetSendingOrderHandler(getSendingOrderService);
						GetSendingOrderRsp rsp = handler.execute(req, request);
						
						rspxml.setMessageType(MessageType.GetSendingOrderRsp);
						rspStr = coder.encode(rsp);
					}
					// 10、获取已完成订单列表
					else if(body.getMessageType().equals(MessageType.GetSendedOrderReq)){
						GetSendedOrderReq req = coder.decode(body.getMessageContent(),GetSendedOrderReq.class);
						GetSendedOrderHandler handler = new GetSendedOrderHandler(getSendedOrderService);
						GetSendedOrderRsp rsp = handler.execute(req, request);
						
						rspxml.setMessageType(MessageType.GetSendedOrderRsp);
						rspStr = coder.encode(rsp);
					}
					// 11、更改订单状态
					else if(body.getMessageType().equals(MessageType.ShopUpdateOrderReq)){
						ShopUpdateOrderReq req = coder.decode(body.getMessageContent(),ShopUpdateOrderReq.class);
						ShopUpdateOrderHandler handler = new ShopUpdateOrderHandler(shopUpdateOrderService);
						ShopUpdateOrderRsp rsp = handler.execute(req, request);
						
						rspxml.setMessageType(MessageType.ShopUpdateOrderRsp);
						rspStr = coder.encode(rsp);
					}
					// 12、获取销售额统计
					else if(body.getMessageType().equals(MessageType.GetTotalOrderReq)){
						GetTotalOrderReq req = coder.decode(body.getMessageContent(),GetTotalOrderReq.class);
						GetTotalOrderHandler handler = new GetTotalOrderHandler(getTotalOrderService);
						GetTotalOrderRsp rsp = handler.execute(req, request);
						
						rspxml.setMessageType(MessageType.GetTotalOrderRsp);
						rspStr = coder.encode(rsp);
					}
					// 13、获取商品详情
					else if(body.getMessageType().equals(MessageType.GetShopGoodsInfoReq)){
						GetShopGoodsInfoReq req = coder.decode(body.getMessageContent(),GetShopGoodsInfoReq.class);
						GetShopGoodsInfoHandler handler = new GetShopGoodsInfoHandler(getShopGoodsInfoService);
						GetShopGoodsInfoRsp rsp = handler.execute(req, request);
						
						rspxml.setMessageType(MessageType.GetShopGoodsInfoRsp);
						rspStr = coder.encode(rsp);
					}
					else {
						throw new Exception("请求类型错误！");
					}

					
					// 返回时间戳
					String msgTime = HttpDateUtil.getNowDateString();
					rspxml.setMessageTimestamp(msgTime);
					rspxml.setMessageContent(rspStr);

					// 返回数据
					String retxml = coder.encode(rspxml);
					//log.info("返回包体：" + rspStr);
					log.info("返回数据：" + retxml);
					/*URLEncoder编码*/
					retxml=URLEncoder.encode(retxml,"utf-8");
					response.setContentType("application/octet-stream");
					response.setCharacterEncoding("utf-8");
					
					InputStream input = new ByteArrayInputStream(Des3.encode(retxml.getBytes("utf-8"))); 
					OutputStream output=response.getOutputStream();
					int inlen;  
					byte[] buf = new byte[1024];  
					while ((inlen = input.read(buf)) != -1) {  
						output.write(buf, 0, inlen);  
					}  
					output.flush();  
					output.close();

					//PrintWriter out = response.getWriter();
					/*3DES加密*/
					//out.println(Des3.encode(retxml.getBytes("utf-8")));
					//out.flush();
					//out.close();
				} else {
					rsperror.setErrorCode("400001");
					rsperror.setMessage("Invalid http body.");

					log.error("消息体为空.");

					response.setStatus(400);
					response.setContentType("application/xml");
					PrintWriter out = response.getWriter();
					out.println(coder.encode(rsperror));
					out.flush();
					out.close();
				}
			} catch (Exception e) {
				e.printStackTrace();

				log.error(e.getMessage());

				rsperror.setErrorCode("400001");
				rsperror.setMessage("Invalid http body. " + e.getMessage());

				response.setStatus(400);
				response.setContentType("application/xml");
				PrintWriter out;
				try {
					out = response.getWriter();
					out.println(coder.encode(rsperror));
					out.flush();
					out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} else {
			log.error("请求类型或请求长度为空.");

			rsperror.setErrorCode("400001");
			rsperror.setMessage("Invalid http header.");

			response.setStatus(400);
			response.setContentType("application/xml");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println(coder.encode(rsperror));
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}

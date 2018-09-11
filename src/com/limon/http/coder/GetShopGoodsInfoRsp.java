package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetShopGoodsInfoRsp")
public class GetShopGoodsInfoRsp {
	@XmlNode(name="Result")
	private Integer Result;
	@XmlNode(name="ShopGoods")
	private String ShopGoods;
	@XmlNode(name="ErrorMsg")
	private String ErrorMsg;
	
	public String getErrorMsg() {
		return ErrorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
	}
	public Integer getResult() {
		return Result;
	}
	public void setResult(Integer result) {
		Result = result;
	}
	public String getShopGoods() {
		return ShopGoods;
	}
	public void setShopGoods(String shopGoods) {
		ShopGoods = shopGoods;
	}
}

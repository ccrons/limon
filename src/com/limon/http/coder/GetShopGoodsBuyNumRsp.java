package com.limon.http.coder;

import com.limon.http.xml.XmlNode;

@XmlNode(name="GetShopGoodsBuyNumRsp")
public class GetShopGoodsBuyNumRsp {
	@XmlNode(name="Result")
	private String Result;
	@XmlNode(name="ErrorMsg")
	private String ErrorMsg;
	@XmlNode(name="BuyNum")
	private Integer BuyNum;
	@XmlNode(name="LimitNum")
	private Integer LimitNum;
	@XmlNode(name="TotalNum")
	private Integer TotalNum;
	public String getResult() {
		return Result;
	}
	public void setResult(String result) {
		Result = result;
	}
	public String getErrorMsg() {
		return ErrorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
	}
	public Integer getBuyNum() {
		return BuyNum;
	}
	public void setBuyNum(Integer buyNum) {
		BuyNum = buyNum;
	}
	public Integer getLimitNum() {
		return LimitNum;
	}
	public void setLimitNum(Integer limitNum) {
		LimitNum = limitNum;
	}
	public Integer getTotalNum() {
		return TotalNum;
	}
	public void setTotalNum(Integer totalNum) {
		TotalNum = totalNum;
	}
}

package com.limon.manage.model;

import java.io.Serializable;

/**
 * 广告产品关联表
 * 项目名称：project
 * 类名称：StoreAdProduct
 * 创建人：WN
 * 创建时间：2015年11月14日下午10:45:10
 * @version v1.0
 */
public class StoreAdProductInfo implements Serializable {

	private static final long serialVersionUID = -556917883429926727L;
	private Integer id;
	private String name;
	private Double price;
	private Double sprice;
	private Double saleprice;
	private Double adprice;
	private String unit;
	private String imgurl;
	private String paddress;
	private String brandname;
	private String pic1;
	private String pic2;
	private String pic3;
	private String pic4;
	private String pic5;
	private String description;
	private Integer tlnum;
	private String tltime;
	private String islimit;
	private String buynum;
	private String limitnum;
	
	private Integer isfrist;//0元购
	private Integer isluck;//抽奖
	private Integer isrec;//推荐
	private Integer isofficial;//官方供应
	private Integer isfav;
	
	private String adid;//广告活动id
	private Double tlprice;//限时抢购价钱
	public Integer getTlnum() {
		return tlnum;
	}
	public void setTlnum(Integer tlnum) {
		this.tlnum = tlnum;
	}
	public String getTltime() {
		return tltime;
	}
	public void setTltime(String tltime) {
		this.tltime = tltime;
	}
	public String getPaddress() {
		return paddress;
	}
	public void setPaddress(String paddress) {
		this.paddress = paddress;
	}
	public String getBrandname() {
		return brandname;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	public String getPic1() {
		return pic1;
	}
	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}
	public String getPic2() {
		return pic2;
	}
	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}
	public String getPic3() {
		return pic3;
	}
	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}
	public String getPic4() {
		return pic4;
	}
	public void setPic4(String pic4) {
		this.pic4 = pic4;
	}
	public String getPic5() {
		return pic5;
	}
	public void setPic5(String pic5) {
		this.pic5 = pic5;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getSprice() {
		return sprice;
	}
	public void setSprice(Double sprice) {
		this.sprice = sprice;
	}
	public Double getAdprice() {
		return adprice;
	}
	public void setAdprice(Double adprice) {
		this.adprice = adprice;
	}
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getIslimit() {
		return islimit;
	}
	public void setIslimit(String islimit) {
		this.islimit = islimit;
	}
	public String getBuynum() {
		return buynum;
	}
	public void setBuynum(String buynum) {
		this.buynum = buynum;
	}
	public String getLimitnum() {
		return limitnum;
	}
	public void setLimitnum(String limitnum) {
		this.limitnum = limitnum;
	}
	public String getAdid() {
		return adid;
	}
	public void setAdid(String adid) {
		this.adid = adid;
	}
	public Double getTlprice() {
		return tlprice;
	}
	public void setTlprice(Double tlprice) {
		this.tlprice = tlprice;
	}
	public Integer getIsfrist() {
		return isfrist;
	}
	public void setIsfrist(Integer isfrist) {
		this.isfrist = isfrist;
	}
	public Integer getIsluck() {
		return isluck;
	}
	public void setIsluck(Integer isluck) {
		this.isluck = isluck;
	}
	public Integer getIsrec() {
		return isrec;
	}
	public void setIsrec(Integer isrec) {
		this.isrec = isrec;
	}
	public Integer getIsofficial() {
		return isofficial;
	}
	public void setIsofficial(Integer isofficial) {
		this.isofficial = isofficial;
	}
	public Double getSaleprice() {
		return saleprice;
	}
	public void setSaleprice(Double saleprice) {
		this.saleprice = saleprice;
	}
	public Integer getIsfav() {
		return isfav;
	}
	public void setIsfav(Integer isfav) {
		this.isfav = isfav;
	}


}

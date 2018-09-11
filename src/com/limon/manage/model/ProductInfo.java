package com.limon.manage.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wn
 *
 * 2015-7-2
 */
public class ProductInfo implements Serializable {
	private static final long serialVersionUID = -5407943680868650223L;
	private Integer id;
	private String name;
	private Integer type;
	private String typename;
	private Integer brand;
	private String brandname;
	
	private Double price;
	private Double sprice;
	private Integer upnum;
	private String unit;
	private Integer weight;
	private String paddress;
	private Date creattime;
	private String imgurl;
	
	private String pic1;
	private String pic2;
	private String pic3;
	private String pic4;
	
	private String pic5;
	private String description;
	private Integer isofficial;
	private Integer isonline;
	private Integer isdel;
	private Integer isself;
	private Integer isrec;
	private Integer status;
	private Integer isfrist;//0元购
	private Integer isluck;//抽奖
	
	private Integer sid;
	private Integer storeid;
	private Integer salenum;
	private Double saleprice;
	private Double proxyprice;//销售价
	private Double stockprice;//进货价
	
	private Integer ordernum;
	private String orderno;
	private Double allprice;//销售额
	
	private String saleid;
	private String adid;//广告活动id
	private Double adprice;//广告活动价钱
	
	private Integer tlnum;//限时抢购数量
	private Integer buynum;//每人限时抢购数量
	private Double tlprice;//限时抢购价钱
	private String tltime;//限时抢购时间
	private String tltime1;
	private String tltime2;
	private String tltime3;
	
	private Double allorderprice;
	private Double allsendprice;
	
	private String opprice;

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Double getProxyprice() {
		return proxyprice;
	}

	public void setProxyprice(Double proxyprice) {
		this.proxyprice = proxyprice;
	}

	public Double getStockprice() {
		return stockprice;
	}

	public void setStockprice(Double stockprice) {
		this.stockprice = stockprice;
	}

	public String getOpprice() {
		return opprice;
	}
	public void setOpprice(String opprice) {
		this.opprice = opprice;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getBrand() {
		return brand;
	}
	public void setBrand(Integer brand) {
		this.brand = brand;
	}

	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getBrandname() {
		return brandname;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Date getCreattime() {
		return creattime;
	}
	public void setCreattime(Date creattime) {
		this.creattime = creattime;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
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
	public Integer getIsonline() {
		return isonline;
	}
	public void setIsonline(Integer isonline) {
		this.isonline = isonline;
	}
	public Integer getIsdel() {
		return isdel;
	}
	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}
	public Integer getStoreid() {
		return storeid;
	}
	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}
	public Integer getSalenum() {
		return salenum;
	}
	public void setSalenum(Integer salenum) {
		this.salenum = salenum;
	}
	public Double getSaleprice() {
		return saleprice;
	}
	public void setSaleprice(Double saleprice) {
		this.saleprice = saleprice;
	}
	public Integer getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
	}
	public Double getAllprice() {
		return allprice;
	}
	public void setAllprice(Double allprice) {
		this.allprice = allprice;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public String getSaleid() {
		return saleid;
	}
	public void setSaleid(String saleid) {
		this.saleid = saleid;
	}
	public String getAdid() {
		return adid;
	}
	public void setAdid(String adid) {
		this.adid = adid;
	}
	public Integer getIsself() {
		return isself;
	}
	public void setIsself(Integer isself) {
		this.isself = isself;
	}
	public Integer getUpnum() {
		return upnum;
	}
	public void setUpnum(Integer upnum) {
		this.upnum = upnum;
	}
	public Double getSprice() {
		return sprice;
	}
	public void setSprice(Double sprice) {
		this.sprice = sprice;
	}
	public String getPaddress() {
		return paddress;
	}
	public void setPaddress(String paddress) {
		this.paddress = paddress;
	}
	public Integer getIsofficial() {
		return isofficial;
	}
	public void setIsofficial(Integer isofficial) {
		this.isofficial = isofficial;
	}
	public Integer getIsrec() {
		return isrec;
	}
	public void setIsrec(Integer isrec) {
		this.isrec = isrec;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsfrist() {
		return isfrist;
	}
	public void setIsfrist(Integer isfrist) {
		this.isfrist = isfrist;
	}
	public Double getAdprice() {
		return adprice;
	}
	public void setAdprice(Double adprice) {
		this.adprice = adprice;
	}
	public Integer getTlnum() {
		return tlnum;
	}
	public void setTlnum(Integer tlnum) {
		this.tlnum = tlnum;
	}
	public Double getTlprice() {
		return tlprice;
	}
	public void setTlprice(Double tlprice) {
		this.tlprice = tlprice;
	}
	public String getTltime() {
		return tltime;
	}
	public void setTltime(String tltime) {
		this.tltime = tltime;
	}
	public String getTltime1() {
		return tltime1;
	}
	public void setTltime1(String tltime1) {
		this.tltime1 = tltime1;
	}
	public String getTltime2() {
		return tltime2;
	}
	public void setTltime2(String tltime2) {
		this.tltime2 = tltime2;
	}
	public String getTltime3() {
		return tltime3;
	}
	public void setTltime3(String tltime3) {
		this.tltime3 = tltime3;
	}
	public Integer getBuynum() {
		return buynum;
	}
	public void setBuynum(Integer buynum) {
		this.buynum = buynum;
	}
	public Integer getIsluck() {
		return isluck;
	}
	public void setIsluck(Integer isluck) {
		this.isluck = isluck;
	}
	public Double getAllorderprice() {
		return allorderprice;
	}
	public void setAllorderprice(Double allorderprice) {
		this.allorderprice = allorderprice;
	}
	public Double getAllsendprice() {
		return allsendprice;
	}
	public void setAllsendprice(Double allsendprice) {
		this.allsendprice = allsendprice;
	}
	
	
}

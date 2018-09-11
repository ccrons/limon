package com.limon.store.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 便利店广告
 * @author WN
 *
 */
public class StoreAd implements Serializable {
	private static final long serialVersionUID = -7156985931286055251L;
	private Integer id;
	private Integer storeid;
	private String adname;
	private String adcontent;
	private Date createtime;
	private String isdel;
	private Double isup;
	private String imgurl;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStoreid() {
		return storeid;
	}
	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}
	public String getAdname() {
		return adname;
	}
	public void setAdname(String adname) {
		this.adname = adname;
	}
	public String getAdcontent() {
		return adcontent;
	}
	public void setAdcontent(String adcontent) {
		this.adcontent = adcontent;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getIsdel() {
		return isdel;
	}
	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}
	public Double getIsup() {
		return isup;
	}
	public void setIsup(Double isup) {
		this.isup = isup;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	
}

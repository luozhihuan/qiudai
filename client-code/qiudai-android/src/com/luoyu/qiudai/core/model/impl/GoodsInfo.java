package com.luoyu.qiudai.core.model.impl;
/**
 * 商品信息
 * @author chuanrong
 *
 */
public class GoodsInfo {
	
	
	/**商品id**/
	private long goodsInfoId;
	
	/**商品名称**/
	private String goodsName;
	
	/**商品价格**/
	private double goodsPrice;
	
	/**商品种类**/
	private int goodsCategory;
	
	/**商品图片链接**/
	private String goodsImageUrl;
	
	/**商品购买数量**/
	private int goodsBuyNum;

	public long getGoodsInfoId() {
		return goodsInfoId;
	}

	public void setGoodsInfoId(long goodsInfoId) {
		this.goodsInfoId = goodsInfoId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public double getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public int getGoodsCategory() {
		return goodsCategory;
	}

	public void setGoodsCategory(int goodsCategory) {
		this.goodsCategory = goodsCategory;
	}

	public String getGoodsImageUrl() {
		return goodsImageUrl;
	}

	public void setGoodsImageUrl(String goodsImageUrl) {
		this.goodsImageUrl = goodsImageUrl;
	}

	public int getGoodsBuyNum() {
		return goodsBuyNum;
	}

	public void setGoodsBuyNum(int goodsBuyNum) {
		this.goodsBuyNum = goodsBuyNum;
	}
	

}

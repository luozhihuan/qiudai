package com.hust.qiudai.core.db;

public class GoodsIdAndBuyNum {

	public GoodsIdAndBuyNum(long goodsId, int buyNum) {
		this.goodsId = goodsId;
		this.buyNum = buyNum;
	}

	public GoodsIdAndBuyNum(GoodsInfo goodsInfo) {
		this.goodsId = goodsInfo.getGoodsInfoId();
		this.buyNum = goodsInfo.getGoodsBuyNum();
	}

	/**商品购买信息id**/
	private long goodsBuyInfoId;
	
	/**求带信息id**/
	private long qiudaiInfoId;
	
	/** 商品id **/
	private long goodsId;

	/** 商品购买数量 **/
	private int buyNum;

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public int getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}

	public long getQiudaiInfoId() {
		return qiudaiInfoId;
	}

	public void setQiudaiInfoId(long qiudaiInfoId) {
		this.qiudaiInfoId = qiudaiInfoId;
	}

	public long getGoodsBuyInfoId() {
		return goodsBuyInfoId;
	}

	public void setGoodsBuyInfoId(long goodsBuyInfoId) {
		this.goodsBuyInfoId = goodsBuyInfoId;
	}

}

package com.luoyu.qiudai.core.model.impl;

import com.luoyu.qiudai.core.model.SelectionModel;


/**
 * 商品类别
 * @author chuanrong
 *
 */
public class GoodsCategory extends SelectionModel{
	
	/**商品所属小类别id**/
	private int categoryId;
	
	/**商品所属小类别名称**/
	private String categoryName;
	
	/**商品所属大类别id（大类别下细分为小类别，小类别下细分为具体商品）**/
	private int kindCategoryId;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getKindCategoryId() {
		return kindCategoryId;
	}

	public void setKindCategoryId(int kindCategoryId) {
		this.kindCategoryId = kindCategoryId;
	}
	
	@Override
	public long getselectedId() {
		return getCategoryId();
	}
	@Override
	public String getSelectedName() {
		return getCategoryName();
	}
	
	@Override
	public String getDefaultName() {
		// TODO Auto-generated method stub
		return "商品种类";
	}

}

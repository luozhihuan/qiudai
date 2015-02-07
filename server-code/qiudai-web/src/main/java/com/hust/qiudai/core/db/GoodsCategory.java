package com.hust.qiudai.core.db;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 商品类别
 * @author chuanrong
 *
 */
@XmlRootElement(name="GoodsCategory") 
public class GoodsCategory {
	
	/**商品所属小类别id**/
	@XmlElement(name="id")  
	private int categoryId;
	
	/**商品所属小类别名称**/
	@XmlElement(name="name")  
	private String categoryName;
	
	/**商品所属大类别id（大类别下细分为小类别，小类别下细分为具体商品）**/
	@XmlElement(name="kind")  
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
	
	

}

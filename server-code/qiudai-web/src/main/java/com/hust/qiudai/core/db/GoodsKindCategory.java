package com.hust.qiudai.core.db;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 商品大类分类实体
 * @author chuanrong
 *
 */
@XmlRootElement
public class GoodsKindCategory {

	/**商品大类分类id**/
	private int kindCategoryId;

	/**商品大类分类名称**/
	private String kindCategoryName;

	public int getKindCategoryId() {
		return kindCategoryId;
	}

	public void setKindCategoryId(int kindCategoryId) {
		this.kindCategoryId = kindCategoryId;
	}

	public String getKindCategoryName() {
		return kindCategoryName;
	}

	public void setKindCategoryName(String kindCategoryName) {
		this.kindCategoryName = kindCategoryName;
	}

}

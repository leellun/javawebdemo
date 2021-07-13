package com.newland.cms.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_dict")
public class Dict implements Serializable {

    private static final long serialVersionUID = 7780820231535870010L;

    @TableId(value = "DICT_ID", type = IdType.AUTO)
    private Long dictId;

    private String keyy;

    private String valuee;

    private String tableName;

    private String fieldName;

    private String otherKeyy;

	public Long getDictId() {
		return dictId;
	}

	public void setDictId(Long dictId) {
		this.dictId = dictId;
	}

	public String getKeyy() {
		return keyy;
	}

	public void setKeyy(String keyy) {
		this.keyy = keyy;
	}

	public String getValuee() {
		return valuee;
	}

	public void setValuee(String valuee) {
		this.valuee = valuee;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getOtherKeyy() {
		return otherKeyy;
	}

	public void setOtherKeyy(String otherKeyy) {
		this.otherKeyy = otherKeyy;
	}
    
}

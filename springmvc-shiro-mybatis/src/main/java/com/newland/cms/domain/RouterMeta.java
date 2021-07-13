package com.newland.cms.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Vue路由 Meta
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RouterMeta implements Serializable {

    private static final long serialVersionUID = 5499925008927195914L;

    private Boolean closeable;

    private Boolean isShow;

	public RouterMeta() {
		super();
	}

	public RouterMeta(Boolean closeable, Boolean isShow) {
		super();
		this.closeable = closeable;
		this.isShow = isShow;
	}

	public Boolean getCloseable() {
		return closeable;
	}

	public void setCloseable(Boolean closeable) {
		this.closeable = closeable;
	}

	public Boolean getIsShow() {
		return isShow;
	}

	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

}

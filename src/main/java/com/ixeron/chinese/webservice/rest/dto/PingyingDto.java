package com.ixeron.chinese.webservice.rest.dto;

import com.ixeron.chinese.domain.Pingying;

public class PingyingDto extends Pingying{

    private Integer listOrder;

	public Integer getListOrder() {
		return listOrder;
	}

	public void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
	}
}

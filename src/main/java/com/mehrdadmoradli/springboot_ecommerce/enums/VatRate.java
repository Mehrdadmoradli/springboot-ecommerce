package com.mehrdadmoradli.springboot_ecommerce.enums;

import java.math.*;

public enum VatRate {
	
	STANDARD(BigDecimal.valueOf(0.19));
	
	private BigDecimal rate;
	
	 VatRate(BigDecimal rate) {
		this.setRate(rate);
	}

	 public BigDecimal getRate() {
		return rate;
	}

	 public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
}
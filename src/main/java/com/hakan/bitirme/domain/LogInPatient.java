package com.hakan.bitirme.domain;

import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;

public class LogInPatient {

	
	
	public class GirisKullanici {
		@Column(length = 11, nullable = false)
		@Getter
		@Setter
		private String citizennumber;
		
		@Column(length = 200, nullable = false)
		@Getter
		@Setter
		private String sifre;

	}
}

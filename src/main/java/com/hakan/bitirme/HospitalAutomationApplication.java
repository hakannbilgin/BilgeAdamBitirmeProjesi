package com.hakan.bitirme;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HospitalAutomationApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalAutomationApplication.class, args);
	}
	
	@Bean
	public CacheManager cacheManager() {

		SimpleCacheManager cM = new SimpleCacheManager();
		Cache kullaniciCache = new ConcurrentMapCache("patient_List");
		Cache kullanicilarCache = new ConcurrentMapCache("patients");
		
		cM.setCaches(Arrays.asList(kullanicilarCache,kullaniciCache));
		return cM;
	}

}

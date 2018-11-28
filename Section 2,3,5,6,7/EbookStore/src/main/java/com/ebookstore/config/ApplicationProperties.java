package com.ebookstore.config;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties("application")
@Validated
public class ApplicationProperties {

	@NotNull
	@Size(min = 1)
	private String uploaddir;

	public String getUploaddir() {
		return uploaddir;
	}

	public void setUploaddir(String uploaddir) {
		this.uploaddir = uploaddir;
	}
}

package com.dtr.oas.config;

import com.dtr.oas.bean.CodeAutoGeneratorPropertyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:app.properties")
public class AppConfig {
	@Autowired
	Environment env;

	@Bean
	public CodeAutoGeneratorPropertyBean codeAutoGeneratorPropertyBean() {
		CodeAutoGeneratorPropertyBean codeAutoGeneratorPropertyBean = new CodeAutoGeneratorPropertyBean();
		codeAutoGeneratorPropertyBean.setIndexHtmlPath(env.getProperty("cag.indexHtmlPath"));
		codeAutoGeneratorPropertyBean.setAppJsPath(env.getProperty("cag.appJsPath"));
		codeAutoGeneratorPropertyBean.setAppSrcPath(env.getProperty("cag.appSrcPath"));
		codeAutoGeneratorPropertyBean.setTemplateResourcesPath(env.getProperty("cag.templateResourcesPath"));
		return codeAutoGeneratorPropertyBean;
	}
}
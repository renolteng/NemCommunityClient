package org.nem.deploy;

import org.nem.core.deploy.*;
import org.nem.core.serialization.AccountLookup;
import org.nem.ncc.controller.interceptors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * Class supplying Spring MVC configuration.
 */
@Configuration
@ComponentScan(basePackages = { "org.nem.ncc.controller" })
//@EnableWebMvc // this cannot be present, when using WebMvcConfigurationSupport
public class NccWebAppInitializer extends WebMvcConfigurationSupport {
	@Autowired
	private AccountLookup accountLookup;

	@Autowired
	org.nem.ncc.model.Configuration configuration;

	private static void addConvertersForPolicy(
			final List<HttpMessageConverter<?>> converters,
			final SerializationPolicy policy) {
		converters.add(new DeserializerHttpMessageConverter(policy));
		converters.add(new SerializableEntityHttpMessageConverter(policy));
		converters.add(new DeserializableEntityMessageConverter(policy));
	}

	@Override
	protected void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {
		// NCC should only support JSON APIs
		addConvertersForPolicy(converters, new JsonSerializationPolicy(this.accountLookup));
		// addConvertersForPolicy(converters, new BinarySerializationPolicy(this.accountLookup));
		this.addDefaultHttpMessageConverters(converters);
	}

	@Override
	protected void addInterceptors(final InterceptorRegistry registry) {
		registry.addInterceptor(new AuditInterceptor());
		registry.addInterceptor(new LocalNisInterceptor(this.configuration));
		super.addInterceptors(registry);
	}
}

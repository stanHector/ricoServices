package service.ricotunes.giftcards.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import service.ricotunes.giftcards.logger.VisitorLogger;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Value("cors.allowedOrigins")
	private String allowedOrigins;

    private final VisitorLogger visitorLogger;

	public WebMvcConfig(VisitorLogger visitorLogger) {
		this.visitorLogger = visitorLogger;
	}

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(visitorLogger);
    }


	public void addCorsMappings(CorsRegistry registry) {
		final long MAX_AGE_SECS = 3600;

		registry.addMapping("/**")
				.allowedOrigins(allowedOrigins)
				.allowedMethods("GET", "POST", "PUT", "DELETE")
				.allowedHeaders("*")
				.maxAge(MAX_AGE_SECS);
	}
}

package com.example.security.springsecurityauthserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Value("${user.oauth.clientId}")
	private String ClientID;
	@Value("${user.oauth.clientSecret}")
	private String ClientSecret;
	@Value("${user.oauth.redirectUris}")
	private String RedirectURLs;

	private final PasswordEncoder passwordEncoder;

//	@Autowired
//	private AuthenticationManager authenticationManager;
	
	public AuthorizationServerConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

	// @Override
	// @Bean
	// public AuthenticationManager authenticationManagerBean() throws Exception {
	// return super.authenticationManagerBean();
	// }

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");

	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient(ClientID).secret(passwordEncoder.encode(ClientSecret)).authorizedGrantTypes("authorization_code")
				.scopes("user_info").autoApprove(true).redirectUris(RedirectURLs);
	}

//	@Override
//	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//
//		endpoints.authenticationManager(authenticationManager);
//	}

}

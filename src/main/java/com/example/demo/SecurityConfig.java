package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.example.demo.security.ConsumerAccessDeniedHandler;
import com.example.demo.security.ConsumerFailHandler;
import com.example.demo.security.ConsumerSuccessHandler;
import com.example.demo.security.LoginIdPwValidator;
import com.example.demo.seller.security.SellerLoginIdPwValidator;
import com.example.demo.seller.security.SellerSuccessHandler;


@EnableWebSecurity // 시큐리티 필터가 스프링 필터체인에 등록
public class SecurityConfig {
	
	@Order(2)
	@Configuration //설정파일 or Bean을 등록하기 위한 어노테이션
	public class ConsumerSecurityConfig extends WebSecurityConfigurerAdapter{
		
		@Autowired
		private LoginIdPwValidator logininfo;
		
		@Autowired
		private ConsumerSuccessHandler consumerSuccessHandler;
		
		@Autowired
		private ConsumerFailHandler consumerFailHandler;
		
		@Autowired
		private ConsumerAccessDeniedHandler consumerAccessDeniedHandler;
		
		public ConsumerSecurityConfig(LoginIdPwValidator loginIdPwValidator) {
			this.logininfo = loginIdPwValidator;
		}
		
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable();
			
			http.exceptionHandling().accessDeniedHandler(consumerAccessDeniedHandler)
			.and().formLogin().loginPage("/conusmer/login").loginProcessingUrl("/login").successHandler(consumerSuccessHandler).failureHandler(consumerFailHandler)
			.usernameParameter("cId")
			.passwordParameter("cPassword")
			.and().logout().logoutUrl("/consumer/logout").logoutSuccessUrl("/main/home").invalidateHttpSession(true);
			
		}
		
		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception{
			auth.userDetailsService(logininfo);
		}
		
	}
	
	
	@Order(1)
	@Configuration
	public class SellerSecurityConfig extends WebSecurityConfigurerAdapter{
		
		@Autowired
		private SellerLoginIdPwValidator sellerlogininfo;
			
		@Autowired
		private SellerSuccessHandler sellerSuccessHandler;
			
		public SellerSecurityConfig(SellerLoginIdPwValidator sellerloginIdPwValidator) {
			this.sellerlogininfo = sellerloginIdPwValidator;
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			http.authorizeRequests().antMatchers("/seller/**").permitAll();
			
			http.csrf().disable();
				
			http.requestMatchers().antMatchers("/seller/**").and().authorizeRequests().anyRequest().hasRole("SELLER")
			.and().formLogin().loginPage("/seller/seller/login").loginProcessingUrl("/seller/slogin").defaultSuccessUrl("/seller/seller/home").successHandler(sellerSuccessHandler)
			.usernameParameter("sId")
			.passwordParameter("sPassword")
			.and().logout().logoutUrl("/seller/logout").logoutSuccessUrl("/seller/seller/login").invalidateHttpSession(true);
				
			}
			
		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception{
			auth.userDetailsService(sellerlogininfo);
		}
			
	}
}

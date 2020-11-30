package com.ssooya.study1.config;


import com.ssooya.study1.domain.member.Member;
import com.ssooya.study1.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Configurable
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

	private final MemberRepository memberRepository;
	private final UserDetailsService userDetailsService;

	@Autowired
	private MessageSource messageSource;

	/* Password Encoder 등록 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/* 인증방식 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	/* Security 제외 패턴 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/templates/**");
		web.ignoring().antMatchers("/static/**");
	}


	/* 시큐어 패턴 등록 */
	// protected -> 모든 패키지에서 사용 가능하지 않도록 함
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable() // csrf를 사용하려면 뷰에서 csrf를 가져와야함ㄴㄴ

//				.csrf()
//				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//				.and()

				.authorizeRequests()

				// 페이지 권한 설정
				.antMatchers("/admin/**").hasRole("ADMIN")    // 관리자롤을 가진 회원만 접근 가능
				.antMatchers("/**").permitAll() // 모든 경로에 대해서 권한없이 접근 가능

				.and() // 로그인 설정
				.formLogin()
				.loginPage("/signin")    // 커스텀 로그인 page
				.loginProcessingUrl("/login") // form url
				.permitAll()
//				.failureHandler(failureHandler())
//				.successHandler(successHandler())
//				.defaultSuccessUrl("/loginSuccess") // 성공 핸들러를 따로 사용하기위해 주석처리


				.and() // 로그아웃 설정
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
				.and()

				// 403 예외처리 핸들링
				.exceptionHandling().accessDeniedPage("/user/denied");

	}
//
//	/**
//	 * 로그인 성공 핸들러
//	 *
//	 * @return 성공 시 메인페이지 리턴
//	 */
//	private AuthenticationSuccessHandler successHandler() {
//
//		return new AuthenticationSuccessHandler() {
//			@Override
//			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//
//				User user = (User) authentication.getPrincipal();
//				String userId = user.getUsername();
//
//				// View에서 username으로 넘겨야 함..!
//				Member member = memberRepository.findByUserId(userId)
//						.orElseThrow(() -> new UsernameNotFoundException(userId));
//
//
//				response.getWriter().print("success");
//
//				response.getWriter().flush();
//			}
//		};
//
//	}
//
//	/**
//	 * 로그인 실패 핸들러
//	 *
//	 * @return 실패시 에러메세지
//	 */
//	private AuthenticationFailureHandler failureHandler() {
//
//		return new AuthenticationFailureHandler() {
//			@Override
//			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//
//				logger.info(String.valueOf(exception));
//
//				if (exception.getMessage().equals("Bad credentials")) {
//					response.setCharacterEncoding("utf-8");
//					response.getWriter().print("아이디와 비밀번호를 확인해주세요.");
//					response.getWriter().flush();
//				}
//
//
//			}
//		};
//
//	}

}

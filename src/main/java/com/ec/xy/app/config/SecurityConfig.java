package com.ec.xy.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.xiuye.util.cls.XType;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		super.configure(http);
		http.formLogin()
		.and()
		.authorizeRequests()
		.anyRequest()
		.authenticated();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return XType.newInstance(BCryptPasswordEncoder::new);
	}
}

//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //下面这两行配置表示在内存中配置了两个用户
//        auth.inMemoryAuthentication()
//                .withUser("javaboy").roles("admin").password("$2a$10$OR3VSksVAmCzc.7WeaRPR.t0wyCsIj24k0Bne8iKWV1o.V9wsP8Xe")
//                .and()
//                .withUser("lisi").roles("user").password("$2a$10$p1H8iWa8I4.CA.7Z8bwLjes91ZpY.rYREGHQEInNtAp4NzL6PLKxi");
//    }
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}

//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    VerifyCodeFilter verifyCodeFilter;
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.addFilterBefore(verifyCodeFilter, UsernamePasswordAuthenticationFilter.class);
//        http
//        .authorizeRequests()//开启登录配置
//        .antMatchers("/hello").hasRole("admin")//表示访问 /hello 这个接口，需要具备 admin 这个角色
//        .anyRequest().authenticated()//表示剩余的其他接口，登录之后就能访问
//        .and()
//        .formLogin()
//        //定义登录页面，未登录时，访问一个需要登录之后才能访问的接口，会自动跳转到该页面
//        .loginPage("/login_p")
//        //登录处理接口
//        .loginProcessingUrl("/doLogin")
//        //定义登录时，用户名的 key，默认为 username
//        .usernameParameter("uname")
//        //定义登录时，用户密码的 key，默认为 password
//        .passwordParameter("passwd")
//        //登录成功的处理器
//        .successHandler(new AuthenticationSuccessHandler() {
//            @Override
//            public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
//                    resp.setContentType("application/json;charset=utf-8");
//                    PrintWriter out = resp.getWriter();
//                    out.write("success");
//                    out.flush();
//                }
//            })
//            .failureHandler(new AuthenticationFailureHandler() {
//                @Override
//                public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException exception) throws IOException, ServletException {
//                    resp.setContentType("application/json;charset=utf-8");
//                    PrintWriter out = resp.getWriter();
//                    out.write("fail");
//                    out.flush();
//                }
//            })
//            .permitAll()//和表单登录相关的接口统统都直接通过
//            .and()
//            .logout()
//            .logoutUrl("/logout")
//            .logoutSuccessHandler(new LogoutSuccessHandler() {
//                @Override
//                public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
//                    resp.setContentType("application/json;charset=utf-8");
//                    PrintWriter out = resp.getWriter();
//                    out.write("logout success");
//                    out.flush();
//                }
//            })
//            .permitAll()
//            .and()
//            .httpBasic()
//            .and()
//            .csrf().disable();
//    }
//}
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/vercode");
//    }
//}


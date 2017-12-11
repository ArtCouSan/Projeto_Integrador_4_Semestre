package br.senac.pi4.ProjetoIntegrador.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Autowired
    @Qualifier("userDetails")
    UserDetailsService userDetails;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetails).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().httpBasic().and()
                .authorizeRequests()
                .antMatchers(
                        "/js/**",
                        "/Bootstrap/**",
                        "/Font-Awesome/**",
                        "/css/**",
                        "/images/**",
                        "/IncornButtons/**",
                        "/**",
                        "/sessao/**",
                        "/cadastroC/**",
                        "/descricao/**",
                        "/novoCliente/**",
                        "/login-error/**",
                        "/sac",
                        "/sobreEmpresa",
                        "/sacAdd",
                        "/login").permitAll()
                .antMatchers("/admin/**").hasAuthority("JOSELITO")
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("senha")
                .failureUrl("/login-error").permitAll()
                .defaultSuccessUrl("/").permitAll()
                .and()
                .logout()
                .logoutUrl("/logout").logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID");
    }

}

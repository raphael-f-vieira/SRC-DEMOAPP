package com.example.demo.security.config;

import com.example.demo.security.authentication.UserAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserAuthenticationFilter userAuthenticationFilter;
    public static final String [] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            "/h2-console",
            "/h2-console/",
            "/h2-console/stylesheet.css",
            "/h2-console/login.jsp",
            "/h2-console/login.do",
            "/h2-console/background.gif",
            "/h2-console/help.jsp",
            "/h2-console/header.jsp",
            "/h2-console/query.do",
            "/h2-console/query.jsp",
            "/h2-console/icon_commit.gif",
            "/h2-console/icon_disconnect.gif",
            "/h2-console/icon_help.gif",
            "/h2-console/icon_history.gif",
            "/h2-console/icon_line.gif",
            "/h2-console/icon_rollback.gif",
            "/h2-console/icon_refresh.gif",
            "/h2-console/icon_run.gif",
            "/h2-console/icon_run_selected.gif",
            "/h2-console/icon_stop.gif",
            "/h2-console/table.js",
            "/h2-console/tables.do",
            "/h2-console/tree.js",
            "/h2-console/tree_column.gif",
            "/h2-console/tree_database.gif",
            "/h2-console/tree_empty.gif",
            "/h2-console/tree_folder.gif",
            "/h2-console/tree_index.gif",
            "/h2-console/tree_index_az.gif",
            "/h2-console/tree_info.gif",
            "/h2-console/tree_minus.gif",
            "/h2-console/tree_plus.gif",
            "/h2-console/tree_table.gif",
            "/h2-console/tree_type.gif",
            "/h2-console/tree_user.gif",
            "/h2-console/tree_users.gif",
            "/h2-console/tree_view.gif",
            "/favicon.ico",
            "/favicon.ico:1",
            "/users/login", //url que usaremos para fazer login
            "/users" //url que usaremos para criar um usuário
    };

    // Endpoints que requerem autenticação para serem acessados
    public static final String [] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {
            "/users/test",
            "/beneficiarios",
            "/documentos"
    };

    // Endpoints que só podem ser acessador por usuários com permissão de cliente
    public static final String [] ENDPOINTS_CUSTOMER = {
            "/users/test/customer"
    };

    // Endpoints que só podem ser acessador por usuários com permissão de administrador
    public static final String [] ENDPOINTS_ADMIN = {
            "/users/test/administrator"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable() // Desativa a proteção contra CSRF
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Configura a política de criação de sessão como stateless
                .and().authorizeHttpRequests() // Habilita a autorização para as requisições HTTP
                .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).authenticated()
                .requestMatchers(ENDPOINTS_ADMIN).hasRole("ADMINISTRATOR") // Repare que não é necessário colocar "ROLE" antes do nome, como fizemos na definição das roles
                .requestMatchers(ENDPOINTS_CUSTOMER).hasRole("CUSTOMER")
                .anyRequest().authenticated().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().headers().frameOptions().sameOrigin()
                // Adiciona o filtro de autenticação de usuário que criamos, antes do filtro de segurança padrão do Spring Security
                .and().addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
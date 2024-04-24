package com.techacademy;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


/**
 * ログイン認証クラス
 * - @Configuration:設定用クラス
 */
@Configuration
public class SecurityConfig {
    /** 認証・認可設定 */
    @Bean // DIコンテナの登録対象にするアノテーション
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        // ログイン(フォーム認証)に関する設定
        http.formLogin(login -> login
            .loginProcessingUrl("/login")    // ユーザー名・パスワードの送信先
            .loginPage("/login")             // ログイン画面
            .defaultSuccessUrl("/user/list") // ログイン成功後のリダイレクト先
            .failureUrl("/login?error")      // ログイン失敗時のリダイレクト先
            .permitAll()                     // ログイン画面は未ログインでアクセス可
        ).logout(logout -> logout // ログアウトに関する設定
            .logoutSuccessUrl("/login")      // ログアウト後のリダイレクト先
        ).authorizeHttpRequests(auth -> auth
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .permitAll()                 // css等は未ログインでアクセス可
            .anyRequest().authenticated()    // その他はログイン必要
        );

        return http.build();
    }

    /** ハッシュ化したパスワードの比較に使用する(「認可」の設定) */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
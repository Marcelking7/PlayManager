package bj.csam.playlist.PlayManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// ... (votre classe SecurityConfig avec @Configuration et @EnableWebSecurity)

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // =================================================================
    // Bean 1 : Chaîne de Filtres de Sécurité (le coeur de la configuration)
    // =================================================================
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(authorize -> authorize
                        // Autoriser l'accès aux ressources statiques (CSS, JS, images, etc.)
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        // Autoriser la console H2 en dev
                        .requestMatchers("/h2-console/**").permitAll()
                        // Toutes les autres requêtes nécessitent une authentification.
                        .anyRequest().authenticated()
                )
                // Laisse Spring Security générer le formulaire de connexion (URL /login)
                .formLogin(Customizer.withDefaults())

                // Laisse Spring Security gérer la déconnexion (URL /logout)
                .logout(Customizer.withDefaults());

        // Autoriser l'affichage en iframe pour H2 Console
        http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }

    // =================================================================
    // Bean 2 : Encodeur de Mot de Passe (OBLIGATOIRE)
    // =================================================================
    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt est l'encodeur recommandé par Spring Security.
        return new BCryptPasswordEncoder();
    }

    // =================================================================
    // Bean 3 : Gestionnaire d'Utilisateurs en Mémoire (Pour le démarrage rapide)
    // =================================================================
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        // Crée un utilisateur de test qui sera valide.
        UserDetails user = User.builder()
                .username("user") // Nom d'utilisateur
                .password(passwordEncoder.encode("password")) // Le mot de passe haché
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("adminpass"))
                .roles("USER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
package za.co.georg.svs.config;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import za.co.georg.svs.vaadin.views.LoginView;

/**
 * {@link SecurityConfiguration} class is responsible for configuring security settings for the application.
 * It extends {@link VaadinWebSecurity} and includes various security mechanisms such as authentication,
 * method security, and custom login behavior. The class leverages Spring Security to manage HTTP
 * security and user authentication within the application.
 * <p>
 * This configuration defines:
 * <li>HTTP security customization for the application.</li>
 * <li>An in-memory user details service for authentication with predefined users and roles.</li>
 * <li>A success handler to define redirection logic after successful authentication.</li>
 *
 * @author: Georg Nieuwoudt
 * @email: georg.nieu@gmail.com
 * @created: 2025/02/15 09:30
 */
@EnableWebSecurity
@EnableMethodSecurity(
        jsr250Enabled = true
)
@Configuration
public class SecurityConfiguration extends VaadinWebSecurity {

    /**
     * Configures {@link HttpSecurity} settings for the application.
     * <p>
     * This method customizes the security configurations for the application,
     * including setting up the login view mechanism. By overriding the parent
     * method, it allows additional security-related adjustments specific to
     * the application's requirements.
     *
     * @param http the {@link HttpSecurity} instance used to configure security settings
     *             for HTTP requests in the application
     * @throws Exception if an error occurs while configuring HttpSecurity
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);

        setLoginView(http, LoginView.class);
    }

    /**
     * Creates an in-memory {@link UserDetailsService} with predefined user credentials.
     * <p>
     * This method defines three users: "alice", "bob", and "admin". All users share the
     * same password ("password"), which is securely stored using bcrypt hashing. The following
     * roles are assigned:
     * <p>
     * <li>"alice": USER</li>
     * <li>"bob": USER</li>
     * <li>"admin": USER, ADMIN</li>
     * <p>
     * The returned {@link UserDetailsService} is suitable for applications that require an
     * in-memory authentication provider for simplicity or testing purposes.
     *
     * @return a {@link UserDetailsService} with the predefined in-memory users and their roles
     */
    @Bean
    public UserDetailsService users() {
        var alice = User.builder()
                .username("alice")
                // password = password with this hash, don't tell anybody :-)
                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
                .roles("USER")
                .build();
        var bob = User.builder()
                .username("bob")
                // password = password with this hash, don't tell anybody :-)
                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
                .roles("USER")
                .build();
        var admin = User.builder()
                .username("admin")
                // password = password with this hash, don't tell anybody :-)
                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
                .roles("ADMIN", "USER")
                .build();
        return new InMemoryUserDetailsManager(alice, bob, admin);
    }

    /**
     * Provides a custom {@link AuthenticationSuccessHandler} to define actions after a successful login.
     * This handler redirects the user to the dashboard view upon authentication success.
     *
     * @return an instance of {@link AuthenticationSuccessHandler} configured to redirect users
     * to the {@code "/dashboard"} URL after successful login
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        // Redirect user to the Dashboard after a successful login
        var successHandler = new SimpleUrlAuthenticationSuccessHandler();
        successHandler.setDefaultTargetUrl("/dashboard");
        return successHandler;
    }
}

package za.co.georg.svs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Spring Vaadin Security Application.
 * <p>
 * This application is configured as a Spring Boot application with the main class
 * annotated with {@code @SpringBootApplication}. It scans for components, configurations,
 * and services in the package "za.co.georg.svs" and its sub-packages.
 * <p>
 * The {@code main} method initializes and launches the Spring application using
 * {@link SpringApplication#run(Class, String...)}.
 */
@SpringBootApplication(
        scanBasePackages = {"za.co.georg.svs"}
)
public class SpringVaadinSecurityApplication {

    /**
     * The main entry point of the Spring Vaadin Security Application.
     * This method initializes and starts the Spring application.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringVaadinSecurityApplication.class, args);
    }
}

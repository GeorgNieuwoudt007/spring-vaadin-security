package za.co.georg.svs.vaadin.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import lombok.extern.slf4j.Slf4j;

/**
 * A view that provides a user login interface for the application.
 * <p>
 * This implementation uses the Vaadin Flow framework, offering a {@link LoginForm} component
 * to handle user authentication. It is primarily designed to accept user credentials,
 * manage errors during login, and facilitate the navigation flow based on authentication status.
 * <p>
 * The {@code LoginView} represents the login route of the application and is accessible
 * to all users, including those who are not authenticated.
 *
 * @author: Georg Nieuwoudt
 * @email: georg.nieu@gmail.com
 * @created: 2025/02/15 09:26
 */
@Route(
        value = "login"
)
@PageTitle("Login")
@AnonymousAllowed
@Slf4j
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    /**
     * Represents a login form used within the {@link LoginView}.
     * The {@code loginForm} provides a user interface for authentication,
     * including fields for entering credentials and managing errors during login.
     * It is initialized and configured to handle user login actions.
     */
    private final LoginForm loginForm;

    /**
     * Constructs the {@link LoginView}, which serves as the user login interface.
     * <p>
     * This constructor initializes the view's layout properties and adds components
     * that include:
     * <li>A heading labeled "Vaadin".</li>
     * <li>Informative text detailing the available credentials.</li>
     * <li>A {@link LoginForm} for handling user authentication.</li>
     * <p>
     * The layout is styled to occupy the full available space and aligns its content
     * centrally both vertically and horizontally. The {@link LoginForm} is configured
     * with an action URL of "login", to which authentication submissions are sent.
     */
    public LoginView() {
        loginForm = new LoginForm();

        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        loginForm.setAction("login");
        loginForm.addLoginListener(
                loginEvent -> {
                    getUI().ifPresent(ui -> ui.navigate("dashboard"));
                }
        );

        add(
                new H1("Vaadin"),
                new Div("You can log in as 'alice', 'bob' or 'admin'. The password for all of them is 'password'."),
                loginForm
        );
    }

    /**
     * Handles logic to be executed before entering the view.
     * Checks if the query parameters of the navigation location contain an "error" key,
     * and if present, sets an error state in the login form.
     *
     * @param event the event providing details about the navigation before entering the view
     */
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (event.getLocation().getQueryParameters().getParameters().containsKey("error")) {
            loginForm.setError(true);
        }
    }
}

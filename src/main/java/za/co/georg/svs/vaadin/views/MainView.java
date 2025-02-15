package za.co.georg.svs.vaadin.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

/**
 * Represents the main view of the application, serving as the default route.
 * This view is primarily responsible for handling redirection to the login page
 * for unauthorized or anonymous users.
 * <p>
 * The {@link MainView} utilizes the {@code Vaadin} Flow framework and implements
 * {@link BeforeEnterObserver} to handle pre-navigation logic.
 *
 * @author: Georg Nieuwoudt
 * @email: georg.nieu@gmail.com
 * @created: 2025/02/15 08:51
 */
@Route("") // The main route
@AnonymousAllowed
public class MainView extends Div implements BeforeEnterObserver {

    /**
     * Constructs the {@link MainView} component.
     * <p>
     * This constructor sets the text content of the view to indicate that the user
     * is being redirected to the login page. The view serves as the main route
     * of the application, typically handling redirections for unauthorized or
     * anonymous access.
     */
    public MainView() {
        setText("Redirecting to login...");
    }

    /**
     * Handles actions that need to occur before entering the current view.
     * This implementation ensures that the user is redirected to the login view.
     *
     * @param event the event providing information about the navigation
     *              before the view is entered
     */
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        // Redirect to login view
        event.forwardTo(LoginView.class);
    }
}

package za.co.georg.svs.vaadin.views;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import lombok.extern.slf4j.Slf4j;
import za.co.georg.svs.vaadin.layouts.MainLayout;

/**
 * Represents the dashboard view of the application.
 * <p>
 * This class extends the {@link VerticalLayout} and serves as the main user interface component
 * for the dashboard page. It is part of the application's routing system, with the URL path "dashboard".
 * The view is integrated into the main application layout through the {@link MainLayout} class
 * and is accessible to all authenticated users.
 * <p>
 * The page includes a simple welcome message to greet the user on accessing the dashboard.
 *
 * @author: Georg Nieuwoudt
 * @email: georg.nieu@gmail.com
 * @created: 2025/02/15 09:21
 */
@Route(
        value = "dashboard", layout = MainLayout.class
)
@PageTitle("Dashboard")
@PermitAll
//@RolesAllowed({"ADMIN", "USER"})
@Slf4j
public class DashboardView extends VerticalLayout {

    /**
     * Constructs the {@link DashboardView}, which serves as the user interface
     * for the dashboard page of the application.
     * <p>
     * The view displays a welcome message to the user and is part of the main
     * application layout. It is accessible to all authenticated users and
     * integrates seamlessly within the {@link MainLayout}.
     */
    public DashboardView() {
        var content = new H2("Welcome to the Dashboard!");
        add(content);
    }
}

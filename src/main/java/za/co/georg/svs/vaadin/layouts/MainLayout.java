package za.co.georg.svs.vaadin.layouts;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.theme.lumo.LumoUtility;

/**
 * Represents the main layout for the application, extending the {@link AppLayout}.
 * This layout is used as the primary structure of the application, providing components
 * such as a header, a navigation drawer, and a side navigation bar.
 * <p>
 * The {@link MainLayout} integrates different sections of the application into
 * a consistent and cohesive user interface, including:
 * <li>A header section with the application title and user-related options.</li>
 * <li>A drawer navigation toggle for showing or hiding the navigation drawer.</li>
 * <li>A configurable side navigation bar for accessing various areas of the application.</li>
 *
 * @author: Georg Nieuwoudt
 * @email: georg.nieu@gmail.com
 * @created: 2025/02/15 08:56
 */
public class MainLayout extends AppLayout {

    /**
     * Creates an instance of the {@link MainLayout} class, which serves as the primary layout
     * for the application. This layout includes a header, navigation drawer toggle, and
     * a side navigation bar.
     * <p>
     * The method initializes the following components:
     * <li>A header containing the application title and a user menu.</li>
     * <li>A drawer toggle to show or hide the drawer.</li>
     * <li>A side navigation bar for accessing different sections of the application.</li>
     * <p>
     * The side navigation bar is wrapped in a scroller with padding for better usability.
     * These components are added to the drawer and navigation bar.
     */
    public MainLayout() {
        var header = createHeader();
        var toggle = createDrawer();
        var navigation = createSideNavigationBar();

        var scroller = new Scroller(navigation);
        scroller.setClassName(LumoUtility.Padding.MEDIUM);

        addToDrawer(scroller);
        addToNavbar(toggle, header);
    }

    /**
     * Creates and returns a configured side navigation bar for the application.
     * The navigation bar provides links to different sections of the application,
     * starting with an item for the "Dashboard".
     *
     * @return a {@link SideNav} instance representing the application's side navigation bar
     */
    private SideNav createSideNavigationBar() {
        var sideNav = new SideNav();
        sideNav.addItem(
                new SideNavItem(
                        "Dashboard", "/dashboard", VaadinIcon.DASHBOARD.create()
                )
        );

        return sideNav;
    }

    /**
     * Creates and returns the header layout of the application. The header includes
     * a logo, a spacer for alignment, and a dropdown menu. It is styled with specific
     * class names and spans the full width of the application.
     *
     * @return a configured {@link HorizontalLayout} instance representing the application header
     */
    private HorizontalLayout createHeader() {
        var logo = new H1("Spring Boot, Vaadin, and Spring Security");
        logo.addClassNames("text-l", "m-m");

        var logoutButton = new Button("Log out", logout -> logout());
        logoutButton.setDisableOnClick(true);

        var spacer = new Span();
        spacer.getStyle().set("flex-grow", "1");

        var header = new HorizontalLayout(
                logo,
                spacer,
                createDropDownMenu()
        );
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");

        return header;
    }

    /**
     * Creates and returns a dropdown menu bar containing a settings menu with a "Logout" option.
     * The menu bar has dropdown indicators enabled for better visual feedback.
     *
     * @return a configured {@link MenuBar} instance with a settings menu and its submenu items
     */
    private MenuBar createDropDownMenu() {
        var dropDownMenuBar = new MenuBar();
        dropDownMenuBar.addThemeVariants(MenuBarVariant.LUMO_DROPDOWN_INDICATORS);

        // Add user profile dropdown
        // You can replace "Settings" with an icon or avatar
        var settingsMenuItem = dropDownMenuBar.addItem(VaadinIcon.COG.create(), "Settings");

        // Add submenu items
        settingsMenuItem.getSubMenu().addItem("Logout", event -> logout());

        return dropDownMenuBar;
    }

    /**
     * Creates and returns a new instance of {@link DrawerToggle}, which is used as the toggle
     * element for opening and closing the application drawer.
     *
     * @return a new instance of DrawerToggle
     */
    private DrawerToggle createDrawer() {
        return new DrawerToggle();
    }

    /**
     * Logs the user out of the application and redirects them to the root page.
     * This method updates the current UI's page location to "/", effectively
     * ending the user's session and returning them to the main entry point of the application.
     */
    public void logout() {
        UI.getCurrent().getPage().setLocation("/");
    }
}

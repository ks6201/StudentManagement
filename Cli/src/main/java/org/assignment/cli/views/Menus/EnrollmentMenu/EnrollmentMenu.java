package org.assignment.cli.views.Menus.EnrollmentMenu;

import org.assignment.cli.libs.menu.IMenu;
import org.assignment.cli.libs.menu.ListingMenuNode;
import org.assignment.cli.libs.menu.MenuNode;

public class EnrollmentMenu implements IMenu {

    @Override
    public MenuNode getMenu() {
        var root = new ListingMenuNode("Manage Enrollments");

        root.addSubMenu(new EnrollStudent().getMenu());
        root.addSubMenu(new RemoveEnrollment().getMenu());
        root.addSubMenu(new ViewEnrollment().getMenu());
        root.addSubMenu(new AssignOrUpdateGrade().getMenu());
        root.addSubMenu(new ViewEnrollmentsByCourse().getMenu());
        root.addSubMenu(new ViewEnrollmentsByStudent().getMenu());

        return root;
    }
}


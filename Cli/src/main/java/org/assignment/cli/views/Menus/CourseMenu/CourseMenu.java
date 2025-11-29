package org.assignment.cli.views.Menus.CourseMenu;

import org.assignment.cli.libs.menu.IMenu;
import org.assignment.cli.libs.menu.MenuNode;
import org.assignment.cli.libs.menu.ListingMenuNode;

public class CourseMenu implements IMenu {

    @Override
    public MenuNode getMenu() {
        ListingMenuNode courseSubMenu = new ListingMenuNode("Manage Courses");

        AddCourse addCourse = new AddCourse();
        courseSubMenu.addSubMenu(addCourse.getMenu());

        UpdateCourse updateCourse = new UpdateCourse();
        courseSubMenu.addSubMenu(updateCourse.getMenu());

        RemoveCourse removeCourse = new RemoveCourse();
        courseSubMenu.addSubMenu(removeCourse.getMenu());

        ViewCourse viewCourse = new ViewCourse();
        courseSubMenu.addSubMenu(viewCourse.getMenu());

        return courseSubMenu;
    }
}

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private double rating;
    private List<MenuItem> menu;

    public Restaurant(String name, double rating) {
        this.name = name;
        this.rating = rating;
        this.menu = new ArrayList<>();
        initMenu();
    }

    private void initMenu() {
        menu.add(new MenuItem(1, "Cheeseburger", 12.50, "Food"));
        menu.add(new MenuItem(2, "Pepperoni Pizza", 15.00, "Food"));
        menu.add(new MenuItem(3, "Caesar Salad", 9.00, "Food"));
        menu.add(new MenuItem(4, "Coke", 2.50, "Drink"));
        menu.add(new MenuItem(5, "Iced Coffee", 4.00, "Drink"));
        menu.add(new MenuItem(6, "Chocolate Cake", 6.50, "Dessert"));
    }

    public void displayMenu() {
        System.out.println("\n--- " + name + " Menu (Rating: " + rating + "/5.0) ---");
        for (MenuItem item : menu) {
            System.out.println(item);
        }
    }

    public MenuItem getItemById(int id) {
        for (MenuItem item : menu) {
            if (item.getId() == id) return item;
        }
        return null;
    }
}
import java.util.ArrayList;
import java.util.List;

class Restaurant {
    private String name;
    private double rating;
    private List<MenuItem> menu;
    public Restaurant(String name, double rating) {
        this.name = name;
        this.rating = rating;
        this.menu = new ArrayList<>();
    }
    public void addMenuItem(MenuItem item) {
        menu.add(item);
    }
    public void displayMenu() {
        System.out.println("\n--- " + name + " Menu (Rating: " + rating + "/5.0) ---");
        if (menu.isEmpty()) {
            System.out.println("No items available in the menu.");
        } else {
            for (MenuItem item : menu) {
                System.out.println(item);
            }
        }
    }
    public MenuItem getItemById(int id) {
        for (MenuItem item : menu) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
    public void addRating(int customerRating) {
        if (customerRating >= 1 && customerRating <= 5) {
            this.rating = (this.rating + customerRating) / 2.0;
            System.out.println("Thank you for your feedback! New Restaurant Rating: " + this.rating);
        } else {
            System.out.println("Invalid rating. Please enter a value between 1 and 5.");
        }
    }
}
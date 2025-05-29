import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

// Main application class
public class CarRentalGUI extends Application {
    private AuthenticationService authService;
    private RentalSystem rentalSystem;
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.authService = new AuthenticationService();
        this.rentalSystem = new RentalSystem();
        setupTestData();

        showWelcomeScreen();
        primaryStage.setTitle("Car Rental System");
        primaryStage.show();
    }

    private void setupTestData() {
        // Add sample vehicles with images
        Car car1 = new Car("ABC123", "Toyota", "Camry", 2022, 50.0, "Sedan", 5, true);
        car1.setImagePath("/images/camry.jpg"); 

        SUV suv1 = new SUV("XYZ789", "Ford", "Explorer", 2021, 70.0, true, 7);
        suv1.setImagePath("/images/explorer.jpg");

        rentalSystem.addVehicle(car1);
        rentalSystem.addVehicle(suv1);

        // Add sample customer
        rentalSystem.addCustomer(new Customer("CUST001", "John Doe", "555-1234", "john@example.com", "DL12345678"));
    }

    private void showWelcomeScreen() {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #f5f5f5;");

        Label title = new Label("Welcome to Car Rental");
        title.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");

        Button loginBtn = createStyledButton("Login");
        Button signupBtn = createStyledButton("Sign Up");

        loginBtn.setOnAction(e -> showLoginScreen());
        signupBtn.setOnAction(e -> showSignupScreen());

        layout.getChildren().addAll(title, loginBtn, signupBtn);
        primaryStage.setScene(new Scene(layout, 600, 400));
    }

    private void showLoginScreen() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25));
        grid.setStyle("-fx-background-color: #f5f5f5;");

        Label title = new Label("Login");
        title.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
        grid.add(title, 0, 0, 2, 1);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        grid.add(usernameLabel, 0, 1);
        grid.add(usernameField, 1, 1);

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        grid.add(passwordLabel, 0, 2);
        grid.add(passwordField, 1, 2);

        Button loginBtn = createStyledButton("Login");
        Button backBtn = createStyledButton("Back");
        HBox buttonBox = new HBox(10, loginBtn, backBtn);
        buttonBox.setAlignment(Pos.CENTER);
        grid.add(buttonBox, 0, 3, 2, 1);

        Label messageLabel = new Label();
        grid.add(messageLabel, 0, 4, 2, 1);

        loginBtn.setOnAction(e -> {
            if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                messageLabel.setText("Please enter username and password");
                messageLabel.setStyle("-fx-text-fill: red;");
                return;
            }
            if (authService.login(usernameField.getText(), passwordField.getText())) {
                showMainMenu();
            } else {
                messageLabel.setText("Invalid username or password");
                messageLabel.setStyle("-fx-text-fill: red;");
            }
        });

        backBtn.setOnAction(e -> showWelcomeScreen());

        primaryStage.setScene(new Scene(grid, 600, 400));
    }

    private void showSignupScreen() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25));
        grid.setStyle("-fx-background-color: #f5f5f5;");

        Label title = new Label("Sign Up");
        title.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
        grid.add(title, 0, 0, 2, 1);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        grid.add(usernameLabel, 0, 1);
        grid.add(usernameField, 1, 1);

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        grid.add(passwordLabel, 0, 2);
        grid.add(passwordField, 1, 2);

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        grid.add(emailLabel, 0, 3);
        grid.add(emailField, 1, 3);

        Button signupBtn = createStyledButton("Sign Up");
        Button backBtn = createStyledButton("Back");
        HBox buttonBox = new HBox(10, signupBtn, backBtn);
        buttonBox.setAlignment(Pos.CENTER);
        grid.add(buttonBox, 0, 4, 2, 1);

        Label messageLabel = new Label();
        grid.add(messageLabel, 0, 5, 2, 1);

        signupBtn.setOnAction(e -> {
            if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty() || emailField.getText().isEmpty()) {
                messageLabel.setText("Please fill all fields");
                messageLabel.setStyle("-fx-text-fill: red;");
                return;
            }
            if (authService.signUp(usernameField.getText(), passwordField.getText(), emailField.getText())) {
                messageLabel.setText("Account created successfully!");
                messageLabel.setStyle("-fx-text-fill: green;");
            } else {
                messageLabel.setText("Username already exists");
                messageLabel.setStyle("-fx-text-fill: red;");
            }
        });

        backBtn.setOnAction(e -> showWelcomeScreen());

        primaryStage.setScene(new Scene(grid, 600, 400));
    }

    private void showMainMenu() {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #f5f5f5;");

        Label title = new Label("Main Menu");
        title.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");

        Button inventoryBtn = createStyledButton("View Inventory");
        Button rentedCarsBtn = createStyledButton("My Rented Cars");
        Button logoutBtn = createStyledButton("Logout");

        inventoryBtn.setOnAction(e -> showInventory());
        rentedCarsBtn.setOnAction(e -> showRentedCars());
        logoutBtn.setOnAction(e -> {
            authService.logout();
            showWelcomeScreen();
        });

        layout.getChildren().addAll(title, inventoryBtn, rentedCarsBtn, logoutBtn);
        primaryStage.setScene(new Scene(layout, 800, 600));
    }

    private void showInventory() {
        ScrollPane scrollPane = new ScrollPane();
        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(15));
        flowPane.setHgap(15);
        flowPane.setVgap(15);

        for (Vehicle vehicle : rentalSystem.getAvailableVehicles()) {
            VBox card = createVehicleCard(vehicle, true);
            flowPane.getChildren().add(card);
        }

        scrollPane.setContent(flowPane);
        scrollPane.setFitToWidth(true);

        Button backBtn = createStyledButton("Back to Menu");
        backBtn.setOnAction(e -> showMainMenu());

        VBox layout = new VBox(10, backBtn, scrollPane);
        layout.setPadding(new Insets(10));

        primaryStage.setScene(new Scene(layout, 800, 600));
    }

    private void showRentedCars() {
        ScrollPane scrollPane = new ScrollPane();
        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(15));
        flowPane.setHgap(15);
        flowPane.setVgap(15);

        User currentUser = authService.getCurrentUser();
        if (currentUser == null) {
            showAlert("Error", "No user logged in");
            showWelcomeScreen();
            return;
        }

        Customer customer = rentalSystem.findCustomer(currentUser.getUserId());
        if (customer != null) {
            for (Rental rental : rentalSystem.getActiveRentalsForCustomer(customer.getCustomerId())) {
                VBox card = createVehicleCard(rental.getVehicle(), false);
                flowPane.getChildren().add(card);
            }
        } else {
            showAlert("Info", "No rented cars found");
        }

        scrollPane.setContent(flowPane);
        scrollPane.setFitToWidth(true);

        Button backBtn = createStyledButton("Back to Menu");
        backBtn.setOnAction(e -> showMainMenu());

        VBox layout = new VBox(10, backBtn, scrollPane);
        layout.setPadding(new Insets(10));

        primaryStage.setScene(new Scene(layout, 800, 600));
    }

    private VBox createVehicleCard(Vehicle vehicle, boolean showRentButton) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(15));
        card.setStyle("-fx-background-color: white; -fx-border-color: #ddd; -fx-border-width: 1; -fx-border-radius: 5;");
        card.setPrefWidth(250);

        // Image handling
        ImageView imageView = new ImageView();
        try {
            String imagePath = vehicle.getImagePath();
            // Use resource loading for proper packaging
            Image image = new Image(getClass().getResourceAsStream(imagePath != null ? imagePath : "/images/placeholder.jpg"));
            imageView.setImage(image);
        } catch (Exception e) {
            imageView.setImage(new Image(getClass().getResourceAsStream("/images/placeholder.jpg")));
        }
        imageView.setFitWidth(220);
        imageView.setFitHeight(150);
        imageView.setPreserveRatio(true);

        Label modelLabel = new Label(vehicle.getBrand() + " " + vehicle.getModel());
        modelLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        Label yearLabel = new Label("Year: " + vehicle.getYear());
        Label priceLabel = new Label("$" + vehicle.getBaseRatePerDay() + "/day");
        priceLabel.setStyle("-fx-font-weight: bold;");

        Button actionBtn = createStyledButton(showRentButton ? "Rent This Car" : "Return This Car");
        actionBtn.setOnAction(e -> {
            if (showRentButton) {
                showRentForm(vehicle);
            } else {
                returnVehicle(vehicle);
            }
        });

        Button detailsBtn = createStyledButton("View Details");
        detailsBtn.setOnAction(e -> showVehicleDetails(vehicle));

        HBox buttonBox = new HBox(10, detailsBtn, actionBtn);
        buttonBox.setAlignment(Pos.CENTER);

        card.getChildren().addAll(imageView, modelLabel, yearLabel, priceLabel, buttonBox);
        return card;
    }

    private void showVehicleDetails(Vehicle vehicle) {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #f5f5f5;");

        // Image
        ImageView imageView = new ImageView();
        try {
            String imagePath = vehicle.getImagePath();
            imageView.setImage(new Image(getClass().getResourceAsStream(imagePath != null ? imagePath : "/images/placeholder.jpg")));
        } catch (Exception e) {
            imageView.setImage(new Image(getClass().getResourceAsStream("/images/placeholder.jpg")));
        }
        imageView.setFitWidth(400);
        imageView.setPreserveRatio(true);

        VBox details = new VBox(10);
        details.setPadding(new Insets(20));
        details.setStyle("-fx-background-color: white; -fx-border-color: #ddd; -fx-border-width: 1;");

        Label title = new Label(vehicle.getBrand() + " " + vehicle.getModel());
        title.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        Label year = new Label("Year: " + vehicle.getYear());
        Label price = new Label("Daily Rate: $" + vehicle.getBaseRatePerDay());
        price.setStyle("-fx-font-weight: bold;");

        // Vehicle-specific details
        VBox specBox = new VBox(5);
        if (vehicle instanceof Car) {
            Car car = (Car) vehicle;
            specBox.getChildren().addAll(
                    new Label("Type: " + car.getCarType()),
                    new Label("Seating: " + car.getSeatingCapacity()),
                    new Label("AC: " + (car.hasAC() ? "Yes" : "No"))
            );
        } else if (vehicle instanceof SUV) {
            SUV suv = (SUV) vehicle;
            specBox.getChildren().addAll(
                    new Label("Type: SUV"),
                    new Label("Seating: " + suv.getSeatingCapacity()),
                    new Label("4WD: " + (suv.is4WheelDrive() ? "Yes" : "No"))
            );
        }

        Button backBtn = createStyledButton("Back");
        backBtn.setOnAction(e -> showInventory());

        Button rentBtn = createStyledButton("Rent This Car");
        rentBtn.setOnAction(e -> showRentForm(vehicle));

        HBox buttonBox = new HBox(20, backBtn, rentBtn);
        buttonBox.setAlignment(Pos.CENTER);

        details.getChildren().addAll(title, year, price, specBox);
        layout.getChildren().addAll(imageView, details, buttonBox);

        primaryStage.setScene(new Scene(layout, 800, 600));
    }

    private void showRentForm(Vehicle vehicle) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25));
        grid.setStyle("-fx-background-color: #f5f5f5;");

        Label title = new Label("Rent " + vehicle.getBrand() + " " + vehicle.getModel());
        title.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
        grid.add(title, 0, 0, 2, 1);

        // Track grid row for dynamic field addition
        int row = 1;
        TextField nameField = null, phoneField = null, licenseField = null;

        // Only show profile fields if customer profile not completed
        User currentUser = authService.getCurrentUser();
        Customer customer = currentUser != null ? rentalSystem.findCustomer(currentUser.getUserId()) : null;
        if (customer == null) {
            Label nameLabel = new Label("Full Name:");
            nameField = new TextField();
            grid.add(nameLabel, 0, row);
            grid.add(nameField, 1, row++);

            Label phoneLabel = new Label("Phone Number:");
            phoneField = new TextField();
            grid.add(phoneLabel, 0, row);
            grid.add(phoneField, 1, row++);

            Label licenseLabel = new Label("Driving License:");
            licenseField = new TextField();
            grid.add(licenseLabel, 0, row);
            grid.add(licenseField, 1, row++);
        }

        Label daysLabel = new Label("Rental Days:");
        Spinner<Integer> daysSpinner = new Spinner<>(1, 30, 1);
        grid.add(daysLabel, 0, row);
        grid.add(daysSpinner, 1, row++);

        Button confirmBtn = createStyledButton("Confirm Rental");
        Button cancelBtn = createStyledButton("Cancel");
        HBox buttonBox = new HBox(10, confirmBtn, cancelBtn);
        buttonBox.setAlignment(Pos.CENTER);
        grid.add(buttonBox, 0, row++, 2, 1);

        Label messageLabel = new Label();
        grid.add(messageLabel, 0, row, 2, 1);

        // Store fields for lambda access
        final TextField finalNameField = nameField;
        final TextField finalPhoneField = phoneField;
        final TextField finalLicenseField = licenseField;

        confirmBtn.setOnAction(e -> {
            int days = daysSpinner.getValue();
            Customer currentCustomer = customer;

            // Validate and create customer profile if needed
            if (currentCustomer == null) {
                if (finalNameField.getText().isEmpty() || finalPhoneField.getText().isEmpty() || finalLicenseField.getText().isEmpty()) {
                    messageLabel.setText("Please fill all fields");
                    messageLabel.setStyle("-fx-text-fill: red;");
                    return;
                }
                currentCustomer = new Customer(
                        authService.getCurrentUser().getUserId(),
                        finalNameField.getText(),
                        finalPhoneField.getText(),
                        authService.getCurrentUser().getEmail(),
                        finalLicenseField.getText()
                );
                rentalSystem.addCustomer(currentCustomer);
            }

            String rentalId = "RENT-" + System.currentTimeMillis();
            Rental rental = rentalSystem.createRental(
                    rentalId,
                    currentCustomer.getCustomerId(),
                    vehicle.getRegistrationNumber(),
                    days
            );

            if (rental != null) {
                messageLabel.setText("Rental confirmed! Total: $" + rental.getTotalCost());
                messageLabel.setStyle("-fx-text-fill: green;");
                // Return to main menu after 2 seconds
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                javafx.application.Platform.runLater(() -> showMainMenu());
                            }
                        },
                        2000
                );
            } else {
                messageLabel.setText("Failed to create rental");
                messageLabel.setStyle("-fx-text-fill: red;");
            }
        });

        cancelBtn.setOnAction(e -> showVehicleDetails(vehicle));

        primaryStage.setScene(new Scene(grid, 600, 400));
    }

    private void returnVehicle(Vehicle vehicle) {
        User currentUser = authService.getCurrentUser();
        if (currentUser == null) {
            showAlert("Error", "No user logged in");
            return;
        }

        Customer customer = rentalSystem.findCustomer(currentUser.getUserId());
        if (customer != null) {
            for (Rental rental : rentalSystem.getActiveRentalsForCustomer(customer.getCustomerId())) {
                if (rental.getVehicle().getRegistrationNumber().equals(vehicle.getRegistrationNumber())) {
                    if (rentalSystem.completeRental(rental.getRentalId())) {
                        showAlert("Success", "Vehicle returned successfully!");
                        showRentedCars();
                        return;
                    }
                }
            }
        }
        showAlert("Error", "Failed to return vehicle");
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #4a90e2; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 5;");
        button.setPadding(new Insets(10, 20, 10, 20));
        return button;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Placeholder classes for compilation
    static class User {
        private final String userId;
        private final String email;

        User(String userId, String email) {
            this.userId = userId;
            this.email = email;
        }

        String getUserId() {
            return userId;
        }

        String getEmail() {
            return email;
        }
    }

    static class AuthenticationService {
        private User currentUser;
        private final List<User> users = new ArrayList<>();

        boolean login(String username, String password) {
            // Simple authentication logic
            for (User user : users) {
                if (user.getUserId().equals(username)) {
                    currentUser = user;
                    return true;
                }
            }
            return false;
        }

        boolean signUp(String username, String password, String email) {
            for (User user : users) {
                if (user.getUserId().equals(username)) {
                    return false;
                }
            }
            users.add(new User(username, email));
            return true;
        }

        void logout() {
            currentUser = null;
        }

        User getCurrentUser() {
            return currentUser;
        }
    }

    static class Vehicle {
        private final String registrationNumber;
        private final String brand;
        private final String model;
        private final int year;
        private final double baseRatePerDay;
        private String imagePath;
        private boolean available;

        Vehicle(String registrationNumber, String brand, String model, int year, double baseRatePerDay) {
            this.registrationNumber = registrationNumber;
            this.brand = brand;
            this.model = model;
            this.year = year;
            this.baseRatePerDay = baseRatePerDay;
            this.available = true;
        }

        String getRegistrationNumber() {
            return registrationNumber;
        }

        String getBrand() {
            return brand;
        }

        String getModel() {
            return model;
        }

        int getYear() {
            return year;
        }

        double getBaseRatePerDay() {
            return baseRatePerDay;
        }

        String getImagePath() {
            return imagePath;
        }

        void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        boolean isAvailable() {
            return available;
        }

        void setAvailable(boolean available) {
            this.available = available;
        }
    }

    static class Car extends Vehicle {
        private final String carType;
        private final int seatingCapacity;
        private final boolean hasAC;

        Car(String registrationNumber, String brand, String model, int year, double baseRatePerDay,
            String carType, int seatingCapacity, boolean hasAC) {
            super(registrationNumber, brand, model, year, baseRatePerDay);
            this.carType = carType;
            this.seatingCapacity = seatingCapacity;
            this.hasAC = hasAC;
        }

        String getCarType() {
            return carType;
        }

        int getSeatingCapacity() {
            return seatingCapacity;
        }

        boolean hasAC() {
            return hasAC;
        }
    }

    static class SUV extends Vehicle {
        private final boolean is4WheelDrive;
        private final int seatingCapacity;

        SUV(String registrationNumber, String brand, String model, int year, double baseRatePerDay,
            boolean is4WheelDrive, int seatingCapacity) {
            super(registrationNumber, brand, model, year, baseRatePerDay);
            this.is4WheelDrive = is4WheelDrive;
            this.seatingCapacity = seatingCapacity;
        }

        boolean is4WheelDrive() {
            return is4WheelDrive;
        }

        int getSeatingCapacity() {
            return seatingCapacity;
        }
    }

    static class Customer {
        private final String customerId;
        private final String name;
        private final String phone;
        private final String email;
        private final String licenseNumber;

        Customer(String customerId, String name, String phone, String email, String licenseNumber) {
            this.customerId = customerId;
            this.name = name;
            this.phone = phone;
            this.email = email;
            this.licenseNumber = licenseNumber;
        }

        String getCustomerId() {
            return customerId;
        }
    }

    static class Rental {
        private final String rentalId;
        private final String customerId;
        private final Vehicle vehicle;
        private final int days;
        private final double totalCost;

        Rental(String rentalId, String customerId, Vehicle vehicle, int days) {
            this.rentalId = rentalId;
            this.customerId = customerId;
            this.vehicle = vehicle;
            this.days = days;
            this.totalCost = vehicle.getBaseRatePerDay() * days;
        }

        String getRentalId() {
            return rentalId;
        }

        String getCustomerId() {
            return customerId;
        }

        Vehicle getVehicle() {
            return vehicle;
        }

        double getTotalCost() {
            return totalCost;
        }
    }

    static class RentalSystem {
        private final List<Vehicle> vehicles = new ArrayList<>();
        private final List<Customer> customers = new ArrayList<>();
        private final List<Rental> rentals = new ArrayList<>();

        void addVehicle(Vehicle vehicle) {
            vehicles.add(vehicle);
        }

        void addCustomer(Customer customer) {
            customers.add(customer);
        }

        List<Vehicle> getAvailableVehicles() {
            List<Vehicle> available = new ArrayList<>();
            for (Vehicle vehicle : vehicles) {
                if (vehicle.isAvailable()) {
                    available.add(vehicle);
                }
            }
            return available;
        }

        Customer findCustomer(String customerId) {
            for (Customer customer : customers) {
                if (customer.getCustomerId().equals(customerId)) {
                    return customer;
                }
            }
            return null;
        }

        Rental createRental(String rentalId, String customerId, String registrationNumber, int days) {
            Vehicle vehicle = null;
            for (Vehicle v : vehicles) {
                if (v.getRegistrationNumber().equals(registrationNumber) && v.isAvailable()) {
                    vehicle = v;
                    break;
                }
            }
            if (vehicle == null) {
                return null;
            }
            vehicle.setAvailable(false);
            Rental rental = new Rental(rentalId, customerId, vehicle, days);
            rentals.add(rental);
            return rental;
        }

        List<Rental> getActiveRentalsForCustomer(String customerId) {
            List<Rental> customerRentals = new ArrayList<>();
            for (Rental rental : rentals) {
                if (rental.getCustomerId().equals(customerId)) {
                    customerRentals.add(rental);
                }
            }
            return customerRentals;
        }

        boolean completeRental(String rentalId) {
            for (Rental rental : rentals) {
                if (rental.getRentalId().equals(rentalId)) {
                    rental.getVehicle().setAvailable(true);
                    rentals.remove(rental);
                    return true;
                }
            }
            return false;
        }
    }
}
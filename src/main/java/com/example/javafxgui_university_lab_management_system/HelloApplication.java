package com.example.javafxgui_university_lab_management_system;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class HelloApplication extends Application {

    private Stage primaryStage;
    private static final String FILE_PATH = "login_data.txt";
    ArrayList<Employee> employeeArrayList = new ArrayList<>();
    ArrayList<Lab> labArrayList = new ArrayList<>();

    FileMethods methods = new FileMethods();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setScene(loginPage());
    }
    private Scene loginPage(){
        primaryStage.setTitle("Login Form");

        // Create the username and password fields
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        // Create the login button
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> primaryStage.setScene(createMenuPage()));
        // Create a grid pane and add the components
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);


        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(usernameField, 1, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(loginButton, 1, 2);

        Scene scene = new Scene(gridPane, 300, 150);
//        primaryStage.setScene(scene);
        primaryStage.show();
        return scene;
    }
    private Scene createMenuPage() {
        Label label = new Label("CLICK ON ANY BUTTON");
        Button addEmployeeButton = new Button("Add Employee");
        Button addLabButton = new Button("Add Lab");
        Button addcomputerButton = new Button("Add Computer");
        Button searchEmployeeButton = new Button("Search Employee");
        Button searchLabButton = new Button("Search Lab");
        Button searchComputerButton = new Button("Search Computer");
        addEmployeeButton.setOnAction(actionEvent -> addEmployeeScene());
        addLabButton.setOnAction(actionEvent -> addNewLabScene());
        addcomputerButton.setOnAction(actionEvent -> addNewComputerScene());
        searchEmployeeButton.setOnAction(actionEvent -> searchEmployeeScene());
        searchLabButton.setOnAction(actionEvent -> searchLabScene());
        searchComputerButton.setOnAction(actionEvent -> searchComputerScene());
        Button backButton = new Button("Back");
        backButton.setOnAction(actionEvent -> {
            primaryStage.setScene(loginPage());
        });
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.add(label, 0, 0);
        gridPane.add(addEmployeeButton, 0, 1);
        gridPane.add(addLabButton, 1, 1);
        gridPane.add(addcomputerButton, 0, 2);
        gridPane.add(searchEmployeeButton, 1, 2);
        gridPane.add(searchLabButton, 0, 3);
        gridPane.add(searchComputerButton, 1, 3);
        gridPane.add(backButton,0,4);

        Scene scene = new Scene(gridPane, 300, 200);
        primaryStage.setTitle("Menu Page");
        return scene;
    }

    String name;
    String grade;
    private Scene addEmployeeScene() {
        primaryStage.setTitle("Add Employee");
        System.out.println("Add Employee");
        Label listLabel = new Label("EMPLOYEE");
        Label nameLabel = new Label("Name");
        TextField nameField = new TextField();
        Label idLabel = new Label("Employee ID: ");
        TextField idField = new TextField();
        Label gradeLabel = new Label("Grade");
        TextField gradeField = new TextField();
        Button saveButton = new Button("Save");
        ComboBox<String> dropdownMenu = new ComboBox<>();
        dropdownMenu.getItems().addAll("HOD", "Lab InCharge", "Director", "Lab Staff");
        dropdownMenu.setPromptText("Select an option");
        Button backButton = new Button("Back");
        backButton.setOnAction(actionEvent -> primaryStage.setScene(createMenuPage()));

        GridPane employeeGrid = new GridPane();
        employeeGrid.setPadding(new Insets(10));
        employeeGrid.setVgap(10);
        employeeGrid.setHgap(10);
        employeeGrid.add(listLabel, 0, 0);
        employeeGrid.add(dropdownMenu, 0, 1);
        employeeGrid.add(nameLabel, 0, 2);
        employeeGrid.add(nameField, 1, 2);
        employeeGrid.add(idLabel, 0,3);
        employeeGrid.add(idField,1,3);
        employeeGrid.add(gradeLabel, 0, 4);
        employeeGrid.add(gradeField, 1, 4);
        employeeGrid.add(saveButton, 0, 5);
        employeeGrid.add(backButton, 1, 5);
        saveButton.setOnAction(actionEvent -> {
            // Handle save button action
            String selectedOption = dropdownMenu.getSelectionModel().getSelectedItem();
            Path path1 = Paths.get("Employee.txt");
            String nameValue = nameField.getText();
            String gradeValue = gradeField.getText();
            String idValue = idField.getText();
            if (selectedOption.equals("HOD")) {
                Label showMessage;
                // Check if both name and grade are non-empty
                if (!nameValue.isEmpty() && !gradeValue.isEmpty()) {
                    // Create an instance of HOD with the entered values
                    HOD hod = new HOD(nameValue, gradeValue, idValue);

                    // Add the HOD object to the employeeArrayList
                    employeeArrayList.add(hod);

                    // Write to file
                    Path path = path1;
                    if (methods.writeFileEmployee(path, "HOD", hod)){
                        showMessage = new Label("Data Saved Successfully");
                    }
                    else{
                        showMessage = new Label("Error Occurred");
                    }
                    // Clear text fields
                    nameField.clear();
                    gradeField.clear();
                    idField.clear();
                    employeeGrid.add(showMessage,1,6);
                }
            }
            else if (selectedOption.equals("Lab InCharge")){
                Label showMessage;
                // Check if both name and grade are non-empty
                if (!nameValue.isEmpty() && !gradeValue.isEmpty()) {
                    // Create an instance of Lab InCharge with the entered values
                    LabIncharge labIncharge = new LabIncharge(nameValue, gradeValue, idValue);

                    // Add the Lab Incharge object to the employeeArrayList
                    employeeArrayList.add(labIncharge);

                    // Write to file
                    Path path = path1;
                    if (methods.writeFileEmployee(path, "Lab InCharge",labIncharge)){
                        showMessage = new Label("Data Saved Successfully");
                    }
                    else{
                        showMessage = new Label("Error Occurred");
                    }
                    // Clear text fields
                    nameField.clear();
                    gradeField.clear();
                    idField.clear();
                    employeeGrid.add(showMessage, 1,6);
                }
            }
            else if (selectedOption.equals("Director")){
                Label showMessage;
                // Check if both name and grade are non-empty
                if (!nameValue.isEmpty() && !gradeValue.isEmpty()) {
                    // Create an instance of Director with the entered values
                    Director director = new Director(nameValue, gradeValue, idValue);
                    // Add the Lab Director object to the employeeArrayList
                    employeeArrayList.add(director);

                    // Write to file
                    Path path = path1;
                    if (methods.writeFileEmployee(path, "Director",director)) {
                        showMessage = new Label("Data Saved Successfully");
                    } else{
                        showMessage = new Label("Error Occurred");
                    }
                    // Clear text fields
                    nameField.clear();
                    gradeField.clear();
                    idField.clear();
                    employeeGrid.add(showMessage, 1,6);
                }
            }
            else if (selectedOption.equals("Lab Staff")){
                Label showMessage;
                // Check if both name and grade are non-empty
                if (!nameValue.isEmpty() && !gradeValue.isEmpty()) {
                    // Create an instance of Lab Staff with the entered values
                    LabStaff labStaff = new LabStaff(nameValue, gradeValue, idValue);
                    // Add the Lab Director object to the employeeArrayList
                    employeeArrayList.add(labStaff);

                    // Write to file
                    Path path = path1;
                    if (methods.writeFileEmployee(path, "Lab Staff" ,labStaff)){
                        showMessage = new Label("Data Saved Successfully");
                    }
                    else{
                        showMessage = new Label("Error Occurred");
                    }
                    // Clear text fields
                    nameField.clear();
                    gradeField.clear();
                    idField.clear();
                    employeeGrid.add(showMessage, 1,6);
                }
            }
        });

        Scene employeeScene = new Scene(employeeGrid, 320, 250);
        primaryStage.setScene(employeeScene);
        primaryStage.show();
        return employeeScene;
    }

    private Scene addNewLabScene(){
        System.out.println("Add New Lab");
        primaryStage.setTitle("Add Lab");
        Label nameLabel = new Label("Lab Name");
        TextField nameField = new TextField();
        Label staffNameLabel = new Label("Staff Name");
        TextField staffNameField = new TextField();
        Button saveButton = new Button("Save");
        // Create dropdown menu for projector selection
        ComboBox<String> projectorDropdown = new ComboBox<>();
        projectorDropdown.getItems().addAll("Yes", "No");
        projectorDropdown.setPromptText("Yes/No");
        boolean projectCheck;
        final String[] selectedOption = new String[1];
        projectorDropdown.setOnAction(event -> {
            selectedOption[0] = projectorDropdown.getValue();
        });
        String sel = selectedOption[0];
        projectCheck = Boolean.parseBoolean(sel);
        // Create dropdown menu for subject selection
        ComboBox<String> departmentDropDownMenu = new ComboBox<>();
        departmentDropDownMenu.getItems().addAll("Computer Science", "Physics", "Architecture", "Psychology",
                "Mathematics", "Chemistry");
        departmentDropDownMenu.setPromptText("Select a Department");
        GridPane gridPane = new GridPane();
        saveButton.setOnAction(actionEvent -> {
            String selectedOption1 = departmentDropDownMenu.getSelectionModel().getSelectedItem();
            Path path1 = Paths.get("LabData.txt");
            String nameValue = nameField.getText();
            String staffNameValue = staffNameField.getText();
            Label showMessage;
            // Create an instance of Lab with the entered values
            Lab lab = new Lab(nameValue, staffNameValue, projectCheck);
            // Add the lab object to the labArrayList
            labArrayList.add(lab);
            Path path = path1;
            if (selectedOption1.equals("Computer Science")) {
                // Check if both name and grade are non-empty
                if (!nameValue.isEmpty() && !staffNameValue.isEmpty()) {
                    // Write to file
                    if (methods.writeFileLab(path, "Computer Science",lab)){
                        showMessage = new Label("Data Saved Successfully");
                    }
                    else {
                        showMessage = new Label("Error Occurred while Saving");
                    }
                    // Clear text fields
                    nameField.clear();
                    staffNameField.clear();
                    gridPane.add(showMessage,1,5);
                }
            } else if (selectedOption1.equals("Physics")) {
                if (!nameValue.isEmpty() && !staffNameValue.isEmpty()) {
                    // Write to file
                    if (methods.writeFileLab(path, "Physics",lab)){
                        showMessage = new Label("Data Saved Successfully");
                    }
                    else {
                        showMessage = new Label("Error Occurred while Saving");
                    }
                    // Clear text fields
                    nameField.clear();
                    staffNameField.clear();
                    gridPane.add(showMessage,1,5);
                }
            } else if (selectedOption1.equals("Architecture")) {
                if (!nameValue.isEmpty() && !staffNameValue.isEmpty()) {
                    // Write to file
                    if (methods.writeFileLab(path, "Architecture",lab)){
                        showMessage = new Label("Data Saved Successfully");
                    }
                    else {
                        showMessage = new Label("Error Occurred while Saving");
                    }
                    // Clear text fields
                    nameField.clear();
                    staffNameField.clear();
                    gridPane.add(showMessage,1,5);
                }
            } else if (selectedOption1.equals("Psychology")) {
                if (!nameValue.isEmpty() && !staffNameValue.isEmpty()) {
                    // Write to file
                    if (methods.writeFileLab(path, "Psychology",lab)){
                        showMessage = new Label("Data Saved Successfully");
                    }
                    else {
                        showMessage = new Label("Error Occurred while Saving");
                    }
                    // Clear text fields
                    nameField.clear();
                    staffNameField.clear();
                    gridPane.add(showMessage,1,5);
                }
            } else if (selectedOption1.equals("Mathematics")) {
                if (!nameValue.isEmpty() && !staffNameValue.isEmpty()) {
                    // Write to file
                    if (methods.writeFileLab(path, "Mathematics",lab)){
                        showMessage = new Label("Data Saved Successfully");
                    }
                    else {
                        showMessage = new Label("Error Occurred while Saving");
                    }
                    // Clear text fields
                    nameField.clear();
                    staffNameField.clear();
                    gridPane.add(showMessage,1,5);
                }
            } else if (selectedOption1.equals("Chemistry")) {
                if (!nameValue.isEmpty() && !staffNameValue.isEmpty()) {
                    // Write to file
                    if (methods.writeFileLab(path, "Chemistry",lab)){
                        showMessage = new Label("Data Saved Successfully");
                    }
                    else {
                        showMessage = new Label("Error Occurred while Saving");
                    }
                    // Clear text fields
                    nameField.clear();
                    staffNameField.clear();
                    gridPane.add(showMessage,1,5);
                }
            }
        });
        // Create labels and text fields
        Button backButton = new Button("Back");
        backButton.setOnAction(actionEvent -> primaryStage.setScene(createMenuPage()));
        // Create grid pane and set its properties
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Add components to the grid pane
        gridPane.add(new Label("ADD LAB"), 0, 0);
        gridPane.add(departmentDropDownMenu, 1, 0);

        gridPane.add(nameLabel, 0, 1);
        gridPane.add(nameField, 1, 1);

        gridPane.add(staffNameLabel, 0, 2);
        gridPane.add(staffNameField, 1, 2);

        gridPane.add(new Label("Projector:"), 0, 3);
        gridPane.add(projectorDropdown, 1, 3);

        gridPane.add(backButton, 1,4);
        gridPane.add(saveButton, 0,4);

        // Create scene and set it on the stage
        Scene labScene = new Scene(gridPane, 400, 200);
        primaryStage.setScene(labScene);
        primaryStage.show();
        return labScene;
    }
    private Scene addNewComputerScene(){
        System.out.println("Add new computer");
        primaryStage.setTitle("ADD Computer");
        // Create the GridPane layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        // Create labels
        Label pcIDLabel = new Label("PC ID:");
        Label pcNameLabel = new Label("PC Name:");
        Label corePCIdLabel = new Label("PC Core:");
        Label ramSizeLabel = new Label("RAM Size:");
        Label diskSizeLabel = new Label("Disk Size:");
        Label lcdTextLabel = new Label("LCD Text:");

        // Create text fields
        TextField labNameTextField = new TextField();
        TextField pcNameTextField = new TextField();
        TextField corePCIdTextField = new TextField();
        TextField ramSizeTextField = new TextField();
        TextField diskSizeTextField = new TextField();
        TextField lcdTextField = new TextField();
        TextField pcID = new TextField();

        // Create a dropdown menu
        ComboBox<String> labDropdown = new ComboBox<>();
        labDropdown.setPromptText("Select Lab");
        Path labFilePath = Paths.get("LabData.txt");
        methods.readLabFileData(labFilePath, labDropdown);
        Path path = Paths.get("PcData.txt");
        // Create a button for adding a new lab
        Button saveData = new Button("Save Data");
        saveData.setOnAction(event -> {
            String selectedOption1 = labDropdown.getSelectionModel().getSelectedItem();
            String pcName = pcNameTextField.getText();
            String corePC = corePCIdTextField.getText();
            String ramSize = ramSizeTextField.getText();
            String diskSize = diskSizeTextField.getText();
            String lcdText = lcdTextField.getText();
            String pcId = pcID.getText();

            if (selectedOption1 != null && !selectedOption1.isEmpty() &&
                    pcName != null && !pcName.isEmpty() &&
                    corePC != null && !corePC.isEmpty() &&
                    ramSize != null && !ramSize.isEmpty() &&
                    diskSize != null && !diskSize.isEmpty() &&
                    lcdText != null && !lcdText.isEmpty() &&
                    pcId != null && !pcId.isEmpty()) {
                // All fields are filled, proceed with saving
                Pc pc = new Pc(pcName, corePC, ramSize, diskSize, lcdText, pcId, selectedOption1);
                boolean saved = methods.writePcInfoToFile(path, pc);

                // Clear the text fields
                labNameTextField.clear();
                pcNameTextField.clear();
                corePCIdTextField.clear();
                ramSizeTextField.clear();
                diskSizeTextField.clear();
                lcdTextField.clear();
                pcID.clear();

                // Remove the emptyFieldsMessage label if it exists
                Label emptyFieldsMessage = (Label) gridPane.lookup("#emptyFieldsMessage");
                if (emptyFieldsMessage != null) {
                    gridPane.getChildren().remove(emptyFieldsMessage);
                }

                // Create a label for the message
                Label showMessage = new Label(saved ? "Data Saved Successfully" : "Error Occurred");

                // Add the message to the grid pane
                gridPane.add(showMessage, 0, 8, 2, 1);

                // Show a success message or perform other actions
                System.out.println("Lab added successfully!");
            } else {
                // Empty fields, show a message to the user

                // Remove the previous emptyFieldsMessage label if it exists
                Label emptyFieldsMessage = (Label) gridPane.lookup("#emptyFieldsMessage");
                if (emptyFieldsMessage != null) {
                    gridPane.getChildren().remove(emptyFieldsMessage);
                }

                // Create a label for the empty fields message
                Label newEmptyFieldsMessage = new Label("Please Fill in All the Fields");
                newEmptyFieldsMessage.setId("emptyFieldsMessage");

                // Add the message to the grid pane
                gridPane.add(newEmptyFieldsMessage, 0, 8, 2, 1);
            }
        });
        Button backButton = new Button("Back");
        backButton.setOnAction(actionEvent -> {
            primaryStage.setScene(createMenuPage());
        });

        // Add all components to the GridPane
        gridPane.add(pcIDLabel, 0, 0);
        gridPane.add(pcNameLabel, 0, 1);
        gridPane.add(corePCIdLabel, 0, 2);
        gridPane.add(ramSizeLabel, 0, 3);
        gridPane.add(diskSizeLabel, 0, 4);
        gridPane.add(lcdTextLabel, 0, 5);

        gridPane.add(pcID, 1, 0);
        gridPane.add(pcNameTextField, 1, 1);
        gridPane.add(corePCIdTextField, 1, 2);
        gridPane.add(ramSizeTextField, 1, 3);
        gridPane.add(diskSizeTextField, 1, 4);
        gridPane.add(lcdTextField, 1, 5);

        gridPane.add(new Label("Lab:"), 0, 6);
        gridPane.add(labDropdown, 1, 6);
        gridPane.add(backButton,0,7,2,1);
        gridPane.add(saveData, 1, 7, 2, 1);

        // Create the scene and set it on the stage
        Scene addComputerScene = new Scene(gridPane, 400, 350);
        primaryStage.setScene(addComputerScene);
        primaryStage.setTitle("Add New Lab");
        primaryStage.show();
        return addComputerScene;
    }
    private Scene searchEmployeeScene() {
        System.out.println("Searching Employee");
        primaryStage.setTitle("Employee Search");

        // Create the GridPane layout
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        // Create labels and text fields
        Label idLabel = new Label("ID:");
        TextField idTextField = new TextField();
        Button searchButton = new Button("Search");

        // Configure search button action
        searchButton.setOnAction(event -> {
            String id = idTextField.getText();
            Path employeeFilePath = Paths.get("Employee.txt");
            String line = methods.readEmployeeFile(employeeFilePath, id);

            Label employeeFoundLabel = (Label) gridPane.lookup("#found");
            if (employeeFoundLabel != null) {
                gridPane.getChildren().remove(employeeFoundLabel);
            }

            employeeFoundLabel = new Label("");
            employeeFoundLabel.setId("found");
            gridPane.add(employeeFoundLabel, 1, 1);

            if (line != null) {
                employeeFoundLabel.setText(line); // Update the label's text
            } else {
                employeeFoundLabel.setText("Employee Not Found"); // Update the label's text
            }

            System.out.println(line);
        });
        Button backButton = new Button("Back");
        backButton.setOnAction(actionEvent -> {
            primaryStage.setScene(createMenuPage());
        });
        // Add components to the GridPane
        gridPane.add(idLabel, 0, 0);
        gridPane.add(idTextField, 1, 0);
        gridPane.add(backButton,2,0);
        gridPane.add(searchButton, 3, 0);

        // Create the scene and set it on the stage
        Scene scene = new Scene(gridPane, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
        return scene;
    }
    private Scene searchLabScene(){
        System.out.println("Searching Lab");
        primaryStage.setTitle("Lab Search");

        // Create the GridPane layout
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        // Create labels and text fields
        Label nameLabel = new Label("Enter Lab Name:");
        TextField nameTextField = new TextField();
        Button searchButton = new Button("Search");

        // Configure search button action
        searchButton.setOnAction(event -> {
            String name = nameTextField.getText();
            Path employeeFilePath = Paths.get("LabData.txt");
            String line = methods.readLabFile(employeeFilePath, name);

            Label employeeFoundLabel = (Label) gridPane.lookup("#found");
            if (employeeFoundLabel != null) {
                gridPane.getChildren().remove(employeeFoundLabel);
            }

            employeeFoundLabel = new Label("");
            employeeFoundLabel.setId("found");
            gridPane.add(employeeFoundLabel, 1, 1);

            if (line != null) {
                employeeFoundLabel.setText(line); // Update the label's text
            } else {
                employeeFoundLabel.setText("Lab Not Found"); // Update the label's text
            }

            System.out.println(line);
        });
        Button backButton = new Button("Back");
        backButton.setOnAction(actionEvent -> {
            primaryStage.setScene(createMenuPage());
        });
        // Add components to the GridPane
        gridPane.add(backButton,2,0);
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameTextField, 1, 0);
        gridPane.add(searchButton, 3, 0);

        // Create the scene and set it on the stage
        Scene scene = new Scene(gridPane, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
        return scene;
    }
    private Scene searchComputerScene(){
        System.out.println("Searching Computer");
        primaryStage.setTitle("Computer Search");

        // Create the GridPane layout
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        // Create labels and text fields
        Label idLabel = new Label("Enter PC ID:");
        TextField idTextField = new TextField();
        Button searchButton = new Button("Search");

        // Configure search button action
        searchButton.setOnAction(event -> {
            String name = idTextField.getText();
            Path employeeFilePath = Paths.get("PcData.txt");
            String line = methods.readPcFile(employeeFilePath, name);

            Label employeeFoundLabel = (Label) gridPane.lookup("#found");
            if (employeeFoundLabel != null) {
                gridPane.getChildren().remove(employeeFoundLabel);
            }

            employeeFoundLabel = new Label("");
            employeeFoundLabel.setId("found");
            gridPane.add(employeeFoundLabel, 1, 1);

            if (line != null) {
                employeeFoundLabel.setText(line); // Update the label's text
            } else {
                employeeFoundLabel.setText("Lab Not Found"); // Update the label's text
            }

            System.out.println(line);
        });
        Button backButton = new Button("Back");
        backButton.setOnAction(actionEvent -> {
            primaryStage.setScene(createMenuPage());
        });
        // Add components to the GridPane
        gridPane.add(backButton,2,0);
        gridPane.add(idLabel, 0, 0);
        gridPane.add(idTextField, 1, 0);
        gridPane.add(searchButton, 3, 0);

        // Create the scene and set it on the stage
        Scene scene = new Scene(gridPane, 600, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
        return scene;
    }
}
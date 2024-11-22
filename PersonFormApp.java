package com.example.helloworld;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonFormApp extends Application {
    private final ArrayList<Person> personList = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Person Data Form");

        // Layout
        VBox formLayout = new VBox(10);
        formLayout.setPadding(new Insets(15));

        // Banner
        Label banner = new Label("Person Data Form");
        banner.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        banner.setAlignment(Pos.CENTER);

        // Name Field with Label
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter Name");
        HBox nameBox = new HBox(10, nameLabel, nameField);
        nameBox.setAlignment(Pos.CENTER_LEFT);

        // Father Name Field with Label
        Label fatherNameLabel = new Label("Father Name:");
        TextField fatherNameField = new TextField();
        fatherNameField.setPromptText("Enter Father Name");
        HBox fatherNameBox = new HBox(10, fatherNameLabel, fatherNameField);
        fatherNameBox.setAlignment(Pos.CENTER_LEFT);

        // CNIC Field with Label
        Label cnicLabel = new Label("CNIC:");
        TextField cnicField = new TextField();
        cnicField.setPromptText("Enter CNIC");
        HBox cnicBox = new HBox(10, cnicLabel, cnicField);
        cnicBox.setAlignment(Pos.CENTER_LEFT);

        // Date Picker
        Label dateLabel = new Label("Date of Birth:");
        DatePicker datePicker = new DatePicker();
        HBox dateBox = new HBox(10, dateLabel, datePicker);
       dateBox.setAlignment(Pos.CENTER_LEFT);

        // Gender Radio Buttons
        Label genderLabel = new Label("Gender:");
        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton maleRadio = new RadioButton("Male");
        maleRadio.setToggleGroup(genderGroup);
        RadioButton femaleRadio = new RadioButton("Female");
        femaleRadio.setToggleGroup(genderGroup);
        HBox genderBox = new HBox(10, genderLabel, maleRadio, femaleRadio);
        genderBox.setAlignment(Pos.CENTER_LEFT);

        // City ComboBox
        Label cityLabel = new Label("City:");
        ComboBox<String> cityComboBox = new ComboBox<>();
        cityComboBox.getItems().addAll("City A", "City B", "City C");
        cityComboBox.setPromptText("Select City");
        HBox cityBox = new HBox(10, cityLabel, cityComboBox);
        cityBox.setAlignment(Pos.CENTER_LEFT);

        // File Chooser for Image
        Label imageLabel = new Label("Image:");
        Button fileChooserButton = new Button("Choose Image");
        Label fileLabel = new Label("No file chosen");
        ImageView imageView = new ImageView();
        imageView.setFitHeight(120);
        imageView.setFitWidth(120);
        imageView.setPreserveRatio(true);

        fileChooserButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select an Image");
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                fileLabel.setText(selectedFile.getName());
                imageView.setImage(new Image(selectedFile.toURI().toString()));
            }
        });

        VBox imageLayout = new VBox(10, imageLabel, fileChooserButton, fileLabel, imageView);
        imageLayout.setAlignment(Pos.TOP_CENTER);

        // Save Button
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            String name = nameField.getText();
            String fatherName = fatherNameField.getText();
            String cnic = cnicField.getText();
            String date = (datePicker.getValue() != null) ? datePicker.getValue().toString() : "Not selected";
            String gender = ((RadioButton) genderGroup.getSelectedToggle()) != null
                    ? ((RadioButton) genderGroup.getSelectedToggle()).getText()
                    : "Not selected";
            String city = cityComboBox.getValue() != null ? cityComboBox.getValue() : "Not selected";

            Person person = new Person(name, fatherName, cnic, date, gender, city);
            personList.add(person);

            // Clear inputs
            nameField.clear();
            fatherNameField.clear();
            cnicField.clear();
            datePicker.setValue(null);
            genderGroup.selectToggle(null);
            cityComboBox.setValue(null);
            fileLabel.setText("No file chosen");
            imageView.setImage(null);

            // Confirmation
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Person Data Saved");
            alert.setContentText("Details of " + name + " have been saved.");
            alert.showAndWait();
        });

        // Main Layout (Form on Left, Picture on Right)
        HBox mainLayout = new HBox(20, formLayout, imageLayout);
        formLayout.getChildren().addAll(banner, nameBox, fatherNameBox, cnicBox, dateBox, genderBox, cityBox, saveButton);

        Scene scene = new Scene(mainLayout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Person class to store data
    static class Person {
        private final String name;
        private final String fatherName;
        private final String cnic;
        private final String date;
        private final String gender;
        private final String city;

        public Person(String name, String fatherName, String cnic, String date, String gender, String city) {
            this.name = name;
            this.fatherName = fatherName;
            this.cnic = cnic;
            this.date = date;
            this.gender = gender;
            this.city = city;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", fatherName='" + fatherName + '\'' +
                    ", cnic='" + cnic + '\'' +
                    ", date='" + date + '\'' +
                    ", gender='" + gender + '\'' +
                    ", city='" + city + '\'' +
                    '}';
        }
    }
}

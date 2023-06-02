package com.example.javafxgui_university_lab_management_system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;

public class FileMethods {
    public boolean writeFileEmployee(Path path, String represent, Employee employee){
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            String writeFile = employee.getName() + "," + employee.getEmployeeID() + "," + employee.getGrade() + "," + represent + "\n";
            Files.write(path, writeFile.getBytes(), StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public boolean writeFileLab(Path path, String represent, Lab lab){
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            String writeFile = lab.getLabName() + "," + lab.getStaffName() + ","+ represent + "\n";
            Files.write(path, writeFile.getBytes(), StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public boolean writePcInfoToFile(Path path, Pc pc){
        try{
            if (!Files.exists(path)){
                Files.createFile(path);
            }
            String writeFile = pc.getPcId() + "," + pc.getName() + "," + pc.getCore() + ","
                    + pc.getRamSize() + "," + pc.getLcd() + "," + pc.getDiskSize() + ","
                    + pc.getLabName() + "\n";
            System.out.println(pc.toString());
            Files.write(path, writeFile.getBytes(), StandardOpenOption.APPEND);
            return true;
        } catch(IOException e){
            return false;
        }
    }
    public void readLabFileData(Path labFilePath, ComboBox<String> labDropdown) {
        try {
            // Read all lines from the file
            List<String> lines = Files.readAllLines(labFilePath);

            // Create an ObservableList to store the lab names
            ObservableList<String> labNames = FXCollections.observableArrayList();

            // Extract the lab names from the lines
            for (String line : lines) {
                String[] parts = line.split(",");
                labNames.add(parts[1]);
            }
            labDropdown.setItems(labNames);
            // Set the lab names in the ComboBox
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String readEmployeeFile(Path path, String id){
        try{
            List<String> lines = Files.readAllLines(path);
            for (String line: lines){
                String[] parts = line.split(",");
                if (Objects.equals(parts[1], id)){
                    return line;
                }
            }
        } catch (IOException e){
            System.out.println("Exception Occurred");
        }
        return null;
    }
    public String readLabFile(Path path, String name){
        try{
            List<String> lines = Files.readAllLines(path);
            for (String line: lines){
                String[] parts = line.split(",");
                if (Objects.equals(parts[1], name)){
                    return line;
                }
            }
        } catch (IOException e){
            System.out.println("Exception Occurred");
        }
        return null;
    }
    public String readPcFile(Path path, String id){
        try{
            List<String> lines = Files.readAllLines(path);
            for (String line: lines){
                String[] parts = line.split(",");
                if (Objects.equals(parts[0], id)){
                    return line;
                }
            }
        } catch (IOException e){
            System.out.println("Exception Occurred");
        }
        return null;
    }
}

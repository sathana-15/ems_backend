package com.example.SpringBoot_Project.model;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
//@AllArgsConstructor
//@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;
    private String title;




    @ManyToOne
    @JoinColumn(name = "emp_id")
    private RegisterDetails assignedEmployee;

    public Task() {
    }


    public Task(int i, String sathana, String trainner) {
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RegisterDetails getAssignedEmployee() {
        return assignedEmployee;
    }

    public void setAssignedEmployee(RegisterDetails assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }

}


package com.example.SpringBoot_Project.model;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;

    private String title;

    @Column(name = "status") // ✅ ADD THIS
    private String status;    // ✅ ADD THIS

    @ManyToOne
    @JoinColumn(name = "emp_id")
    private RegisterDetails assignedEmployee;

    public Task() {
    }

    public Task(int taskId, String title, String status) {
        this.taskId = taskId;
        this.title = title;
        this.status = status;
    }

    public void setAssignedEmployee(RegisterDetails employee) {
    }

    // Getters and setters (Lombok @Data already does this)
}

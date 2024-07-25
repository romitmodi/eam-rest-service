package com.lbg.hackathon.entity;


import com.lbg.hackathon.model.ERoleEmployee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "emp_role")
@Getter
@Setter
@AllArgsConstructor
public class EmployeeRole {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private ERoleEmployee name;

    @OneToOne(mappedBy = "role")
    private Employee employee;

    public EmployeeRole() {

    }

    public EmployeeRole(ERoleEmployee name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERoleEmployee getName() {
        return name;
    }

    public void setName(ERoleEmployee name) {
        this.name = name;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}

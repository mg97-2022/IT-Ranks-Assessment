package com.it_ranks.employee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "National_id", unique = true, nullable = false)
    private String nationalId;

    @Column(name = "age", nullable = false)
    private int age;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;
}

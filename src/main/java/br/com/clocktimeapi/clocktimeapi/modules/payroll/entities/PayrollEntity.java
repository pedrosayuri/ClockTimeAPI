package br.com.clocktimeapi.clocktimeapi.modules.payroll.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import br.com.clocktimeapi.clocktimeapi.modules.employee.entities.EmployeeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "payroll")
public class PayrollEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime reference_month;

    private double remuneration;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)
    private EmployeeEntity employee;

    @CreationTimestamp
    private LocalDateTime createdAt;
}

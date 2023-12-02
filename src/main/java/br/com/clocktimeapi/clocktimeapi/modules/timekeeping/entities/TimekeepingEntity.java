package br.com.clocktimeapi.clocktimeapi.modules.timekeeping.entities;

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
@Entity(name = "timekeeping")
public class TimekeepingEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime check_in;
    
    private LocalDateTime check_out;

    private Double work_hours;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)
    private EmployeeEntity employee;

    @CreationTimestamp
    private LocalDateTime createdAt;


}

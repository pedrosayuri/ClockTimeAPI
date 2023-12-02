package br.com.clocktimeapi.clocktimeapi.modules.employee.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.clocktimeapi.clocktimeapi.modules.job.entities.JobEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "employees")
public class EmployeeEntity {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "O campo nome é obrigatório.")
    private String name;

    @NotBlank(message = "O campo email é obrigatório.")
    @Email(message = "Email inválido. Exemplo: email@email.com")
    private String email;

    @NotBlank(message = "O campo uid é obrigatório.")
    private String uid;

    @DecimalMin(value = "0.0", inclusive = false, message = "O campo valor_hora deve ser maior que zero")
    private double hourly_rate;

    @NotNull(message = "A data de admissão não pode ser nula. Exemplo: 01/01/2023 00:00:00")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime hire_date;

    @NotNull(message = "O campo job_id é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "id", nullable = false)
    private JobEntity job;

    @CreationTimestamp
    private LocalDateTime createdAt;
}

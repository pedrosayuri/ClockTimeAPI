package br.com.clocktimeapi.clocktimeapi.modules.controleJornada.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.clocktimeapi.clocktimeapi.modules.funcionarios.entities.Funcionarios;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "controleJornada")

public class controleJornada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private Funcionarios funcionario;

    @NotNull(message = "a data-hora de entrada não pode ser nula")
    @JsonFormat(pattern = "dd/mm/yy hh:mm:ss")
    private LocalDateTime time_entrada;

    @NotNull(message = "a data-hora de saida não pode ser nula")
    @JsonFormat(pattern = "dd/mm/yy hh:mm:ss")
    private LocalDateTime time_saida;

    @DecimalMin(value = "0.0", inclusive = false, message = "O campo hora-trabalhada não pode ser menor que zero")
    private double horas_trabalhadas;
}

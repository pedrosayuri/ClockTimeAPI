package br.com.clocktimeapi.clocktimeapi.modules.folhaSalarial.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.clocktimeapi.clocktimeapi.modules.funcionarios.entities.Funcionarios;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "folhaSalarial")
public class folhaSalarial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private Funcionarios funcionario;

    @NotBlank(message = "O campo mes de refencia não pode ser vazio")
    @JsonFormat(pattern = "mm/yy")
    private LocalDate mes_referencia;

    private double remuneracao;
}

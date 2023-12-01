package br.com.clocktimeapi.clocktimeapi.modules.cargo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cargos")


public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "O campo cargo é obrigatório")
    private String cargo;

    @NotBlank(message = "O campo hierarquia é obrigatório")
    @DecimalMin(value = "1", inclusive = false, message = "O campo hierarquia deve está entre 1 e 2")
    @DecimalMax(value = "2", inclusive = false, message = "O campo hierarquia deve estar entre 1 e 2")
    private Integer hierarquia;
}

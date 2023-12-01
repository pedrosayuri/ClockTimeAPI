package br.com.clocktimeapi.clocktimeapi.modules.funcionarios.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.clocktimeapi.clocktimeapi.modules.cargo.entities.Cargo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
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
@Entity(name = "funcionarios")

public class Funcionarios {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  @NotBlank(message = "O campo nome é obrigatório")
  private String nome;
  
  @NotBlank(message = "O campo UID é obrigatório")
  private String uid;

  @NotNull(message = "A Data de admissão não pode ser nula.")
  @JsonFormat(pattern = "dd/mm/yy")
  private LocalDate data_admissao;

  @DecimalMin(value = "0.0", inclusive = false, message = "O campo valor-hora não pode ser 0")
  private double valor_hora;

  @Column(nullable = true)
  private String email;
  
  @ManyToOne
  @JoinColumn(nullable = false, updatable = false)
  private Cargo cargo;
}

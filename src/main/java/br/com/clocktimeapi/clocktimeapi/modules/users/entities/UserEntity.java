package br.com.clocktimeapi.clocktimeapi.modules.users.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Entity(name = "users")
public class UserEntity {
 
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O campo nome é obrigatório.")
    private String nome;

    @NotBlank(message = "O campo email é obrigatório.")
    @Email(message = "Email inválido. Exemplo: email@email.com")
    private String email;

    @NotBlank(message = "O campo uid é obrigatório.")
    private String uid;

    @DecimalMin(value = "0.0", inclusive = false, message = "O campo valor_hora deve ser maior que zero")
    private double valor_hora;

    @NotNull(message = "A data de admissão não pode ser nula. Exemplo: 01/01/2023 00:00:00")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data_admissao;

    @CreationTimestamp
    private LocalDateTime createdAt;

}

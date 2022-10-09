//package br.vet.sidekick.poc.model;
//
//
//import lombok.*;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotBlank;
//
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@ToString
//@Entity
//public class Funcionario {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Long id;
//
//    @Getter(AccessLevel.NONE)
//    @Column(name = "user_name", nullable = false)
//    private String username;
//
//    @NotBlank(message = "Valor não pode ser nulo ou em branco")
//    @ToString.Exclude
//    @Basic(fetch = FetchType.LAZY)
//    private String password;
//    public String getUsername() {
//        return this.username;
//    }
//
//}

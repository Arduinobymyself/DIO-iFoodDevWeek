package me.abmstelecom.sacola.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.abmstelecom.sacola.enumeration.FormaPagamento;


import javax.persistence.*;
import java.util.List;


@AllArgsConstructor // cria construtores com todos os atributos
@Builder // para criar objetos de forma simplificada
@Data // para obter os getters and setters and equals and hashcode methods
@Entity // diz que a classe é convertida para uma tabela no banco de dados
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // ignora erros de json
@NoArgsConstructor // cria o construtor vazio
public class Sacola {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // auto incrementado
    private Long id; // primary key
    @ManyToOne(fetch = FetchType.LAZY, optional = false) // um cliente várias sacolas
    @JsonIgnore
    private Cliente cliente;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> itens;
    private Double valorTotal;
    @Enumerated
    private FormaPagamento FormaPagamento;
    private boolean fechada;


}

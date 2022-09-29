package me.abmstelecom.sacola.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor // cria construtores com todos os atributos
@Builder // para criar objetos de forma simplificada
@Data // para obter os getters and setters and equals and hashcode methods
@Entity // diz que a classe é convertida para uma tabela no banco de dados
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // ignora erros de json
@NoArgsConstructor // cria o construtor vazio
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Produto produto;
    private int quantidade;
    @ManyToOne // item relaciona com sacola e produto
    @JsonIgnore
    private Sacola sacola;
}

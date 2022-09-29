package me.abmstelecom.sacola.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;



@AllArgsConstructor // cria construtores com todos os atributos
@Builder // para criar objetos de forma simplificada
@Data // para obter os getters and setters and equals and hashcode methods
@Entity // diz que a classe é convertida para uma tabela no banco de dados
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // ignora erros de json
@NoArgsConstructor // cria o construtor vazio
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    @OneToMany(cascade = CascadeType.ALL) // um restaurante vários produtos
    private List<Produto> cardapio;
    @Embedded // endereço não é uma tabela no banco de dados e faz reuso em outras partes do programa
    private Endereco endereco;

}

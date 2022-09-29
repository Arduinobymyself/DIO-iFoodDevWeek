package me.abmstelecom.sacola.resource.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@AllArgsConstructor // cria construtores com todos os atributos
@Builder // para criar objetos de forma simplificada
@Data // para obter os getters and setters and equals and hashcode methods
@Embeddable // não será uma tabela na base de dados mas será usada em outras partes do programa
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // ignora erros de json
@NoArgsConstructor // cria o construtor vazio
public class ItemDto {

    private Long produtoId;
    private int quantidade;
    private Long sacolaId;

}

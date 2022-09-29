package me.abmstelecom.sacola.service.impl;

import lombok.RequiredArgsConstructor;
import me.abmstelecom.sacola.enumeration.FormaPagamento;
import me.abmstelecom.sacola.model.Item;
import me.abmstelecom.sacola.model.Restaurante;
import me.abmstelecom.sacola.model.Sacola;
import me.abmstelecom.sacola.repository.ItemRepository;
import me.abmstelecom.sacola.repository.ProdutoRepository;
import me.abmstelecom.sacola.repository.SacolaRepository;
import me.abmstelecom.sacola.resource.dto.ItemDto;
import me.abmstelecom.sacola.service.SacolaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SacolaServiceImpl implements SacolaService {

    private final SacolaRepository sacolaRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemRepository itemRepository;
    @Override
    public Sacola verSacola(Long id) {
        return sacolaRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("Sacola inexistente!");
                }
        );
    }

    @Override
    public Sacola fecharSacola(Long id, int numeroFormaPagamento) {
        Sacola sacola = verSacola(id);
        if(sacola.getItens().isEmpty()){
            throw new RuntimeException(("Inclua ao menos um item na sacola!"));
        }
        FormaPagamento formaPagamento = numeroFormaPagamento == 0 ? FormaPagamento.DINHEIRO : FormaPagamento.MAQUINETA;
        sacola.setFormaPagamento(formaPagamento);
        sacola.setFechada(true);
        return sacolaRepository.save(sacola);

    }

    @Override
    public Item incluirItemSacola(ItemDto itemDto) {
        Sacola sacola = verSacola(itemDto.getSacolaId());
        if(sacola.isFechada()){
            throw new RuntimeException("Esta sacola está fechada! Impossível lançar itens!");
        }
        Item itemParaSerInserido = Item.builder()
                .quantidade(itemDto.getQuantidade())
                .sacola(sacola)
                .produto(produtoRepository.findById(itemDto.getProdutoId()).orElseThrow(
                        () -> {
                            throw new RuntimeException("Produto inexistente!");
                        }
                ))
                .build();

        List<Item> itensDaSacola = sacola.getItens();
        if(itensDaSacola.isEmpty()){
            itensDaSacola.add(itemParaSerInserido);
        }else{
            Restaurante restauranteAtual = itensDaSacola.get(0).getProduto().getRestaurante();
            Restaurante restauranteItemParaAdicionar = itemParaSerInserido.getProduto().getRestaurante();
            if(restauranteAtual.equals(restauranteItemParaAdicionar)){
                itensDaSacola.add(itemParaSerInserido);
            }else{
                throw new RuntimeException("Permitido somente produtos de um únicao restaurante! Conclua a sacola atual ou esvazie.");
            }
        }

        List<Double> valorDosItens = new ArrayList<>();
        for (Item itemDaSacola : itensDaSacola) {
            double valorTotalItem = itemDaSacola.getProduto().getValorUnitario() * itemDaSacola.getQuantidade();
            valorDosItens.add(valorTotalItem);
        }

        double valorTotalSacola = valorDosItens.stream()
                .mapToDouble(valorTotalDeCadaItem ->valorTotalDeCadaItem )
                .sum();

        /*
        Double valorTotalSacola = 0.0;
        for (Double valorDeCadaItem : valorDosItens) {
            valorTotalSacola += valorDeCadaItem;
        }*/


        sacola.setValorTotal(valorTotalSacola);
        sacolaRepository.save(sacola);

        //return itemRepository.save(itemParaSerInserido);
        return itemParaSerInserido;
    }
}

package me.abmstelecom.sacola.service;

import me.abmstelecom.sacola.model.Item;
import me.abmstelecom.sacola.model.Sacola;
import me.abmstelecom.sacola.resource.dto.ItemDto;

public interface SacolaService {

    Sacola verSacola(Long id);
    Sacola fecharSacola(Long id, int formaPagamento);
    Item incluirItemSacola(ItemDto itemDto);

}

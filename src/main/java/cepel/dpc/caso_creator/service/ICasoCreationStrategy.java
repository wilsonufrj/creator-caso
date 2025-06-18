package cepel.dpc.caso_creator.service;

import cepel.dpc.caso_creator.domain.CasoInfo;
import cepel.dpc.caso_creator.dto.CasoInfoDTO;

public interface ICasoCreationStrategy {
    void createCaso(CasoInfoDTO casoInfoDTO);
}

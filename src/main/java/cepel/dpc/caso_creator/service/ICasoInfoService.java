package cepel.dpc.caso_creator.service;

import cepel.dpc.caso_creator.domain.CasoInfo;
import cepel.dpc.caso_creator.dto.CasoInfoDTO;

import java.util.List;

public interface ICasoInfoService {
    CasoInfoDTO criaCaso(CasoInfoDTO casoInfoDTO);
    CasoInfoDTO getCaso(Long idCaso) throws Exception;
    List<CasoInfoDTO> getAllCasos();
}

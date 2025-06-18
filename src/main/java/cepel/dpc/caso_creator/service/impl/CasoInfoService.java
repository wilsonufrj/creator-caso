package cepel.dpc.caso_creator.service.impl;

import cepel.dpc.caso_creator.domain.CasoInfo;
import cepel.dpc.caso_creator.dto.CasoInfoDTO;
import cepel.dpc.caso_creator.repository.ICasoInfoRepository;
import cepel.dpc.caso_creator.service.ICasoInfoService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CasoInfoService implements ICasoInfoService {

    @Autowired
    private ICasoInfoRepository casoInfoRepository;

    @Override
    public CasoInfoDTO criaCaso(CasoInfoDTO casoInfoDTO) {
        CasoInfo casoInfo = new CasoInfo();

        casoInfo.setNomeProjeto(casoInfoDTO.getNomeProjeto());
        casoInfo.setLocalDateTime(LocalDateTime.now());
        casoInfo.setPathLocal("/home");
        casoInfo.setPathRemoto("");
        casoInfo.setNome(casoInfoDTO.getNomeProjeto());
        casoInfo.setDescricao(casoInfoDTO.getDescricao());
        casoInfo.setAutor(casoInfoDTO.getAutor());

        return toDTO(casoInfoRepository.save(casoInfo));
    }

    @Override
    public CasoInfoDTO getCaso(Long idCaso) throws Exception {
        return casoInfoRepository.findById(idCaso)
                .map(CasoInfoService::toDTO)
                .orElseThrow(()->new Exception("Caso nao encontrado"));
    }

    @Override
    public List<CasoInfoDTO> getAllCasos() {
        return casoInfoRepository.findAll()
                .stream()
                .map(CasoInfoService::toDTO)
                .toList();
    }

    @NotNull
    @Contract("_ -> new")
    public static CasoInfoDTO toDTO(@NotNull CasoInfo casoInfo){
        return new CasoInfoDTO(
                casoInfo.getId(),
                casoInfo.getNomeProjeto(),
                casoInfo.getLocalDateTime(),
                casoInfo.getPathLocal(),
                casoInfo.getPathRemoto(),
                casoInfo.getNome(),
                casoInfo.getDescricao(),
                casoInfo.getAutor()
        );
    }
}

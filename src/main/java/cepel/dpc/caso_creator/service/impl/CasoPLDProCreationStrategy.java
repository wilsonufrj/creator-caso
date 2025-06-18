package cepel.dpc.caso_creator.service.impl;

import cepel.dpc.caso_creator.domain.CasoInfo;
import cepel.dpc.caso_creator.dto.CasoInfoDTO;
import cepel.dpc.caso_creator.service.ICasoCreationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Qualifier("PLDPro")
public class CasoPLDProCreationStrategy implements ICasoCreationStrategy {

    @Autowired
    private RestTemplate restTemplate;

    private final String projetoAUrl = "http://localhost:8085";

    @Override
    public void createCaso(CasoInfoDTO casoInfoDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CasoInfoDTO> request = new HttpEntity<>(casoInfoDTO, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity(
                projetoAUrl,
                request,
                Void.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Falha ao criar caso no Projeto PLDPro");
        }
    }
}

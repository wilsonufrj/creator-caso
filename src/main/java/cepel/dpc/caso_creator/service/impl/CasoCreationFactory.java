package cepel.dpc.caso_creator.service.impl;

import cepel.dpc.caso_creator.service.ICasoCreationFactory;
import cepel.dpc.caso_creator.service.ICasoCreationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class CasoCreationFactory implements ICasoCreationFactory {

    private final Map<String, ICasoCreationStrategy> strategies;

    @Autowired
    public CasoCreationFactory(
            @Qualifier("PLDPro") ICasoCreationStrategy pldproStrategy
    ){
        this.strategies = Map.of(
                "PLDPro", pldproStrategy
        );
    }

    @Override
    public ICasoCreationStrategy getStrategy(String nomeProjeto) {
        return Optional.ofNullable(this.strategies.get(nomeProjeto))
                .orElseThrow(()-> new IllegalArgumentException("Projeto não existe"));

    }
}

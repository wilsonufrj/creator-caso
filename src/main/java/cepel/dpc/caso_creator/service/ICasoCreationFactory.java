package cepel.dpc.caso_creator.service;

public interface ICasoCreationFactory {
ICasoCreationStrategy getStrategy(String nomeProjeto);
}

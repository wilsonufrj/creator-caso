package cepel.dpc.caso_creator.repository;

import cepel.dpc.caso_creator.domain.CasoInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICasoInfoRepository extends JpaRepository<CasoInfo,Long> {
}

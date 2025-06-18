package cepel.dpc.caso_creator.controller;

import cepel.dpc.caso_creator.domain.CasoInfo;
import cepel.dpc.caso_creator.dto.CasoInfoDTO;
import cepel.dpc.caso_creator.dto.CasoInfoToBackend;
import cepel.dpc.caso_creator.service.ICasoCreationFactory;
import cepel.dpc.caso_creator.service.ICasoCreationStrategy;
import cepel.dpc.caso_creator.service.ICasoInfoService;
import cepel.dpc.caso_creator.service.impl.CasoCreationFactory;
import cepel.dpc.caso_creator.service.impl.CasoPLDProCreationStrategy;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/v1/caso")
@Tag(
        name = "Casos",
        description = "Endpoint para criação de casos de todos os projetos do CEPEL"
)
public class CasoInfoController {
    @Autowired
    private ICasoInfoService casoInfoService;

    @Autowired
    private ICasoCreationFactory casoCreationFactory;

    @PostMapping
    @Operation(summary = "Criar um caso",
            description = "Retorna um caso criado")
    @ApiResponse(responseCode = "200", description = "Caso criado")
    ResponseEntity<CasoInfoDTO> criaCaso(@RequestBody CasoInfoDTO casoInfoDTO){

        CasoInfoDTO casoInfo = casoInfoService.criaCaso(casoInfoDTO);

        ICasoCreationStrategy strategy = casoCreationFactory.getStrategy(casoInfo.getNomeProjeto());
        strategy.createCaso(casoInfo);

        return ResponseEntity.ok(casoInfo);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um caso passando o id",
            description = "Retorna um caso")
    @ApiResponse(responseCode = "200", description = "Caso encontrado")
    @ApiResponse(responseCode = "404", description = "Caso não encontrado")
    ResponseEntity getCasoInfo(@PathVariable Long id){
        try {
            return ResponseEntity.ok(casoInfoService.getCaso(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    @Operation(summary = "Busca todos os casos criados",
            description = "Retorna uma lista de casos")
    @ApiResponse(responseCode = "200")
    ResponseEntity<List<CasoInfoDTO>> getAllCasoInfo(){
        return ResponseEntity.ok(casoInfoService.getAllCasos());
    }
}

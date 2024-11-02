package bf.armelymg.pharmapi.web;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bf.armelymg.pharmapi.dto.PharmacieDTO;
import bf.armelymg.pharmapi.entities.Pharmacie;
import bf.armelymg.pharmapi.service.PharmacieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/api")
public class PharmacieController {
	
	@Autowired
	public PharmacieService pharmacieService;
	
	// Recupérer tous la liste de toutes les pharmacies
    @Operation(summary = "Recupérer la liste de toutes les pharmacies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recupération de la liste de toutes les pharmacies",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pharmacie.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Liste des pharmacies non trouvée",
                    content = @Content)})
    //@GetMapping(path = "/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(path = "/pharmacie/ouaga")
    public ResponseEntity<List<PharmacieDTO>> getAllDemandeSupervision() throws InterruptedException, ExecutionException {
    	log.debug("REST request to get all Pharmacie");
        List<PharmacieDTO> result = pharmacieService.getAllPharmacie();
        //List<PharmacieDTO> result = pharmacieService.findNearestPharmacies();
        return ResponseEntity.ok().body(result);
    }

    // Recupérer tous la liste de toutes les pharmacies de la ville de Bobo Dioulasso
    @Operation(summary = "Recupérer la liste de toutes les pharmacies de la ville de Bobo Dioulasso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recupération de la liste de toutes les pharmacies de la ville de Bobo Dioulasso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pharmacie.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Liste des pharmacies de la ville de Bobo Dioulasso non trouvée",
                    content = @Content)})
    //@GetMapping(path = "/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(path = "/pharmacie/bobo")
    public ResponseEntity<List<PharmacieDTO>> getAllDemandeBobo() throws InterruptedException, ExecutionException {
    	log.debug("REST request to get all Pharmacie");
        List<PharmacieDTO> result = pharmacieService.getAllPharmacieBobo();
        return ResponseEntity.ok().body(result);
    }

    // Recupérer tous la liste de toutes les pharmacies de la ville de Bobo Dioulasso
    @Operation(summary = "Recupérer la liste de toutes les pharmacies de la ville de Bobo Dioulasso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recupération de la liste de toutes les pharmacies de la ville de Bobo Dioulasso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pharmacie.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Liste des pharmacies de la ville de Bobo Dioulasso non trouvée",
                    content = @Content)})
    //@GetMapping(path = "/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(path = "/pharmacie/koudougou")
    public ResponseEntity<List<PharmacieDTO>> getAllDemandeKoudougou() throws InterruptedException, ExecutionException {
    	log.debug("REST request to get all Pharmacie");
        List<PharmacieDTO> result = pharmacieService.getAllPharmacieKoudougou();
        return ResponseEntity.ok().body(result);
    }
	
	

}

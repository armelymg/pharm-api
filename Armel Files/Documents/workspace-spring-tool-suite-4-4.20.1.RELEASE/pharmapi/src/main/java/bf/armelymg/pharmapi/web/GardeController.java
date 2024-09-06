package bf.armelymg.pharmapi.web;

import java.text.ParseException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bf.armelymg.pharmapi.dto.PharmacieDTO;
import bf.armelymg.pharmapi.entities.Garde;
import bf.armelymg.pharmapi.entities.Pharmacie;
import bf.armelymg.pharmapi.service.GardeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/api/garde")
public class GardeController {
	
	//@Autowired
	private final GardeService gardeService;

        public GardeController(GardeService gardeService){
                this.gardeService = gardeService;
        }
	
	// Recupérer tous la liste de toutes les gardes
    @Operation(summary = "Recupérer la liste de toutes les gardes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recupération de la liste de toutes les gardes",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pharmacie.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Liste des gardes non trouvée",
                    content = @Content)})
    //@GetMapping(path = "/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(path = "/all")
    public ResponseEntity<List<Garde>> getAllDemandeSupervision() throws InterruptedException, ExecutionException {
    	log.debug("REST request to get all Pharmacie");
        List<Garde> result = gardeService.getAllGarde();
        return ResponseEntity.ok().body(result);
    }
    
    // Recupérer tous la liste de toutes les pharmacies de gardes
    @Operation(summary = "Recupérer la liste de toutes les phamarcies de gardes de la ville de Ouagadougou")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recupération de la liste de toutes les phamrcies de gardes",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pharmacie.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Liste des phamarcies de garde non trouvée",
                    content = @Content)})
    //@GetMapping(path = "/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(path = "/ouaga")
    public ResponseEntity<List<PharmacieDTO>> getAllPharmacieGardeOuaga() throws InterruptedException, ExecutionException {
    	log.debug("REST request to get all Pharmacie Garde Ouaga");
        List<PharmacieDTO> result = gardeService.getAllPharmacieGarde();
        return ResponseEntity.ok().body(result);
    }

    // Recupérer tous la liste de toutes les pharmacies de gardes de la ville de Bobo Dioulasso
    @Operation(summary = "Recupérer la liste de toutes les phamarcies de gardes de la ville de Bobo Dioulasso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recupération de la liste de toutes les phamrcies de gardes",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pharmacie.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Liste des phamarcies de garde non trouvée",
                    content = @Content)})
    //@GetMapping(path = "/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(path = "/bobo")
    public ResponseEntity<List<PharmacieDTO>> getAllPharmacieGardeBobo() throws InterruptedException, ExecutionException {
    	log.debug("REST request to get all Pharmacie Garde Bobo");
        List<PharmacieDTO> result = gardeService.getAllPharmacieGardeBobo();
        return ResponseEntity.ok().body(result);
    }

    // Recupérer tous la liste de toutes les pharmacies de gardes de la ville de Koudougou
    @Operation(summary = "Recupérer la liste de toutes les phamarcies de gardes de la ville de Koudougou")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recupération de la liste de toutes les phamarcies de gardes ",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pharmacie.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Liste des phamarcies de garde non trouvée",
                    content = @Content)})
    //@GetMapping(path = "/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(path = "/koudougou")
    public ResponseEntity<List<PharmacieDTO>> getAllPharmacieGardeKoudougou() throws InterruptedException, ExecutionException {
    	log.debug("REST request to get all Pharmacie Garde Koudougou");
        List<PharmacieDTO> result = gardeService.getAllPharmacieGardeKdg();
        return ResponseEntity.ok().body(result);
    }
    
    
    // Recupérer la dernière garde enregistrée dans ma BD pour pouvoir faire des mises à jour dans le futur
    @Operation(summary = "Recupérer la dernière garde enregistrée dans ma BD pour pouvoir faire des mises à jour dans le futur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recupération de la dernière garde",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pharmacie.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Dernière garde non trouvée",
                    content = @Content)})
    //@GetMapping(path = "/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(path = "/last")
    public ResponseEntity<Garde> getLastGarde() throws InterruptedException, ExecutionException {
    	log.debug("REST request to get last Garde");
        Garde result = gardeService.getDerniereGardeDansBD();
        return ResponseEntity.ok().body(result);
    }


    // Modifier les gardes bobo charger à partir du json des gardes de ouaga su firefoo
    // les numéro de groupes étant différents en fonction des semaines
    /*
    @Operation(summary = "Recupérer la liste de toutes les gardes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recupération de la liste de toutes les gardes",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pharmacie.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Liste des gardes non trouvée",
                    content = @Content)})
    //@GetMapping(path = "/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PutMapping(path = "/bobo")
    public ResponseEntity<String> getAllDemandeBobo() throws InterruptedException, ExecutionException {
    	log.debug("REST request to get all Pharmacie");
        String result = gardeService.modGardeGroupePourAjouterBobo();
        return ResponseEntity.ok().body(result);
    }
    */


}

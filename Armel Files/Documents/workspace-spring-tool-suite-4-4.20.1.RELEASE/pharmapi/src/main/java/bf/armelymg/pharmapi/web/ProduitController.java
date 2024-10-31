package bf.armelymg.pharmapi.web;

import bf.armelymg.pharmapi.dto.PharmacieDTO;
import bf.armelymg.pharmapi.entities.Pharmacie;
import bf.armelymg.pharmapi.entities.Produit;
import bf.armelymg.pharmapi.service.ProduitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequestMapping("/v1/api")
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    // Sauvegarder un nouveau produit
    @Operation(summary = "Sauvegarder un nouveau produit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recupération de la liste de toutes les pharmacies",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Produit.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Liste des pharmacies non trouvée",
                    content = @Content)})
    @PostMapping(path = "/produit/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Produit> save(@RequestBody Produit produit) throws InterruptedException, ExecutionException {
        log.debug("REST request to save Produit");
        Produit result = produitService.saveProduit(produit);
        return ResponseEntity.ok().body(result);
    }


    // Recupérer tous la liste de toutes les pharmacies
    @Operation(summary = "Sauvegarder un nouveau produit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recupération de la liste de toutes les pharmacies",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Produit.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Liste des pharmacies non trouvée",
                    content = @Content)})
    @PostMapping(path = "/produit/save_a_lot", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Produit>> save_a_lot(@RequestBody List<Produit> produit) throws InterruptedException, ExecutionException {
        log.debug("REST request to save a list Produit");
        List<Produit> result = produitService.saveProduitList(produit);
        return ResponseEntity.ok().body(result);
    }


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
    @GetMapping(path = "/produit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Produit>> getAllProduitPageable(Pageable pageable) throws InterruptedException, ExecutionException {
        log.debug("REST request to get all Produits");
        Page<Produit> result = produitService.getAllProduitPageable(pageable);
        return ResponseEntity.ok().body(result);
    }

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
    @GetMapping(path = "/produit/having", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Produit> getProduitByLibelle(@RequestParam String libelle) throws InterruptedException, ExecutionException {
        log.debug("REST request to get all Produits");
        Produit result = produitService.getProduitByLibelle(libelle);
        return ResponseEntity.ok().body(result);
    }

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
    @GetMapping(path = "/produit/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Produit>> getProduitByLibellePageable(@RequestParam String libelle, Pageable page) throws InterruptedException, ExecutionException {
        log.debug("REST request to get all Produits");
        Page<Produit> result = produitService.getProduitByLibellePageable(libelle, page);
        return ResponseEntity.ok().body(result);
    }



}

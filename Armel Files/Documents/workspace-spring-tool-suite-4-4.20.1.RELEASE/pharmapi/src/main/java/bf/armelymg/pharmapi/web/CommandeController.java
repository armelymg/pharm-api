package bf.armelymg.pharmapi.web;

import bf.armelymg.pharmapi.entities.Commande;
import bf.armelymg.pharmapi.entities.Utilisateur;
import bf.armelymg.pharmapi.service.CommandeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequestMapping("/v1/api")
public class CommandeController {

    @Autowired
    public CommandeService commandeService;

    // Sauvegarder une nouvelle commande
    @Operation(summary = "Sauvegarder une nouvelle commande")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sauvegarder une nouvelle commande",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Commande.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Echec de Sauvegarde de la commande",
                    content = @Content)})
    @PostMapping(path = "/commande/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Commande> save(@RequestBody Commande commande) throws InterruptedException, ExecutionException {
        log.debug("REST request to save Utilisateur");
        Commande result = commandeService.saveCommande(commande);
        return ResponseEntity.ok().body(result);
    }


    // Rechercher les commandes effectuées par un utilisateur
    @Operation(summary = "Rechercher les commandes effectuées par un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recherche des commandes d'un utilisateur",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Utilisateur.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Aucune commande trouvée",
                    content = @Content)})
    @GetMapping(path = "/commande/find", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Commande>> enableUser(@RequestParam String tel) throws InterruptedException, ExecutionException {
        log.debug("REST request to get a utilisateur");
        List<Commande> result = commandeService.findCommandeUtilisateur(tel);
        return ResponseEntity.ok().body(result);
    }

}

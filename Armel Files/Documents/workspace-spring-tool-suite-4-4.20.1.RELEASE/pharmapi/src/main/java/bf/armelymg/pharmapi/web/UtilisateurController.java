package bf.armelymg.pharmapi.web;

import bf.armelymg.pharmapi.entities.Pharmacie;
import bf.armelymg.pharmapi.entities.Produit;
import bf.armelymg.pharmapi.entities.Utilisateur;
import bf.armelymg.pharmapi.service.UtilisateurService;
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

import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequestMapping("/v1/api")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    // Sauvegarder un nouveau utilisateur
    @Operation(summary = "Sauvegarder un nouveau utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sauvegarder un nouveau utilisateur",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Utilisateur.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Echec de Sauvegarde de nouveau utilisateur",
                    content = @Content)})
    @PostMapping(path = "/utilisateur/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Utilisateur> save(@RequestBody Utilisateur utilisateur) throws InterruptedException, ExecutionException {
        log.debug("REST request to save Utilisateur");
        Utilisateur result = utilisateurService.saveUtilisateur(utilisateur);
        return ResponseEntity.ok().body(result);
    }


    // Recupérer un utilisateur
    @Operation(summary = "Recupérer un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recupération d'un utilisateur",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Utilisateur.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé",
                    content = @Content)})
    @GetMapping(path = "/utilisateur/having", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Utilisateur> getProduitByLibelle(@RequestParam String tel) throws InterruptedException, ExecutionException {
        log.debug("REST request to get a utilisateur");
        Utilisateur result = utilisateurService.getUtilisateurByTelephone(tel);
        return ResponseEntity.ok().body(result);
    }


    // Recupérer un utilisateur
    @Operation(summary = "Recupérer un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recupération d'un utilisateur",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Utilisateur.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé",
                    content = @Content)})
    @GetMapping(path = "/utilisateur/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Utilisateur> login(@RequestParam String tel, String password) throws InterruptedException, ExecutionException {
        log.debug("REST request to get a utilisateur");
        Utilisateur result = utilisateurService.loginByTelephone(tel, password);
        return ResponseEntity.ok().body(result);
    }

    // Valider un utilisateur
    @Operation(summary = "Valider un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Validation d'un utilisateur",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Utilisateur.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé",
                    content = @Content)})
    @PostMapping(path = "/utilisateur/enable", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Utilisateur> enableUser(@RequestParam String tel) throws InterruptedException, ExecutionException {
        log.debug("REST request to get a utilisateur");
        Utilisateur result = utilisateurService.enable(tel);
        return ResponseEntity.ok().body(result);
    }

}

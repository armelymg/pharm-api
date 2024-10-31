package bf.armelymg.pharmapi.service;

import bf.armelymg.pharmapi.entities.Utilisateur;

import java.util.concurrent.ExecutionException;

public interface UtilisateurService {

    Utilisateur getUtilisateurByEmail(String email);
    Utilisateur getUtilisateurByUsername(String username);
    Utilisateur getUtilisateurByTelephone(String telephone) throws InterruptedException, ExecutionException;
    Utilisateur loginByTelephone(String telephone, String password) throws InterruptedException, ExecutionException;
    Utilisateur enable(String telephone) throws InterruptedException, ExecutionException;

    Utilisateur saveUtilisateur(Utilisateur utilisateur) throws InterruptedException, ExecutionException;

}

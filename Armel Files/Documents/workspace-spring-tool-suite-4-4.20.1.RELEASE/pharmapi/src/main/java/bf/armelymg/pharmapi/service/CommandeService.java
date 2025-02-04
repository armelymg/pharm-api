package bf.armelymg.pharmapi.service;

import bf.armelymg.pharmapi.entities.Commande;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface CommandeService {

    Commande saveCommande(Commande commande) throws InterruptedException, ExecutionException;

    List<Commande> findCommandeUtilisateur(String tel) throws InterruptedException, ExecutionException;
    List<Commande> findCommandePharmacie(String pharmacie) throws InterruptedException, ExecutionException;
}

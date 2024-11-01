package bf.armelymg.pharmapi.service;

import bf.armelymg.pharmapi.entities.Commande;

import java.util.concurrent.ExecutionException;

public interface CommandeService {

    Commande saveCommande(Commande commande) throws InterruptedException, ExecutionException;

}

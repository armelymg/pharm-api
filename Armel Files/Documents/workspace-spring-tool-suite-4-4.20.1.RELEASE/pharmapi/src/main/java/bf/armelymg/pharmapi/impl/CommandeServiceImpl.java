package bf.armelymg.pharmapi.impl;

import bf.armelymg.pharmapi.entities.Commande;
import bf.armelymg.pharmapi.entities.Produit;
import bf.armelymg.pharmapi.service.CommandeService;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CommandeServiceImpl implements CommandeService {

    @Override
    public Commande saveCommande(Commande commande) throws InterruptedException, ExecutionException  {

        Firestore firestore = FirestoreClient.getFirestore();

        CollectionReference produitsRef = firestore.collection("commandes");

        // Ajoute le produit à Firestore
        DocumentReference docRef = produitsRef.add(commande).get();

        // Vérifie si le document a bien été créé
        if (docRef != null) {
            return commande;
        } else {
            throw new RuntimeException("Échec de l'enregistrement de la commande.");
        }

    }

    @Override
    public List<Commande> findCommandeUtilisateur(String tel) throws InterruptedException, ExecutionException {

        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference commandesRef = firestore.collection("commandes");

        // Requête pour rechercher des commandes par telCLient
        Query query = commandesRef.whereEqualTo("telephoneClient", tel);
        QuerySnapshot querySnapshot = query.get().get();

        List<Commande> commandes = new ArrayList<>();
        for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
            Commande commande = document.toObject(Commande.class);
            commandes.add(commande);

        }

        return commandes;
    }

    @Override
    public List<Commande> findCommandePharmacie(String pharmacie) throws InterruptedException, ExecutionException {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference commandesRef = firestore.collection("commandes");

        // Requête pour rechercher des commandes par telCLient
        Query query = commandesRef.whereEqualTo("pharmacie.pharmacie", pharmacie);
        QuerySnapshot querySnapshot = query.get().get();

        List<Commande> commandes = new ArrayList<>();
        for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
            Commande commande = document.toObject(Commande.class);
            commandes.add(commande);
        }

        return commandes;
    }

}

package bf.armelymg.pharmapi.impl;

import bf.armelymg.pharmapi.entities.Commande;
import bf.armelymg.pharmapi.service.CommandeService;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

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

}

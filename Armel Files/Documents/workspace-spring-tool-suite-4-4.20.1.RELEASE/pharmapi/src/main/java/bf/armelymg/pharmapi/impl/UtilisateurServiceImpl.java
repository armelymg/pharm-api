package bf.armelymg.pharmapi.impl;

import bf.armelymg.pharmapi.entities.Produit;
import bf.armelymg.pharmapi.entities.Utilisateur;
import bf.armelymg.pharmapi.service.UtilisateurService;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Override
    public Utilisateur getUtilisateurByEmail(String email) {
        return null;
    }

    @Override
    public Utilisateur getUtilisateurByUsername(String username) {
        return null;
    }

    @Override
    public Utilisateur getUtilisateurByTelephone(String telephone) throws InterruptedException, ExecutionException {

        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference utilisateursRef = firestore.collection("utilisateurs");

        // Requête pour rechercher un utilisateur en fonction de son numéro de téléphone
        Query query = utilisateursRef.whereEqualTo("telephone", telephone);
        QuerySnapshot querySnapshot = query.get().get();

        // Vérifier si un utilisateur a été trouvé
        if (!querySnapshot.isEmpty()) {
            // Retourne le premier produit trouvé
            QueryDocumentSnapshot document = querySnapshot.getDocuments().get(0);
            return document.toObject(Utilisateur.class);
        }

        // Retourne null ou lance une exception si aucun utilisateur n'est trouvé
        return null;
    }

    @Override
    public Utilisateur loginByTelephone(String telephone, String password) throws InterruptedException, ExecutionException {

        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference utilisateursRef = firestore.collection("utilisateurs");

        // Requête pour rechercher un utilisateur en fonction de son numéro de téléphone
        Query query = utilisateursRef.whereEqualTo("telephone", telephone);
        QuerySnapshot querySnapshot = query.get().get();

        // Vérifier si un utilisateur a été trouvé
        if (!querySnapshot.isEmpty()) {
            // Retourne le premier produit trouvé
            QueryDocumentSnapshot document = querySnapshot.getDocuments().get(0);

            Utilisateur utilisateurFetch = document.toObject(Utilisateur.class);
            if (utilisateurFetch.getPassword().equals(password)) {
                // login succesfully
                return utilisateurFetch;
            }
            return null;
        }

        // Retourne null ou lance une exception si aucun utilisateur n'est trouvé
        return null;

    }

    @Override
    public Utilisateur enable(String telephone) throws InterruptedException, ExecutionException {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference utilisateursRef = firestore.collection("utilisateurs");

        // Requête pour rechercher un utilisateur en fonction de son numéro de téléphone
        Query query = utilisateursRef.whereEqualTo("telephone", telephone);
        QuerySnapshot querySnapshot = query.get().get();

        // Vérifier si un utilisateur a été trouvé
        if (!querySnapshot.isEmpty()) {
            // Retourne le premier produit trouvé
            QueryDocumentSnapshot document = querySnapshot.getDocuments().get(0);

            Utilisateur utilisateurFetch = document.toObject(Utilisateur.class);
            utilisateurFetch.setEnabled(true);

            DocumentReference docRef = utilisateursRef.add(utilisateurFetch).get();

            // Vérifie si le document a bien été créé
            if (docRef != null) {
                // Retourne l'utilisateur
                return utilisateurFetch;
            } else {
                return null;
            }
        }

        // Retourne null ou lance une exception si aucun utilisateur n'est trouvé
        return null;
    }

    @Override
    public Utilisateur saveUtilisateur(Utilisateur utilisateur) throws InterruptedException, ExecutionException {

        Firestore firestore = FirestoreClient.getFirestore();

        CollectionReference utilisateursRef = firestore.collection("utilisateurs");

        // Ajoute l'utilisateur à Firestore
        utilisateur.setAvailable(false);
        utilisateur.setEnabled(false);

        // Requête pour rechercher un utilisateur en fonction de son numéro de téléphone
        Query query = utilisateursRef.whereEqualTo("telephone", utilisateur.getTelephone());
        QuerySnapshot querySnapshot = query.get().get();

        // Vérifier si un utilisateur a été trouvé
        if (!querySnapshot.isEmpty()) {
            throw new RuntimeException("Cet utilisateur existe déjà !");
        }

        DocumentReference docRef = utilisateursRef.add(utilisateur).get();

        // Vérifie si le document a bien été créé
        if (docRef != null) {
            // Retourne l'utilisateur
            return utilisateur;
        } else {
            throw new RuntimeException("Échec de l'enregistrement de l'utilisateur.");
        }

    }

}

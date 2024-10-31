package bf.armelymg.pharmapi.impl;

import bf.armelymg.pharmapi.entities.Produit;
import bf.armelymg.pharmapi.service.ProduitService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ProduitServiceImpl implements ProduitService {

    @Override
    public List<Produit> getProduit() throws InterruptedException, ExecutionException {
        return List.of();
    }

    @Override
    public Produit getProduitByLibelle(String libelle) throws InterruptedException, ExecutionException {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference produitsRef = firestore.collection("produits");

        // Requête pour rechercher des produits par libellé
        Query query = produitsRef.whereEqualTo("libelle", libelle);
        QuerySnapshot querySnapshot = query.get().get();

        // Vérifier si des produits ont été trouvés
        if (!querySnapshot.isEmpty()) {
            // Retourne le premier produit trouvé
            QueryDocumentSnapshot document = querySnapshot.getDocuments().get(0);
            return document.toObject(Produit.class);
        }

        // Retourne null ou lance une exception si aucun produit n'est trouvé
        throw new RuntimeException("Produit non trouvé");
    }


    @Override
    public Page<Produit> getProduitByLibellePageable(String libelle, Pageable pageable) throws InterruptedException, ExecutionException {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference produitsRef = firestore.collection("produits");

        // Requête pour récupérer tous les produits (sans filtrage initial)
        Query query = produitsRef.orderBy("libelle") // Assurez-vous d'avoir un champ sur lequel ordonner
                .limit(pageable.getPageSize())
                .offset((int) pageable.getOffset());

        QuerySnapshot querySnapshot = query.get().get();

        List<Produit> produits = new ArrayList<>();
        for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
            Produit produit = document.toObject(Produit.class);
            // Vérifie si le libellé contient la sous-chaîne recherchée
            if (produit.getLibelle().toLowerCase().contains(libelle.toLowerCase())) {
                produits.add(produit);
            }
        }

        // Compter le total d'éléments
        long totalElements = produitsRef.whereEqualTo("libelle", libelle).get().get().size();

        return new PageImpl<>(produits, pageable, totalElements);
    }

    @Override
    public Page<Produit> getAllProduitPageable(Pageable pageable) {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference produitsRef = firestore.collection("produits");

        try {
            // Effectuer la requête avec limit
            QuerySnapshot querySnapshot = produitsRef
                    .orderBy("libelle")
                    .limit(pageable.getPageSize())
                    .offset((int) pageable.getOffset()) // Calculer l'offset
                    .get()
                    .get(); // Récupérer les résultats

            List<Produit> produits = new ArrayList<>();
            for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
                produits.add(document.toObject(Produit.class));
            }

            // Compter le total d'éléments
            long totalElements = produitsRef.get().get().size();

            return new PageImpl<>(produits, pageable, totalElements);

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Erreur lors de la récupération des produits", e);
        }
    }

    @Override
    public Produit saveProduit(Produit produit) throws InterruptedException, ExecutionException {

        Firestore firestore = FirestoreClient.getFirestore();

        CollectionReference produitsRef = firestore.collection("produits");

        // Ajoute le produit à Firestore
        DocumentReference docRef = produitsRef.add(produit).get();

        // Vérifie si le document a bien été créé
        if (docRef != null) {
            // Retourne le produit avec son ID
            return produit;
        } else {
            throw new RuntimeException("Échec de l'enregistrement du produit.");
        }

    }

    @Override
    public List<Produit> saveProduitList(List<Produit> produits) throws InterruptedException, ExecutionException {

        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference produitsRef = firestore.collection("produits");

        List<Produit> savedProduits = new ArrayList<>();

        for (Produit produit : produits) {
            // Ajoute le produit à Firestore
            ApiFuture<DocumentReference> future = produitsRef.add(produit);
            DocumentReference docRef = future.get();

            // Vérifie si le document a bien été créé
            if (docRef != null) {
                savedProduits.add(produit);
            } else {
                throw new RuntimeException("Échec de l'enregistrement du produit : " + produit);
            }
        }

        // Retourne la liste des produits sauvegardés
        return savedProduits;

    }

}

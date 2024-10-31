package bf.armelymg.pharmapi.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query.Direction;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

import bf.armelymg.pharmapi.dto.PharmacieDTO;
import bf.armelymg.pharmapi.entities.Garde;
import bf.armelymg.pharmapi.service.GardeService;

@Service
public class GardeServiceImpl implements GardeService{

	@Override
	public List<Garde> getAllGarde() throws InterruptedException, ExecutionException {

        Firestore firestore = FirestoreClient.getFirestore();
						
		ApiFuture<QuerySnapshot> future = firestore.collection("gardes").get();
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();

		List<Garde> listGardeRetrieve = new ArrayList<>();
		
		for (QueryDocumentSnapshot document : documents) {
			
			// Afficher la structure de l'objet récupéré
            Map<String, Object> data = document.getData();
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                System.out.println(entry.getKey() + " => " + entry.getValue() + " => " + entry.getValue().getClass());
            }
            
            //System.out.println(document.getId() + " => " + document.getClass().toString());
			  
			 listGardeRetrieve.add(Garde.fromSnapshot(document));
		}

		return listGardeRetrieve;
		
	}
	
	public List<PharmacieDTO> getAllPharmacieGarde() {

        Firestore firestore = FirestoreClient.getFirestore();
				
        Date now = new Date();
        CollectionReference gardesRef = firestore.collection("gardes");

        try {
            QuerySnapshot querySnapshot = firestore.collection("gardes").whereLessThanOrEqualTo("dateDebut", now).get()
                    .get(); // Utilisation de get() pour attendre la complétion

            List<Integer> groupes = new ArrayList<>();
            for (QueryDocumentSnapshot doc : querySnapshot.getDocuments()) {
                Date dateFin = doc.getDate("dateFin");
                if (dateFin.after(now)) {
                    groupes.add(doc.getLong("groupe").intValue());
                }
            }

            QuerySnapshot snapshot = firestore.collection("pharmacies")
                    .whereEqualTo("groupe", groupes.get(0))
                    .get()
                    .get(); // Utilisation de get() pour attendre la complétion

            List<PharmacieDTO> pharmacieData = new ArrayList<>();
            for (QueryDocumentSnapshot document : snapshot.getDocuments()) {
                pharmacieData.add(PharmacieDTO.fromSnapshot(document));
            }

            return pharmacieData;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

	@Override
	public Garde getDerniereGardeDansBD() throws InterruptedException, ExecutionException {
		
		Firestore firestore = FirestoreClient.getFirestore();
		
        CollectionReference gardesRef = firestore.collection("gardes");
        
        // Effectuer la requête pour récupérer la demande avec la date d'expiration la plus éloignée
        ApiFuture<QuerySnapshot> future = gardesRef.orderBy("dateFin", Direction.DESCENDING).limit(1)
                .get();
        
        QuerySnapshot querySnapshot = future.get();
        
        if (querySnapshot != null && !querySnapshot.isEmpty()) {
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                // Mapper le document Firestore à votre classe Demande
            	System.out.println("Class groupe "+document.get("groupe").getClass());
                Garde garde = Garde.fromSnapshot(document);
                
                return garde;
            }
        } else {
            System.out.println("Aucune demande trouvée.");
        }
        
		return null;
	}

    @Override
    public String modGardeGroupePourAjouterBobo() throws InterruptedException, ExecutionException {

        Firestore firestore = FirestoreClient.getFirestore();

        // Référence à la collection "amis"
        CollectionReference collection = firestore.collection("gardes-bobo");

        // Récupérer tous les documents dans la collection "amis"
        ApiFuture<QuerySnapshot> query = collection.get();
        QuerySnapshot querySnapshot = query.get();

        // Parcourir tous les documents et mettre à jour la colonne "groupe"
        for (DocumentSnapshot document : querySnapshot.getDocuments()) {
            DocumentReference docRef = collection.document(document.getId());

            // Récupérer la valeur actuelle de la colonne "groupe"
            Integer groupeActuel = document.getLong("groupe").intValue();
            int nouveauGroupe;
            switch (groupeActuel) {
                case 4:
                    nouveauGroupe = 3;
                    break;
                case 1:
                    nouveauGroupe = 4;
                    break;
                case 2:
                    nouveauGroupe = 1;
                    break;
                case 3:
                    nouveauGroupe = 2;
                    break;
                default:
                    nouveauGroupe = groupeActuel; // Aucune modification pour les autres valeurs
                    break;
            }

            // Mettre à jour la colonne "groupe" (remplacez "NOUVEAU_GROUPE" par la valeur souhaitée)
            docRef.update("groupe", nouveauGroupe).get();
        }

        return "OK"; 
        // Fermer Firestore lorsque vous avez terminé
        //firestore.close();
        // TODO Auto-generated method stub
    }

    @Override
    public List<PharmacieDTO> getAllPharmacieGardeBobo() {
        Firestore firestore = FirestoreClient.getFirestore();
				
        Date now = new Date();
        CollectionReference gardesRef = firestore.collection("gardes-bobo"); // Collection gardes-bobo

        try {
            QuerySnapshot querySnapshot = firestore.collection("gardes-bobo").whereLessThanOrEqualTo("dateDebut", now).get()
                    .get(); // Utilisation de get() pour attendre la complétion

            List<Integer> groupes = new ArrayList<>();
            for (QueryDocumentSnapshot doc : querySnapshot.getDocuments()) {
                Date dateFin = doc.getDate("dateFin");
                if (dateFin.after(now)) {
                    groupes.add(doc.getLong("groupe").intValue());
                }
            }

            QuerySnapshot snapshot = firestore.collection("pharmacies-bobo")
                    .whereEqualTo("groupe", groupes.get(0))
                    .get()
                    .get(); // Utilisation de get() pour attendre la complétion

            List<PharmacieDTO> pharmacieData = new ArrayList<>();
            for (QueryDocumentSnapshot document : snapshot.getDocuments()) {
                pharmacieData.add(PharmacieDTO.fromSnapshot(document));
            }

            return pharmacieData;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public String supprGardeAnterieure() throws InterruptedException, ExecutionException, ParseException {
        // Initialiser Firestore
        Firestore firestore = FirestoreClient.getFirestore();

        // Référence à la collection "amis"
        CollectionReference amisCollection = firestore.collection("gardes-koudougou");

        // Récupérer tous les documents dans la collection "amis"
        ApiFuture<com.google.cloud.firestore.QuerySnapshot> query = amisCollection.get();
        com.google.cloud.firestore.QuerySnapshot querySnapshot = query.get();

        // Parcourir tous les documents et supprimer ceux ayant une "dateDebut" antérieure au 25/10/2021
        for (DocumentSnapshot document : querySnapshot.getDocuments()) {
            DocumentReference docRef = amisCollection.document(document.getId());

            // Vérifier la dateDebut
            Date dateDebut = document.getTimestamp("dateDebut").toDate();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            if (dateDebut != null && dateDebut.before(sdf.parse("29/12/2023"))) {
                // Supprimer le document
                docRef.delete().get();
            }
        }
        return "OK";
    }

    @Override
    public List<PharmacieDTO> getAllPharmacieGardeKdg() {
        Firestore firestore = FirestoreClient.getFirestore();
				
        Date now = new Date();
        CollectionReference gardesRef = firestore.collection("gardes-koudougou"); // Collection gardes-koudougou

        try {
            QuerySnapshot querySnapshot = firestore.collection("gardes-koudougou").whereLessThanOrEqualTo("dateDebut", now).get()
                    .get(); // Utilisation de get() pour attendre la complétion

            List<Integer> groupes = new ArrayList<>();
            for (QueryDocumentSnapshot doc : querySnapshot.getDocuments()) {
                Date dateFin = doc.getDate("dateFin");
                if (dateFin.after(now)) {
                    groupes.add(doc.getLong("groupe").intValue());
                }
            }

            QuerySnapshot snapshot = firestore.collection("pharmacies-koudougou")
                    .whereEqualTo("groupe", groupes.get(0))
                    .get()
                    .get(); // Utilisation de get() pour attendre la complétion

            List<PharmacieDTO> pharmacieData = new ArrayList<>();
            for (QueryDocumentSnapshot document : snapshot.getDocuments()) {
                pharmacieData.add(PharmacieDTO.fromSnapshot(document));
            }

            return pharmacieData;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
	
}

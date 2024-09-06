package bf.armelymg.pharmapi.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

import bf.armelymg.pharmapi.dto.PharmacieDTO;
import bf.armelymg.pharmapi.entities.Garde;
import bf.armelymg.pharmapi.entities.Pharmacie;
import bf.armelymg.pharmapi.service.PharmacieService;

@Service
public class PharmacieImpl implements PharmacieService {
	
	@Override
	public List<PharmacieDTO> getAllPharmacie() throws InterruptedException, ExecutionException {
		
		/*Firestore firestore = FirestoreClient.getFirestore();
		DocumentReference documentReference = firestore.collection("pharmacies").get();
		
		ApiFuture<DocumentSnapshot> future = documentReference.get();
		DocumentSnapshot document = future.get();*/
		
		Firestore firestore = FirestoreClient.getFirestore();
		
		ApiFuture<QuerySnapshot> future = firestore.collection("pharmacies").get();
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();

		List<PharmacieDTO> listPharmacieRetrieve = new ArrayList<>();
		
		for (QueryDocumentSnapshot document : documents) {
			listPharmacieRetrieve.add(PharmacieDTO.fromSnapshot(document));
		}

		return listPharmacieRetrieve;
	}

	@Override
	public List<PharmacieDTO> getAllPharmacieBobo() throws InterruptedException, ExecutionException {
		Firestore firestore = FirestoreClient.getFirestore();
		
		ApiFuture<QuerySnapshot> future = firestore.collection("pharmacies-bobo").get();
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();

		List<PharmacieDTO> listPharmacieRetrieve = new ArrayList<>();
		
		for (QueryDocumentSnapshot document : documents) {
			  listPharmacieRetrieve.add(PharmacieDTO.fromSnapshot(document));
		}

		return listPharmacieRetrieve;
	}

	@Override
	public List<PharmacieDTO> getAllPharmacieKoudougou() throws InterruptedException, ExecutionException {
		Firestore firestore = FirestoreClient.getFirestore();
		
		ApiFuture<QuerySnapshot> future = firestore.collection("pharmacies-koudougou").get();
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();

		List<PharmacieDTO> listPharmacieRetrieve = new ArrayList<>();
		
		for (QueryDocumentSnapshot document : documents) {
			  listPharmacieRetrieve.add(PharmacieDTO.fromSnapshot(document));
		}

		return listPharmacieRetrieve;
	}

}

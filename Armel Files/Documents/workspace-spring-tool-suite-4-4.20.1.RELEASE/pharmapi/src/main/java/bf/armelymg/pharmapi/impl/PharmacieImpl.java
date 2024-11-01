package bf.armelymg.pharmapi.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
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

	public List<PharmacieDTO> findNearestPharmacies(GeoPoint userLocation) throws ExecutionException, InterruptedException {

		Firestore firestore = FirestoreClient.getFirestore();

		ApiFuture<QuerySnapshot> future = firestore.collection("pharmacies").get();
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();

		List<PharmacieDTO> pharmacies = new ArrayList<>();

		for (QueryDocumentSnapshot document : documents) {
			pharmacies.add(PharmacieDTO.fromSnapshot(document));
		}

		return pharmacies.stream()
				.map(pharmacie -> {
					double distance = calculateDistance(userLocation, pharmacie.getLocalisation());
					return new PharmacyWithDistance(pharmacie, distance); // Créer un nouvel objet avec pharmacie et distance
				})
				.sorted((pd1, pd2) -> Double.compare(pd1.getDistance(), pd2.getDistance())) // Tri par distance
				//.limit(3) // Prendre les 3 plus proches
				.map(PharmacyWithDistance::getPharmacie) // Extraire seulement la pharmacie
				.collect(Collectors.toList());
	}

	private double calculateDistance(GeoPoint point1, GeoPoint point2) {
		final int R = 6371; // Rayon de la terre en kilomètres
		double latDistance = Math.toRadians(point2.getLatitude() - point1.getLatitude());
		double lonDistance = Math.toRadians(point2.getLongitude() - point1.getLongitude());

		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
				Math.cos(Math.toRadians(point1.getLatitude())) * Math.cos(Math.toRadians(point2.getLatitude())) *
						Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return R * c; // Distance en kilomètres
	}

	private static class PharmacyWithDistance {
		private final PharmacieDTO pharmacie;
		private final double distance;

		public PharmacyWithDistance(PharmacieDTO pharmacie, double distance) {
			this.pharmacie = pharmacie;
			this.distance = distance;
		}

		public PharmacieDTO getPharmacie() {
			return pharmacie;
		}

		public double getDistance() {
			return distance;
		}
	}

}

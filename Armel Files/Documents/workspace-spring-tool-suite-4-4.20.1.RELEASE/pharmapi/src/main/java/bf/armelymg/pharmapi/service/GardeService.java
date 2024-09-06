package bf.armelymg.pharmapi.service;

import java.text.ParseException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import bf.armelymg.pharmapi.dto.PharmacieDTO;
import bf.armelymg.pharmapi.entities.Garde;
import bf.armelymg.pharmapi.entities.Pharmacie;

public interface GardeService {
	
	List<Garde> getAllGarde() throws InterruptedException, ExecutionException;
	List<PharmacieDTO> getAllPharmacieGarde(); // Ouaga
	List<PharmacieDTO> getAllPharmacieGardeBobo(); // Bobo
	List<PharmacieDTO> getAllPharmacieGardeKdg(); // Koudougou
	Garde getDerniereGardeDansBD() throws InterruptedException, ExecutionException;
	String modGardeGroupePourAjouterBobo() throws InterruptedException, ExecutionException;
	String supprGardeAnterieure() throws InterruptedException, ExecutionException, ParseException;

}

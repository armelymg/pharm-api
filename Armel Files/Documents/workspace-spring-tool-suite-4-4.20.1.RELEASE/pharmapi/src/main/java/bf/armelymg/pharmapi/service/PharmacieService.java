package bf.armelymg.pharmapi.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import bf.armelymg.pharmapi.dto.PharmacieDTO;
import bf.armelymg.pharmapi.entities.Pharmacie;


public interface PharmacieService {
	
	List<PharmacieDTO> getAllPharmacie() throws InterruptedException, ExecutionException;
	List<PharmacieDTO> getAllPharmacieBobo() throws InterruptedException, ExecutionException;
	List<PharmacieDTO> getAllPharmacieKoudougou() throws InterruptedException, ExecutionException;


}

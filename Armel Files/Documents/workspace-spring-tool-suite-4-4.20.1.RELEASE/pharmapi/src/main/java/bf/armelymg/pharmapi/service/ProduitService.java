package bf.armelymg.pharmapi.service;

import bf.armelymg.pharmapi.dto.PharmacieDTO;
import bf.armelymg.pharmapi.entities.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ProduitService {

    List<Produit> getProduit() throws InterruptedException, ExecutionException;
    Produit getProduitByLibelle(String libelle) throws InterruptedException, ExecutionException;
    Page<Produit> getProduitByLibellePageable(String libelle, Pageable pageable) throws InterruptedException, ExecutionException;
    Page<Produit> getAllProduitPageable(Pageable pageable);

    Produit saveProduit(Produit produit) throws InterruptedException, ExecutionException;
    List<Produit> saveProduitList(List<Produit> produit) throws InterruptedException, ExecutionException;

}

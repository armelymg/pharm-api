package bf.armelymg.pharmapi.entities;

import bf.armelymg.pharmapi.dto.PharmacieDTO;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.GeoPoint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produit {

    private String libelle;
    private String description;
    private double prix;

    public static Produit fromSnapshot(DocumentSnapshot document) {
        Map<String, Object> data = document.getData();
        if (data != null) {
            return new Produit(
                    (String) data.get("libelle"),
                    (String) data.get("description"),
                    (double) data.get("prix")
            );
        } else {
            // Gestion d'une absence de données (si nécessaire)
            return null;
        }
    }

}

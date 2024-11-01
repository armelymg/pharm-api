package bf.armelymg.pharmapi.entities;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.GeoPoint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commande {

    private Date date;
    private double montant;
    private boolean status;
    private GeoPoint localisationCient;
    private List<Produit> produitList;
    private Pharmacie pharmacie;

    public static Commande fromSnapshot(DocumentSnapshot document) {
        Map<String, Object> data = document.getData();
        if (data != null) {
            return new Commande(
                    (Date) data.get("libelle"),
                    (double) data.get("description"),
                    (boolean) data.get("status"),
                    (GeoPoint) data.get("localisationCient"),
                    (List<Produit>) data.get("produitList"),
                    (Pharmacie) data.get("pharmacie")
            );
        } else {
            // Gestion d'une absence de données (si nécessaire)
            return null;
        }
    }

}

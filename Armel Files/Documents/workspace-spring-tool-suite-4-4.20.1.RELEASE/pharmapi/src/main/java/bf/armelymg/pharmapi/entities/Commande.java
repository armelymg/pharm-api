package bf.armelymg.pharmapi.entities;

import bf.armelymg.pharmapi.utils.GeoPointDeserializer;
import bf.armelymg.pharmapi.utils.GeoPointSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
    @JsonSerialize(using = GeoPointSerializer.class)
    @JsonDeserialize(using = GeoPointDeserializer.class)
    private GeoPoint localisationClient;
    private String telephoneClient;
    private List<Produit> produitList;
    private Pharmacie pharmacie;

    public static Commande fromSnapshot(DocumentSnapshot document) {
        Map<String, Object> data = document.getData();
        if (data != null) {
            return new Commande(
                    (Date) data.get("libelle"),
                    (double) data.get("description"),
                    (boolean) data.get("status"),
                    (GeoPoint) data.get("localisationClient"),
                    (String) data.get("telephoneClient"),
                    (List<Produit>) data.get("produitList"),
                    (Pharmacie) data.get("pharmacie")
            );
        } else {
            // Gestion d'une absence de données (si nécessaire)
            return null;
        }
    }

}

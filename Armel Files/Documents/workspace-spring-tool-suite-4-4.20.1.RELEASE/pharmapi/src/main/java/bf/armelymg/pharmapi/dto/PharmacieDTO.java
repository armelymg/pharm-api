package bf.armelymg.pharmapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.GeoPoint;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PharmacieDTO {

    private Long groupe;
	private String pharmacie;
	private Long telephone;
	private GeoPoint localisation;
    
    public static PharmacieDTO fromSnapshot(DocumentSnapshot document) {
        Map<String, Object> data = document.getData();
        if (data != null) {
            return new PharmacieDTO(
                    (Long) data.get("groupe"),
                    (String) data.get("pharmacie"),
                    (Long) data.get("telephone"),
                    (GeoPoint) data.get("localisation")
            );
        } else {
            // Gestion d'une absence de données (si nécessaire)
            return null;
        }
    }

}

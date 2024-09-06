package bf.armelymg.pharmapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Map;

import org.hibernate.annotations.GenericGenerator;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.GeoPoint;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Pharmacie {
	
	@Id
	/*@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(name = "id", updatable = false, nullable = false)*/
	private String id;
	private Long groupe;
	private String pharmacie;
	private Long telephone;
	private GeoPoint localisation;
	
	public static Pharmacie fromSnapshot(DocumentSnapshot document) {
        Map<String, Object> data = document.getData();
        if (data != null) {
            return new Pharmacie(
                    // document.getId(),
                    null,
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

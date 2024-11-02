package bf.armelymg.pharmapi.entities;

import bf.armelymg.pharmapi.utils.GeoPointDeserializer;
import bf.armelymg.pharmapi.utils.GeoPointSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Map;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.GeoPoint;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
// @Entity
public class Pharmacie {
	
	//@Id
	/*@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(name = "id", updatable = false, nullable = false)*/
	//private String id;
	private Long groupe;
	private String pharmacie;
	private Long telephone;
    @JsonSerialize(using = GeoPointSerializer.class)
    @JsonDeserialize(using = GeoPointDeserializer.class)
	private GeoPoint localisation;
	
	public static Pharmacie fromSnapshot(DocumentSnapshot document) {
        Map<String, Object> data = document.getData();
        if (data != null) {
            return new Pharmacie(
                    // document.getId(),
                    //null,
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

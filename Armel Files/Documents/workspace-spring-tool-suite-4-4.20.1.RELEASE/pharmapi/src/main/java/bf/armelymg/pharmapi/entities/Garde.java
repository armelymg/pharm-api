package bf.armelymg.pharmapi.entities;

import java.util.Date;
import java.util.Map;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.type.DateTime;
import com.google.cloud.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Garde {
	
	@Id
	private String id;
  	private Date dateDebut;
  	private Date dateFin;
  	private Long groupe;
  	
  	public static Garde fromSnapshot(DocumentSnapshot document) {
        Map<String, Object> data = document.getData();
        if (data != null) {
            return new Garde(
                    // document.getId(),
                    null,
                    (Timestamp) data.get("dateDebut"),
                    (Timestamp) data.get("dateFin"),
                    (Long) data.get("groupe")
            );
        } else {
            // Gestion d'une absence de données (si nécessaire)
            return null;
        }
    }

	public Garde(String id2, Timestamp dateDebut, Timestamp dateFin, Long groupe2) {
		this.id = id2;
        this.dateDebut = dateDebut.toDate();  // Convertir le Timestamp en Date
        this.dateFin = dateFin.toDate();
        this.groupe = groupe2;
	}

}

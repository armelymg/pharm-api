package bf.armelymg.pharmapi.dto;

import java.util.Date;
import java.util.Map;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.Timestamp;

import bf.armelymg.pharmapi.entities.Garde;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GardeDto {

    private Date dateDebut;
  	private Date dateFin;
  	private Long groupe;


	public static GardeDto fromSnapshot(DocumentSnapshot document) {
        Map<String, Object> data = document.getData();
        if (data != null) {
            return new GardeDto(
                    (Timestamp) data.get("dateDebut"),
                    (Timestamp) data.get("dateFin"),
                    (Long) data.get("groupe")
            );
        } else {
            // Gestion d'une absence de données (si nécessaire)
            return null;
        }
    }

    public GardeDto(Timestamp dateDebut, Timestamp dateFin, Long groupe2) {
        this.dateDebut = dateDebut.toDate();  // Convertir le Timestamp en Date
        this.dateFin = dateFin.toDate();
        this.groupe = groupe2;
	}
    
}

package bf.armelymg.pharmapi.entities;

import com.google.cloud.firestore.DocumentSnapshot;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur {

    private String nom;
    private String prenoms;
    //private String username;
    private String telephone;
    private String email;
    private String password;
    private String type;
    private boolean isAvailable;
    private boolean isEnabled;

    public static Utilisateur fromSnapshot(DocumentSnapshot document) {
        Map<String, Object> data = document.getData();
        if (data != null) {
            return new Utilisateur(
                    (String) data.get("nom"),
                    (String) data.get("prenoms"),
                    (String) data.get("username"),
                    (String) data.get("password"),
                    (String) data.get("email"),
                    (String) data.get("type"),
                    (boolean) data.get("isAvailable"),
                    (boolean) data.get("isEnabled")
            );
        } else {
            // Gestion d'une absence de données (si nécessaire)
            return null;
        }
    }

}

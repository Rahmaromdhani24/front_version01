package Front_java.Modeles;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int matricule;
    private String nom;
    private String prenom;
    private String email;
	@Override
	public String toString() {
		return "UserDTO [matricule=" + matricule + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + "]";
	}
    
    
}

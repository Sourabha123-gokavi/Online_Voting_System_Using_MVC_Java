package Models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Voter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int voterId;
    private String voterName;
    private String email;
    private String phone;
    private String region;
    private String password;

    public int getVoterId() {
        return voterId;
    }

    public void setVoterId(int voterId) {
        this.voterId = voterId;
    }

    public String getVoterName() {
        return voterName;
    }

    public void setVoterName(String voterName) {
        this.voterName = voterName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getemail(){
        return email;
    }

    public void setemail(String email) {
        this.email=email;
        
    }
    
    public String getphone() {
        return phone;
    }

    public void setphone(String phone) {
        this.phone = phone;

    }
    
    public String getregion() {
        return region;
    }

    public void setregion(String region) {
        this.region = region;

}
}
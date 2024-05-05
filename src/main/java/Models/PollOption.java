package Models;

import javax.persistence.*;

@Entity
public class PollOption implements Models{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int optionId;
    private int oppollId;
    private String candidateName;
    private String party;
    private int opcandidateId;
    private int oppartyId;

    public int getopPollId() {
        return oppollId;
    }

    public void setopPollId(int oppollId) {
        this.oppollId = oppollId;
    }

    public int getopPartyId() {
        return oppartyId;
    }

    public void setopPartyId(int oppartyId) {
        this.oppartyId = oppartyId;
    }

    public int getopcandidateId() {
        return opcandidateId;
    }

    public void setopcandidateId(int opcandidateId) {
        this.opcandidateId = opcandidateId;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getcandidateName() {
        return candidateName;
    }

    public void setcandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getparty(){
        return party;
    }

    public void setparty(String party){
        this.party=party;
    }

}
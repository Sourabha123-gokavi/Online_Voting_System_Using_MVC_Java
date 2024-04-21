package Models;

import javax.persistence.*;


@Entity
public class Poll implements Models {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pollId;

    @Column(name = "poll_name")
    private String pollName;

    @Column(name = "status")
    private boolean status;

    @Column(name = "winner")
    private String winner;

    @Column(name = "is_voted")
    private boolean voted;
    
    @Column(name = "region")
    private String region;

    // Getters and setters
    public int getPollId() {
        return pollId;
    }

    public void setPollId(int pollId) {
        this.pollId = pollId;
    }

    public String getPollName() {
        return pollName;
    }

    public void setPollName(String pollName) {
        this.pollName = pollName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }
    
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}

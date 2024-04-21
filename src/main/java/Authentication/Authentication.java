package Authentication;

import DAO.VoteCountDAO;
import DAO.VoterDAO;
import Models.Voter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Authentication {
    @Autowired
    private VoterDAO voterDAO;
    @Autowired
    private VoteCountDAO voteCountDAO;
    public  String authenticate(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        String role = (String) session.getAttribute("role");

        if (username == null || password == null || role == null) {
            return "not authenticated";
        }
        if (role.equals("admin")) {
            if (username.equals("admin") && password.equals("admin")) {
                return "admin";
            }
        }
        if (role.equals("voter")) {
            Voter voter = voterDAO.findByUsername(username);
            if (voter != null && BCrypt.checkpw(password, voter.getPassword())) {
                return "voter";
            }
        }
        return "not authenticated";
    }
}

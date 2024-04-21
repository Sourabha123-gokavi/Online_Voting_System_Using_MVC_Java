package controllers;
import Authentication.Authentication;
import DAO.PollDAO;
import DAO.VoteCountDAO;
import DAO.VoterDAO;
import Models.Poll;
import Models.Voter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/voter")
public class VoterController {
    @Autowired
    private VoterDAO voterDAO;
    @Autowired
    private PollDAO pollDao;
    @Autowired
    private Authentication authentication;
    @Autowired
    private VoteCountDAO voteCountDAO;

    @RequestMapping("/handleLogin")
    public String handleLoginForm(HttpServletRequest request, @RequestParam("username") String username,
            @RequestParam("password") String password) {
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        session.setAttribute("password", password);
        session.setAttribute("role", "voter");
        session.setAttribute("voterId", voterDAO.getVoterId(username, password));
        return "redirect:/voter/home";
    }

    @RequestMapping("/home")
    public String home(Model model, HttpServletRequest request) {
        if (authentication.authenticate(request).equals("voter")) {
            Voter voter = getCurrentLoggedInVoter(request);
            if (voter != null) {
                List<Poll> polls = pollDao.getAllByRegion(voter.getregion());
                for (Poll poll : polls) {
                    poll.setVoted(voteCountDAO.voted(poll.getPollId(), voter.getVoterId()));
                }
                model.addAttribute("polls", polls);
                return "voterHome";
            }
            return "redirect:/voter/login";
        }
        return "redirect:/voter/login";
    }

    private Voter getCurrentLoggedInVoter(HttpServletRequest request) {
        // Retrieve voter ID from the session
        Integer voterId = (Integer) request.getSession().getAttribute("voterId");
        if (voterId != null) {
            // Fetch the voter details from the database using voterId
            return voterDAO.get(voterId);
        }
        return null;
    }




    @RequestMapping("/login")
    public String loginVoter() {
        return "voterLogin";
    }

    @RequestMapping("/displayAll")
    public String displayVoters(Model model, HttpServletRequest request) {
        if (authentication.authenticate(request).equals("admin")) {
            List<Voter> voters = voterDAO.getAll();
            model.addAttribute("voters", voters);
            return "displayVoters";
        }
        return "redirect:/admin/login";
    }

    @RequestMapping("/add")
    public String registerVotersPage(HttpServletRequest request) {
        if (authentication.authenticate(request).equals("admin")) {
            return "addVoter";
        }
        return "redirect:/admin/login";
    }

    @RequestMapping(value = "/handleForm", method = RequestMethod.POST)
    public String addVoterFormHandler(@ModelAttribute Voter voter, HttpServletRequest request) {
        if (authentication.authenticate(request).equals("admin")) {
            voterDAO.save(voter);
            return "redirect:/voter/displayAll";
        }
        return "redirect:/admin/login";
    }

    @RequestMapping("/deleteVoter/{id}")
    public String deleteVoter(@PathVariable("id") int id, HttpServletRequest request) {
        if (authentication.authenticate(request).equals("admin")) {
            voterDAO.delete(id);
            return "redirect:/voter/displayAll";
        }
        return "redirect:/admin/login";
    }

    @RequestMapping("/setPassword")
    public String setPasswordPage() {
        return "setPassword"; // Corresponds to setPassword.jsp
    }

    @RequestMapping(value = "/handleSetPassword", method = RequestMethod.POST)
    public String handleSetPassword(HttpServletRequest request,
            @RequestParam("username") String username,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match.");
            return "setPassword";
        }

        Voter voter = voterDAO.findByUsername(username);
        if (voter == null) {
            request.setAttribute("error", "User not found.");
            return "setPassword";
        }

        String encryptedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        voter.setPassword(encryptedPassword);
        voterDAO.update(voter);
        return "redirect:/voter/login";
    }
    
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // Get the session if it exists
        if (session != null) {
            session.invalidate(); // Invalidate session to clear all attributes
        }
        return "redirect:/voter/login"; // Redirect to login page
    }
}

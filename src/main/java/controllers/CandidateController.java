package controllers;

import Models.Candidate;
import Models.Voter;
import DAO.CandidateDAO;
import DAO.VoterDAO;
import Authentication.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateDAO candidateDAO;

    @Autowired
    private VoterDAO voterDAO;
    @Autowired
    private Authentication authentication;

    // Single Responsibility Principle: This method is responsible for adding a candidate.
    @RequestMapping("/addcandidate")
    public String addCandidate(Model model, HttpServletRequest request) {
        // Authentication logic ensures that only admin can access this feature.
        if (authentication.authenticate(request).equals("admin")) {
            List<Voter> votersNotCandidates = voterDAO.getVotersNotCandidates();
            model.addAttribute("voters", votersNotCandidates);
            return "addCandidate";
        }
        return "redirect:/admin/login";
        // Redirects to admin login if not authenticated.
    }

    // Single Responsibility Principle: This method is responsible for handling form submission to add a candidate.
    // Open/Closed Principle: This method is open for extension (adding new candidate-related functionalities) but closed for modification.

    @PostMapping("/handleForm")
    public String handleCandidateForm(@RequestParam("voterId") int voterId, RedirectAttributes redirectAttrs) {
        Voter voter = voterDAO.get(voterId);
        if (voter != null) {
            Candidate candidate = new Candidate();
            candidate.setcvoterId(voter.getVoterId());
            candidate.setCandidateName(voter.getVoterName());
            candidate.setEmail(voter.getemail());
            candidate.setPhone(voter.getphone());
            candidate.setRegion(voter.getregion());
            candidate.setPassword(voter.getPassword());

            candidateDAO.save(candidate);
            redirectAttrs.addFlashAttribute("success", "Candidate added successfully!");
            return "redirect:/candidate/displayAll";
        } else {
            redirectAttrs.addFlashAttribute("error", "Invalid voter selected!");
            return "redirect:/candidate/addcandidate";
        }
    }

    // Single Responsibility Principle: This method is responsible for displaying all candidates.
    @GetMapping("/displayAll")
    public String displayAllCandidates(Model model) {
        List<Candidate> candidates = candidateDAO.getAll();
        model.addAttribute("candidates", candidates);
        return "displayCandidates";
    }

    @RequestMapping("/deletecandidate/{id}")
    public String deleteVoter(@PathVariable("id") int id, HttpServletRequest request) {
        if (authentication.authenticate(request).equals("admin")) {
            candidateDAO.delete(id);
            return "redirect:/candidate/displayAll";
        }
        return "redirect:/admin/login";
    }
}
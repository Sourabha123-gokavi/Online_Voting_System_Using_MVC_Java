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

    @RequestMapping("/addcandidate")
    public String addCandidate(Model model, HttpServletRequest request) {
        if (authentication.authenticate(request).equals("admin")) {
            List<Voter> voters = voterDAO.getAll();
            model.addAttribute("voters", voters);
            return "addCandidate";
        }
        return "redirect:/admin/login";
    }

    @PostMapping("/handleForm")
    public String handleCandidateForm(@RequestParam("voterId") int voterId, RedirectAttributes redirectAttrs) {
        Voter voter = voterDAO.get(voterId);
        if (voter != null) {
            Candidate candidate = new Candidate();
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

    @GetMapping("/displayAll")
    public String displayAllCandidates(Model model) {
        List<Candidate> candidates = candidateDAO.getAll();
        model.addAttribute("candidates", candidates);
        return "displayCandidates"; // This would be your JSP file to display all candidates
    }
}
package controllers;

import java.util.List;

import Authentication.Authentication;
import DAO.PollDAO;
import DAO.CandidateDAO;
import DAO.PartyDAO;
import Models.Poll;
import Models.Candidate;
import Models.Party;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/poll")
public class PollController {

    @Autowired
    private PollDAO pollDAO;

    @Autowired
    private CandidateDAO candidateDAO;

    @Autowired
    private PartyDAO partyDAO;

    @Autowired
    private Authentication authentication;

    @RequestMapping("/polls")
    public String pollsPage(Model model, HttpServletRequest request) {
        if (authentication.authenticate(request).equals("admin")) {
            List<Poll> polls = pollDAO.getAll();
            model.addAttribute("polls", polls);
            return "polls";
        }
        return "redirect:/admin/login";
}

    @GetMapping("/add")
    public String addPollsPage(Model model, HttpServletRequest request) {
        if (authentication.authenticate(request).equals("admin")) {
            List<Candidate> candidates = candidateDAO.getAll();
            List<Party> parties = partyDAO.getAll();
            model.addAttribute("candidates", candidates);
            model.addAttribute("parties", parties);
            return "addPolls";
        }
        return "redirect:/admin/login";
    }


    @RequestMapping("/handleForm")
    public String addPollHandleForm(@ModelAttribute Poll poll) {
        poll.setStatus(true);
        poll.setWinner(null);
        pollDAO.save(poll);
        return "redirect:/poll/polls";
    }

    @RequestMapping("/deletePoll/{id}")
    public String deletePoll(@PathVariable("id") int id, HttpServletRequest request) {
        if (authentication.authenticate(request).equals("admin")) {
            pollDAO.delete(id);
            return "redirect:/admin/home";
        }
        return "redirect:/admin/login";
    }
}

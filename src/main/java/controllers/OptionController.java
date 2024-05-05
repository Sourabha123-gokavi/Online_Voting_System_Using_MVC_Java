package controllers;

import DAO.OptionDAO;
import DAO.PollDAO;
import Models.PollOption;
import jakarta.servlet.http.HttpServletRequest;
import Models.Poll;
import Models.Candidate;
import Models.Party;
import DAO.CandidateDAO;
import DAO.PartyDAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import Authentication.Authentication;


@Controller
@RequestMapping("/pollOption")
public class OptionController {
    // Using Dependency Injection to get instances of DAOs
    @Autowired
    private OptionDAO optionDAO;

    @Autowired
    private PollDAO pollDAO;

    @Autowired
    private CandidateDAO candidateDAO;

    @Autowired
    private PartyDAO partyDAO;

    @Autowired
    private Authentication authentication;
    

    // Facade Pattern: Simplifying interaction with multiple DAOs
    // how means:
    // The addOptionPage method acts as a Facade by providing a simplified
    //interface for retrieving data related to adding options to a poll. 
    //It hides the complexity of interacting with multiple DAOs 
    //(pollDAO, optionDAO, candidateDAO, and partyDAO) and presents 
    //a unified entry point for clients to fetch the required data. 
    //This abstraction reduces client dependency on individual DAO implementations,
    //promotes code maintainability, and encapsulates the details of data retrieval.
    
    @GetMapping("/add/{id}")
    public String addOptionPage(@PathVariable("id") int id, Model model) {
        Poll poll = pollDAO.get(id);
        List<PollOption> pollOptions = optionDAO.getOptionByPollId(id);
        // Fetch candidates and parties not in the current poll
        List<Candidate> candidates = candidateDAO.getCandidatesNotInPoll(id);
        List<Party> parties = partyDAO.getPartiesNotInPoll(id);

        model.addAttribute("poll", poll);
        model.addAttribute("options", pollOptions);
        model.addAttribute("candidates", candidates);
        model.addAttribute("parties", parties);
        return "addOption";
    }

    @PostMapping("/handleForm")
    public String addOptionHandleForm(@RequestParam("candidateId") int candidateId,
            @RequestParam("partyId") int partyId,
            @RequestParam("pollId") int pollId) {
        PollOption pollOption = new PollOption();
        Candidate candidate = candidateDAO.get(candidateId);
        Party party = partyDAO.get(partyId);

        pollOption.setopPollId(pollId);
        pollOption.setcandidateName(candidate.getCandidateName()); // Adapt based on actual method
        pollOption.setopcandidateId(candidate.getCandidateId());
        pollOption.setparty(party.getPartyName()); // Adapt based on actual method
        pollOption.setopPartyId(party.getPartyId());

        optionDAO.save(pollOption);
        return "redirect:/pollOption/add/" + pollId;
    }

    @GetMapping("/toVote/{id}")
    public String displayOptionsToVoter(@PathVariable("id") int pollId, Model model) {
        Poll poll = pollDAO.get(pollId);
        List<PollOption> pollOptions = optionDAO.getOptionByPollId(pollId);
        model.addAttribute("poll", poll);
        model.addAttribute("options", pollOptions);
        return "optionsToVote";
    }

    @GetMapping("/delete/{pollId}/{optionId}")
    public String deleteOption(@PathVariable("pollId") int pollId, @PathVariable("optionId") int optionId) {
        optionDAO.delete(optionId);
        return "redirect:/pollOption/add/" + pollId;
    }

    @RequestMapping("/delete/{pollId}/{optionId}")
    public String deleteOption(@PathVariable("pollId") int pollId, @PathVariable("optionId") int optionId, HttpServletRequest request) {
        if (authentication.authenticate(request).equals("admin")) {
            partyDAO.delete(optionId);
            return "redirect:/pollOption/add/" + pollId;
        }
        return "redirect:/admin/login";
    }
}

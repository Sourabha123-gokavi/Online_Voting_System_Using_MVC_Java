package controllers;

import DAO.PartyDAO;
import Models.Party;
import Authentication.Authentication; // Import the Authentication class
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/party")
public class PartyController {
    @Autowired
    private PartyDAO partyDAO;
    @Autowired
    private Authentication authentication; // Autowire the Authentication class

    @RequestMapping("/add")
    public String addPartyPage(HttpServletRequest request) {
        if (authentication.authenticate(request).equals("admin")) {
            return "addParty";
        }
        return "redirect:/admin/login";
    }

    @RequestMapping(value = "/handleForm", method = RequestMethod.POST)
    public String addPartyFormHandler(@ModelAttribute Party party, HttpServletRequest request) {
        if (authentication.authenticate(request).equals("admin")) {
            partyDAO.save(party);
            return "redirect:/party/displayAll";
        }
        return "redirect:/admin/login";
    }

    @RequestMapping("/displayAll")
    public String displayParties(Model model, HttpServletRequest request) {
        if (authentication.authenticate(request).equals("admin")) {
            List<Party> parties = partyDAO.getAll();
            model.addAttribute("parties", parties);
            return "displayParty";
        }
        return "redirect:/admin/login";
    }

    @RequestMapping("/deleteParty/{id}")
    public String deleteParty(@PathVariable("id") int id, HttpServletRequest request) {
        if (authentication.authenticate(request).equals("admin")) {
            partyDAO.delete(id);
            return "redirect:/party/displayAll";
        }
        return "redirect:/admin/login";
    }
}

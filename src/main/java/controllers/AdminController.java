// authentication method; follows the singletone pattern
// If it's designed to have only one instance throughout the application and provides 
// a global point of access to that instance, then it follows the Singleton pattern.



package controllers;
import Authentication.Authentication;
import DAO.PollDAO;
import Models.Poll;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


// Singleton Pattern
//The Authentication class 
// is injected into the AdminController using Spring's dependency injection (@Autowired annotation).

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PollDAO pollDAO;
    @Autowired
    private Authentication authentication;

    // Single Responsibility Principle: Each method has a single responsibility 
    // like mentioned below

    // Handles the request for admin login page
    @RequestMapping("/login")
    public String loginAdmin() {
        return "adminLogin";
    }

    // Handles the request to display admin home page
    @RequestMapping("/home")
    public String home(HttpServletRequest request, Model model) {
        if (authentication.authenticate(request).equals("admin")) {
            List<Poll> polls = pollDAO.getAll();
            model.addAttribute("polls", polls);
            return "adminHomePage";
        }
        return "redirect:login";
    }

    // Handles form submission from admin login page
    @RequestMapping("/handleForm")
    public String formHandler(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        session.setAttribute("password", password);
        session.setAttribute("role", "admin");
        return "redirect:/admin/home";
    }
    // Handles admin logout
    @RequestMapping("/logout")
    public String formHandler(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        session.removeAttribute("password");
        session.removeAttribute("role");
        return "redirect:/admin/login";
    }
}

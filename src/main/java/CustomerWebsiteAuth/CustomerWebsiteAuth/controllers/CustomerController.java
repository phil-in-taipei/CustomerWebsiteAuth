package CustomerWebsiteAuth.CustomerWebsiteAuth.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.security.RolesAllowed;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@Controller
public class CustomerController {
    @GetMapping("/")
    public String viewHomePage() { //Model model
        // Here you call the service to retrieve all the customers
        //final List<Customer> customerList = customerService.getAllCustomers();
        // Once the customers are retrieved, you can store them in model and return it to the view
        //model.addAttribute("customerList", customerList);
        return "index";
    }
}

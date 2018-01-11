package ca.uwindsor.ices.controller;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ca.uwindsor.ices.jpa.Customer;
import ca.uwindsor.ices.jpa.User;
import ca.uwindsor.ices.repository.CustomerRepository;
import ca.uwindsor.ices.repository.UserRepository;

@Controller
public class UserController {

    @Autowired
    private UserRepository usrRepo;

    @Autowired
    private CustomerRepository custRepo;

    @Autowired
    private BCryptPasswordEncoder bcpe;

    @GetMapping("/register")
    public String showPage() {
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(HttpServletRequest request, String error) {
        User usr = new User();
        usr.setEmail(request.getParameter("email"));
        Optional<User> existing = usrRepo.findByEmail(request.getParameter("email"));
        System.out.print("-----"+existing.toString());
        if (!existing.toString().contains("Optional.empty")){
            error = "error";
            return "redirect:/register?error";
        }
        else {
            usr.setPassword(bcpe.encode(request.getParameter("password")));
            usr.setDateRegist(new Date(System.currentTimeMillis()));
            usr.setStatus("A");
            usr.setUserRegist("SYSTEM");

            Customer cust = new Customer();
            cust.setDateBirth(new Date(System.currentTimeMillis()));
            cust.setCodeCountry("CA");
            cust.setCodeProvince(request.getParameter("province"));
            cust.setCodePostal(request.getParameter("code"));
            cust.setNameAddress(request.getParameter("address"));
            cust.setNameCity(request.getParameter("city"));
            cust.setNameFirst(request.getParameter("firstname"));
            cust.setNameLast(request.getParameter("lastname"));
            cust.setNumbPhone(request.getParameter("number"));
            cust.setTypePhone("CELL");
            cust.setDateRegist(new Date(System.currentTimeMillis()));
            cust.setUserRegist("SYSTEM");
            cust.setNameMiddle(null);
            cust.setStatus("A");
            cust.setUserRegist("SYSTEM");
            custRepo.save(cust);
            usr.setCustomer(cust);
            usrRepo.save(usr);
        }
        return "regis-confirmation";
    }
}

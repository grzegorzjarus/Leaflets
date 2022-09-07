package pl.coderslab.leaflets.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.leaflets.model.Distributor;
import pl.coderslab.leaflets.repository.DistributorRepository;

import java.time.LocalDate;
import java.util.Date;

@Controller
public class DistributorController {

    private final DistributorRepository distributorRepository;

    public DistributorController(DistributorRepository distributorRepository) {
        this.distributorRepository = distributorRepository;
    }

    @GetMapping("/registerDistributor")
    public String registerDistributor(Model model){
        Distributor distributor = new Distributor();
        model.addAttribute(distributor);
        return "distributor/registerDistributor";
    }

    @PostMapping("/registerDistributor")
    public String registerDistributor(Distributor distributor){
        System.out.println(distributor);
        distributor.setPassword(BCrypt.hashpw(distributor.getPassword(),BCrypt.gensalt()));
        distributorRepository.save(distributor);
        return "leaflets";
    }

    @GetMapping("/date/test")
    @ResponseBody
    public String testDate(){
        LocalDate localDate = LocalDate.now();
       LocalDate date = LocalDate.of(2022,9,12);
       String stringDate ="2022-09-14";
       LocalDate date2 = LocalDate.parse(stringDate);
       return date2.toString();
    }
}

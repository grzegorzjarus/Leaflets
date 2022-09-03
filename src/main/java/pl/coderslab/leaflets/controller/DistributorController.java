package pl.coderslab.leaflets.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.leaflets.model.Distributor;
import pl.coderslab.leaflets.repository.DistributorRepository;

@Controller
public class DistributorController {

    @Autowired
    DistributorRepository distributorRepository;

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
}

package pl.coderslab.leaflets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.leaflets.model.Client;
import pl.coderslab.leaflets.repository.ClientRepository;
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public Iterable<Client> getClientList() {
        return clientRepository.findAll();

    }
    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }
    public void save(Client client) {
        clientRepository.save(client);
    }
}

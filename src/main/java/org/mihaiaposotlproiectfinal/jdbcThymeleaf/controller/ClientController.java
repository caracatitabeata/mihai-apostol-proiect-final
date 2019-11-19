package org.mihaiaposotlproiectfinal.jdbcThymeleaf.controller;


import org.mihaiaposotlproiectfinal.jdbcThymeleaf.entities.Client;
import org.mihaiaposotlproiectfinal.jdbcThymeleaf.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/clientList")
    public String getClients(Model model) {
        model.addAttribute("clients", clientService.getClientDao().getAllClients());
        model.addAttribute("client", new Client());
        return "list";
    }

    @GetMapping(value ="/clientListJson")
    public Collection<Client> getWrecked(){
        return clientService.getClientDao().getClientProduct();
    }

//        @PutMapping(value = "/client")
//        public Client updateClientById(@RequestBody Client client) {
//        clientService.getClientDao().updateClientPhoneNumber(client);
//        return client;
//        }

    @PostMapping(value = "/client/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientService.getClientDao().deleteClientById(id);
        return "redirect:/clients/clientList";
        }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="n", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("sth", name);
        return "greeting";
    }

    @PostMapping(value = "/submit")
    public String submitClient(@ModelAttribute Client client, Model model) {
            try {
                clientService.validateName(client);
            }
            catch (RuntimeException e){
                return "failedLogin";
            }
        clientService.getClientDao().insertClient(client);
        model.addAttribute("clientName", client.getClientName());
        return "clientLogin";
    }

    @GetMapping (value= "/loginPage")
    public String formClient(Model model) {
        model.addAttribute("client", new Client());
        return "clientForm";
    }
}


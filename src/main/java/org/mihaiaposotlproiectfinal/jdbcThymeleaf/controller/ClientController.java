package org.mihaiaposotlproiectfinal.jdbcThymeleaf.controller;


import lombok.AllArgsConstructor;
import org.mihaiaposotlproiectfinal.jdbcThymeleaf.entities.Client;
import org.mihaiaposotlproiectfinal.jdbcThymeleaf.entities.Product;
import org.mihaiaposotlproiectfinal.jdbcThymeleaf.service.ClientService;
import org.mihaiaposotlproiectfinal.jdbcThymeleaf.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//@RestController
@AllArgsConstructor
@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/clientList")
    public String getClients(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("client", new Client());
        return "list";
    }

    @GetMapping(value ="/clientsProducts")
    public String getWrecked(Model model){

        model.addAttribute("clients", clientService.getClientsAndProductName());

        model.addAttribute("client", new Client());
        model.addAttribute("clientProducts", new Product());
        return "clientsProducts";
    }

    @PostMapping(value = "/client/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientService.deleteClientById(id);
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
            clientService.insertClient(client);
        }
        catch (RuntimeException e){
            return "failedLogin";
        }
        model.addAttribute("clientName", client.getClientName());
        return "clientLogin";
    }

    @GetMapping (value= "/loginPage")
    public String formClient(Model model) {
        model.addAttribute("client", new Client());
        return "clientForm";
    }

    //Security
//    @GetMapping("/admin")
//    public String admin() {
//        return "/admin";
//    }
//
//    @GetMapping("/user")
//    public String user() {
//        return "/user";
//    }
//
//    @GetMapping("/login")
//    public String login() { return "/login"; }
//
//    @GetMapping("/home")
//    public String home() {
//        return "/home";
//    }
}


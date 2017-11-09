package com.jcg.examples.controller;

import com.jcg.examples.models.*;
import com.jcg.examples.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.jcg.examples.forms.ListUserForm;
import com.jcg.examples.forms.UserRow;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Controller
public class UserController {

    private UserService userService = new UserServiceImpl();
    private TransactionService transactionService = new TransactionServiceImpl();
    private BankAccountService bankAccountService = new BankAccountServiceImpl();
    private ClientService clientService = new ClientServiceImpl();
    private CardService cardService = new CardServiceImpl();

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String indexUser() {
        return "homeUserPage";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String indexAdmin() {
        return "homeAdminPage";
    }

    @RequestMapping(value = "/user/about", method = RequestMethod.GET)
    public String aboutUser() {
        return "aboutUserPage";
    }

    @RequestMapping(value = "/admin/about", method = RequestMethod.GET)
    public String aboutAdmin() {
        return "aboutAdminPage";
    }

    @RequestMapping(value = "/user/show_transactions", method = RequestMethod.GET)
    public ModelAndView showTransactions() {
        ModelAndView m = new ModelAndView("showTransactionsForUser");
        List<Transfer> tr;
        int id = 3;
        try {
            tr = transactionService.selectAll(id);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            m.addObject("error", e.getMessage());
            return m;
        }
        m.addObject("transactions",tr);
        return m;
    }

    @RequestMapping(value = "/user/make_payment", method = RequestMethod.GET)
    public ModelAndView makePayment(){
        ModelAndView m = new ModelAndView("makePayment");
        return m;
    }

    @RequestMapping(value = "/user/make_payment", method = RequestMethod.POST)
    public String submitMakePayment(HttpServletRequest request,Model m, @ModelAttribute("card") Card card,
                                    @ModelAttribute("transfer") Transfer tr) {
        try {

            String login = request.getUserPrincipal().getName();
            User user = userService.findByLog(login);
            Client c = clientService.findByUserId(user.getId());
            Card cc = cardService.findByNumber(card.getCard_number());
            BankAccount bankAcc = bankAccountService.getById(cc.getAccount_id());

            if (bankAcc.getIs_blocked()==false) {
                bankAcc.setBalance(bankAcc.getBalance() - tr.getTr_sum());
                bankAccountService.update(bankAcc);

                tr.setCard_id((int) (long) cc.getId());
                tr.setTr_type(0);
                tr.setTr_date(java.sql.Date.valueOf(LocalDate.now()));
                tr.setClient_id((int) (long) c.getId());
                transactionService.save(tr);
            }

        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            m.addAttribute("error", e.getMessage());
            return "user/make_payment";
        }
        return "redirect:/user";
    }

    @RequestMapping(value = "/user/add_funds", method = RequestMethod.GET)
    public ModelAndView addFunds(){
        ModelAndView m = new ModelAndView("addFunds");
        return m;
    }

    @RequestMapping(value = "/user/add_funds", method = RequestMethod.POST)
    public String submitAddFunds(HttpServletRequest request,Model m, @ModelAttribute("card") Card card,
                                    @ModelAttribute("transfer") Transfer tr) {
        try {

            String login = request.getUserPrincipal().getName();
            User user = userService.findByLog(login);
            Client c = clientService.findByUserId(user.getId());
            Card cc = cardService.findByNumber(card.getCard_number());
            BankAccount bankAcc = bankAccountService.getById(cc.getAccount_id());

            if (bankAcc.getIs_blocked()==false) {
                bankAcc.setBalance(bankAcc.getBalance() + tr.getTr_sum());
                bankAccountService.update(bankAcc);

                tr.setCard_id((int) (long) cc.getId());
                tr.setTr_type(1);
                tr.setTr_date(java.sql.Date.valueOf(LocalDate.now()));
                tr.setClient_id((int) (long) c.getId());
                transactionService.save(tr);
            }

        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            m.addAttribute("error", e.getMessage());
            return "user/add_funds";
        }
        return "redirect:/user";
    }

    @RequestMapping(value = "/admin/show_transactions", method = RequestMethod.GET)
    public ModelAndView showAllTransactions() {
        List<Transfer> tr = transactionService.selectAll();
        ModelAndView m = new ModelAndView("showTransactionsForAdmin");
        m.addObject("transactions",tr);
        return m;
    }

    @RequestMapping(value = "/admin/show_users", method = RequestMethod.GET)
    public ModelAndView showAllUsers() {
        ModelAndView m = new ModelAndView("showUsers");
        List<User> users = new LinkedList<User>();
        try {
            users = userService.selectAll();
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            m.addObject("error", e.getMessage());
            return m;
        }
        m.addObject("users",users);
        return m;
    }

    @RequestMapping(value = "/user/show_user_bank-accounts")
    public ModelAndView showBankAccounts(HttpServletRequest request){
        ModelAndView m = new ModelAndView("showUserBankAccounts");
        List<Card> cards = new LinkedList<Card>();
        List<BankAccount> bankAccs = new LinkedList<BankAccount>();
        String login = request.getUserPrincipal().getName();
        Client client = null;
        try {
            User user = userService.findByLog(login);
            client = clientService.findByUserId(user.getId());
            cards = cardService.findByClientId(client.getId());
            for (Card c: cards) {
                bankAccs.add(bankAccountService.getById(c.getAccount_id()));
            }
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            m.addObject("error", e.getMessage());
            return m;
        }
        m.addObject("bankAccs",bankAccs);
        return m;
    }

    @RequestMapping(value = "/user/show_user_bank-accounts/change_status/{id}", method = RequestMethod.GET)
    public String changeUserBankAccountStatus(@PathVariable("id") long id){
        try {
            bankAccountService.changeStatusById(id);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            return "redirect:/user/show_user_bank-accounts";
        }
        return "redirect:/user/show_user_bank-accounts";
    }

    @RequestMapping(value = "/admin/show_bank-accounts")
    public ModelAndView changeBankAccountStatus(){
        ModelAndView m = new ModelAndView("showBankAccounts");
        List<BankAccount> bankAccs = new LinkedList<BankAccount>();
        try {
            bankAccs = bankAccountService.selectAll();
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            m.addObject("error", e.getMessage());
            return m;
        }
        m.addObject("bankAccs",bankAccs);
        return m;
    }

    @RequestMapping(value = "/admin/show_bank-accounts/change_status/{id}", method = RequestMethod.GET)
    public String changeBankAccountStatus(@PathVariable("id") long id){
        try {
            bankAccountService.changeStatusById(id);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            return "redirect:/admin/show_bank-accounts";
        }
        return "redirect:/admin/show_bank-accounts";
    }

    @RequestMapping(value = "/admin/create_acc", method = RequestMethod.GET)
    public String createBankAccount(){
        try {
            BankAccount acc = new BankAccount();
            acc.setBalance(0);
            acc.setIs_blocked(false);
            bankAccountService.save(acc);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            return "redirect:/admin/show_bank-accounts";
        }
        return "redirect:/admin/show_bank-accounts";
    }


    @RequestMapping(value = "/admin/delete_acc/{id}", method = RequestMethod.GET)
    public String deleteBankAccountById(@PathVariable("id") long id ) {

        try {
            bankAccountService.deleteById(id);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            return "redirect:/admin/show_bank-accounts";
        }
        return "redirect:/admin/show_bank-accounts";
    }


    @RequestMapping(value = "/admin/show_clients")
    public ModelAndView showClients(){
        ModelAndView m = new ModelAndView("showClients");
        List<Client> clients = new LinkedList<Client>();
        try {
            clients = clientService.selectAll();
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            m.addObject("error", e.getMessage());
            return m;
        }
        m.addObject("clients",clients);
        return m;
    }

    @RequestMapping(value = "/admin/create_client", method = RequestMethod.GET)
    public ModelAndView createClient(){
        ModelAndView m = new ModelAndView("createClientByAdmin");
        return m;
    }

    @RequestMapping(value = "/admin/create_client", method = RequestMethod.POST)
    public String submitCreateClient(Model m, @ModelAttribute("client") Client client) {
        try {
            clientService.save(client);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            m.addAttribute("error", e.getMessage());
            return "createClientByAdmin";
        }
        return "redirect:/admin/show_clients";
    }

    @RequestMapping(value = "/admin/update_client/{id}", method = RequestMethod.GET)
    public ModelAndView updateClient(@PathVariable("id") long id){
        ModelAndView m = new ModelAndView("updateClientByAdmin");
        Client client = clientService.findById(id);
        m.addObject("client", client);
        return m;
    }

    @RequestMapping(value = "/admin/update_client/{id}", method = RequestMethod.POST)
    public String submitUpdateClient(Model m, @ModelAttribute("client") Client client) {
        try {
            clientService.update(client);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            m.addAttribute("error", e.getMessage());
            return "redirect:/admin/show_clients";
        }
        return "redirect:/admin/show_clients";
    }

    @RequestMapping(value = "/admin/delete_client/{id}", method = RequestMethod.GET)
    public String deleteClientById(@PathVariable("id") long id ) {

        try {
            clientService.deleteById(id);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            return "redirect:/admin/show_clients";
        }
        return "redirect:/admin/show_clients";
    }

    @RequestMapping(value = "/admin/show_cards")
    public ModelAndView showCards(){
        ModelAndView m = new ModelAndView("showCards");
        List<Card> cards = new LinkedList<Card>();
        try {
            cards = cardService.selectAll();
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            m.addObject("error", e.getMessage());
            return m;
        }
        m.addObject("cards",cards);
        return m;
    }

    @RequestMapping(value = "/admin/create_card", method = RequestMethod.GET)
    public ModelAndView createCard(){
        ModelAndView m = new ModelAndView("createCardByAdmin");
        return m;
    }

    @RequestMapping(value = "/admin/create_card", method = RequestMethod.POST)
    public String submitCreateCard(Model m, @ModelAttribute("card") Card card) {
        try {
            cardService.save(card);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            m.addAttribute("error", e.getMessage());
            return "createCardByAdmin";
        }
        return "redirect:/admin/show_cards";
    }

    @RequestMapping(value = "/admin/update_card/{id}", method = RequestMethod.GET)
    public ModelAndView updateCard(@PathVariable("id") long id){
        ModelAndView m = new ModelAndView("updateCardByAdmin");
        Card card = cardService.findById(id);
        m.addObject("card", card);
        return m;
    }

    @RequestMapping(value = "/admin/update_card/{id}", method = RequestMethod.POST)
    public String submitUpdateCard(Model m, @ModelAttribute("card") Card card) {
        try {
            cardService.update(card);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            m.addAttribute("error", e.getMessage());
            return "redirect:/admin/show_cards";
        }
        return "redirect:/admin/show_cards";
    }

    @RequestMapping(value = "/admin/delete_card/{id}", method = RequestMethod.GET)
    public String deleteCardById(@PathVariable("id") long id ) {

        try {
            cardService.deleteById(id);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            return "redirect:/admin/show_cards";
        }
        return "redirect:/admin/show_cards";
    }

    @RequestMapping(value = "/admin/create", method = RequestMethod.GET)
    public ModelAndView createByAdmin() {
        ModelAndView m = new ModelAndView("createUserByAdmin");
        return m;
    }

    @RequestMapping(value = "/admin/create", method = RequestMethod.POST)
    public String submitCreateByAdmin(Model m, @ModelAttribute("user") User user) {
        try {
            userService.save(user);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            m.addAttribute("error", e.getMessage());
            return "createUserByAdmin";
        }
        return "redirect:/admin/show_users";
    }

    @RequestMapping(value = "/admin/update/{id}", method = RequestMethod.GET)
    public ModelAndView updateByAdmin(@PathVariable("id") long id) {
        ModelAndView m = new ModelAndView("updateUserByAdmin");
        User user = userService.findById(id);
        m.addObject("user", user);
        return m;
    }

    @RequestMapping(value = "/admin/update/{id}", method = RequestMethod.POST)
    public String submitUpdateByAdmin(Model m, @ModelAttribute("user") User user) {
        try {
            userService.update(user);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            m.addAttribute("error", e.getMessage());
            m.addAttribute("user", user);
            return "updateUserByAdmin";
        }
        return "redirect:/admin/show_users";
    }

    @RequestMapping(value = "/admin/find", method = RequestMethod.GET)
    public String find() {
        return "searchPage";
    }

    @RequestMapping(value = "/admin/find", method = RequestMethod.POST)
    public ModelAndView findByLog(Model model, HttpServletRequest request) {
        String log = request.getParameter("login");
        User user = new User();
        ModelAndView m = new ModelAndView("searchResults");
        try {
            user = userService.findByLog(log);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            m.addObject("error", e.getMessage());
            return m;
        }
        m.addObject("user", user);
        return m;
    }

    @RequestMapping(value = "/admin/delete/{id}", method = RequestMethod.GET)
    public String deleteById(@PathVariable("id") long id ) {

        try {
            userService.deleteById(id);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            //m.addAttribute("error", e.getMessage()); ///////////////////////////////////////////////????????
            return "createUserByAdmin";
        }
        return "redirect:/admin/show_users";
    }


    @RequestMapping(value = "/admin/delete_users")
    public ModelAndView delete(HttpServletRequest request) {
        List<User> users = userService.selectAll();
        List <UserRow>  ur = new ArrayList<UserRow>();
        for (User u: users) {
            ur.add(new UserRow(u));
        }
        ListUserForm l = new ListUserForm();
        l.setList(ur);
        return new ModelAndView("deleteUsers","ListUserForm",l);
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.POST)
    public String delete(@ModelAttribute("ListUserForm") ListUserForm listUserForm, BindingResult result) {
        //TODO
        try{
            List<UserRow>  selectedList = listUserForm.getList();
            List<Long> id = new ArrayList();
            for (UserRow ur : selectedList) {
                if (ur.isCheckControl() == false) {
                    id.add(ur.getUser().getId());
                }
            }
            System.out.println(selectedList);
            for (int i = 0; i < id.size(); i++) {
                userService.deleteById(id.get(i));
            }
        } catch(Exception e){}
        return "redirect:/admin/delete_users";
    }


    @RequestMapping(value = "/login/registration", method = RequestMethod.POST)
    public String registrationPost(Model m, @ModelAttribute("user") User user, @ModelAttribute("client") Client client) {
        try {
            user.setIs_admin("user");
            userService.save(user);
            client.setUser_id((int) (long) user.getId()); // так делать не надо!!!
            clientService.save(client);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            m.addAttribute("error", e.getMessage());
            return "/login/registration";
        }
        return "redirect:/login";

     }

    @RequestMapping(value = "/login/registration", method = RequestMethod.GET)
    public ModelAndView registrationGet() {
        ModelAndView m = new ModelAndView("registration");
        return m;
    }

}


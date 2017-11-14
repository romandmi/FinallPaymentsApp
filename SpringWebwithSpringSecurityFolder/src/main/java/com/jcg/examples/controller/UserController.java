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

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;


/**
 * Controller for all operations in application except login and logout.
 */
@Controller
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);
    private UserService userService = new UserServiceImpl();
    private TransactionService transactionService = new TransactionServiceImpl();
    private BankAccountService bankAccountService = new BankAccountServiceImpl();
    private ClientService clientService = new ClientServiceImpl();
    private CardService cardService = new CardServiceImpl();

    /**
     * Moves to user home page
     * @return homeUserPage.jsp
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String indexUser() {
        return "homeUserPage";
    }

    /**
     * Moves to admin home page
     * @return homeAdminPage.jsp
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String indexAdmin() {
        return "homeAdminPage";
    }

    /**
     * Shows users transactions.
     * @return
     */
    @RequestMapping(value = "/user/show_transactions", method = RequestMethod.GET)
    public ModelAndView showTransactions() {
        ModelAndView m = new ModelAndView("showTransactionsForUser");
        List<Transfer> tr;
        int id = 3;
        try {
            tr = transactionService.selectAll(id);
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            m.addObject("error", e.getMessage());
            return m;
        }
        m.addObject("transactions",tr);
        return m;
    }

    /**
     * Moves to /user/make_payment url.
     * @return makePayment.jsp
     */
    @RequestMapping(value = "/user/make_payment", method = RequestMethod.GET)
    public ModelAndView makePayment(){
        ModelAndView m = new ModelAndView("makePayment");
        return m;
    }

    /**
     *
     * @param request
     * @param m
     * @param card
     * @param tr
     * @return
     */
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
            logger.error(e.getMessage());
            m.addAttribute("error", e.getMessage());
            return "user/make_payment";
        }
        return "redirect:/user";
    }

    /**
     * Moves to /user/add_funds url
     * @return addFunds.jsp
     */
    @RequestMapping(value = "/user/add_funds", method = RequestMethod.GET)
    public ModelAndView addFunds(){
        ModelAndView m = new ModelAndView("addFunds");
        return m;
    }

    /**
     * This method replenishes bank account(card)
     * @param request getting request from /user/add_funds
     * @param m
     * @param card
     * @param tr
     * @return user homepage.
     */
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
            logger.error(e.getMessage());
            m.addAttribute("error", e.getMessage());
            return "user/add_funds";
        }
        return "redirect:/user";
    }

    /**
     *Shows transaction for admin.
     * @return m
     */
    @RequestMapping(value = "/admin/show_transactions", method = RequestMethod.GET)
    public ModelAndView showAllTransactions() {
        List<Transfer> tr = transactionService.selectAll();
        ModelAndView m = new ModelAndView("showTransactionsForAdmin");
        m.addObject("transactions",tr);
        return m;
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/admin/show_users", method = RequestMethod.GET)
    public ModelAndView showAllUsers() {
        ModelAndView m = new ModelAndView("showUsers");
        List<User> users = new LinkedList<User>();
        try {
            users = userService.selectAll();
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
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
            logger.error(e.getMessage());
            m.addObject("error", e.getMessage());
            return m;
        }
        m.addObject("bankAccs",bankAccs);
        return m;
    }

    /**
     * Changes users bank account status.
     * @param id bank account's id.
     * @return redirect to updated /user/show_user_bank-accounts.
     */
    @RequestMapping(value = "/user/show_user_bank-accounts/change_status/{id}", method = RequestMethod.GET)
    public String changeUserBankAccountStatus(@PathVariable("id") long id){
        try {
            bankAccountService.changeStatusById(id);
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
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
            logger.error(e.getMessage());
            m.addObject("error", e.getMessage());
            return m;
        }
        m.addObject("bankAccs",bankAccs); // смотреть в jsp "showBankAccounts"
        return m;
    }

    /**
     * This method blocks or unblocks bank account by pushing button block/unblock by admin.
     * @param id of bank account
     * @return updated /admin/show_bank-accounts page.
     */
    @RequestMapping(value = "/admin/show_bank-accounts/change_status/{id}", method = RequestMethod.GET)
    public String changeBankAccountStatus(@PathVariable("id") long id){
        try {
            bankAccountService.changeStatusById(id);
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            return "redirect:/admin/show_bank-accounts";
        }
        return "redirect:/admin/show_bank-accounts";
    }

    /**
     * Creates new bank account by pushing button create.
     * @return Updated /admin/show_bank-accounts page.
     */
    @RequestMapping(value = "/admin/create_acc", method = RequestMethod.GET)
    public String createBankAccount(){
        try {
            BankAccount acc = new BankAccount();
            acc.setBalance(0);
            acc.setIs_blocked(false);
            bankAccountService.save(acc);
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            return "redirect:/admin/show_bank-accounts";
        }
        return "redirect:/admin/show_bank-accounts";
    }

    /**
     * Deletes bank account by pushing delete button
     * @param id id of bank account
     * @return Updated /admin/show_bank-accounts page with deleted account.
     */
    @RequestMapping(value = "/admin/delete_acc/{id}", method = RequestMethod.GET)
    public String deleteBankAccountById(@PathVariable("id") long id ) {

        try {
            bankAccountService.deleteById(id);
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            return "redirect:/admin/show_bank-accounts";
        }
        return "redirect:/admin/show_bank-accounts";
    }

    /**
     * This method shows clients for admin
     * @return m
     */
    @RequestMapping(value = "/admin/show_clients")
    public ModelAndView showClients(){
        ModelAndView m = new ModelAndView("showClients");
        List<Client> clients = new LinkedList<Client>();
        try {
            clients = clientService.selectAll();
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            m.addObject("error", e.getMessage());
            return m;
        }
        m.addObject("clients",clients);
        return m;
    }

    /**
     * Shows page (view) "createClientByAdmin"
     * @return view "createClientByAdmin"
     */
    @RequestMapping(value = "/admin/create_client", method = RequestMethod.GET)
    public ModelAndView createClient(){
        ModelAndView m = new ModelAndView("createClientByAdmin");
        return m;
    }

    /**
     * By pushing button submin at url admin/create_client creates new client.
     * @param m
     * @param client
     * @return to updated page /admin/show_clients with new created client
     */
    @RequestMapping(value = "/admin/create_client", method = RequestMethod.POST)
    public String submitCreateClient(Model m, @ModelAttribute("client") Client client) {
        try {
            clientService.save(client);
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            m.addAttribute("error", e.getMessage());
            return "createClientByAdmin";
        }
        return "redirect:/admin/show_clients";
    }

    /**
     * This method moves to "updateClientByAdmin" view.
     * @param id cliet id
     * @return "updateClientByAdmin"
     */
    @RequestMapping(value = "/admin/update_client/{id}", method = RequestMethod.GET)
    public ModelAndView updateClient(@PathVariable("id") long id){
        ModelAndView m = new ModelAndView("updateClientByAdmin");
        Client client = clientService.findById(id);
        m.addObject("client", client);
        return m;
    }

    /**
     * Method changes client parameters after pushing button update on /admin/show_clients page.
     * @param m
     * @param client
     * @return updated page /admin/show_clients with changed clients parameters.
     */
    @RequestMapping(value = "/admin/update_client/{id}", method = RequestMethod.POST)
    public String submitUpdateClient(Model m, @ModelAttribute("client") Client client) {
        try {
            clientService.update(client);
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            m.addAttribute("error", e.getMessage());
            return "redirect:/admin/show_clients";
        }
        return "redirect:/admin/show_clients";
    }

    /**
     * This method deletes client by pushing delete button on /admin/show_clients page
     * @param id
     * @return updated page /admin/show_clients without deleted client.
     */
    @RequestMapping(value = "/admin/delete_client/{id}", method = RequestMethod.GET)
    public String deleteClientById(@PathVariable("id") long id ) {
        try {
            clientService.deleteById(id);
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
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
            logger.error(e.getMessage());
            m.addObject("error", e.getMessage());
            return m;
        }
        m.addObject("cards",cards);
        return m;
    }

    /**
     * This method moves to createCardByAdmin.jsp after pushing button create.
     * @return createCardByAdmin.jsp
     */
    @RequestMapping(value = "/admin/create_card", method = RequestMethod.GET)
    public ModelAndView createCard(){
        ModelAndView m = new ModelAndView("createCardByAdmin");
        return m;
    }

    /**
     * Create new banking card with three parameters: Card number, BankAccount Id, Clien Id.
     * @param m
     * @param card
     * @return redirect to updated page /admin/show_cards with added card.
     */
    @RequestMapping(value = "/admin/create_card", method = RequestMethod.POST)
    public String submitCreateCard(Model m, @ModelAttribute("card") Card card) {
        try {
            cardService.save(card);
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
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

    /**
     * Updates card with changing Card number, BankAccount Id, Clien Id.
     * @param m
     * @param card
     * @return updated page /admin/show_cards
     */
    @RequestMapping(value = "/admin/update_card/{id}", method = RequestMethod.POST)
    public String submitUpdateCard(Model m, @ModelAttribute("card") Card card) {
        try {
            cardService.update(card);
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            m.addAttribute("error", e.getMessage());
            return "redirect:/admin/show_cards";
        }
        return "redirect:/admin/show_cards";
    }

    /**
     * Deletes card.
     * @param id
     * @return updated page /admin/show_cards withiot deleted card.
     */
    @RequestMapping(value = "/admin/delete_card/{id}", method = RequestMethod.GET)
    public String deleteCardById(@PathVariable("id") long id ) {

        try {
            cardService.deleteById(id);
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            return "redirect:/admin/show_cards";
        }
        return "redirect:/admin/show_cards";
    }

    /**
     * Moves to admin/create page by pushing button create on admin/show_users
     * @return createUserByAdmin page
     */
    @RequestMapping(value = "/admin/create", method = RequestMethod.GET)
    public ModelAndView createByAdmin() {
        ModelAndView m = new ModelAndView("createUserByAdmin");
        return m;
    }

    /**
     * Creates new user or admin by admin and after pushing submit button redirect to updated /admin/show_users url
     * @param m
     * @param user
     * @return /admin/show_users url
     */
    @RequestMapping(value = "/admin/create", method = RequestMethod.POST)
    public String submitCreateByAdmin(Model m, @ModelAttribute("user") User user) {
        try {
            userService.save(user);
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
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

    /**
     * Updates user information at admin/show_users
     * @param m
     * @param user
     * @return updated admin/show_users page
     */
    @RequestMapping(value = "/admin/update/{id}", method = RequestMethod.POST)
    public String submitUpdateByAdmin(Model m, @ModelAttribute("user") User user) {
        try {
            userService.update(user);
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            m.addAttribute("error", e.getMessage());
            m.addAttribute("user", user);
            return "updateUserByAdmin";
        }
        return "redirect:/admin/show_users";
    }

    /**
     * Moves to admin/find
     * @return searchPage.jsp
     */
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
            logger.error(e.getMessage());
            m.addObject("error", e.getMessage());
            return m;
        }
        m.addObject("user", user);
        return m;
    }

    /**
     *
     * @param id User id
     * @return
     */
    @RequestMapping(value = "/admin/delete/{id}", method = RequestMethod.GET)
    public String deleteById(@PathVariable("id") long id ) {
        try {
            userService.deleteById(id);
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            return "createUserByAdmin";
        }
        return "redirect:/admin/show_users";
    }

    /**
     * This method makes a registration for user and creates account. There are two tables in database,
     * one for user and one for client, thats why there are both of them in params.
     * @param m
     * @param user
     * @param client
     * @return returns login page with updated database with new user.
     */

    @RequestMapping(value = "/login/registration", method = RequestMethod.POST)
    public String registrationPost(Model m, @ModelAttribute("user") User user, @ModelAttribute("client") Client client) {
        try {
            user.setIs_admin("user");
            userService.save(user);
            client.setUser_id((int) (long) user.getId()); // так делать не надо!!!
            clientService.save(client);
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            m.addAttribute("error", e.getMessage());
            return "/login/registration";
        }
        return "redirect:/login";

     }
    /**
     * On login page there is registration button. After pushing button this method makes mapping for url
     * "/login/registration" and creating new object with jsp page and returns it( user can see registration.jsp )
     * @return registration.jsp as view
     */

    @RequestMapping(value = "/login/registration", method = RequestMethod.GET)
    public ModelAndView registrationGet() {
        ModelAndView m = new ModelAndView("registration");
        return m;
    }



}


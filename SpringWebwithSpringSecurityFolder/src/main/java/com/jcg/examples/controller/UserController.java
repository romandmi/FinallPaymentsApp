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
import java.util.ArrayList;
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
        //User user = (User)request.getSession().getAttribute("currentUser");
        Client client = null;
        try {
            //TODO we should get user from current session
            User user = userService.findById(4);
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
        //FIXME
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
}


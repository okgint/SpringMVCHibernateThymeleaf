package org.ogin.ui.controller;

import org.ogin.entity.Customer;
import org.ogin.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author ogin
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    @Qualifier("customerService")
    private CustomerService customerService;

    @GetMapping("/")
    public String getName() {
        return "redirect:customers.html";
    }

    @GetMapping("/customers")
    public ModelAndView getCustomers() {
        ModelAndView model = new ModelAndView("customers.html");
        List<Customer> list = customerService.getAllCustomers();
        model.addObject("customers", list);
        return model;
    }

    @GetMapping("/editCustomerView/{customerId}")
    public ModelAndView getEditCustomerForm(@PathVariable("customerId") String customerId) {
        ModelAndView model = new ModelAndView("editCustomer.html");
        Customer customer = customerService.getCustomer(Long.parseLong(customerId));
        model.addObject("customer", customer);
        return model;
    }

    @RequestMapping(value = "/editCustomer", method = RequestMethod.POST)
    public ModelAndView editCustomer(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        model.setViewName("customers.html");
        String customerId = request.getParameter("customerId");
        String name = request.getParameter("name");
        String country = request.getParameter("country");

        Customer customer = customerService.updateCustomer(Long.parseLong(customerId), name, country);
        if (customer != null) {
            model.addObject("saveSuccess", "Customer Added Successfully: " + customer.getCustomerName());
        }
        else {
            model.addObject("saveError", "Customer Creation Failed");
        }
        List<Customer> customerList = customerService.getAllCustomers();
        model.addObject("customers", customerList);
        return model;
    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    public ModelAndView addCustomer(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        model.setViewName("customers.html");
        String name = request.getParameter("name");
        String country = request.getParameter("country");

        Customer customer = customerService.addCustomer(name, country);
        if (customer != null) {
            model.addObject("saveSuccess", "Customer Added Successfully: " + customer.getCustomerName());
        }
        else {
            model.addObject("saveError", "Customer Creation Failed");
        }
        List<Customer> customerList = customerService.getAllCustomers();
        model.addObject("customers", customerList);
        return model;
    }
}

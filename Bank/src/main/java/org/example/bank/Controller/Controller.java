package org.example.bank.Controller;
import org.example.bank.Api.ApiResponse;
import org.example.bank.Bank.Bank;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/bank/customers")
public class Controller {

    private ArrayList<Bank> customers = new ArrayList<>();


    @GetMapping("")
    public ArrayList<Bank> getCustomers() {
        return customers;
    }
    @PostMapping("/create")
    public ApiResponse addCustomer(@RequestBody Bank customer) {
        customers.add(customer);
        return new ApiResponse("customer add successfully");
    }

        @PutMapping("/update/{index}")
    public ApiResponse updateCustomer(@RequestBody Bank customer,@PathVariable int index) {
        customers.set(index, customer);
        return new ApiResponse("customer update successfully");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteCustomer(@PathVariable int index) {
        customers.remove(index);
        return new ApiResponse("customer delete successfully");
    }

    @PutMapping("/deposit/{index}/{money}")
    public ApiResponse addDepositByIndex(@PathVariable int index, @PathVariable int money) {
        customers.get(index).setBalance(customers.get(index).getBalance()+money);
        return  new ApiResponse("customer deposit successfully");
    }

    @PutMapping("/withdraw/{index}/{money}")
    public ApiResponse withdrawMoney(@PathVariable int index, @PathVariable int money) {
        if (customers.get(index).getBalance()>=money) {
            customers.get(index).setBalance(customers.get(index).getBalance()-money);
        }else  {
            return new ApiResponse("cannot withdraw money");
        }

        return new ApiResponse("customer withdraw successfully");
    }


}

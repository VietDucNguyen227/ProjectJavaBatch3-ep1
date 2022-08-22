package com.example.projectjavabatch3.controller.view.admin;

import com.example.projectjavabatch3.model.Item;
import com.example.projectjavabatch3.model.OrderItem;
import com.example.projectjavabatch3.model.Staff;
import com.example.projectjavabatch3.service.ItemService;
import com.example.projectjavabatch3.service.OrderItemService;
import com.example.projectjavabatch3.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderItemController {
    @Autowired
    OrderItemService orderItemService;

    @Autowired
    ItemService itemService;

    @GetMapping("/admin/listOrderItem")
    public String showOrderList(Model model){
        List<OrderItem> list = orderItemService.getAllOrder();
        List<Item> listItem = itemService.getAllItem();
        model.addAttribute("list", list);
        return "listOrderItem";
    }

    @GetMapping("/admin/showNewOrderItemForm")
    public String showNeOrderForm(Model model){
        OrderItem order = new OrderItem();
        model.addAttribute("orderitem", order);
        return "new_orderItem";
    }

    @PostMapping("/admin/saveOrderItem")
    public String saveOrder(@Valid @ModelAttribute("orderItem") OrderItem order,
                            BindingResult result) {
        if (result.hasErrors()) {
            return "new_orderItem";
        } else {
            orderItemService.saveOrder(order);
            return "redirect:/admin/listOrderItem";
        }
    }

    @GetMapping("/admin/showFormForUpdate2/{id}")
    public String showFormForUpdate2(@PathVariable(value = "id") long id, Model model) {
        Optional<OrderItem> order = orderItemService.findOrderByID(id);
        model.addAttribute("orderitem", order);
        return "update_orderItem";
    }

    @GetMapping("/admin/deleteOrderItem/{id}")
    public String deleteOrder(@PathVariable (value = "id") long id) {
        this.orderItemService.deleteOrder(id);
        return "redirect:/admin/listOrderItem";
    }
}

package com.example.projectjavabatch3.controller.view.admin;


import com.example.projectjavabatch3.model.Orders;
import com.example.projectjavabatch3.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * orderStatus -> init|procesing|done
 * /menu -> done
 * /orders/create -> tao order + add order item -> redirect /orders/{id}
 * GET /orders/{orderId} -> view menu | cart
 * POST /orders/{orderId}/{itemId}/add -> add item to cart id ->`
 * POST /orders/{orderId}/{itemId}/remove
 */

@Controller
public class OrdersController {
    @Autowired
    OrdersService ordersService;

    @GetMapping("/admin/listOrders")
    public String showOrdersList(Model model){
        List<Orders> list = ordersService.getAllOrders();
        model.addAttribute("list", list);
        return "listOrders";
    }

    @GetMapping("/admin/showNewOrdersForm")
    public String showNewOrderForm(Model model){
        Orders orders = new Orders();
        model.addAttribute("orders", orders);
        return "new_order";
    }

//    @GetMapping("/admin/OrderInfo")
//    public String showOrderInfo(Model model){
//        model.addAttribute("list", ordersService.findAllOrdersInfo());
//        return "admin-order";
//    }

    @GetMapping(value={"/admin/createOrderInfo"})
    public String createOrder(Model model) {
        return "admin-createOrder";
    }

//    @RequestMapping(value = "/createOrderInfoDone", method = RequestMethod.POST)
//    public String createStudentDone(@RequestParam String staffName,
//                                    @RequestParam Long orderId,
//                                    @RequestParam String itemName,
//                                    @RequestParam String type,
//                                    @RequestParam Integer quantity,
//                                    @RequestParam String customerName,
//                                    @RequestParam String customerPhone,
//                                    @RequestParam String address,
//                                    @RequestParam Date dateCheckIn,
//                                    @RequestParam Integer status) {
//
//        ordersService.saveOrderInfo(staffName, orderId, itemName,type, quantity, customerName, customerPhone, address, dateCheckIn, status);
//        return "redirect:/admin/OrderInfo";
//    }

    @PostMapping("/admin/saveOrder")
    public String saveOrder(@ModelAttribute("orders") Orders orders) {
        ordersService.saveOrders(orders);
        return "redirect:/admin/listOrders";
    }

    @GetMapping("/admin/showFormForUpdate1/{id}")
    public String showFormForUpdate1(@PathVariable(value = "id") long id, Model model) {
        Optional<Orders> orders = ordersService.findOrdersByID(id);
        model.addAttribute("orders", orders);
        return "update_order";
    }

    @GetMapping("/admin/deleteOrder/{id}")
    public String deleteOrder(@PathVariable (value = "id") long id) {
        this.ordersService.deleteOrders(id);
        return "redirect:/admin/listOrders";
    }
}

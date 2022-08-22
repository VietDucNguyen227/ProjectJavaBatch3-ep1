package com.example.projectjavabatch3.controller.view.guest;

import com.example.projectjavabatch3.model.CartItem;
import com.example.projectjavabatch3.model.OrderItem;
import com.example.projectjavabatch3.model.Orders;
import com.example.projectjavabatch3.model.Users;
import com.example.projectjavabatch3.service.ItemService;
import com.example.projectjavabatch3.service.OrderItemService;
import com.example.projectjavabatch3.service.OrdersService;
import com.example.projectjavabatch3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("cart")
public class CartController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrderItemService orderItemService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpSession session, ModelMap modelMap){
        modelMap.put("total", total(session));
        return "/cartindex";
    }


    @RequestMapping(value = "buy/{id}", method = RequestMethod.GET)
    public String buy(@PathVariable("id") long id, HttpSession session){
        if (session.getAttribute("cart") == null){
            List<CartItem> cart = new ArrayList<CartItem>();
            cart.add(new CartItem(itemService.getOne(id), 1));
            session.setAttribute("cart", cart);
        }else{
            List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
            int index = isExists(id, cart);
            if (index == -1){
                cart.add(new CartItem(itemService.getOne(id), 1));
            } else{
                int quantity = cart.get(index).getQuantity() + 1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", cart);
        }

        return "redirect:../../cart";
    }

    @RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") long id, HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        int index = isExists(id, cart);
        cart.remove(index);
        session.setAttribute("cart", cart);
        return "redirect:../../cart";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(HttpSession session, HttpServletRequest request) {
        String[] quantities = request.getParameterValues("quantity");
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        for (int i = 0; i < cart.size(); i++){
            cart.get(i).setQuantity(Integer.parseInt(quantities[i]));
        }
        session.setAttribute("cart", cart);
        return "redirect:../cart";
    }


    @RequestMapping(value = "checkout",method = RequestMethod.GET)
    public String checkout(HttpSession session, ModelMap modelMap){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Users user = userService.findByUsername(username);
            if (user == null){
            return "redirect:/";
        }else{
            //add orders
            Orders orders = new Orders();
            orders.setUsers(user);
            orders.setUsername(user.getUsername());
            orders.setDateCheckIn(new Date());
            orders.setStatus(0);
            ordersService.saveOrders(orders);

            //add order detail
            List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
            for (CartItem item : cart){
                OrderItem orderItem = new OrderItem();
                orderItem.setItemId(item.getItem().getId());
                orderItem.setOrderId(orders.getId());
                orderItem.setPrice((int) item.getItem().getPrice());
                orderItem.setQuantity(item.getQuantity());
                orderItemService.saveOrder(orderItem);

            }
            //delete cart
            session.removeAttribute("cart");

            return "thanks";
        }

    }

    private int isExists(long id, List<CartItem> cart){
        for (int i = 0; i < cart.size(); i++){
            if(cart.get(i).getItem().getId() == id){
                return i;
            }
        }
        return -1;
    }

    private double total(HttpSession session){
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        double s = 0;
        if (cart != null){
            for (CartItem item : cart){
                s += item.getQuantity() * item.getItem().getPrice();
            }
        }

        return s;
    }
}

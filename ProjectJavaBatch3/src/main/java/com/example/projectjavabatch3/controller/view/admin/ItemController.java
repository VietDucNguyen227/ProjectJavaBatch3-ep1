package com.example.projectjavabatch3.controller.view.admin;

import com.example.projectjavabatch3.dto.ItemDto;
import com.example.projectjavabatch3.model.Item;
import com.example.projectjavabatch3.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Controller
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/admin/listItem")
    public String showItem(Model model){
        List<Item> list = itemService.getAllItem();
        model.addAttribute("list",list);
        return "listItem";
    }


    @GetMapping("/showItem")
    public String viewHomePage(Model model) {
        model.addAttribute("list", itemService.getAllItem());
        return "menu";
    }

    @GetMapping("/admin/saveItem")
    public String addOrEdit(ModelMap model){
        ItemDto item = new ItemDto();
        model.addAttribute("ITEMDTO",item);
        model.addAttribute("ACTION","/admin/updateOrAdd");
        return "new_item";
    }


    @PostMapping("/admin/updateOrAdd")
    public String add(ModelMap model, @ModelAttribute("ITEMDTO") ItemDto dto){
            Optional<Item> optionalItem = itemService.findItemByID(dto.getId());
            Item newItem = null;
            Item update = null;
            String image = "Logo.png";
            Path path = Paths.get("src/main/resources/static/uploads/");
            if (optionalItem.isPresent()) {
                if (dto.getImage().isEmpty()) {
                    image = optionalItem.get().getImage();
                } else {
                    try {
                        InputStream input = dto.getImage().getInputStream();
                        Files.copy(input, path.resolve(dto.getImage().getOriginalFilename()),
                                StandardCopyOption.REPLACE_EXISTING);
                        image = dto.getImage().getOriginalFilename().toString();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                update = new Item(dto.getId(), dto.getName(), image, dto.getDetail(), dto.getPrice());
                itemService.saveItem(update);
                return "redirect:/admin/listItem";
            } else {
                if (!dto.getImage().isEmpty()) {
                    try {
                        InputStream input = dto.getImage().getInputStream();
                        Files.copy(input, path.resolve(dto.getImage().getOriginalFilename()),
                                StandardCopyOption.REPLACE_EXISTING);
                        image = dto.getImage().getOriginalFilename().toString();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            newItem = new Item(dto.getId(), dto.getName(), image, dto.getDetail(), dto.getPrice());
            itemService.saveItem(newItem);
            return "redirect:/admin/listItem";
        }
    @RequestMapping("/admin/edit/{id}")
    public String edit(ModelMap model, @PathVariable(name = "id")long id){
        Optional<Item> itemOptional = itemService.findItemByID(id);
        ItemDto dto = null;
        if (itemOptional.isPresent()) {
            Item c = itemOptional.get();
            File file = new File("src/main/resources/static/uploads/" + c.getImage());
            FileInputStream inputStream;
            try {
                inputStream = new FileInputStream(file);
                MultipartFile multiPhoto =
                        new MockMultipartFile("file", file.getName(), "text/plain",
                                org.apache.commons.io.IOUtils.toByteArray(inputStream));
                dto = new ItemDto(c.getId(), c.getName(), multiPhoto, c.getDetail(), c.getPrice());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            model.addAttribute("ITEMDTO", dto);
        } else {
            model.addAttribute("ITEMDTO",new ItemDto());
        }
        model.addAttribute("ACTION","/admin/updateOrAdd");
        return "update_item";
    }

    @GetMapping("/admin/deleteItem/{id}")
    public String deleteItem(@PathVariable (value = "id") long id) {
        this.itemService.deleteItem(id);
        return "redirect:/admin/listItem";
    }

}

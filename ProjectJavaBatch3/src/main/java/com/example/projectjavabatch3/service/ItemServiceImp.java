package com.example.projectjavabatch3.service;

import com.example.projectjavabatch3.model.Item;
import com.example.projectjavabatch3.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemServiceImp implements ItemService {
    @Autowired
    ItemRepository itemRepository;

    @Override
    public List<Item> getAllItem() {
        return itemRepository.findAll();
    }

    @Override
    public Item saveItem(Item item) {
       return itemRepository.save(item);
    }

    @Override
    public void deleteItem(long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public Optional<Item> findItemByID(long id) {
        return itemRepository.findById(id);
    }

    @Override
    public Item getOne(long id) {
        return itemRepository.findById(id).get();
    }

}

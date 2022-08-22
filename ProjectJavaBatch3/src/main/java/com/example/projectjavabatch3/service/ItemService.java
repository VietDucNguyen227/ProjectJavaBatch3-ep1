package com.example.projectjavabatch3.service;

import com.example.projectjavabatch3.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    List<Item> getAllItem();

    Item saveItem(Item item);

    void deleteItem(long id);

    Optional<Item> findItemByID(long id);

    Item getOne(long id);

}

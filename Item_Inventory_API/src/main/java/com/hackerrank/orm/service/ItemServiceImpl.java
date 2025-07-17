package com.hackerrank.orm.service;

import com.hackerrank.orm.model.Item;
import com.hackerrank.orm.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    public ItemRepository itemRepo;

    @Override
    public Item getItem(int id) {
        return null;
    }

    @Override
    public ResponseEntity<Item> createItem(Item item) {
        int itemId = item.getItemId();
        // Check if the item already exists in the database
        if (itemRepo.existsById(itemId)) {
            // Item already exists, return 400 Bad Request
            return ResponseEntity.badRequest().build();
        } else {
            // Item doesn't exist, insert it and return 201 Created
            Item savedItem = itemRepo.save(item);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
        }
    }

    @Override
    public ResponseEntity<Object> deleteItem(int itemId) {
        if (itemRepo.existsById(itemId)) {
            // deleting item
            itemRepo.deleteById(itemId);
            return ResponseEntity.status(200).body("Item deleted successfully");
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @Override
    public ResponseEntity<Item> getSpecificItem(int itemId) {
        if (itemRepo.existsById(itemId)) {
            // Return item
            return ResponseEntity.status(HttpStatus.OK).body(itemRepo.findById(itemId).get());
        } else {
            // Item not found
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public String updateItem(Item e, int id) {
        return null;
    }

    @Override
    public List<Item> getAllItems() {
        // Fetch all the items
        return itemRepo.findAll();
    }

}

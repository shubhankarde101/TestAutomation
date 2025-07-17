package com.hackerrank.orm.controller;

import com.hackerrank.orm.model.Item;

import com.hackerrank.orm.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {
    @Autowired
    public ItemService itemService;

    //1. insert POST
    @RequestMapping(value = "/app/item", method = RequestMethod.POST)
    public ResponseEntity<Item> add(@RequestBody Item newItem) {
       return itemService.createItem(newItem);
    }

    //5. get by itemId GET
    @RequestMapping(value = "/app/item/{id}", method = RequestMethod.GET)
    public ResponseEntity<Item> one(@PathVariable("id") int id) {
        return itemService.getSpecificItem(id);
    }

    //6. get all GET
    @RequestMapping(value = "/app/item", method = RequestMethod.GET)
    public List<Item> all() {
        return itemService.getAllItems();
    }

    //3. delete by itemId DELETE
    @RequestMapping(value = "app/item/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> erase(@PathVariable("id") int id){
        return itemService.deleteItem(id);
    }


    //2. update PUT


    //4. delete all DELETE


    //7. filters by fields ?itemStatus={status}&itemEnteredByUser={modifiedBy} GET


    //8. select all with sorting and pagination ?pageSize={pageSize}&page={page}&sortBy={sortBy} GET

}

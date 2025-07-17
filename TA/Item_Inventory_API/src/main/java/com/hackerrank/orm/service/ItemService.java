package com.hackerrank.orm.service;

import com.hackerrank.orm.model.Item;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ItemService {

   Item getItem(int id);
   ResponseEntity<Item> createItem(Item e);
   ResponseEntity<Item> getSpecificItem(int id);
   ResponseEntity<Object> deleteItem(int id);
   String updateItem(Item e,int id);
   List<Item> getAllItems();






}

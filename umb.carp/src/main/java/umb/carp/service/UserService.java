package umb.carp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import umb.carp.dto.ItemDTO;
import umb.carp.exception.UserNotFoundException;
import umb.carp.model.Item;
import umb.carp.repository.ItemRepository;

@Service
public class UserService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	
	//return all users
	public List<Item> getItems(){
		return itemRepository.findAll();
	}
	
	private Item findById(int id) {
		Item item = this.itemRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Item non presente per id: " + id));
		return item;
	}
	
	//return a item of the list
	public Item getItem(int id) {
		Item item = findById(id);
		return item;
	}
	
	public Item save(ItemDTO itemDTO) {
		Item item = new Item();
		item = setItem(item, itemDTO);
		return this.itemRepository.save(item);
	}
	
	//delete a item
	public Map<String, Boolean> deleteItem(int id) {
		Item item = findById(id);
		this.itemRepository.delete(item);
		Map<String, Boolean> response = new HashMap<>();
        response.put("success", Boolean.TRUE);
        return response;
	}
	
	public Item updateItem(int id, ItemDTO itemDTO) {
		Item item = findById(id);
		item = setItem(item, itemDTO);
		return this.itemRepository.save(item);
	}
	
	public Item setItem(Item item, ItemDTO itemDTO) {
		item.setName(itemDTO.getName());
		item.setLastname(itemDTO.getLastname());
		item.setEmail(itemDTO.getEmail());
		item.setPhone(itemDTO.getPhone());
		item.setProvince(itemDTO.getProvince());
		item.setAge(itemDTO.getAge());
		item.setFiscalcode(itemDTO.getFiscalcode());
		return item;
	}
	
}

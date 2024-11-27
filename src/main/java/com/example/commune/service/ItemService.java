package com.example.commune.service;

import com.example.commune.dto.ItemDTO;
import com.example.commune.model.Item;
import com.example.commune.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    public List<ItemDTO> getAllItems(Integer userId) {
        return itemRepository.findAll(userId);
    }

    public Map<String, String> getSellerContactInfo(Integer sellerId) {
        return itemRepository.getSellerContactInfo(sellerId);
    }
}

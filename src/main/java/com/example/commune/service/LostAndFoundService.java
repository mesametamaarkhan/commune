package com.example.commune.service;

import com.example.commune.dto.LostAndFoundDTO;
import com.example.commune.model.LostAndFoundItem;
import com.example.commune.repository.LostAndFoundItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LostAndFoundService {

    private final LostAndFoundItemRepository lostAndFoundItemRepository;

    public LostAndFoundItem saveItem(LostAndFoundItem lostAndFoundItem) {
        return lostAndFoundItemRepository.save(lostAndFoundItem);
    }

    public List<LostAndFoundDTO> getLostAndFoundItems(Integer userId) {
        return lostAndFoundItemRepository.findAll(userId);
    }

    public Map<String, String> getPosterContactInfo(Integer posterId) {
        return lostAndFoundItemRepository.getPosterContactInfo(posterId);
    }
}

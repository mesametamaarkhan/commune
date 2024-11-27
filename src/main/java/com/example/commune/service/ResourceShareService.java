package com.example.commune.service;

import com.example.commune.dto.ResourceShareDTO;
import com.example.commune.model.ResourceShare;
import com.example.commune.repository.ResourceShareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ResourceShareService {

    private final ResourceShareRepository resourceShareRepository;

    public ResourceShare save(ResourceShare resourceShare) {
        return resourceShareRepository.save(resourceShare);
    }

    public List<ResourceShareDTO> getAllResourceShares(Integer userId) {
        return resourceShareRepository.findAll(userId);
    }

    public Map<String, String> getSharerContactInfo(Integer sharerId) {
        return resourceShareRepository.getSharerContactInfo(sharerId);
    }
}

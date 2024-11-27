package com.example.commune.service;

import com.example.commune.model.BabySitting;
import com.example.commune.model.User;
import com.example.commune.repository.BabySittingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BabysittingService {

    private final BabySittingRepository babySittingRepository;

    public BabySitting createBabysitter(BabySitting babySitting) {
        return babySittingRepository.save(babySitting);
    }

    public List<BabySitting> getAllBabysitters(Integer userId) {
        return babySittingRepository.findAll(userId);
    }

    public Map<String, String> getBabysitterContact(Integer userId) {
        return babySittingRepository.getBabysitterContact(userId);
    }

}

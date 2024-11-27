package com.example.commune.service;

import com.example.commune.dto.CarPoolDTO;
import com.example.commune.model.CarPool;
import com.example.commune.repository.CarPoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CarPoolService {

    private final CarPoolRepository carPoolRepository;

    public CarPool save(CarPool carPool) {
        return carPoolRepository.save(carPool);
    }

    public List<CarPoolDTO> getAllCarpools(Integer userId) {
        return carPoolRepository.findAll(userId);
    }

    public Map<String, String> getDriverContactInfo(Integer driverId) {
        return carPoolRepository.getDriverContactInfo(driverId);
    }
}

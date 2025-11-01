package com.hospital.backend.serviceImpl;

import com.hospital.backend.entity.Labtest;
import com.hospital.backend.repository.LabtestRepository;
import com.hospital.backend.service.LabtestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LabtestServiceImpl implements LabtestService {

    @Autowired
    private LabtestRepository labtestRepository;

    @Override
    public Labtest saveLabtest(Labtest labtest) {
        return labtestRepository.saveLabtest(labtest);
    }

    @Override
    public List<Labtest> getAllLabtests() {
        return labtestRepository.getAllLabtests();
    }

    @Override
    public Optional<Labtest> getLabtestById(int id) {
        return labtestRepository.getLabtestById(id);
    }

    @Override
    public void deleteLabtest(int id) {
        labtestRepository.deleteLabtest(id);
    }

    @Override
    public boolean existsById(int id) {
        return labtestRepository.existsById(id);
    }

    @Override
    public List<Labtest> searchLabtestsByName(String name) {
        return labtestRepository.searchLabtestsByName(name);
    }
}

package com.hospital.backend.service;

import java.util.List;
import java.util.Optional;
import com.hospital.backend.entity.Labtest;

public interface LabtestService {

    Labtest saveLabtest(Labtest labtest);
    List<Labtest> getAllLabtests();
    Optional<Labtest> getLabtestById(int id);
    void deleteLabtest(int id);
    boolean existsById(int id);
    List<Labtest> searchLabtestsByName(String name);
}

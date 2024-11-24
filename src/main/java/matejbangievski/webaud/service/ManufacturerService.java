package matejbangievski.webaud.service;

import matejbangievski.webaud.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {
    List<Manufacturer> findAll();

    Optional<Manufacturer> findById(Long id);
}
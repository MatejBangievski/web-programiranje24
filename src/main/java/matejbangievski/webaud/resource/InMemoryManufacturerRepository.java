package matejbangievski.webaud.resource;

import matejbangievski.webaud.bootstrap.DataHolder;
import matejbangievski.webaud.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryManufacturerRepository {

    public List<Manufacturer> findAll() {
        return DataHolder.manufacturers;
    }

    public Optional<Manufacturer> findById (Long manufacturerId) {
        return DataHolder.manufacturers.stream().filter(m -> m.getId().equals(manufacturerId)).findFirst();
    }

    public Optional<Manufacturer> save (String name, String address) {
        Manufacturer manufacturer = new Manufacturer(name, address);
        DataHolder.manufacturers.add(manufacturer);

        return Optional.of(manufacturer);
    }

    public boolean deleteById(Long id) {
        return DataHolder.manufacturers.removeIf(m -> m.getId().equals(id));
    }
}

package com.example.Country_Page.Service;
import com.example.Country_Page.Entity.Country;
import com.example.Country_Page.Repository.CountryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Country addCountry(Country country) {
        return countryRepository.save(country);
    }

    public Page<Country> getCountries(PageRequest pageRequest) {
        return countryRepository.findAll(pageRequest);
    }

    public Country getCountryById(long id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found with ID: " + id));
    }

    public Country editCountry(long id, Country country) {
        Country existingCountry = countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found"));

        existingCountry.setName(country.getName());
        return countryRepository.save(existingCountry);
    }

    public boolean deleteCountry(long id) {
        if (countryRepository.existsById(id)) {
            countryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean removeEntriesContainingShaw() {
        List<Country> countries = countryRepository.findAll();


        countryRepository.saveAll(countries);
        return true;
    }
}

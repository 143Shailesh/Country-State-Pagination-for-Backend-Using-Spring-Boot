package com.example.Country_Page.Controller;
import com.example.Country_Page.Entity.Country;
import com.example.Country_Page.Service.CountryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/countries")
@CrossOrigin(origins = "http://localhost:3000")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping
    public ResponseEntity<Country> addCountry(@RequestBody Country country) {
        return ResponseEntity.ok(countryService.addCountry(country));
    }

    @GetMapping
    public ResponseEntity<Page<Country>> getCountries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(countryService.getCountries(PageRequest.of(page, size)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Country> editCountry(@PathVariable long id, @RequestBody Country country) {
        return ResponseEntity.ok(countryService.editCountry(id, country));
    }

    @GetMapping("/{id}")
    public Country getCountryById(@PathVariable long id) {
        return countryService.getCountryById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCountry(@PathVariable long id) {
        return ResponseEntity.ok(countryService.deleteCountry(id));
    }

}

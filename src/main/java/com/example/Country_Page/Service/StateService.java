package com.example.Country_Page.Service;

import com.example.Country_Page.DTO.StateDTO;
import com.example.Country_Page.Entity.State;
import com.example.Country_Page.Entity.Country;
import com.example.Country_Page.Repository.CountryRepository;
import com.example.Country_Page.Repository.StateRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class StateService {
    private final StateRepository stateRepository;
    private final CountryRepository countryRepository;

    public StateService(StateRepository stateRepository, CountryRepository countryRepository) {
        this.stateRepository = stateRepository;
        this.countryRepository = countryRepository;
    }

    public StateDTO addState(StateDTO stateDto) {
        Country country = countryRepository.findByName(stateDto.getCountry());
        if (country == null) {
            throw new RuntimeException("Country not found with name: " + stateDto.getCountry());
        }

        State state = new State();
        state.setName(stateDto.getName());
        state.setCountry(country);
        stateRepository.save(state);
        stateDto.setId(state.getId());
        return stateDto;
    }

    public Page<StateDTO> getAllStates(PageRequest pageRequest) {
        return stateRepository.findAll(pageRequest).map(state -> {
            StateDTO dto = new StateDTO();
            dto.setId(state.getId());
            dto.setName(state.getName());
            dto.setCountry(state.getCountry() != null ? state.getCountry().getName() : "Unknown Country");
            return dto;
        });
    }

    public StateDTO getStateById(long id) {
        State state = stateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("State not found with ID: " + id));
        StateDTO dto = new StateDTO();
        dto.setId(state.getId());
        dto.setName(state.getName());
        dto.setCountry(state.getCountry() != null ? state.getCountry().getName() : "Unknown Country");
        return dto;
    }

    public StateDTO editState(long id, StateDTO stateDto) {
        State existingState = stateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("State not found with ID: " + id));

        Country country = countryRepository.findByName(stateDto.getCountry());
        if (country == null) {
            throw new RuntimeException("Country not found with name: " + stateDto.getCountry());
        }

        existingState.setName(stateDto.getName());
        existingState.setCountry(country);
        stateRepository.save(existingState);
        stateDto.setId(existingState.getId());
        return stateDto;
    }

    public boolean deleteState(long id) {
        if (stateRepository.existsById(id)) {
            stateRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

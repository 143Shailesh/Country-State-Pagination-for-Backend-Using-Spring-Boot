package com.example.Country_Page.Controller;
import com.example.Country_Page.DTO.StateDTO;
import com.example.Country_Page.Service.StateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/states")
@CrossOrigin(origins = "http://localhost:3000")
public class StateController {
    private final StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @PostMapping
    public ResponseEntity<StateDTO> addState(@RequestBody StateDTO stateDto) {
        return ResponseEntity.ok(stateService.addState(stateDto));
    }

    @GetMapping
    public ResponseEntity<Page<StateDTO>> getAllStates(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(stateService.getAllStates(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StateDTO> getStateById(@PathVariable long id) {
        return ResponseEntity.ok(stateService.getStateById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StateDTO> editState(@PathVariable long id, @RequestBody StateDTO stateDto) {
        return ResponseEntity.ok(stateService.editState(id, stateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteState(@PathVariable long id) {
        return ResponseEntity.ok(stateService.deleteState(id));
    }
}

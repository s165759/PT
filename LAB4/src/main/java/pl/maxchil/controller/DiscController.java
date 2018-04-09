package pl.maxchil.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.maxchil.Repository.DiscRepository;
import pl.maxchil.model.Disc;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/discs")
public class DiscController {

    private final DiscRepository dr;

    public DiscController(DiscRepository dr){
        this.dr = dr;
    }

    @GetMapping
    public List<Disc> listDiscs(){
        return dr.findAll();
    }

    @PostMapping
    public ResponseEntity<Void> addDisc(@RequestBody Disc disc, UriComponentsBuilder uriComponentsBuilder){
        if(dr.findById(disc.getId()).isPresent())
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        dr.save(disc);

        URI location = uriComponentsBuilder.path("/books/{id}").buildAndExpand(disc.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disc> getDisc(@PathVariable UUID id){
        Optional<Disc> disc = dr.findById(id);

        return disc.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disc> updateDisc(@PathVariable UUID id, @RequestBody Disc disc){

        if(dr.findById(disc.getId()) == null)
            return ResponseEntity.notFound().build();

        dr.save(disc);
        return ResponseEntity.ok().build();
    }


}

package org.springframework.samples.petclinic.Colour;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColourService {
    private final ColourRepository colourRepository;
    
    @Autowired
	public ColourService(ColourRepository colourRepository) {
		this.colourRepository = colourRepository;
	}
    public List<Colour> getAllColours(){
        return colourRepository.findAll();
    }
    public Colour getColourByName(String name){
        return colourRepository.findColourByName(name);
    }
    public List<Colour> getOtherColoursExcept(String name){
        return colourRepository.findOtherColoursExcept(name);
    }
}

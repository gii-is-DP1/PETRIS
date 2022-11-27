package org.springframework.samples.petclinic.model;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class PetrisBoardService {

    @Autowired 
	PetrisBoardRepository boardRepository;
	
	public Optional<PetrisBoard> getById(Integer id){
		return boardRepository.findById(id);
	}
}

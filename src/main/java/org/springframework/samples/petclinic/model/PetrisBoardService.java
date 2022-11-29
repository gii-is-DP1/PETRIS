package org.springframework.samples.petclinic.model;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PetrisBoardService {

	private final PetrisBoardRepository petrisBoardRepository;

    @Autowired 
	public PetrisBoardService(PetrisBoardRepository petrisBoardRepository) {
		this.petrisBoardRepository = petrisBoardRepository;
	}
	
	public Optional<PetrisBoard> getById(Integer id){
		return petrisBoardRepository.findById(id);
	}

	public PetrisBoard getByGameId(Integer gameId){
		return petrisBoardRepository.findByGameId(gameId);
	}

	public void save(PetrisBoard petrisBoard){
		petrisBoardRepository.save(petrisBoard);
	}
}

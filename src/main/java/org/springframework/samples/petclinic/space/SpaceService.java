package org.springframework.samples.petclinic.space;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpaceService {

    private final SpaceRepository spaceRepository;
    
    @Autowired
	public SpaceService(SpaceRepository spaceRepository) {
		
        this.spaceRepository = spaceRepository;
	}

    public Space findSpaceById(Integer space1Id) {
        return spaceRepository.findSpaceByid(space1Id);
    }

    public void save(Space space1) {
        spaceRepository.save(space1);
    }
}

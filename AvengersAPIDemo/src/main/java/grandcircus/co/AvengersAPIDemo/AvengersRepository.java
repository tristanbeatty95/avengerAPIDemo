package grandcircus.co.AvengersAPIDemo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AvengersRepository extends MongoRepository<AVCharacter, String>{

	List<AVCharacter> findBySkills(String skill);
}

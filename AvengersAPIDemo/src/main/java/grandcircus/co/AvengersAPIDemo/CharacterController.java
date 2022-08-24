package grandcircus.co.AvengersAPIDemo;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

//denotes that we are creating a REST web service
@RestController
public class CharacterController {

	@Autowired
	private AvengersRepository ch_repo;

	@GetMapping("/reset")
	public String reset() {

		// Delete all
		ch_repo.deleteAll();

		// Add characters

		AVCharacter ac = new AVCharacter("Iron Man", 8, true, Arrays.asList("flying", "fighting", "intelligence"));
		ch_repo.insert(ac);

		ac = new AVCharacter("Thor", 9, true, Arrays.asList("fighting", "strength"));
		ch_repo.insert(ac);

		ac = new AVCharacter("Hulk", 10, true, Arrays.asList("fighting", "strength", "jumping"));
		ch_repo.insert(ac);

		ac = new AVCharacter("Black Panther", 8, true, Arrays.asList("stealth", "intelligence", "fighting"));
		ch_repo.insert(ac);

		ac = new AVCharacter("Dr. Strange", 7, true, Arrays.asList("magic", "intelligence"));
		ch_repo.insert(ac);

		ac = new AVCharacter("Thanos", 9, false, Arrays.asList("strength", "intelligence"));
		ch_repo.insert(ac);

		return "Data reset.";

	}

	// C(R)UD - read all or by skill
	@GetMapping("/character")
	public List<AVCharacter> readAll(@RequestParam(required = false) String skill) {
		if (skill != null) {
			return ch_repo.findBySkills(skill);
		} else {
			return ch_repo.findAll();
		}
	}

	// C(R)UD - read one
	@GetMapping("/character/{id}")
	public AVCharacter readOne(@PathVariable("id") String id) {
		return ch_repo.findById(id).orElseThrow(() -> new CharacterNotFoundException(id));
	}

	// (C)RUD
	@PostMapping("/character")
	public AVCharacter create(@RequestBody AVCharacter avchar) {
		ch_repo.insert(avchar);
		return avchar;
	}

	// CRU(D)
	@DeleteMapping("/character/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		ch_repo.deleteById(id);
	}
	
	// CR(U)D
	@PutMapping("/character/{id}")
	public AVCharacter update(@RequestBody AVCharacter avchar, @PathVariable("id") String id) {
		avchar.setId(id);
		return ch_repo.save(avchar);
	}
	
	@ResponseBody
	@ExceptionHandler(CharacterNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String characterNotFoundHandler(CharacterNotFoundException ex) {
		return ex.getMessage();
	}

}

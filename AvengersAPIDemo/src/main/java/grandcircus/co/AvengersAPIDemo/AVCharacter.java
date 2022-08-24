package grandcircus.co.AvengersAPIDemo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//name of collection in DB
@Document("avengers")
public class AVCharacter {

	@Id
	public String id;

	private String name;
	private Integer strength;
	private Boolean god;
	private List<String> skills;

	public AVCharacter() {

	}

	public AVCharacter(String name, Integer strength, Boolean god, List<String> skills) {
		this.name = name;
		this.strength = strength;
		this.god = god;
		this.skills = skills;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStrength() {
		return strength;
	}

	public void setStrength(Integer strength) {
		this.strength = strength;
	}

	public Boolean getGod() {
		return god;
	}

	public void setGod(Boolean god) {
		this.god = god;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

}

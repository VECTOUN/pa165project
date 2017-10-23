package cz.fi.muni.pa165.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

import cz.fi.muni.pa165.enums.MonsterAgility;

/**
 * @author Vojtech Sassmann &lt;vojtech.sassmann@gmail.com&gt;
 */
@Entity
public class Monster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotNull
	@Column(nullable = false, unique = true)
	private String name;

	@ManyToMany(mappedBy = "monsters")
	private Set<Area> areas = new HashSet<>();

	@ManyToMany(mappedBy = "appropriateMonsters")
	private Set<Weapon> appropriateWeapons = new HashSet<>();

	private Double weight;

	private Double height;

	@Enumerated
	private MonsterAgility agility;

	public Monster(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Name can not be null.");
		}
		if (name.isEmpty()) {
			throw new IllegalArgumentException("Name can not be empty.");
		}
		this.name = name;
	}

	public Set<Weapon> getAppropriateWeapons() {
		return appropriateWeapons;
	}

	public void setAppropriateWeapons(Set<Weapon> appropriateWeapons) {
		this.appropriateWeapons = appropriateWeapons;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Area> getAreas() {
		return areas;
	}

	public void setAreas(Set<Area> areas) {
		this.areas = areas;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public MonsterAgility getAgility() {
		return agility;
	}

	public void setAgility(MonsterAgility agility) {
		this.agility = agility;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || !(o instanceof Monster)) return false;

		Monster monster = (Monster) o;

		if (!getName().equals(monster.getName())) return false;
		if (getWeight() != null ? !getWeight().equals(monster.getWeight()) : monster.getWeight() != null) return false;
		return getHeight() != null ? getHeight().equals(monster.getHeight()) : monster.getHeight() == null;
	}

	@Override
	public int hashCode() {
		int result = getName().hashCode();
		result = 31 * result + (getWeight() != null ? getWeight().hashCode() : 0);
		result = 31 * result + (getHeight() != null ? getHeight().hashCode() : 0);
		return result;
	}
}

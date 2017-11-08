package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.MonsterDao;
import cz.fi.muni.pa165.entity.Monster;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonsterServiceImpl implements MonsterService {

	private final MonsterDao monsterDao;

	@Inject
	public MonsterServiceImpl(MonsterDao monsterDao) {
		this.monsterDao = monsterDao;
	}

	@Override
	public List<Monster> getAllMonsters() {
		return monsterDao.getAll();
	}

	@Override
	public void createMonster(Monster monster) {
		monsterDao.create(monster);
	}

	@Override
	public List<Monster> getTheMostWidespreadMonsters() {
		List<Monster> allMonsters = monsterDao.getAll();
		int maximumOccurrence = 0;
		for (Monster monster : allMonsters) {
			if (monster.getAreas().size() > maximumOccurrence) {
				maximumOccurrence = monster.getAreas().size();
			}
		}
		int finalMaximumOccurrence = maximumOccurrence;
		return allMonsters.stream()
				.filter(monster -> monster.getAreas().size() == finalMaximumOccurrence)
				.collect(Collectors.toList());
	}
}
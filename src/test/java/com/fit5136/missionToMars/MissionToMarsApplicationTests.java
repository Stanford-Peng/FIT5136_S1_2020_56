package com.fit5136.missionToMars;

import com.fit5136.missionToMars.model.Candidate;
import com.fit5136.missionToMars.util.CSVOperator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class MissionToMarsApplicationTests {

	@Test
	void contextLoads() {
		List<Candidate> userDb = new ArrayList<>();
		userDb.add(new Candidate(1, "", "", null));
		userDb.add(new Candidate(2, "", "", null));
		userDb.add(new Candidate(6, "", "", null));
		userDb.add(new Candidate(4, "", "", null));
		long nextId = userDb.stream().sorted(Comparator.comparing(Candidate::getUserId).reversed())
				.collect(Collectors.toList()).get(0).getUserId();
		System.out.println(nextId);
	}

}

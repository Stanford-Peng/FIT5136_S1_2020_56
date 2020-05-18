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
		List<String[]> list = CSVOperator.readAll("MissionData.csv", 1);
		for (int i = 0; i < list.get(0).length; i++) {
			System.out.println(list.get(0)[i]);
		}
	}

}

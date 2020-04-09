package com.fit5136.missionToMars;

import com.fit5136.missionToMars.dao.CSVOperator;
import com.fit5136.missionToMars.dao.CargoDataAccessService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MissionToMarsApplicationTests {

	@Test
	void contextLoads() {
		List<String[]> list = CSVOperator.readAll("cargoData.csv");
		System.out.println(list.size());
				list.forEach(a ->{
					for (String str : a)
					{
						System.out.print(str);
					}
				});

	}

}

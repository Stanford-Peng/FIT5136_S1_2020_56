package com.fit5136.missionToMars;

import com.fit5136.missionToMars.model.Candidate;
import com.fit5136.missionToMars.util.CSVOperator;
import com.fit5136.missionToMars.util.StringUtil;
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
		String a = null;
		try{
			a.isEmpty();
		}
		catch(NullPointerException e)
		{System.err.println("error");}
	}

}

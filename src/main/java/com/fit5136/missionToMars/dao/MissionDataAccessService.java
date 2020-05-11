package com.fit5136.missionToMars.dao;

import com.fit5136.missionToMars.model.Candidate;
import com.fit5136.missionToMars.model.CoordinatorInfo;
import com.fit5136.missionToMars.model.Mission;
import com.fit5136.missionToMars.model.Shuttle;
import com.fit5136.missionToMars.util.CSVOperator;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository("missionDao")
public class MissionDataAccessService implements MissionDao {
    private List<Mission> missionDb = new ArrayList<>();
    private List<Shuttle> shuttleDb = new ArrayList<>();

    @Override
    public List<Mission> getAll() {
        sync();
        return missionDb;
    }

    @Override
    public int insert(Mission mission) {
        missionDb.add(mission);
        return 0;
    }

    @Override
    public Optional<Mission> findById(long id) {
        sync();
        return missionDb.stream().filter(m -> m.getId() == id).findFirst();
    }

    @Override
    public int updateById(long id, Mission mission) {
        return 0;
    }

    @Override
    public int deleteById(long id) {
        return 0;
    }

    @Override
    public int selectShuttle(long id, Shuttle shuttle) {
        return 0;
    }

    @Override
    public List<Candidate> findCandidates(long id) {
        return null;
    }

    @Override
    public int selectCandidates(long id, List<Candidate> candidates) {
        return 0;
    }

    public void sync(){
        missionDb.clear();
        List<String[]> list = CSVOperator.readAll("MissionData.csv", 1);
        list.forEach(a -> Optional.ofNullable(a).ifPresent(array ->{
            HashMap<String, String> jobs = new HashMap<>();
            String[] jobNames =  array[8].split(", ");
            String[] jobDesc = array[9].split(", ");
            for (int i = 0; i < jobNames.length; i++) {
                jobs.put(jobNames[i], jobDesc[i]);
            }
            HashMap<String, Integer> empReqs = new HashMap();
            String[] titles = array[13].split(", ");
            String[] numbers = array[14].split(", ");
            Integer[] nums = new Integer[numbers.length];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = Integer.parseInt(numbers[i]);
            }
            for (int i = 0; i < nums.length; i++) {
                empReqs.put(titles[i], nums[i]);
            }
            List<Long> candidates = new ArrayList<>();
            String[] cans = array[16].split(", ");
            for (String c : cans) {
                candidates.add(Long.parseLong(c));
            }
            Date date = new Date();
            try{
                date = new SimpleDateFormat("MM/dd/yyyy").parse(array[10]);
            }
            catch (ParseException e){
                e.printStackTrace();
            }
            missionDb.add(new Mission(Long.parseLong(array[0]),array[1], array[2], array[3],
                    array[4].split(", "), new CoordinatorInfo(array[5], array[6], array[7]),
                    jobs, date, Integer.parseInt(array[11]), array[12], empReqs, array[15],
                    candidates , Long.parseLong(array[17])));
        }));
    }
    public void syncShuttleDb(){
        shuttleDb.clear();
        List<String[]> list = CSVOperator.readAll("MissionData.csv", 1);
        list.forEach(a -> Optional.ofNullable(a).ifPresent(array ->{
            Date date = new Date();
            try {
                date = new SimpleDateFormat("MM/dd/yyyy").parse(array[2]);
            }
            catch (ParseException e){
                e.printStackTrace();
            }
            shuttleDb.add(new Shuttle(Long.parseLong(array[0]), array[1], date,
                    Integer.parseInt(array[3]), Integer.parseInt(array[4]), Integer.parseInt(array[5]),
                    Integer.parseInt(array[6]), array[7]));
        }));
    }

    public List<String[]> read(){
        //Include the column name line
        return CSVOperator.readAll("MissionData.csv", 0);
    }

    public void write(List<String[]> list){
        CSVOperator.write("MissionData.csv", list);
    }
}

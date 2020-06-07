package com.fit5136.missionToMars.dao;
import com.fit5136.missionToMars.model.CoordinatorInfo;
import com.fit5136.missionToMars.model.Mission;
import com.fit5136.missionToMars.util.CSVOperator;
import org.springframework.stereotype.Repository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Repository("missionDao")
public class MissionDataAccessService implements MissionDao {
    private List<Mission> missionDb = new ArrayList<>();

    @Override
    public List<Mission> getAll() {
        sync();
        return missionDb;
    }

    @Override
    public int insert(Mission mission) {
        sync();
        if (mission.getId() == -1) {
            Long nextId = missionDb.stream().sorted(Comparator.comparing(Mission::getId).reversed())
                    .collect(Collectors.toList()).get(0).getId() + 1;
            mission = new Mission(nextId, mission.getMissionName(), mission.getMissionDesc(),
                    mission.getOrigin(), mission.getAllowedCountries(), mission.getCoordinatorInfo(),
                    mission.getJobs(), mission.getLaunchDate(), mission.getDuration(),
                    mission.getLocation(), mission.getCargoFor(), mission.getEmpReq(), mission.getStatus(),
                    mission.getCandidates(), mission.getShuttleId());
        }
        //Stop insertion if id is duplicate
        long inputId = mission.getId();
        if (missionDb.stream().anyMatch(m -> m.getId() ==inputId)) {
            return 1;
        }
        missionDb.add(mission);
        List<String[]> list = read();
        list.add(mission.toArray());
        write(list);

        return 0;
    }

    @Override
    public Optional<Mission> findById(long id) {
        sync();
        return missionDb.stream().filter(m -> m.getId() == id).findFirst();
    }

    @Override
    public int updateById(long id, Mission mission) {
        if ( id != mission.getId() || deleteById(id) == 1)
            return 1;
        //Make sure id is immutable
        return insert(mission);
    }

    @Override
    public int deleteById(long id) {
        Optional<Mission> optional = findById(id);
        optional.ifPresent(m -> {
            List<String[]> list = read();
            //The first row is the column names
            list.remove(missionDb.indexOf(m) + 1);
            write(list);
            missionDb.remove(m);
        });
        return optional.isPresent() ? 0 : 1;
    }

    @Override
    public int selectShuttle(long id, Long shuttleId) {
        Optional<Mission> optional = findById(id);
        optional.ifPresent(m -> {
            m.setShuttleId(shuttleId);
            updateById(id, m);
        });
        return optional.isPresent() ? 0 : 1;
    }

    @Override
    public int selectCandidates(long id, List<Long> candidates) {
        Optional<Mission> optional = findById(id);
        optional.ifPresent(m ->{
            List<Long> list =
                    m.getCandidates() == null ? new ArrayList<>() : m.getCandidates();
            candidates.addAll(list);
            m.setCandidates(candidates);
            updateById(id, m);
        });
        return optional.isPresent() ? 0 : 1;
    }

    private void sync(){
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
            String[] titles = array[14].split(", ");
            String[] numbers = array[15].split(", ");
            Integer[] nums = new Integer[numbers.length];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = Integer.parseInt(numbers[i]);
            }
            for (int i = 0; i < nums.length; i++) {
                empReqs.put(titles[i], nums[i]);
            }
            List<Long> candidates = new ArrayList<>();
            String[] cans = array[17].split(", ");
            if (cans.length > 0) {
                for (String c : cans) {
                    candidates.add(Long.parseLong(c));
                }
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
                    jobs, date, Integer.parseInt(array[11]), array[12], array[13], empReqs, array[16],
                    candidates , !array[18].isEmpty() ? Long.parseLong(array[18]) : null));
        }));
    }


    private List<String[]> read(){
        //Include the column name line
        return CSVOperator.readAll("MissionData.csv", 0);
    }

    private void write(List<String[]> list){
        CSVOperator.write("MissionData.csv", list);
    }
}

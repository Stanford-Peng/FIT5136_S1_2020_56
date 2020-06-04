package com.fit5136.missionToMars.dao;

import com.fit5136.missionToMars.exception.DuplicateUserNameException;
import com.fit5136.missionToMars.exception.UnknownUserIdException;
import com.fit5136.missionToMars.model.*;
import com.fit5136.missionToMars.util.CSVOperator;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository("userDao")
public class UserDataAccessService implements UserDao{
    private List<Candidate> userDb = new ArrayList<>();
    @Override
    public List<Candidate> getAll() {
        sync();
        return userDb;
    }

    @Override
    public void insert(Candidate candidate) {
        sync();
        //use -1 as id when post a candidate at the front end, replace -1 with the next id in database
        //don't replace if it is a put request
        //id is final, so use new candidate()
        if (candidate.getUserId() == -1) {
            long nextId = userDb.stream().sorted(Comparator.comparing(Candidate::getUserId).reversed())
                    .collect(Collectors.toList()).get(0).getUserId() + 1;
            candidate = new Candidate(nextId, candidate.getUserName(), candidate.getPassword(),
                    candidate.getProfile());
            //stop inserting if username is duplicate
            long inputId = candidate.getUserId();
            String inputUserName = candidate.getUserName();
            Optional<Candidate> optional = userDb.stream().
                    filter(c -> c.getUserName().equals(inputUserName)).findFirst();
            if (optional.isPresent()) {
                throw new DuplicateUserNameException("Username exists: " + inputUserName);
            }
        }
        userDb.add(candidate);
        String[] array = candidate.toArray();
        List list = read();
        list.add(array);
        write(list);
    }

    @Override
    public Optional<Candidate> findById(long id) {
        sync();
        Optional<Candidate> optional = userDb
                .stream()
                .filter(c -> c.getUserId() == id)
                .findFirst();
        if (!optional.isPresent()){
            throw new UnknownUserIdException("unknown user id: " + id);
        }
        return optional;
    }

    @Override
    public void updateById(long id, Candidate candidate) {
        try {
            //Make sure id an user name are immutable
            String userName = findById(id).map(c -> c.getUserName()).orElse("");
            if (id != candidate.getUserId() || !userName.equals(candidate.getUserName()))
                return;
            deleteById(id);
            insert(candidate);
        }
        catch (UnknownUserIdException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(long id) {
        try {
            Optional<Candidate> optional = findById(id);
            optional.ifPresent(c -> {
                List<String[]> list = read();
                //the first line is the column name
                list.remove(userDb.indexOf(c) + 1);
                write(list);
            });
        }
        catch (UnknownUserIdException e){
            e.printStackTrace();
        }

    }

    @Override
    public void setProfile(long id, Profile profile) {
        try {
            Optional<Candidate> optional = findById(id);
            optional.ifPresent(c -> {
                c.setProfile(profile);
                updateById(id, c);
            });
        }
        catch(UnknownUserIdException e){
            e.printStackTrace();
        }
    }

    @Override
    public void changePassword(long id, String password) {
        try {
            Optional<Candidate> optional = findById(id);
            optional.ifPresent(c -> {
                c.setPassword(password);
                updateById(id, c);
            });
        }
        catch (UnknownUserIdException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Candidate> findQualifiedCandidates(Criteria criteria, Mission mission) {
        sync();
        List<Candidate> candidates = userDb.stream()
                .filter(candidate -> candidate.getProfile() != null)
                .collect(Collectors.toList());
        List<Candidate> qualifiedCandidates = new ArrayList<>();
        candidates.forEach(c -> {
            Calendar calendar = Calendar
                    .getInstance();
            calendar.setTime(c.getProfile().getDob());
            int age = Calendar
                    .getInstance()
                    .get(Calendar.YEAR)
                    - calendar.get(Calendar.YEAR);
            int exp = IntStream.of(c.getProfile().getWorkExp()).sum();
            if (
                    age >= criteria.getMinAge() && age <= criteria.getMaxAge()
            && exp >= criteria.getWorkExp()
           // && c.getProfile().getCriminalRecord().getCrimes().size() < 2
            && c.getProfile().getHealthRecord().getHealthIssues().size() < 2
            && Arrays.asList(c.getProfile().getOccupations())
                            .containsAll(Arrays.asList(criteria.getOccupations()))
            && Arrays.asList(c.getProfile().getLanguages())
                            .containsAll(Arrays.asList(criteria.getLanguages()))
            && !Collections
                    .disjoint(Arrays.asList(c.getProfile().getQualifications())
                            , Arrays.asList(criteria.getQualifications()))
        ){
                qualifiedCandidates.add(c);
            }
        });
        List<Candidate> sorted = qualifiedCandidates.stream()
                .sorted(Comparator.comparing(c ->
                        IntStream.of(c.getProfile().getWorkExp()).sum()))
                .limit(mission.getEmpReq().values().stream().mapToInt(i -> i).sum())
                .collect(Collectors.toList());
        Collections.reverse(sorted);
        return sorted;
    }

    @Override
    //Return 0 if the username exists in the user database
    public int hasDuplicateUsername(String username) {
        sync();
        boolean has = userDb.stream().anyMatch(c -> c.getUserName().equals(username));
        return has ? 0 : 1;
    }

    @Override
    //Return the userID, return -1 if credential is wrong
    public long candidateLogin(String userName, String password) {
        Optional<Candidate> optional = userDb.stream().filter(c -> c.getUserName().equals(userName)
                && c.getPassword().equals(password))
                .findFirst();
        //Return the userID, return -1 if credential is wrong
        return optional.isPresent() ? optional.get().getUserId() : -1;
    }

    //Read the csv file, for each row instantiate a new candidate, add to userDb
    public void sync(){
        userDb.clear();
        List<String[]> list = CSVOperator.readAll("candidateData.csv", 1);
        list.forEach(a -> Optional.ofNullable(a)
                .ifPresent(array -> {
                    if (String.join("", Arrays.copyOfRange(array, 3, array.length))
                            .equals("")){
                        userDb.add(new Candidate(Long.parseLong(array[0]), array[1], array[2], null));
                    }
                    else {
                        Date date = new Date();
                        try {
                            date = new SimpleDateFormat("MM/dd/yyyy").
                                    parse(array[4]);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String[] strWorkExp = array[15].split(", ");
                        int[] workExp = new int[strWorkExp.length];
                        //If work experience is blank, pass 0
                        for (int i = 0; i < strWorkExp.length; i++) {
                            workExp[i] = !strWorkExp[i].isEmpty() ? Integer.parseInt(strWorkExp[i]) : 0;
                        }
                        userDb.add(new Candidate(Integer.parseInt(array[0]), array[1], array[2],
                                new Profile(array[3], date, array[5], array[6], array[7], array[8],
                                        array[9], array[10], array[11], array[12].split(", "),
                                        array[13], array[14].split(", "),
                                        workExp, array[16].split(", "),
                                        array[17], array[18].split(", "),
                                        new HealthRecord(Arrays.asList(array[19].split(", "))),
                                        new CriminalRecord(Arrays.asList(array[20].split(", "))))));
                    }
                }));
    }

    public List<String[]> read(){
        //Include the column name line
        return CSVOperator.readAll("candidateData.csv", 0);
    }

    public void write(List<String[]> list){
        CSVOperator.write("candidateData.csv", list);
    }
}

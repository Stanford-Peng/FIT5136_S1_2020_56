package com.fit5136.missionToMars.dao;

import com.fit5136.missionToMars.model.Candidate;
import com.fit5136.missionToMars.model.Profile;
import com.fit5136.missionToMars.model.User;
import com.fit5136.missionToMars.util.CSVOperator;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Repository("userDao")
public class UserDataAccessService implements UserDao{
    private List<Candidate> userDb = new ArrayList<>();
    @Override
    public List<Candidate> getAll() {
        sync();
        return userDb;
    }

    @Override
    public int insert(Candidate candidate) {
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
            if (optional.isPresent()) { return 1; }
        }
        userDb.add(candidate);
        String[] array = candidate.toArray();
        List list = read();
        list.add(array);
        write(list);
        return 0;
    }

    @Override
    public Optional<Candidate> findById(long id) {
        sync();
        return userDb.stream().filter(c -> c.getUserId() == id).findFirst();
    }

    @Override
    public int updateById(long id, Candidate candidate) {
        //Make sure id an user name are immutable
        String userName = findById(id).map(c -> c.getUserName()).orElse("");
        if (id != candidate.getUserId() || !userName.equals(candidate.getUserName()) || deleteById(id) == 1)
            return 1;
        return insert(candidate);
    }

    @Override
    public int deleteById(long id) {
        Optional<Candidate> optional = findById(id);
        optional.ifPresent(c -> {
            List<String[]> list = read();
            //the first line is the column name
            list.remove(userDb.indexOf(c) + 1);
            write(list);
        });
        return optional.isPresent() ? 0 : 1;
    }

    @Override
    public int setProfile(long id, Profile profile) {
        Optional<Candidate> optional = findById(id);
        optional.ifPresent(c -> {
            c.setProfile(profile);
            updateById(id, c);
        });
        return optional.isPresent() ? 0 : 1;
    }

    @Override
    public int changePassword(long id, String password) {
        Optional<Candidate> optional = findById(id);
        optional.ifPresent(c -> {
            c.setPassword(password);
            updateById(id, c);
        });
        return optional.isPresent() ? 0 : 1;
    }

    //Read the csv file, for each row instantiate a new candidate, add to userDb
    public void sync(){
        userDb.clear();
        List<String[]> list = CSVOperator.readAll("candidateData.csv", 1);
        list.forEach(a -> Optional.ofNullable(a)
                .ifPresent(array -> {
                    if (String.join("", Arrays.copyOfRange(array, 3, array.length))
                            .equals("")){
                        userDb.add(new Candidate(Integer.parseInt(array[0]), array[1], array[2], null));
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
                                        array[17], array[18].split(", "))));
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

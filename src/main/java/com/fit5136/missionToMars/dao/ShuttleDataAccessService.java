package com.fit5136.missionToMars.dao;

import com.fit5136.missionToMars.model.Shuttle;
import com.fit5136.missionToMars.util.CSVOperator;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository("shuttleDao")
public class ShuttleDataAccessService implements ShuttleDao {
    private List<Shuttle> shuttleDb = new ArrayList<>();

    @Override
    public List<Shuttle> getAll() {
        sync();
        return shuttleDb;
    }

    @Override
    public Optional<Shuttle> findById(long id) {
        sync();
        return shuttleDb.stream().filter(s -> s.getId() == id).findFirst();
    }

    public void sync(){
        shuttleDb.clear();
        List<String[]> list = CSVOperator.readAll("ShuttleData.csv", 1);
        list.forEach(a -> Optional.ofNullable(a).ifPresent(array ->{
            Date date = new Date();
            try {
                date = new SimpleDateFormat("MM/dd/yyyy").parse(array[2]);
            }
            catch (ParseException e){
                e.printStackTrace();
            }
            shuttleDb.add(new Shuttle(Long.parseLong(array[0]), array[1], date,
                    Integer.parseInt(array[3]),
                    Integer.parseInt(array[4]),
                    Integer.parseInt(array[5]),
                    Integer.parseInt(array[6]), array[7]));
        }));
    }
}

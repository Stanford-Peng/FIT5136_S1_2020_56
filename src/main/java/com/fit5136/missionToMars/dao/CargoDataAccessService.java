package com.fit5136.missionToMars.dao;

import com.fit5136.missionToMars.model.Cargo;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository("cargoDao")
public class CargoDataAccessService implements CargoDao {

    private static List<Cargo> cargoDb = new ArrayList<Cargo>();


    @Override
    public int insert(Cargo cargo) {
        List<String[]> list = CSVOperator.readAll("cargoData.csv");
        String[] array = {String.valueOf(cargo.getId()), cargo.getName(), String.valueOf(cargo.getQuantity())};
        cargoDb.add(new Cargo(cargo.getId(), cargo.getName(), cargo.getQuantity()));
        list.add(array);
        CSVOperator.write("cargoData.csv", list);
        return 0;
    }

    @Override
    public List<Cargo> getAll() {
        integrate();
        return cargoDb;
    }

    @Override
    public Optional<Cargo> findById(int id) {
        integrate();
        return cargoDb.stream().filter(c -> c.getId() == id).findFirst();
    }

    @Override
    public int deleteById(int id) {
        Optional<Cargo> optional = findById(id);
        optional.ifPresent(c ->{
            List<String[]> list = CSVOperator.readAll("cargoData.csv");
            list.remove(cargoDb.indexOf(c));
            CSVOperator.write("cargoData.csv", list);
            cargoDb.remove(c);
        });
        return optional.isPresent() ? 0 : 1;
    }

    @Override
    public int updateById(int id, Cargo cargo) {
        Optional<Cargo> optional = findById(id);
        optional.ifPresent(c -> {
            List<String[]> list = CSVOperator.readAll("cargoData.csv");
            String[] array = {String.valueOf(cargo.getId()), cargo.getName(), String.valueOf(cargo.getQuantity())};
            list.set(cargoDb.indexOf(c), array);
            cargoDb.set(cargoDb.indexOf(c), cargo);
            CSVOperator.write("cargoData.csv", list);
        });
        return optional.isPresent() ? 0 : 1;

    }

    public void integrate() {
        List<String[]> list = CSVOperator.readAll("cargoData.csv");
        list.forEach(a -> Optional.ofNullable(a).
                ifPresent(array -> cargoDb.
                        add(new Cargo(Integer.parseInt(array[0]), array[1], Integer.parseInt(array[2])))));
    }
}
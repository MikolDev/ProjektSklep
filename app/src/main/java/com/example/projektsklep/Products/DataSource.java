package com.example.projektsklep.Products;

import com.example.projektsklep.R;

import java.util.ArrayList;

public class DataSource {
    private ArrayList<CentralUnit> centralUnitRepo;
    private ArrayList<Mouse> mouseRepo;
    private ArrayList<Keyboard> keyboardRepo;
    private ArrayList<Monitor> monitorRepo;

    public DataSource() {
        centralUnitRepo = createCentralUnitRepo();
    }

    private ArrayList<CentralUnit> createCentralUnitRepo() {
        ArrayList<CentralUnit> repo = new ArrayList<>();
        repo.add(new CentralUnit("HP OMEN 45L GT22- 0112nw AMD Ryzen 9 5900X 64GB 2TB RTX3080Ti W11", 1199000, R.drawable.hpomen));
        repo.add(new CentralUnit("HP Victus TG02-0851nw Ryzen 5 5600G/8GB/512GB SSD/GTX1650 4GB/Win11H", 419900, R.drawable.hpvictus));


        return repo;
    }

    private ArrayList<Mouse> createMouseRepo() {
        ArrayList<Mouse> repo = new ArrayList<>();

        return repo;
    }

    private ArrayList<Keyboard> createKeyboardRepo() {
        ArrayList<Keyboard> repo = new ArrayList<>();

        return repo;
    }

    private ArrayList<Monitor> createMonitorRepo() {
        ArrayList<Monitor> repo = new ArrayList<>();

        return repo;
    }

    public ArrayList<CentralUnit> getCentralUnitRepo() {
        return centralUnitRepo;
    }

    public ArrayList<Mouse> getMouseRepo() {
        return mouseRepo;
    }

    public ArrayList<Keyboard> getKeyboardRepo() {
        return keyboardRepo;
    }

    public ArrayList<Monitor> getMonitorRepo() {
        return monitorRepo;
    }
}

package com.example.projektsklep.Products;

import com.example.projektsklep.R;

import java.util.ArrayList;
import java.util.HashMap;

public class DataSource {
    private ArrayList<HashMap> centralUnitRepo;
    private ArrayList<HashMap> mouseRepo;
    private ArrayList<HashMap> keyboardRepo;
    private ArrayList<HashMap> monitorRepo;

    public DataSource() {
        centralUnitRepo = createCentralUnitRepo();
        mouseRepo = createMouseRepo();
        keyboardRepo = createKeyboardRepo();
        monitorRepo = createMonitorRepo();
    }

    private ArrayList<HashMap> productToHashMap(ArrayList<Product> sample) {
        ArrayList<HashMap> list = new ArrayList<>();
        sample.forEach((product) -> {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("description", product.getDescription());
            hashMap.put("price", String.valueOf(product.getPrice()));
            hashMap.put("img", String.valueOf(product.getImg()));

            list.add(hashMap);
        });

        return list;
    }

    private ArrayList<HashMap> createCentralUnitRepo() {
        ArrayList<Product> sample = new ArrayList<>();

        sample.add(new CentralUnit("HP Victus TG02-0851nw Ryzen 5 5600G/8GB/512GB SSD/GTX1650 4GB/Win11H", 419950, R.drawable.hpvictus));
        sample.add(new CentralUnit("HP OMEN 45L GT22- 0112nw AMD Ryzen 9 5900X 64GB 2TB RTX3080Ti W11", 1199000, R.drawable.hpomen));
        sample.add(new CentralUnit("Acer Nitro 50 Intel® Core™ i5-10400F 16GB 1TB + 512GB RTX2060", 459900, R.drawable.acer));
        sample.add(new CentralUnit("Actina SPC Intel® Core™ i5-12400F 16GB 1TB RTX3060", 584900, R.drawable.actina));

        return productToHashMap(sample);
    }

    private ArrayList<HashMap> createMouseRepo() {
        ArrayList<Product> sample = new ArrayList<>();

        sample.add(new Mouse("Logitech G502 Hero High Performance", 22900, R.drawable.logitechg502));
        sample.add(new Mouse("Logitech M185 (czarno-szary)", 6999, R.drawable.logitechm185));
        sample.add(new Mouse("Genesis Krypton 555", 8999, R.drawable.krypton555));

        return productToHashMap(sample);
    }

    private ArrayList<HashMap> createKeyboardRepo() {
        ArrayList<Product> sample = new ArrayList<>();

        sample.add(new Keyboard("Logitech MX Keys", 44900, R.drawable.logitechmxkeys));
        sample.add(new Keyboard("SteelSeries Apex 3", 29900, R.drawable.steelseriesapex3));

        return productToHashMap(sample);
    }

    private ArrayList<HashMap> createMonitorRepo() {
        ArrayList<Product> sample = new ArrayList<>();

        sample.add(new Monitor("Samsung C27G55TQWR 1ms 144Hz", 109900, R.drawable.samsung27144hzlc27g55tqwuxen));
        sample.add(new Monitor("ASUS TUF Gaming VG277Q1A 1ms 165Hz", 109900, R.drawable.asusvg277q1a));

        return productToHashMap(sample);
    }

    public ArrayList<HashMap> getCentralUnitRepo() {
        return centralUnitRepo;
    }

    public ArrayList<HashMap> getMouseRepo() {
        return mouseRepo;
    }

    public ArrayList<HashMap> getKeyboardRepo() {
        return keyboardRepo;
    }

    public ArrayList<HashMap> getMonitorRepo() {
        return monitorRepo;
    }
}

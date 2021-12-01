package model.service;

import model.Brood;
import model.Broodje;
import model.database.Database;
import model.database.Text.BroodjesDatabaseTextFile;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

public class BroodjesService{

    private Database database = new BroodjesDatabaseTextFile();

    public BroodjesService() {
        setService();
    }

}

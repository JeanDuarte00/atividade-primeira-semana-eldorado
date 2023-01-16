package org.eldorado.infrastructure.database.fileDatabase;

import java.io.*;
import java.util.List;
import java.util.logging.Logger;

public abstract class FileDatabase<T> implements IFileDatabase<T> {

    FileWriter fileWriter;
    protected FileReader fileReader;
    Logger LOGGER;
    public FileDatabase(File file){
        this.LOGGER = Logger.getLogger("PersonCreatorCli");
        try {
            fileWriter = new FileWriter(file, true);
            fileReader = new FileReader(file);
        } catch (IOException e) {
            LOGGER.warning("Error while creating database file.");
        }
    }
    public void save(T data){
        try {
            BufferedWriter writer = new BufferedWriter(this.fileWriter);
            writer.append(data.toString() + "\n");
            writer.close();
        } catch (IOException e) {
            LOGGER.warning("Error while saving to file.");
        }
    }

    @Override
    public abstract T read();

    @Override
    public abstract List<T> readAll();
}

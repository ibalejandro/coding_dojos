package co.edu.eafit.conferre.data.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.edu.eafit.conferre.data.base.TransferObject;
import co.edu.eafit.conferre.data.to.SpaceTO;

public class SpaceTXTDAO implements SpaceDAO {
  
  private File file;
  private BufferedReader reader;
  private BufferedWriter writer;
  
  /**
   * @param file Semicolon separated plain text file where spaces are stored.
   */
  public SpaceTXTDAO(File file) {
    this.file = file;
  }
  
  @Override
  public TransferObject create(TransferObject newObject) {
    FileWriter fileWriter;
    SpaceTO space = null;
    try {
      fileWriter = new FileWriter(file, true);
      writer = new BufferedWriter(fileWriter);
      space = (SpaceTO) newObject;
      String newSpace;
      newSpace = space.getId() + ";"
               + space.getMaxCapacity() + ";"
               + space.getLocation() + ";"
               + space.isAvailable();
      writer.append(newSpace);
      writer.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return space;
  }

  @Override
  public List<TransferObject> retrieve(TransferObject params) {
    SpaceTO space = null;
    List<TransferObject> result = new ArrayList<TransferObject>();
    try {
      reader = new  BufferedReader(new FileReader(file));
      space = (SpaceTO) params;
      String line;
      while ((line = reader.readLine()) != null) {
        String [] columns = line.split(";");
        
        //Ignore position 0 because it is the id
        int maxCapacity = Integer.parseInt(columns[1]);
        String location = columns[2];
        boolean available = Boolean.valueOf(columns[3]);
        
        if (maxCapacity == space.getMaxCapacity() &&
            location.equals(space.getLocation()) &&
            available == space.isAvailable()) {
          SpaceTO row = new SpaceTO();
          row.setMaxCapacity(maxCapacity);
          row.setLocation(location);
          row.setAvailable(available);
          result.add(row);
        }
      }
      reader.close();
    }
    catch (FileNotFoundException fnfe) {
      fnfe.printStackTrace();
    }
    catch (IOException ioe) {
      ioe.printStackTrace();
    }
    return result;
  }

  @Override
  public int update(TransferObject object) {
    SpaceTO space = null;
    int response = 0;
    try {
      space = (SpaceTO) object;
      reader = new  BufferedReader(new FileReader(file));
      String line, output = "";
      while ((line = reader.readLine()) != null) {
        String [] column = line.split(";");
        if (Integer.parseInt(column[0]) == space.getId()) {
          String newSpace;
          newSpace = space.getId() + ";"
                   + space.getMaxCapacity() + ";"
                   + space.getLocation() + ";"
                   + space.isAvailable();
          output = output + newSpace + "\n";
        }
        else output = output + line + "\n";
      }
      reader.close();
      writer = new BufferedWriter(new FileWriter(file, false));
      writer.write(output);
      writer.close();
      response = 1;
    }
    catch (FileNotFoundException fnfe) {
      fnfe.printStackTrace();
    }
    catch (IOException ioe) {
      ioe.printStackTrace();
    }
    return response;
  }

  @Override
  public int delete(TransferObject params) {
    SpaceTO space = null;
    int response = 0;
    try {
      space = (SpaceTO) params;
      reader = new  BufferedReader(new FileReader(file));
      String line, output = "";
      while ((line = reader.readLine()) != null) {
        String [] column = line.split(";");
        if (Integer.parseInt(column[0]) == space.getId()) continue;
        else output = output + line + "\n";
      }
      reader.close();
      writer = new BufferedWriter(new FileWriter(file, false));
      writer.write(output);
      writer.close();
      response = 1;
    }
    catch (FileNotFoundException fnfe) {
      fnfe.printStackTrace();
    }
    catch (IOException ioe) {
      ioe.printStackTrace();
    }
    return response;
  }
}

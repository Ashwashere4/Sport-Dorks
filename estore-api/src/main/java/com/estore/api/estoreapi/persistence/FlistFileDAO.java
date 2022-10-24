package com.estore.api.estoreapi.persistence;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.estore.api.estoreapi.model.Facilities;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class FlistFileDAO implements FlistDAO {
     /**
     * The current inventory.
     */
    private Map<Integer, Facilities> flist;

    /**
     * The file name of the inventory file.
     */
    private String filename;

    /**
     * The object mapper.
     */

    private ObjectMapper objectMapper;

    /**
     * 
     * @param flist
     */
    

    public FlistFileDAO(@Value("${flist.filename}") String filename, ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        loadflist();
    }
    
    /**
     * Load the inventory from the file.
     */

    private void loadflist() throws IOException {
        flist = new TreeMap<>();
        Facilities[] flistArray = objectMapper.readValue(new File(filename), Facilities[].class);
        for (Facilities facilities : flistArray) {
            flist.put(facilities.getTeamcode(), facilities);
        }
    }

    private void saveInventory() throws IOException{
        objectMapper.writeValue(new File(filename), getflistArray());
    }

    private ArrayList<Facilities> getflistArray() {
        return new ArrayList<>(flist.values());
    }

    @Override
    public Facilities[] getTeams() throws IOException {
        return getflistArray().toArray(new Facilities[0]);
    }

    @Override
    public Facilities[] searchTeams(String text) throws IOException {
        if (text.length() == 0)
            return new Facilities[0];

        ArrayList<Facilities> facilities = new ArrayList<>();

        for(Facilities teams : flist.values()){
            if(teams.getName().toLowerCase().contains(text.toLowerCase())){
                facilities.add(teams);
                
            }
        }
        return facilities.toArray(new Facilities[0]);
    }

    @Override
    public Facilities getTeam(int code) throws IOException {
        Facilities facilities = flist.get(code);

        if (facilities != null)
            return facilities;
        else
            System.out.println("Team does not exist. Did you type the right code?");
            return null;
    }

    @Override
    public Facilities createTeam(String name, int team_code, int player_count) throws IOException {
        Facilities newTeam = new Facilities(name, team_code, player_count);
        flist.put(team_code, newTeam);
        saveInventory();
        return newTeam;
    }

    @Override
    public Facilities updateTeam(Facilities facilities, String name, int team_code, int player_count)
            throws IOException {
        
        Facilities updatedTeam = new Facilities(name, team_code, player_count);

        deleteTeam(facilities.getTeamcode());

        flist.put(updatedTeam.getTeamcode(), updatedTeam);

        return updatedTeam;
    }

    @Override
    public boolean deleteTeam(int team_code) throws IOException {
        flist.remove(team_code);
        return true;
    }

}
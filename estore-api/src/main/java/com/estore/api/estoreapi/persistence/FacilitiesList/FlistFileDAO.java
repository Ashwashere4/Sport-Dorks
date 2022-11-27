package com.estore.api.estoreapi.persistence.FacilitiesList;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.model.facilities.Facilities;
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
        flist = new HashMap<>();
        Facilities[] flistArray = objectMapper.readValue(new File(filename), Facilities[].class);
        for (Facilities facilities : flistArray) {
            flist.put(facilities.getFacility_id(), facilities);
        }
    }

    private void saveInventory() throws IOException{
        objectMapper.writeValue(new File(filename), getflistArray());
    }

    private ArrayList<Facilities> getflistArray() {
        return new ArrayList<>(flist.values());
    }

    @Override
    public Facilities[] getFacilities() throws IOException {
        return getflistArray().toArray(new Facilities[0]);
    }

    @Override
    public Facilities[] searchFacilities(String text) throws IOException {
        if (text.length() == 0)
            return new Facilities[0];

        ArrayList<Facilities> facilities = new ArrayList<>();

        for(Facilities locations : flist.values()){
            if(locations.getName().toLowerCase().contains(text.toLowerCase())){
                facilities.add(locations);
                
            }
        }
        return facilities.toArray(new Facilities[0]);
    }

    @Override
    public Facilities getFacility(int code) throws IOException {
        Facilities facilities = flist.get(code);

        if (facilities != null)
            return facilities;
        else
            System.out.println("Team does not exist. Did you type the right code?");
            return null;
    }

    @Override
    public Facilities createFacility(Facilities facility) throws IOException {
        Facilities newfacility = new Facilities(facility.getName(), facility.getLocation(), facility.getFacility_id());
        flist.put(facility.getFacility_id(), newfacility);
        saveInventory();
        return newfacility;
    }

    @Override
    public Facilities createFacility(String name, String location, int facility_id) throws IOException{
        Facilities newfacility = new Facilities(name, location, facility_id);
        flist.put(facility_id, newfacility);
        saveInventory();
        return newfacility;
    }

    @Override
    public Facilities updateFacility(Facilities facilities, String name, String location, int facility_id)
            throws IOException {
        
       facilities.setName(name);
       facilities.setLocation(location);
       facilities.setId(facility_id);

        return facilities;
    }

    @Override
    public boolean deleteFacility(int facility_id) throws IOException {
        flist.remove(facility_id);
        return true;
    }

    // @Override
    // public boolean addTeam_reserve(Team team, Facilities facility) throws IOException{

    //     return facility.addTeamReserve(team);
        
    // }

    // @Override
    // public boolean removeTeam_reserve(Facilities facility) throws IOException{
        
    //     return facility.removeTeamReserve();
    // }

}
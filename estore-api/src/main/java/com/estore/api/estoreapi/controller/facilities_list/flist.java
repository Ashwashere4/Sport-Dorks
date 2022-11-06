package com.estore.api.estoreapi.controller.facilities_list;
import java.util.HashMap;

import com.estore.api.estoreapi.model.Facilities.Facilities;


public class flist {
    /** Flist is a list of the facilities, will be a hashmap with CODE pointing to the facilities associated */
    HashMap<Integer, Facilities> flist;

    /**
     * 
     * @param flist
     */

    public flist(HashMap<Integer, Facilities> flist) {
        this.flist = flist;
    }
}

package com.hust.qiudai.web.comparator;

import java.util.Comparator;

import com.hust.qiudai.web.db.University;

public class UniversityDistanceComparator implements Comparator<University> {

	public int compare(University o1, University o2) {
		if (null != o1 && null != o2) {
			if(o1.getDistanceFromLocation() > o2.getDistanceFromLocation()){
        		return 1;
        	}else if(o1.getDistanceFromLocation() < o2.getDistanceFromLocation()){
        		return -1;
        	}else{
        		return 0;
        	}
		}
		
		return 0;
	}

}

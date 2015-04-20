package net.guns_project.manager;

import java.util.List;

import net.guns_project.domain.Description;

public interface IDescriptionManager {
	
	int createDescription(Description desc) throws Exception;
	
	void updateDescription(Description desc) throws Exception;
	
	void removeDescription(int idDescription) throws Exception;
	
	List<Description> getAllDescription() throws Exception;
	
	List<Description> getWeaponDescription(int idDescription) throws Exception;

}

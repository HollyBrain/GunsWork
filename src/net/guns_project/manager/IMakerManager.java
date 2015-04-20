package net.guns_project.manager;

import java.util.List;
import net.guns_project.domain.Maker;

public interface IMakerManager {
	
	int createMaker(Maker maker) throws Exception;
	
	void updateMaker(Maker maker) throws Exception;
	
	void removeMaker(int MakerId) throws Exception;
	
	List<Maker> getAllMakers() throws Exception;
	
	Maker getMakerById(int MakerId) throws Exception;

}

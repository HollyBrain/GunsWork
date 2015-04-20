package net.guns_project.manager;

import java.util.List;

import net.guns_project.domain.Relations;

public interface IRelationManager {
	
	List<Relations> getAllRelations() throws Exception;

}

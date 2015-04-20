package net.guns_project.manager;

import java.util.List;

import net.guns_project.dao.RelationsDAO;
import net.guns_project.domain.Relations;

public class RelationManager implements IRelationManager{
	RelationsDAO rlDAO=new RelationsDAO();

	public List<Relations> getAllRelations() throws Exception {
		return rlDAO.findAll();
	}

}

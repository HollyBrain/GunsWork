package net.guns_project.manager;

import java.util.List;

import net.guns_project.dao.DescriptionDAO;
import net.guns_project.domain.Description;

public class DescriptionManager implements IDescriptionManager{

	DescriptionDAO descDAO=new DescriptionDAO();
	@Override
	public int createDescription(Description desc) throws Exception {
		validateDescription(desc);
		return descDAO.insertDescription(desc);
	}

	@Override
	public void updateDescription(Description desc) throws Exception {
		// TODO Auto-generated method stub
		validateDescription(desc);
		descDAO.updateWeapon(desc);
		
	}

	@Override
	public void removeDescription(int idDescription) throws Exception {
		// TODO Auto-generated method stub
		descDAO.deleteDescription(idDescription);
		
	}

	@Override
	public List<Description> getAllDescription() throws Exception {
		// TODO Auto-generated method stub
		
		return descDAO.findAll();
	}

	
	private void validateDescription(Description desc) {
		if (desc == null) {
			throw new IllegalArgumentException(
					"Опис повинен бути вказаний");
		}
		if (desc.getDescriptionName() == null || desc.getDescriptionName().length() == 0) {
			throw new IllegalArgumentException(
					"Назва опису повнна бути вказана");
		}

	}

	@Override
	public List<Description> getWeaponDescription(int idDescription)
			throws Exception {
		// TODO Auto-generated method stub
		return descDAO.findByWeaponDescription(idDescription);
	}


}

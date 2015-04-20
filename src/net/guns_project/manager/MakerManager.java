package net.guns_project.manager;

import java.util.List;

import net.guns_project.dao.MakerDAO;
import net.guns_project.domain.Maker;

public class MakerManager implements IMakerManager{
	
	MakerDAO makerDAO =new MakerDAO();

	@Override
	public int createMaker(Maker maker) throws Exception {
		// TODO Auto-generated method stub
		validateMaker(maker);
		return makerDAO.insertMaker(maker);
	}

	@Override
	public void updateMaker(Maker maker) throws Exception {
		// TODO Auto-generated method stub
		validateMaker(maker);
		makerDAO.updateMaker(maker);
		
	}

	@Override
	public void removeMaker(int MakerId) throws Exception {
		// TODO Auto-generated method stub
		makerDAO.deleteMaker(MakerId);
		
	}

	@Override
	public List<Maker> getAllMakers() throws Exception {
		// TODO Auto-generated method stub
		return makerDAO.findAll();
	}

	@Override
	public Maker getMakerById(int MakerId) throws Exception {
		// TODO Auto-generated method stub
		return makerDAO.findById(MakerId);
	}
	
	
	private void validateMaker(Maker maker) {

		

	}

}

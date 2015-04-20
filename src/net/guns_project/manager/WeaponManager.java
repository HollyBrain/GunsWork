package net.guns_project.manager;

import java.util.List;

import net.guns_project.dao.WeaponDAO;
import net.guns_project.domain.Weapon;

public class WeaponManager implements IWeaponManager{
	WeaponDAO weaponDAO = new WeaponDAO();

	public int createWeapon(Weapon weapon) throws Exception {
		// TODO Auto-generated method stub
		validateWeapon(weapon);
		return weaponDAO.insertWeapon(weapon);
	}

	@Override
	public void updateWeapon(Weapon weapon) throws Exception {
		// TODO Auto-generated method stub
		validateWeapon(weapon);
		weaponDAO.updateWeapon(weapon);
		
	}

	public void removeWeapon(int WeaponId) throws Exception {
		// TODO Auto-generated method stub
		weaponDAO.deleteWeapon(WeaponId);
		
	}

	@Override
	public List<Weapon> getAllWeapons() throws Exception {
		// TODO Auto-generated method stub
		return weaponDAO.findAll();
	}
	
	@Override
	public List<Weapon> getMakerWeapons(int WeaponId) throws Exception {
		return weaponDAO.findByWeaponMaker(WeaponId);
	}
		
	private void validateWeapon(Weapon weapon) {
		if (weapon == null) {
			throw new IllegalArgumentException(
					"Назва зброї повинна бути вказана");
		}
		if (weapon.getWeaponName() == null || weapon.getWeaponName().length() == 0) {
			throw new IllegalArgumentException(
					"Компанія повинна бути заповнена");
		}

	}


}

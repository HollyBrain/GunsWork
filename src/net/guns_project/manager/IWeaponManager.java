package net.guns_project.manager;

import java.util.List;

import net.guns_project.domain.Weapon;

public interface IWeaponManager {
	
	int createWeapon(Weapon weapon) throws Exception;
	
	void updateWeapon(Weapon weapon) throws Exception;
	
	void removeWeapon(int WeaponId) throws Exception;
	
	List<Weapon> getAllWeapons() throws Exception;
	
	List<Weapon> getMakerWeapons(int WeaponId) throws Exception;

}

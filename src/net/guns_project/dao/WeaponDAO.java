package net.guns_project.dao;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import net.guns_project.domain.Weapon;

public class WeaponDAO {
	private static final String INSERT_QUERY = "INSERT INTO `guns`.`weapon` (`WeaponName`, `Maker`, `Date`, `Other`) VALUES (?,?,?,?)";
	private static final String UPDATE_QUERY = "UPDATE `guns`.`weapon` SET `WeaponName`=?, `Maker`=?, `Date`=?, `Other`=? WHERE `WeaponId`=?";
	private static final String DELETE_QUERY = "DELETE FROM `guns`.`weapon` WHERE `WeaponId`=?";
	private static final String SELECT_QUERY = "SELECT WeaponId, WeaponName, Maker, Date, Other FROM guns.weapon"
			+ "where WeaponId = ?";
	private static final String SELECT_ALL_QUERY = "SELECT WeaponId, WeaponName, Maker, Date, Other FROM guns.weapon ";
	private static final String SELECT_RELATION_QUERY="select weapon.WeaponId, weapon.WeaponName, weapon.Maker, weapon.Date, weapon.Other from `guns`.`weapon` where guns.weapon.Maker=?";
	
	public int insertWeapon(Weapon weapon) throws Exception {

		Connection connection = DataAccessUtil.createConnection();
		PreparedStatement statement = connection.prepareStatement(INSERT_QUERY,
				Statement.RETURN_GENERATED_KEYS);

		try {
			statement.setString(1, weapon.getWeaponName());
			statement.setInt(2, weapon.getMakerId());
			statement.setString(3, weapon.getDate());
			statement.setString(4, weapon.getOther());
			statement.executeUpdate();

			return DataAccessUtil.getNewRowKey(statement);
		} finally {
			DataAccessUtil.close(connection);
		}
	}

	public void updateWeapon(Weapon weapon) throws Exception {
		Connection connection = DataAccessUtil.createConnection();
		PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);

		try {
			statement.setString(1, weapon.getWeaponName());
			statement.setInt(2, weapon.getMakerId());
			statement.setString(3, weapon.getDate());
			statement.setString(4, weapon.getOther());
			statement.executeUpdate();

		} finally {
			DataAccessUtil.close(connection);
		}
	}

	public void deleteWeapon(int weaponId) throws Exception {
		Connection connection = DataAccessUtil.createConnection();
		PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);

		try {
			statement.setInt(1, weaponId);
			statement.executeUpdate();
		} finally {
			DataAccessUtil.close(connection);
		}
	}

	public Weapon findById(int weaponId) throws Exception {
		Connection connection = DataAccessUtil.createConnection();
		PreparedStatement statement = connection.prepareStatement(SELECT_QUERY);

		try {
			statement.setInt(1, weaponId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return getWeaponFromRow(rs);
			}
			return null;
		} finally {
			DataAccessUtil.close(connection);
		}
	}

	public List<Weapon> findAll() throws Exception {
		Connection connection = DataAccessUtil.createConnection();
		PreparedStatement statement = connection
				.prepareStatement(SELECT_ALL_QUERY);

		try {
			ResultSet rs = statement.executeQuery();
			List<Weapon> result = new ArrayList<Weapon>();
			while (rs.next()) {
				result.add(getWeaponFromRow(rs));
			}
			return result;
		} finally {
			DataAccessUtil.close(connection);
		}
	}

	public List<Weapon> findByWeaponMaker(int weaponId) throws Exception {
		Connection connection = DataAccessUtil.createConnection();
		PreparedStatement statement = connection
				.prepareStatement(SELECT_RELATION_QUERY);
		try {
			statement.setInt(1, weaponId);
			ResultSet rs = statement.executeQuery();
			List<Weapon> result = new ArrayList<Weapon>();
			while (rs.next()) {
				result.add(getWeaponFromRow(rs));
			}
			return result;
		} finally {
			DataAccessUtil.close(connection);
		}
	}
	private static Weapon getWeaponFromRow(ResultSet rs) throws Exception {
		Weapon weapon = new Weapon();
		weapon.setId(rs.getInt(1));
		weapon.setWeaponName(rs.getString(2));
		weapon.setMakerId(rs.getInt(3));
		weapon.setDate(rs.getString(4));
		weapon.setOther(rs.getString(5));

		return weapon;
	}



}

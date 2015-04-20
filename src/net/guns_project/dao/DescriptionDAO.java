package net.guns_project.dao;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import net.guns_project.domain.Description;

public class DescriptionDAO {
	
	private static final String INSERT_QUERY = "INSERT INTO `guns`.`description` (`WeaponName`, `DescriptionName`, `ValueDescription`, `OtherDescription`) VALUES (?, ?, ?, ?)";
	private static final String UPDATE_QUERY = "UPDATE `guns`.`description` SET `WeaponName`=?, `DescriptionName`=?, `ValueDescription`=?, `OtherDescription`=? WHERE `idDescription`=?";
	private static final String DELETE_QUERY = "DELETE FROM `guns`.`description` WHERE `idDescription`=?";
	private static final String SELECT_QUERY = "SELECT `WeaponName`, `DescriptionName`, `ValueDescription`, `OtherDescription` FROM guns.description"
			+ "where idDescription = ?";
	private static final String SELECT_ALL_QUERY = "SELECT `WeaponName`, `DescriptionName`, `ValueDescription`, `OtherDescription` FROM guns.description ";
	private static final String SELECT_RELATION_QUERY="select description.WeaponName, description.DescriptionName, description.ValueDescription, description.OtherDescription from `guns`.`weapon`, `guns`.`description` where guns.weapon.weaponId=guns.description.WeaponName";
	
	
	public int insertDescription(Description desc) throws Exception {

		Connection connection = DataAccessUtil.createConnection();
		PreparedStatement statement = connection.prepareStatement(INSERT_QUERY,
				Statement.RETURN_GENERATED_KEYS);

		try {
			statement.setInt(1, desc.getWeaponName());
			statement.setString(2, desc.getDescriptionName());
			statement.setString(3, desc.getValueDescription());
			statement.setString(4, desc.getOtherDescription());
			statement.executeUpdate();

			return DataAccessUtil.getNewRowKey(statement);
		} finally {
			DataAccessUtil.close(connection);
		}
	}
	
	public void updateWeapon(Description desc) throws Exception {
		Connection connection = DataAccessUtil.createConnection();
		PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);

		try {
			statement.setInt(1, desc.getWeaponName());
			statement.setString(2, desc.getDescriptionName());
			statement.setString(3, desc.getValueDescription());
			statement.setString(4, desc.getOtherDescription());
			statement.executeUpdate();

		} finally {
			DataAccessUtil.close(connection);
		}
	}
	
	public void deleteDescription(int idDescription) throws Exception {
		Connection connection = DataAccessUtil.createConnection();
		PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);

		try {
			statement.setInt(1, idDescription);
			statement.executeUpdate();
		} finally {
			DataAccessUtil.close(connection);
		}
	}
	
	public Description findById(int idDescription) throws Exception {
		Connection connection = DataAccessUtil.createConnection();
		PreparedStatement statement = connection.prepareStatement(SELECT_QUERY);

		try {
			statement.setInt(1, idDescription);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return getDescriptionFromRow(rs);
			}
			return null;
		} finally {
			DataAccessUtil.close(connection);
		}
	}
	
	public List<Description> findAll() throws Exception {
		Connection connection = DataAccessUtil.createConnection();
		PreparedStatement statement = connection
				.prepareStatement(SELECT_ALL_QUERY);

		try {
			ResultSet rs = statement.executeQuery();
			List<Description> result = new ArrayList<Description>();
			while (rs.next()) {
				result.add(getDescriptionFromRow(rs));
			}
			return result;
		} finally {
			DataAccessUtil.close(connection);
		}
	}
	
	public List<Description> findByWeaponDescription(int idDecsription) throws Exception {
		Connection connection = DataAccessUtil.createConnection();
		PreparedStatement statement = connection
				.prepareStatement(SELECT_RELATION_QUERY);
		try {
			statement.setInt(1, idDecsription);
			ResultSet rs = statement.executeQuery();
			List<Description> result = new ArrayList<Description>();
			while (rs.next()) {
				result.add(getDescriptionFromRow(rs));
			}
			return result;
		} finally {
			DataAccessUtil.close(connection);
		}
	}
	
	private static Description getDescriptionFromRow(ResultSet rs) throws Exception {
		Description desc = new Description();
		desc.setId(rs.getInt(1));
		desc.setWeaponName(rs.getInt(2));
		desc.setDescriptionName(rs.getString(3));
		desc.setValueDescription(rs.getString(4));
		desc.setOtherDescription(rs.getString(5));

		return desc;
	}

}

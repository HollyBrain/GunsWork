package net.guns_project.dao;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import net.guns_project.domain.Maker;

public class MakerDAO {

	private static final String INSERT_QUERY = "INSERT INTO `guns`.`maker` (`CompanyName`, `YearOfFoundation`, `DirectorName`, `RegistrationNumber`, `Adress`, `PhoneNumber`, `FaxNumber`, `E-mail`, `Web-site`) VALUES (?,?,?,?,?,?,?,?,?)";
	//private static final String UPDATE_QUERY = "UPDATE maker SET CompanyName=?, YearOfFoundation=?, DirectorName=?, RegistrationNumber=?, Adress=?, PhoneNumber=?, FaxNumber=?, `E-mail`='?', `Web-site`='?' WHERE MakerId=?";
	private static final String UPDATE_QUERY = "UPDATE `guns`.`maker`"
			+ "SET`CompanyName` = ?, `YearOfFoundation` = ?, `DirectorName` = ?, `RegistrationNumber` = ?, `Adress` = ?,`PhoneNumber` = ?, `FaxNumber` = ?, `E-mail` = ?, `Web-site` = ?"
			+ "WHERE `MakerId` = ?;";
	private static final String DELETE_QUERY = "delete from maker where MakerId = ?";
	private static final String SELECT_QUERY = "select MakerId, CompanyName, YearOfFoundation, DirectorName, RegistrationNumber, Adress, PhoneNumber, FaxNumber, `E-mail`, `Web-site` "
			+ "from maker where MakerId = ?";
	private static final String SELECT_ALL_QUERY = "select MakerId, CompanyName, YearOfFoundation, DirectorName, RegistrationNumber, Adress, PhoneNumber, FaxNumber, `E-mail`, `Web-site` from maker";

	public int insertMaker(Maker maker) throws Exception {

		Connection connection = DataAccessUtil.createConnection();
		PreparedStatement statement = connection.prepareStatement(INSERT_QUERY,
				Statement.RETURN_GENERATED_KEYS);

		try {
			statement.setString(1, maker.getCompanyName());
			statement.setInt(2, maker.getYearOfFoundation());
			statement.setString(3, maker.getDirectorName());
			statement.setString(4, maker.getRegistrationNumber());
			statement.setString(5, maker.getAdress());
			statement.setString(6, maker.getPhoneNumber());
			statement.setString(7, maker.getFaxNumber());
			statement.setString(8, maker.getMail());
			statement.setString(9, maker.getSite());
			statement.executeUpdate();

			return DataAccessUtil.getNewRowKey(statement);
		} finally {
			DataAccessUtil.close(connection);
		}
	}

	public void updateMaker(Maker maker) throws Exception {
		Connection connection = DataAccessUtil.createConnection();
		PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);

		try {
			statement.setString(1, maker.getCompanyName());
			statement.setInt(2, maker.getYearOfFoundation());
			statement.setString(3, maker.getDirectorName());
			statement.setString(4, maker.getRegistrationNumber());
			statement.setString(5, maker.getAdress());
			statement.setString(6, maker.getPhoneNumber());
			statement.setString(7, maker.getFaxNumber());
			statement.setString(8, maker.getMail());
			statement.setString(9, maker.getSite());
			statement.setInt(10, maker.getId());
			statement.executeUpdate();

		} finally {
			DataAccessUtil.close(connection);
		}
	}

	public void deleteMaker(int makerId) throws Exception {
		Connection connection = DataAccessUtil.createConnection();
		PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);

		try {
			statement.setInt(1, makerId);
			statement.executeUpdate();
		} finally {
			DataAccessUtil.close(connection);
		}
	}

	public Maker findById(int makerId) throws Exception {
		Connection connection = DataAccessUtil.createConnection();
		PreparedStatement statement = connection.prepareStatement(SELECT_QUERY);

		try {
			statement.setInt(1, makerId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return getMakerFromRow(rs);
			}
			return null;
		} finally {
			DataAccessUtil.close(connection);
		}
	}

	public List<Maker> findAll() throws Exception {
		Connection connection = DataAccessUtil.createConnection();
		PreparedStatement statement = connection
				.prepareStatement(SELECT_ALL_QUERY);

		try {
			ResultSet rs = statement.executeQuery();
			List<Maker> result = new ArrayList<Maker>();
			while (rs.next()) {
				result.add(getMakerFromRow(rs));
			}
			return result;
		} finally {
			DataAccessUtil.close(connection);
		}
	}

	private static Maker getMakerFromRow(ResultSet rs) throws Exception {
		Maker maker = new Maker();
		maker.setId(rs.getInt(1));
		maker.setCompanyName(rs.getString(2));
		maker.setYearOfFoundation(rs.getInt(3));
		maker.setDirectorName(rs.getString(4));
		maker.setRegistrationNumber(rs.getString(5));
		maker.setAdress(rs.getString(6));
		maker.setPhoneNumber(rs.getString(7));
		maker.setFaxNumber(rs.getString(8));
		maker.setMail(rs.getString(9));
		maker.setSite(rs.getString(10));

		return maker;
	}
}

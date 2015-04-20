package net.guns_project.dao;

import net.guns_project.domain.Relations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RelationsDAO {
	private static final String SELECT_ALL_QUERY = "select maker.CompanyName, weapon.WeaponName, description.DescriptionName, description.ValueDescription from maker, weapon, description where weapon.Maker=maker.MakerId && description.WeaponName=weapon.WeaponId";

	
	public List<Relations> findAll() throws Exception {
		Connection connection = DataAccessUtil.createConnection();
		PreparedStatement statement = connection
				.prepareStatement(SELECT_ALL_QUERY);

		try {
			ResultSet rs = statement.executeQuery();
			List<Relations> result = new ArrayList<Relations>();
			while (rs.next()) {
				result.add(getRelationFromRow(rs));
			}
			return result;
		} finally {
			DataAccessUtil.close(connection);
		}
	}
	private static Relations getRelationFromRow(ResultSet rs) throws Exception {
		Relations rl = new Relations();
		rl.setCompanyName(rs.getString(0));
		rl.setWeaponName(rs.getString(1));
		rl.setDescName(rs.getString(2));
		rl.setValueDesc(rs.getString(3));

		return rl;
	}
}

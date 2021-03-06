package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import kr.nomad.mars.dto.Group;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class GroupDao {
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper groupMapper = new RowMapper() {
		public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
			Group group = new Group();
			group.setGroupSeq(rs.getInt("group_seq"));
			group.setGroupCode(rs.getString("group_code"));
			group.setGroupName(rs.getString("group_name"));
			group.setPhone(rs.getString("phone"));
			group.setComment(rs.getString("comment"));
			return group;
		}
	};

	public void addGroup(final Group group) {
		String query = "" +
				"INSERT INTO T_NF_GROUP " +
				"	(group_seq, group_code, group_name, phone, comment) " +
				"VALUES " +
				"	(?, ?, ?, ?, ?) ";
		this.jdbcTemplate.update(query, new Object[] {
			group.getGroupSeq(),
			group.getGroupCode(),
			group.getGroupName(),
			group.getPhone(),
			group.getComment()
		});
	}

	public void deleteGroup(String group_seq) {
		String query = "DELETE FROM T_NF_GROUP WHERE group_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { group_seq });
	}

	public void updateGroup(Group group) { 
		String query = "" + 
				"UPDATE T_NF_GROUP SET " +
				"	group_seq = ?, " +
				"	group_code = ?, " +
				"	group_name = ?, " +
				"	phone = ?, " +
				"	comment = ? " +
				"WHERE group_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			group.getGroupSeq(),
			group.getGroupCode(),
			group.getGroupName(),
			group.getPhone(),
			group.getComment()
		});
	}

	public Group getGroup(String group_seq) {
		String query = "" + 
				"SELECT group_seq, group_code, group_name, phone, comment " +
				"FROM T_NF_GROUP " +
				"WHERE group_seq = ? ";
		return (Group)this.jdbcTemplate.queryForObject(query, new Object[] { group_seq }, this.groupMapper);
	}

	/*
	public List<Group> getGroupList(int page, int itemCountPerPage) {
		String query = "" +
				"SELECT TOP "+ itemCountPerPage +" group_seq, group_code, group_name, phone, comment " +
				"FROM T_NF_GROUP " +
				"WHERE group_seq <= ( " +
				"	SELECT MIN(group_seq) " +
				"	FROM ( " +
				"		SELECT TOP "+ ((page-1) * itemCountPerPage + 1) +" group_seq " +
				"		FROM T_NF_GROUP " +
				"		ORDER BY group_seq DESC " +
				"	) AS A) " +
				"ORDER BY group_seq DESC";
		return (List<Group>)this.jdbcTemplate.query(query, this.groupMapper);
	}
	*/
}

package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import kr.nomad.mars.dto.ManageIndex;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class ManageIndexDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper manageindexMapper = new RowMapper() {
		public ManageIndex mapRow(ResultSet rs, int rowNum) throws SQLException {
			ManageIndex manageindex = new ManageIndex();
			manageindex.setIndexSeq(rs.getInt("index_seq"));
			manageindex.setGoalSmblood(rs.getInt("goal_smblood"));
			manageindex.setGoalBmblood(rs.getInt("goal_bmblood"));
			manageindex.setGoalEblood(rs.getInt("goal_eblood"));
			manageindex.setGoalEsblood(rs.getInt("goal_esblood"));
			manageindex.setGoalSblood(rs.getInt("goal_sblood"));
			manageindex.setGoalSsblood(rs.getInt("goal_ssblood"));
			manageindex.setGoalHba(rs.getFloat("goal_hba"));
			manageindex.setGoalSpre(rs.getInt("goal_spre"));
			manageindex.setGoalSpreOld(rs.getInt("goal_spre_old"));
			manageindex.setGoalBpre(rs.getInt("goal_bpre"));
			manageindex.setGoalBpreOld(rs.getInt("goal_bpre_old"));
			manageindex.setGoalPul(rs.getInt("goal_pul"));
			manageindex.setGoalCol(rs.getInt("goal_col"));
			manageindex.setGoalLdl(rs.getInt("goal_ldl"));
			manageindex.setGoalHdl(rs.getInt("goal_hdl"));
			manageindex.setGoalTg(rs.getInt("goal_tg"));
			manageindex.setGoalSbmi(rs.getFloat("goal_sbmi"));
			manageindex.setGoalBbmi(rs.getFloat("goal_bbmi"));
			manageindex.setEndTime(rs.getString("end_time"));
			manageindex.setStartTime(rs.getString("start_time"));
			manageindex.setType(rs.getInt("type"));
			return manageindex;
		}
	};

	public void addManageIndex(final ManageIndex manageindex) {
		String query = "" +
				"INSERT INTO T_NF_MANAGE_INDEX " +
				"	(goal_smblood, goal_bmblood, goal_eblood, goal_esblood, goal_sblood, goal_ssblood, goal_hba, goal_spre, goal_bpre, goal_spre_old, goal_bpre_old, goal_pul, goal_col, goal_ldl, goal_hdl, goal_tg, goal_sbmi, goal_bbmi,end_time,start_time,type) " +
				"VALUES " +
				"	(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		this.jdbcTemplate.update(query, new Object[] {
			manageindex.getGoalSmblood(),
			manageindex.getGoalBmblood(),
			manageindex.getGoalEblood(),
			manageindex.getGoalEsblood(),
			manageindex.getGoalSblood(),
			manageindex.getGoalSsblood(),
			manageindex.getGoalHba(),
			manageindex.getGoalSpre(),
			manageindex.getGoalBpre(),
			manageindex.getGoalSpreOld(),
			manageindex.getGoalBpreOld(),
			manageindex.getGoalPul(),
			manageindex.getGoalCol(),
			manageindex.getGoalLdl(),
			manageindex.getGoalHdl(),
			manageindex.getGoalTg(),
			manageindex.getGoalSbmi(),
			manageindex.getGoalBbmi(),
			manageindex.getEndTime(),
			manageindex.getStartTime(),
			manageindex.getType()
		});
	}

	public void deleteManageIndex(int index_seq) {
		String query = "DELETE FROM T_NF_MANAGE_INDEX WHERE index_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { index_seq });
	}

	public void updateManageIndex(ManageIndex manageindex) { 
		String query = "" + 
				"UPDATE T_NF_MANAGE_INDEX SET " +
				"	goal_smblood = ?, " +
				"	goal_bmblood = ?, " +
				"	goal_eblood = ?, " +
				"	goal_esblood = ?, " +
				"	goal_sblood = ?, " +
				"	goal_ssblood = ?, " +
				"	goal_hba = ?, " +
				"	goal_spre = ?, " +
				"	goal_bpre = ?, " +
				"	goal_spre_old = ?, " +
				"	goal_bpre_old = ?, " +
				"	goal_pul = ?, " +
				"	goal_col = ?, " +
				"	goal_ldl = ?, " +
				"	goal_hdl = ?, " +
				"	goal_tg = ?, " +
				"	goal_sbmi = ?, " +
				"	goal_bbmi = ?, " +
				"	end_time = ?, " +
				"	start_time = ?, " +
				"	type = ? " +
				"WHERE index_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			manageindex.getGoalSmblood(),
			manageindex.getGoalBmblood(),
			manageindex.getGoalEblood(),
			manageindex.getGoalEsblood(),
			manageindex.getGoalSblood(),
			manageindex.getGoalSsblood(),
			manageindex.getGoalHba(),
			manageindex.getGoalSpre(),
			manageindex.getGoalBpre(),
			manageindex.getGoalSpreOld(),
			manageindex.getGoalBpreOld(),
			manageindex.getGoalPul(),
			manageindex.getGoalCol(),
			manageindex.getGoalLdl(),
			manageindex.getGoalHdl(),
			manageindex.getGoalTg(),
			manageindex.getGoalSbmi(),
			manageindex.getGoalBbmi(),
			manageindex.getEndTime(),
			manageindex.getStartTime(),
			manageindex.getType(),
			manageindex.getIndexSeq()

		});
	}

	public ManageIndex getManageIndex(int index_seq) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_MANAGE_INDEX " +
				"WHERE index_seq = ? ";
		return (ManageIndex)this.jdbcTemplate.queryForObject(query, new Object[] { index_seq }, this.manageindexMapper);
	}
	
	public ManageIndex getManageIndex() {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_MANAGE_INDEX where type =1 ";
			
		return (ManageIndex)this.jdbcTemplate.queryForObject(query, this.manageindexMapper);
	}
	
	public ManageIndex getrevTime(int type) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_MANAGE_INDEX where type = ? ";
			
		return (ManageIndex)this.jdbcTemplate.queryForObject(query,new Object[] { type }, this.manageindexMapper);
	}

	
	public List<ManageIndex> getManageIndexList() {
		String query = "" +
				"SELECT * " +
				"FROM T_NF_MANAGE_INDEX " +
				"ORDER BY index_seq DESC";
		return (List<ManageIndex>)this.jdbcTemplate.query(query, this.manageindexMapper);
	}

	public ManageIndex getManageIndexTop() {
		String query = ""
				+ "	SELECT * "
				+ "	FROM T_NF_MANAGE_INDEX "
				+ "	ORDER BY index_seq DESC"
				+ "	LIMIT 1 ";
		return (ManageIndex)this.jdbcTemplate.queryForObject(query, this.manageindexMapper);
	}

	

}

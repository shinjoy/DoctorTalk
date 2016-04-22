package kr.nomad.mars.dao;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.Badge;


public class BadgeDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper badgeMapper = new RowMapper() {
		public Badge mapRow(ResultSet rs, int rowNum) throws SQLException {
			Badge badge = new Badge();
			badge.setBadgeSeq(rs.getInt("badge_seq"));
			badge.setBadgeType(rs.getInt("badge_type"));
			badge.setBadgeStatus(rs.getInt("badge_status"));
			badge.setRegDate(rs.getString("reg_date"));
			badge.setUserId(rs.getString("user_id"));
			return badge;
		}
	};
	//뱃지등록
	public void addBadge(final Badge badge) {
		String query = "" +
				"INSERT INTO T_NF_BADGE " +
				"	(badge_type, badge_status, reg_date, user_id) " +
				"VALUES " +
				"	( ?, ?, SYSDATE(), ?) ";
		this.jdbcTemplate.update(query, new Object[] {
			
			badge.getBadgeType(),
			badge.getBadgeStatus(),
			badge.getUserId()
		});
	}

	public void deleteBadge(String badge_seq) {
		String query = "DELETE FROM T_NF_BADGE WHERE badge_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { badge_seq });
	}

	public void updateBadge(Badge badge) { 
		String query = "" + 
				"UPDATE T_NF_BADGE SET " +
				"	badge_seq = ?, " +
				"	badge_type = ?, " +
				"	badge_status = ?, " +
				"	reg_date = ?, " +
				"	user_id = ? " +
				"WHERE badge_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			badge.getBadgeSeq(),
			badge.getBadgeType(),
			badge.getBadgeStatus(),
			badge.getRegDate(),
			badge.getUserId()
		});
	}
	//뱃지 카운트
	public int getBadgeCount(String userId,int type) {
		String query = "" + 
				"SELECT count(*) " +
				"FROM T_NF_BADGE " +
				"WHERE user_id = ? and badge_status = 1 and badge_type = ? ";
		return this.jdbcTemplate.queryForInt(query, new Object[] { userId,type });
	}
	//읽음 처리
	public void updateBadgeRead(String userId,int type) { 
		String query = "" + 
				"UPDATE T_NF_BADGE SET " +
				
				"	badge_status = 0 " +
				
				"WHERE user_id = ? and  badge_type = ? ";
		this.jdbcTemplate.update(query, new Object[] {userId,type});
	}
	
	public Badge getBadge(String badge_seq) {
		String query = "" + 
				"SELECT badge_seq, badge_type, badge_status, reg_date, user_id " +
				"FROM T_NF_BADGE " +
				"WHERE badge_seq = ? ";
		return (Badge)this.jdbcTemplate.queryForObject(query, new Object[] { badge_seq }, this.badgeMapper);
	}

	public List<Badge> getBadgeList(int page, int itemCountPerPage) {
		String query = "" +
				"SELECT TOP "+ itemCountPerPage +" badge_seq, badge_type, badge_status, reg_date, user_id " +
				"FROM T_NF_BADGE " +
				"WHERE badge_seq <= ( " +
				"	SELECT MIN(badge_seq) " +
				"	FROM ( " +
				"		SELECT TOP "+ ((page-1) * itemCountPerPage + 1) +" badge_seq " +
				"		FROM T_NF_BADGE " +
				"		ORDER BY badge_seq DESC " +
				"	) AS A) " +
				"ORDER BY badge_seq DESC";
		return (List<Badge>)this.jdbcTemplate.query(query, this.badgeMapper);
	}
}

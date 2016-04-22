package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

import kr.nomad.mars.dto.Notice;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class NoticeDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper noticeMapper = new RowMapper() {
		public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
			Notice notice = new Notice();
			notice.setNoticeSeq(rs.getInt("notice_seq"));
			notice.setUserId(rs.getString("user_id"));
			notice.setNotiType(rs.getInt("noti_type"));
			notice.setSendPush(rs.getInt("send_push"));
			notice.setTitle(rs.getString("title"));
			notice.setContentsHtml(rs.getString("contents_html"));
			notice.setContentsText(rs.getString("contents_text"));
			notice.setLinkUrl(rs.getString("link_url"));
			notice.setStartDate(rs.getString("start_date"));
			notice.setEndDate(rs.getString("end_date"));
			notice.setVisible(rs.getInt("visible"));
			notice.setViewCount(rs.getInt("view_count"));
			notice.setRegDate(rs.getString("reg_date"));
			return notice;
		}
	};

	public void addNotice(final Notice notice) {
		String query = "" +
				"INSERT INTO T_NF_DOCTOR_NOTICE " +
				"	(user_id, noti_type, send_push, title, contents_html, contents_text, link_url, start_date, end_date, visible, view_count, reg_date) " +
				"VALUES " +
				"	( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE()) ";
		this.jdbcTemplate.update(query, new Object[] {
			notice.getUserId(),
			notice.getNotiType(),
			notice.getSendPush(),
			notice.getTitle(),
			notice.getContentsHtml(),
			notice.getContentsText(),
			notice.getLinkUrl(),
			notice.getStartDate(),
			notice.getEndDate(),
			notice.getVisible(),
			notice.getViewCount()
		});
	}

	public void deleteNotice(int notice_seq) {
		String query = "DELETE FROM T_NF_DOCTOR_NOTICE WHERE notice_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { notice_seq });
	}

	public void updateNotice(Notice notice) { 
		String query = "" + 
				"UPDATE T_NF_DOCTOR_NOTICE SET " +
				"	user_id = ?, " +
				"	noti_type = ?, " +
				"	send_push = ?, " +
				"	title = ?, " +
				"	contents_html = ?, " +
				"	contents_text = ?, " +
				"	link_url = ?, " +
				"	start_date = ?, " +
				"	end_date = ?, " +
				"	visible = ?, " +
				"	view_count = ?, " +
				"	reg_date = SYSDATE() " +
				"WHERE notice_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			notice.getUserId(),
			notice.getNotiType(),
			notice.getSendPush(),
			notice.getTitle(),
			notice.getContentsHtml(),
			notice.getContentsText(),
			notice.getLinkUrl(),
			notice.getStartDate(),
			notice.getEndDate(),
			notice.getVisible(),
			notice.getViewCount(),
			notice.getNoticeSeq()
		});
	}

	public Notice getNotice(String notice_seq) {
		String query = "" + 
				"SELECT notice_seq, user_id, noti_type, send_push, title, contents_html, contents_text, link_url, start_date, end_date, visible, view_count, reg_date " +
				"FROM T_NF_DOCTOR_NOTICE " +
				"WHERE notice_seq = ? ";
		return (Notice)this.jdbcTemplate.queryForObject(query, new Object[] { notice_seq }, this.noticeMapper);
	}

	public Notice getNoticeUpdateTop(String os) {
		String query = ""
				+ "	SELECT * "
				+ "	FROM T_NF_DOCTOR_NOTICE "
				+ "	WHERE noti_type = 20 AND send_push = ? "
				+ "	ORDER BY reg_date DESC "
				+ "	LIMIT 1 ";
		return (Notice)this.jdbcTemplate.queryForObject(query, new Object[] { os }, this.noticeMapper);
	}
	
	
	
	//메인화면 공지사항 출력

	public List<Notice> getNoticeTopList(int topCount) {
		
		List<Notice> result = null;
		String query = ""
				+ "	SELECT * "
				+ "	FROM T_NF_DOCTOR_NOTICE WHERE noti_type = 0 "
				+ " ORDER BY notice_seq desc "
				+ "	LIMIT "+topCount;
		result = (List<Notice>)this.jdbcTemplate.query(query, this.noticeMapper);
		return result;
	}
	
	public List<Notice> getNoticeMainList(int page, int itemCountPerPage, int notiType) {
		
		List<Notice> result = null;

		String query = ""
				+ "	SELECT * FROM ( "
				+ "		SELECT "
				+ "			* "
				+ "		FROM T_NF_DOCTOR_NOTICE"
				+ "		WHERE noti_type = ?  "
				+ "		ORDER BY notice_seq desc "
				+ ") AS row LIMIT " + (page - 1) * itemCountPerPage + "," + itemCountPerPage;
				
		result = (List<Notice>)this.jdbcTemplate.query(query,new Object[] {notiType}, this.noticeMapper);
		
		return result;
	}
	/*
	 * 모바일 공지사항 (일반, 특별)
	 */
	public List<Notice> getNoticeMainList(int page, int itemCountPerPage) {
		
		List<Notice> result = null;

		String query = ""
				+ "	SELECT * FROM ( "
				+ "		SELECT "
				+ "			* "
				+ "		FROM T_NF_DOCTOR_NOTICE"
				+ "		WHERE noti_type = 0 OR noti_type = 1 "
				+ "		ORDER BY notice_seq desc "
				+ ") AS row LIMIT " + (page - 1) * itemCountPerPage + "," + itemCountPerPage;
				
		result = (List<Notice>)this.jdbcTemplate.query(query,new Object[] {}, this.noticeMapper);
		
		return result;
	}
	
	/*
	 * 모바일 공지사항 카운트 (일반, 특별)
	 */
	public int getNoticeMainCount() {
		int result = 0;		
		String query = "SELECT COUNT(*) AS cnt FROM T_NF_DOCTOR_NOTICE WHERE noti_type = 0 OR noti_type = 1";
		result = this.jdbcTemplate.queryForInt(query,new Object[] {});
				
		return result;
	}
	
	
	
	public int getNoticeMainCount(int notiType) {
		int result = 0;		
		String query = "SELECT COUNT(*) AS cnt FROM T_NF_DOCTOR_NOTICE WHERE noti_type = ?";
		result = this.jdbcTemplate.queryForInt(query,new Object[] {notiType});
				
		return result;
	}
	
	public List<Notice> getNoticeMainList(String keyword, int page, int itemCountPerPage) {
		String query = ""
				+ "    SELECT * FROM ( "
				+ "         SELECT "
				+ "            * "
				+ "         FROM T_NF_DOCTOR_NOTICE "
				+ "         WHERE (noti_type = 0 OR noti_type = 1) AND (title like ? OR contents_text like ? ) "
				+ "			ORDER BY notice_seq DESC "
				+ "    ) AS a LIMIT " + (page - 1) * itemCountPerPage + "," + itemCountPerPage;
		return (List<Notice>) this.jdbcTemplate.query(query,new Object[] {"%" + keyword + "%","%" + keyword + "%"}, this.noticeMapper);
	}

	public int getNoticeMainCount(String keyword) {
		String query = " SELECT COUNT(*) FROM T_NF_DOCTOR_NOTICE WHERE (noti_type = 0 OR noti_type = 1) AND (title like ? OR contents_text like ? ) ";
		return this.jdbcTemplate.queryForInt(query, new Object[] {"%"+ keyword+"%","%" + keyword + "%"});
	}

	/** �˻� ��� **/
	public List<Notice> getNoticeMainList(String keyword, int page, int itemCountPerPage,int notiType) {
		String query = ""
				+ "    SELECT * FROM ( "
				+ "         SELECT "
				+ "            * "
				+ "         FROM T_NF_DOCTOR_NOTICE "
				+ "         WHERE noti_type = ? AND (title like ? OR contents_text like ? ) "
				+ "			ORDER BY notice_seq DESC "
				+ "    ) AS a LIMIT " + (page - 1) * itemCountPerPage + "," + itemCountPerPage;
		return (List<Notice>) this.jdbcTemplate.query(query,new Object[] {notiType, "%" + keyword + "%","%" + keyword + "%"}, this.noticeMapper);
	}

	public int getNoticeMainCount(String keyword,int notiType) {
		String query = " SELECT COUNT(*) FROM T_NF_DOCTOR_NOTICE WHERE noti_type = ? AND (title like ? OR contents_text like ? ) ";
		return this.jdbcTemplate.queryForInt(query, new Object[] {notiType,"%"+ keyword+"%","%" + keyword + "%"});
	}
	
	public Notice getNotice(int seq) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_DOCTOR_NOTICE " +
				"WHERE notice_seq = ? ";
		try {
			return (Notice)this.jdbcTemplate.queryForObject(query, new Object[] { seq }, this.noticeMapper);
		} catch (Exception e) {
			return new Notice();
		}
	}
	
	
	
	public int getLastSeq() {
		String query = " SELECT MAX(notice_seq) FROM T_NF_DOCTOR_NOTICE ";
		return this.jdbcTemplate.queryForInt(query);
	}
	
	

	
	

}

package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.Day;
import kr.nomad.mars.dto.Magazine;
import kr.nomad.mars.dto.MagazinePage;
import kr.nomad.util.T;
public class MagazinePageDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper magazinepageMapper = new RowMapper() {
		public MagazinePage mapRow(ResultSet rs, int rowNum) throws SQLException {
			MagazinePage magazinepage = new MagazinePage();
			magazinepage.setpSeq(rs.getInt("p_seq"));
			magazinepage.setmSeq(rs.getInt("m_seq"));
			magazinepage.setPage(rs.getInt("page"));
			magazinepage.setPageTitle(rs.getString("page_title"));
			magazinepage.setPageContents(rs.getString("page_contents"));
			magazinepage.setPageFilename(rs.getString("page_filename"));
			magazinepage.setPageThumname(rs.getString("page_thumname"));
			magazinepage.setRegDate(rs.getString("reg_date"));
			return magazinepage;
		}
	};
	
	//처음뷰
	
	public List<MagazinePage> getMagazinePageList(int seq) {
		String query = "" +
				"select * from T_NF_MAGAZINE_PAGE where m_seq = ? order by page";
		return (List<MagazinePage>)this.jdbcTemplate.query(query, new Object[] {seq}, this.magazinepageMapper);
	}
	
	//카운트
	
	public int getMagazinePageCnt(int seq) {
		String query = "" +
				"select count(*) from T_NF_MAGAZINE_PAGE where m_seq = ?";
		return this.jdbcTemplate.queryForInt(query, new Object[] {seq});
	}
	
	//뒤에 상세
	public List<MagazinePage> getMagazinePagesubList(int seq) {
		String query = "" +
				"select * from T_NF_MAGAZINE_PAGE where m_seq = ? order by page asc";
		return (List<MagazinePage>)this.jdbcTemplate.query(query, new Object[] {seq}, this.magazinepageMapper);
	}
	//내용보기
	public MagazinePage getMagaginePage(int pSeq) {
		String query = "" +
				"SELECT * FROM T_NF_MAGAZINE_PAGE where p_seq = ?";
		return (MagazinePage)this.jdbcTemplate.queryForObject(query,  new Object[] { pSeq } ,this.magazinepageMapper);
	}
	
	//서브 메뉴처음뷰
	
	public List<MagazinePage> getMagazinePageSubList(int pSeq) {
		String query = "" +
				"select * from T_NF_MAGAZINE_PAGE where m_seq = ?";
		return (List<MagazinePage>)this.jdbcTemplate.query(query, new Object[] {pSeq}, this.magazinepageMapper);
	}
	
	//페이지 업데이트
	public void updatePage(int pSeq,int page) { 
		String query = "" + 
				"UPDATE T_NF_MAGAZINE_PAGE SET " +
				
				"	page = ? " +
			
				"WHERE p_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {page,pSeq});
	}
	

	public void addMagazinePage(MagazinePage magazinepage) {
		String query = "" +
				"INSERT INTO T_NF_MAGAZINE_PAGE " +
				"	(m_seq, page, page_title, page_contents, page_filename, page_thumname , reg_date) " +
				"VALUES " +
				"	( ?, ?, ?, ?, ?, ?, SYSDATE()) ";
		this.jdbcTemplate.update(query, new Object[] {
			magazinepage.getmSeq(),
			magazinepage.getPage(),
			magazinepage.getPageTitle(),
			magazinepage.getPageContents(),
			magazinepage.getPageFilename(),
			magazinepage.getPageThumname()
		});
	}
	public int getLastId() {
	    String query = "SELECT LAST_INSERT_ID(); ";
	    return this.jdbcTemplate.queryForInt(query );
	}
	
	public void deleteMagazinePage(int p_seq) {
		String query = "DELETE FROM T_NF_MAGAZINE_PAGE WHERE p_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { p_seq });
	}
	

	public void updateMagazinePage(MagazinePage magazinepage) { 
		String query = "" + 
				"UPDATE T_NF_MAGAZINE_PAGE SET " +
				"	m_seq = ?, " +
				"	page = ?, " +
				"	page_title = ?, " +
				"	page_contents = ?, " +
				"	page_filename = ?, " +
				"	page_thumname = ? " +
				"WHERE p_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
		
			magazinepage.getmSeq(),
			magazinepage.getPage(),
			magazinepage.getPageTitle(),
			magazinepage.getPageContents(),
			magazinepage.getPageFilename(),
			magazinepage.getPageThumname(),
			magazinepage.getpSeq(),
		});
	}
	
	public void updateFile(String file,int seq) { 
		String query = "" + 
				"UPDATE T_NF_MAGAZINE_PAGE SET " +
				
				"	page_filename = ?, " +
				"	page_thumname = ? " +
				"WHERE p_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
		
			file,file,seq
		});
	}

	public MagazinePage getMagazinePage(String p_seq) {
		String query = "" + 
				"SELECT p_seq, m_seq, page, page_title, page_contents, page_filename, page_thumname " +
				"FROM T_NF_MAGAZINE_PAGE " +
				"WHERE p_seq = ? ";
		return (MagazinePage)this.jdbcTemplate.queryForObject(query, new Object[] { p_seq }, this.magazinepageMapper);
	}

	/*
	public List<MagazinePage> getMagazinePageList(int page, int itemCountPerPage) {
		String query = "" +
				"SELECT TOP "+ itemCountPerPage +" p_seq, m_seq, page, page_title, page_contents, page_filename, page_thumname " +
				"FROM T_NF_MAGAZINE_PAGE " +
				"WHERE p_seq <= ( " +
				"	SELECT MIN(p_seq) " +
				"	FROM ( " +
				"		SELECT TOP "+ ((page-1) * itemCountPerPage + 1) +" p_seq " +
				"		FROM T_NF_MAGAZINE_PAGE " +
				"		ORDER BY p_seq DESC " +
				"	) AS A) " +
				"ORDER BY p_seq DESC";
		return (List<MagazinePage>)this.jdbcTemplate.query(query, this.magazinepageMapper);
	}
	*/
}

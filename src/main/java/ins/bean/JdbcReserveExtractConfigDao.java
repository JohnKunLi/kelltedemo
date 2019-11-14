package ins.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("jdbcReserveExtractConfigDao")
public class JdbcReserveExtractConfigDao implements ReserveExtractConfigDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<ReserveExtractConfigVo> findAll() {
		String sql = "select * from reserve_extract_config";
        RowMapper<ReserveExtractConfigVo> rowMapper = new BeanPropertyRowMapper<ReserveExtractConfigVo>(ReserveExtractConfigVo.class);
        List<ReserveExtractConfigVo> list = jdbcTemplate.query(sql, rowMapper);
        return list;
	}

}

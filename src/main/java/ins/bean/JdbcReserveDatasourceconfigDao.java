package ins.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("jdbcReserveDatasourceconfigDao")
public class JdbcReserveDatasourceconfigDao implements ReserveDatasourceconfigDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<ReserveDatasourceconfigVo> findAll(String dsName) {
		String sql = "select * from reserve_datasourceconfig where dsName = '"+dsName+"'";
        //BeanPropertyRowMapper功能同名加载。查询结果集列名和实体类属性名一致
        RowMapper<ReserveDatasourceconfigVo> rowMapper = new BeanPropertyRowMapper<ReserveDatasourceconfigVo>(ReserveDatasourceconfigVo.class);
        List<ReserveDatasourceconfigVo> list = jdbcTemplate.query(sql, rowMapper);
        return list;
	}

}

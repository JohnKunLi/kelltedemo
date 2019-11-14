package ins.bean;

import java.util.List;

public interface ReserveDatasourceconfigDao {

	public List<ReserveDatasourceconfigVo> findAll(String dsName);
} 

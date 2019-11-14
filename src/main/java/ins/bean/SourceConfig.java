package ins.bean;

import lombok.Data;

/**
 * 数据抽取配置文件
 *
 */

@Data
public class SourceConfig {
	/**数据库连接信息*/
	private ReserveDatasourceconfigVo databaseConfig;
	/**我们的准备金系统数据库连接*/
	private ReserveDatasourceconfigVo myDatabaseConfig;
	/**要操作的表名*/
	private String tableName;
	/**可直接拼写查询sql*/
	private String extractSql;
	/**提数表中的数据字段和我们的相匹配的字段*/	
	private String [] updatelookup;
	/**提数表中的数据字段和我们的相匹配的字段*/
	private String [] updateStream;
	/**查询更新表中的字段*/
	private String [] updatelookupCondition;
	/**查询更新表中的字段*/
	private String [] updateStreamCondition;
	/**关系符号：=、= ~NULL、<>、<、<=、>、>=、LIKE、BETWEEN、IS NULL、IS NOT NULL*/
	private String [] keyCondition;
	/**是否更新：true/false*/
    private Boolean[] updateOrNot;
	/**ktr文件存放地址*/
	private String ktrUrl;
	/**ktr文件名称*/
	private String ktrName;
	
}

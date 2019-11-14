package ins.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 配置任务调度中心(数据库连接参数)
 */
@Configuration
@EnableScheduling
public class QuartzConfig {

	@Value("${spring.datasource.url}")
	private String dataSourceUrl;
	@Value("${spring.datasource.username}")
	private String dataSourceUser;
	@Value("${spring.datasource.password}")
	private String dataSourcePassword;
	public String getDataSourceUrl() {
		return dataSourceUrl;
	}
	public void setDataSourceUrl(String dataSourceUrl) {
		this.dataSourceUrl = dataSourceUrl;
	}
	public String getDataSourceUser() {
		return dataSourceUser;
	}
	public void setDataSourceUser(String dataSourceUser) {
		this.dataSourceUser = dataSourceUser;
	}
	public String getDataSourcePassword() {
		return dataSourcePassword;
	}
	public void setDataSourcePassword(String dataSourcePassword) {
		this.dataSourcePassword = dataSourcePassword;
	}
}

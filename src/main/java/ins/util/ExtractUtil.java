package ins.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ins.bean.ReserveDatasourceconfigVo;
import ins.bean.ReserveExtractConfigVo;
import ins.bean.SourceConfig;
import ins.framework.utils.Beans;

/**
 * 获取数据库连接信息工具类
 */
@Component
public class ExtractUtil {

	@Autowired
	private QuartzConfig quartzConfig;
	
	/**
	 * 获取当前数据库连接信息
	 * @return
	 */
	public ReserveDatasourceconfigVo getOwn(){
		String myDataSourceUrl = quartzConfig.getDataSourceUrl();
		myDataSourceUrl = myDataSourceUrl.replace("?",":");
		myDataSourceUrl = myDataSourceUrl.replace("/",":");
		String[]  urlStrs=myDataSourceUrl.split(":");
		for(int i=0,len=urlStrs.length;i<len;i++){
		    System.out.println(urlStrs[i].toString());
		}
		ReserveDatasourceconfigVo databaseConfig = new ReserveDatasourceconfigVo();
		databaseConfig.setDataBaseType(urlStrs[1]);
		databaseConfig.setAddress(urlStrs[4]);
		databaseConfig.setDsPort(urlStrs[5]);
		databaseConfig.setDataBaseName(urlStrs[6]);
		databaseConfig.setUsername(quartzConfig.getDataSourceUser());
		databaseConfig.setUserPass(quartzConfig.getDataSourcePassword());
		return databaseConfig;
	}
	
	/**
	 * 组装配置数据抽取关系工具
	 * @param reserveExtractConfigVo
	 * @return
	 */
	public static SourceConfig copyConfig(ReserveExtractConfigVo reserveExtractConfigVo){
		SourceConfig sourceConfig = new SourceConfig();
		Beans.copy().from(reserveExtractConfigVo).to(sourceConfig);
		String[] updatelookup = reserveExtractConfigVo.getUpdatelookup().replace(" ", "").split(",");
		String [] updateStream = reserveExtractConfigVo.getUpdateStream().replace(" ", "").split(",");
		String [] keyCondition = reserveExtractConfigVo.getKeyCondition().replace(" ", "").split(",");
		String[] updateOrNotString = reserveExtractConfigVo.getUpdateOrNot().replace(" ", "").split(",");
		Boolean[] updateOrNot = new Boolean[updateOrNotString.length];
		//统计条件的数量
		int count = 0;
		for (String condition : keyCondition) {
			if(!"NOHAVE".equals(condition))
				count++;
		}
		String [] keyConditionOk = new String[count];
		String [] updatelookupCondition = new String[count];
		String [] updateStreamCondition = new String[count];
		count = 0;
		for (int i = 0; i< updateOrNotString.length; i++) {
			updateOrNot[i] = Boolean.parseBoolean(updateOrNotString[i]);
			if(!"NOHAVE".equals(keyCondition[i])){
				keyConditionOk[count] = keyCondition[i];
				updatelookupCondition[count] = updatelookup[i];
				updateStreamCondition[count] = updateStream[i];
				count++;
			}
		}
		sourceConfig.setUpdatelookup(updatelookup);
		sourceConfig.setUpdateStream(updateStream);
		sourceConfig.setUpdateOrNot(updateOrNot);
		sourceConfig.setKeyCondition(keyConditionOk);
		sourceConfig.setUpdatelookupCondition(updatelookupCondition);
		sourceConfig.setUpdateStreamCondition(updateStreamCondition);
		return sourceConfig;
	}
	
}

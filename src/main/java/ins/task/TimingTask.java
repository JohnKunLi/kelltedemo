package ins.task;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.trans.TransMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sun.istack.logging.Logger;

import ins.bean.JdbcReserveDatasourceconfigDao;
import ins.bean.JdbcReserveExtractConfigDao;
import ins.bean.ReserveDatasourceconfigVo;
import ins.bean.ReserveExtractConfigVo;
import ins.bean.SourceConfig;
import ins.util.EncapsulationKtr;
import ins.util.ExtractUtil;
import ins.util.KettleExecu;

/**
 * 定时任务
 *
 */
@Component
public class TimingTask {
	
	private static Logger logger = Logger.getLogger(TimingTask.class);
	
	@Autowired
	private ExtractUtil extractUtil;
	
	@Resource(name = "jdbcReserveExtractConfigDao")
	private JdbcReserveExtractConfigDao jdbcReserveExtractConfigDao;
	
	@Resource(name = "jdbcReserveDatasourceconfigDao")
	private JdbcReserveDatasourceconfigDao  jdbcReserveDatasourceconfigDao;
	
	/**
     * 定时任务，每天晚上21:00点抽取数据
     */
//	@Scheduled(cron= "0 0 21 * * ?")
//	@RequestMapping(value = "/testTimetaisk",method=RequestMethod.GET)
    public void calculatePremiuming(){
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String date = sf.format(new Date());
    	logger.info("数据组装定时任务配置开始！时间："+date);
    	//组装数据库源等配置文件
    	List<SourceConfig> sourceConfigs = init();
    	//进行抽取
    	try {
    		logger.info("数据抽取定时任务开始！时间："+date);
    		startPumingData(sourceConfigs);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("抽取数据定时任务异常！时间："+new Date());
			e.getMessage();
		}
    	logger.info("抽取数据定时任务完成！时间："+new Date());
    }
	
	/**
	 * 1、查询数据库连接信息
	 * 2、我们的准备金系统数据库连接
	 * 3、组装查询sql
	 * 4、组装插入导入记录表数据字段配置数据记录（插入的记录表、字段、重规则）
	 * 5、组装插入业务表数据字段配置（插入的业务表、字段、重复规则）
	 * 6、返回组装好的配置
	 */
	public List<SourceConfig> init(){
		List<SourceConfig> sourceConfigs = new ArrayList<>();
		//查询出所有数据抽取配置
		List<ReserveExtractConfigVo>reserveExtractConfigVos = jdbcReserveExtractConfigDao.findAll();
		//查询出数据库连接信息
		ReserveDatasourceconfigVo reserveDatasourceconfigVo = jdbcReserveDatasourceconfigDao.findAll("admin").get(0);
		//我们的数据库连接信息
		ReserveDatasourceconfigVo myDatabaseConfig = extractUtil.getOwn();
		for (ReserveExtractConfigVo reserveExtractConfigVo : reserveExtractConfigVos) {
			//组装所有抽取配置
			SourceConfig sourceConfig = ExtractUtil.copyConfig(reserveExtractConfigVo);
			//设置数据库连接配置
			sourceConfig.setDatabaseConfig(reserveDatasourceconfigVo);
			//组装我们的数据库连接
			sourceConfig.setMyDatabaseConfig(myDatabaseConfig);
			sourceConfigs.add(sourceConfig);
		}
		return sourceConfigs;
	}
	
	//预留抽取前后可操作方法
	public void startPumingData(List<SourceConfig> sourceConfigs){
		generateFileAndImplementList(sourceConfigs);
	}
	
	/**
	 * 利用配置异步执行抽取
	 * @param sourceConfigs
	 */
	public void generateFileAndImplementList(List<SourceConfig> sourceConfigs){
		//循环调用
		for (SourceConfig sourceConfig : sourceConfigs) {
			logger.info("-------------------------------------------------------------------------------------------");
			logger.info("--------------------------------数据抽取开始!(start)---------------------------------");
			logger.info("--------------------------------目标数据库地址："+sourceConfig.getDatabaseConfig().getAddress()+"--------------------------------");
			logger.info("--------------------------------目标数据库类型："+sourceConfig.getDatabaseConfig().getDataBaseType()+"--------------------------------");
			logger.info("--------------------------------目标数据库名称："+sourceConfig.getDatabaseConfig().getDataBaseName()+"--------------------------------");
			logger.info("--------------------------------目标数据库端口："+sourceConfig.getDatabaseConfig().getDsPort()+"--------------------------------");
			logger.info("--------------------------------目标数据库用户："+sourceConfig.getDatabaseConfig().getUsername()+"--------------------------------");
			logger.info("--------------------------------目标数据库密码："+sourceConfig.getDatabaseConfig().getUserPass()+"--------------------------------");
			generateFileAndImplement(sourceConfig);
			logger.info("---------------------数据抽取结束!(end)---------------------");
			logger.info("-------------------------------------------------------------------------------------------");
		}
	};
	
	/**
	 * 单次生成抽取
	 * @param extractConfig
	 */
	@Async
	public void generateFileAndImplement(SourceConfig sourceConfig){
		logger.info("-------------------------------------------------------------------------------------------");
		logger.info("--------------------------------生成抽取ktr文件开始!(start)--------------------------------");
		logger.info("--------------------------------文件路径："+sourceConfig.getKtrUrl()+"--------------------------------");
		logger.info("--------------------------------文件名称："+sourceConfig.getKtrName()+"--------------------------------");
		logger.info("--------------------------------数据查询SQL："+sourceConfig.getExtractSql()+"--------------------------------");
		logger.info("--------------------------------目标表名："+sourceConfig.getTableName()+"--------------------------------");
		//生成
		String fileName =  generateFile(sourceConfig);
		logger.info("--------------------------------生成抽取ktr文件结束!(end)--------------------------------");
		logger.info("-------------------------------------------------------------------------------------------");
		
		logger.info("-------------------------------------------------------------------------------------------");
		logger.info("--------------------------------执行抽取ktr文件开始!(start)--------------------------------");
		logger.info("--------------------------------执行抽取ktr文件路径："+fileName+"--------------------------------");
		//抽取
		Implement(fileName);
		logger.info("--------------------------------执行抽取ktr文件结束!(end)--------------------------------");
		logger.info("-------------------------------------------------------------------------------------------");
	}
	
	/**
	 * 生成抽取文件
	 */
	public String generateFile(SourceConfig extractConfig){
		String fileName = extractConfig.getKtrUrl()+extractConfig.getKtrName();
	    try {
			KettleEnvironment.init();
			EncapsulationKtr transDemo = new EncapsulationKtr();
			//组装sql和数据库连接
			TransMeta transMeta = transDemo.generateMyOwnTrans(extractConfig);
			String transXml = transMeta.getXML();
			//System.out.println("transXml:"+transXml);
			File file = new File(fileName);
			FileUtils.writeStringToFile(file, transXml, "UTF-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("---------------------------------Kettle生成文件异常----------------------------------------");
			e.printStackTrace();
		} 
		return fileName;
	}
	
	/**
	 * 抽取
	 * @param fileName
	 */
	public void Implement(String fileName){
		KettleExecu.runTrans(fileName);
	}
}



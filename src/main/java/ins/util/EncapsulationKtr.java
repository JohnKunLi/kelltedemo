package ins.util;

import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.core.plugins.StepPluginType;
import org.pentaho.di.trans.TransHopMeta;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.steps.insertupdate.InsertUpdateMeta;
import org.pentaho.di.trans.steps.tableinput.TableInputMeta;

import ins.bean.SourceConfig;


/**
 * 生成ktr文件封装类
 */
public class EncapsulationKtr {

	/**
	 * 生成一个转化,把一个数据库中的数据转移到另一个数据库中,只有两个步骤,第一个是表输入,第二个是表插入与更新操作
	 * 
	 * @return
	 * @throws KettleXMLException
	 */
	public TransMeta generateMyOwnTrans(SourceConfig sourceConfig) throws KettleXMLException {
		/**
		 * 数据库连接信息,适用于DatabaseMeta其中 一个构造器DatabaseMeta(String xml)
		 */
		final String[] databasesXML = {
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<connection>" + "<name>bjdt</name>" + "<server>"
						+ sourceConfig.getDatabaseConfig().getAddress() + "</server>" + "<type>"
						+ sourceConfig.getDatabaseConfig().getDataBaseType() + "</type>" + "<access>Native</access>"
						+ "<database>" + sourceConfig.getDatabaseConfig().getDataBaseName() + "</database>" + "<port>"
						+ sourceConfig.getDatabaseConfig().getDsPort() + "</port>" + "<username>"
						+ sourceConfig.getDatabaseConfig().getUsername() + "</username>" + "<password>"
						+ sourceConfig.getDatabaseConfig().getUserPass() + "</password>" + "</connection>",
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<connection>" + "<name>kettle</name>" + "<server>"
						+ sourceConfig.getMyDatabaseConfig().getAddress() + "</server>" + "<type>"
						+ sourceConfig.getMyDatabaseConfig().getDataBaseType() + "</type>" + "<access>Native</access>"
						+ "<database>" + sourceConfig.getMyDatabaseConfig().getDataBaseName() + "</database>" + "<port>"
						+ sourceConfig.getMyDatabaseConfig().getDsPort() + "</port>" + "<username>"
						+ sourceConfig.getMyDatabaseConfig().getUsername() + "</username>" + "<password>"
						+ sourceConfig.getMyDatabaseConfig().getUserPass() + "</password>" + "</connection>" };

		System.out.println("************start to generate my own transformation***********");

		TransMeta transMeta = new TransMeta();
		// 设置转化的名称
		transMeta.setName("insert_update");
		// 添加转换的数据库连接
		for (int i = 0; i < databasesXML.length; i++) {
			DatabaseMeta databaseMeta = new DatabaseMeta(databasesXML[i]);
			transMeta.addDatabase(databaseMeta);
		}

		// registry是给每个步骤生成一个标识Id用
		PluginRegistry registry = PluginRegistry.getInstance();

		// ******************************************************************

		// 第一个表输入步骤(TableInputMeta)
		TableInputMeta tableInput = new TableInputMeta();
		//String tableInputPluginId = 
		registry.getPluginId(StepPluginType.class, tableInput);
		// 给表输入添加一个DatabaseMeta连接数据库
		DatabaseMeta database_bjdt = transMeta.findDatabase("bjdt");
		tableInput.setDatabaseMeta(database_bjdt);
		tableInput.setSQL(sourceConfig.getExtractSql());
		// 添加TableInputMeta到转换中
		StepMeta tableInputMetaStep = new StepMeta("table input", tableInput);

		// 给步骤添加在spoon工具中的显示位置
		tableInputMetaStep.setDraw(true);
		tableInputMetaStep.setLocation(100, 100);

		transMeta.addStep(tableInputMetaStep);
		// ******************************************************************

		// ******************************************************************
		// 第二个步骤插入与更新
		InsertUpdateMeta insertUpdateMeta = new InsertUpdateMeta();
		//String insertUpdateMetaPluginId = 
		registry.getPluginId(StepPluginType.class, insertUpdateMeta);
		// 添加数据库连接
		DatabaseMeta database_kettle = transMeta.findDatabase("kettle");
		insertUpdateMeta.setDatabaseMeta(database_kettle);
		// 设置操作更新的表
		insertUpdateMeta.setTableName(sourceConfig.getTableName());
		insertUpdateMeta.setUpdateLookup(sourceConfig.getUpdatelookup());
		insertUpdateMeta.setUpdateStream(sourceConfig.getUpdateStream());
		insertUpdateMeta.setUpdate(sourceConfig.getUpdateOrNot());
		// 设置用来查询的关键字
		insertUpdateMeta.setKeyLookup(sourceConfig.getUpdatelookupCondition());
		insertUpdateMeta.setKeyStream(sourceConfig.getUpdateStreamCondition());
		insertUpdateMeta.setKeyStream2(new String[sourceConfig.getKeyCondition().length]);// 一定要加上
		insertUpdateMeta.setKeyCondition(sourceConfig.getKeyCondition());
		// String[] lookup = insertUpdateMeta.getUpdateLookup();
		// System.out.println("******:"+lookup[1]);
		// System.out.println("insertUpdateMetaXMl:"+insertUpdateMeta.getXML());
		// 添加步骤到转换中
		StepMeta insertUpdateStep = new StepMeta("insert_update", insertUpdateMeta);
		insertUpdateStep.setDraw(true);
		insertUpdateStep.setLocation(250, 100);
		transMeta.addStep(insertUpdateStep);
		// ******************************************************************

		// ******************************************************************
		// 添加hop把两个步骤关联起来
		transMeta.addTransHop(new TransHopMeta(tableInputMetaStep, insertUpdateStep));
		System.out.println("***********the end************");
		return transMeta;
	}

}

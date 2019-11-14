package ins.util;


import org.pentaho.di.core.KettleEnvironment;  
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.job.Job;  
import org.pentaho.di.job.JobMeta;  
import org.pentaho.di.trans.Trans;  
import org.pentaho.di.trans.TransMeta;

/**
 * 调用Kettle执行文件工具类
 */
public class KettleExecu {  
  
	/**
	 * 执行kjb文件
	 * @param jobNameUrl
	 */
    public static void runJob(String jobNameUrl) {
        try {  
            KettleEnvironment.init();  
            // jobNameUrl 是Job脚本的路径及名称  
            JobMeta jobMeta = new JobMeta(jobNameUrl, null);
            Job job = new Job(null, jobMeta);
            // 向Job 脚本传递参数，脚本中获取参数值：${参数名}  
            // job.setVariable(paraname, paravalue);  
            job.start();  
            job.waitUntilFinished();  
            if (job.getErrors() > 0) {  
                System.out.println("decompress fail!");  
            }  
        } catch (KettleException e) {  
            System.out.println(e);  
        }  
    }  
  
    /**
     * 执行ktr文件
     * @param filename
     */
    public static void runTrans(String fileName) {  
        try {  
            KettleEnvironment.init();  
            TransMeta transMeta = new TransMeta(fileName);  
            Trans trans = new Trans(transMeta);  
            trans.prepareExecution(null);  
            trans.startThreads();  
            trans.waitUntilFinished();  
            if (trans.getErrors() != 0) {  
                System.out.println("Error");  
            }  
        } catch (KettleXMLException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (KettleException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    } 
  
  
}  
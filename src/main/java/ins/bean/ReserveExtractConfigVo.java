package ins.bean;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * ReserveExtractConfigVo对象.对应实体描述：抽取配置表
 *
 */
@Data
@ApiModel("ReserveExtractConfigVo对象")
public class ReserveExtractConfigVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 对应字段：id */
	@ApiModelProperty()
	private Integer id;
	/** 对应字段：exName,备注：名称 */
	@ApiModelProperty("名称")
	private String exName;
	/** 对应字段：extractType,备注：类型(预留) */
	@ApiModelProperty("类型(预留)")
	private String extractType;
	/** 对应字段：tableName,备注：需要操作的表名 */
	@ApiModelProperty("需要操作的表名")
	private String tableName;
	/** 对应字段：extractSql,备注：查询sql */
	@ApiModelProperty("查询sql")
	private String extractSql;
	/** 对应字段：updatelookup,备注：查询sql结果字段（格式:abc,abc,abc...） */
	@ApiModelProperty("查询sql结果字段（格式:abc,abc,abc...）")
	private String updatelookup;
	/** 对应字段：updateStream,备注：插入表字段（格式:abc,abc,abc...） */
	@ApiModelProperty("插入表字段（格式:abc,abc,abc...）")
	private String updateStream;
	/** 对应字段：keyCondition,备注：关系符号：=、= ~NULL、&lt;&gt;、&lt;、&lt;=、&gt;、&gt;=、LIKE、BETWEEN、IS NULL、IS NOT NULL */
	@ApiModelProperty("关系符号：=、= ~NULL、&lt;&gt;、&lt;、&lt;=、&gt;、&gt;=、LIKE、BETWEEN、IS NULL、IS NOT NULL")
	private String keyCondition;
	/** 对应字段：updateOrNot,备注：是否更新：true/false */
	@ApiModelProperty("是否更新：true/false")
	private String updateOrNot;
	/** 对应字段：ktrUrl,备注：存放地址 */
	@ApiModelProperty("存放地址")
	private String ktrUrl;
	/** 对应字段：ktrName,备注：文件名称 */
	@ApiModelProperty("文件名称")
	private String ktrName;
	/** 对应字段：validStatus,备注：状态：1有效，0无效 */
	@ApiModelProperty("状态：1有效，0无效")
	private String validStatus;
	/** 对应字段：createTime,备注：创建时间 */
	@ApiModelProperty("创建时间")
	private Date createTime;
	/** 对应字段：createUserCode,备注：创建用户 */
	@ApiModelProperty("创建用户")
	private String createUserCode;
	/** 对应字段：updateTime,备注：修改时间 */
	@ApiModelProperty("修改时间")
	private Date updateTime;
	/** 对应字段：updateUserCode,备注：更改时间 */
	@ApiModelProperty("更改时间")
	private String updateUserCode;
}

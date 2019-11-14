package ins.bean;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * ReserveDatasourceconfigVo对象.
 *
 */
@Data
@ApiModel("ReserveDatasourceconfigVo对象")
public class ReserveDatasourceconfigVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 对应字段：id,备注：数据源配置主键ID */
	@ApiModelProperty("数据源配置主键ID")
	private Long id;
	/** 对应字段：dsName,备注：数据源名称 */
	@ApiModelProperty("数据源名称")
	private String dsName;
	/** 对应字段：dataBaseType,备注：数据源类型 */
	@ApiModelProperty("数据源类型")
	private String dataBaseType;
	/** 对应字段：address,备注：数据源地址 */
	@ApiModelProperty("数据源地址")
	private String address;
	/** 对应字段：userName,备注：数据源登录用户名 */
	@ApiModelProperty("数据源登录用户名")
	private String username;
	/** 对应字段：userPass,备注：数据源登录密码 */
	@ApiModelProperty("数据源登录密码")
	private String userPass;
	/** 对应字段：dsPort,备注：数据库连接端口 */
	@ApiModelProperty("数据库连接端口")
	private String dsPort;
	/** 对应字段：dataBaseName,备注：数据库库名 */
	@ApiModelProperty("数据库库名")
	private String dataBaseName;
	/** 对应字段：validStatus,备注：有效状态，1：有效，0：无效 */
	@ApiModelProperty("有效状态，1：有效，0：无效")
	private String validStatus;
	/** 对应字段：updateTime,备注：更新时间 */
	@ApiModelProperty("更新时间")
	private Date updateTime;
	/** 对应字段：updateUserCode,备注：更新用户code */
	@ApiModelProperty("更新用户code")
	private String updateUserCode;
	/** 对应字段：CreateTime,备注：创建时间 */
	@ApiModelProperty("创建时间")
	private Date createTime;
	/** 对应字段：createUserCode,备注：创建人 */
	@ApiModelProperty("创建人")
	private String createUserCode;
	@Override
	public String toString() {
		return "ReserveDatasourceconfigVo [id=" + id + ", dsName=" + dsName + ", dataBaseType=" + dataBaseType
				+ ", address=" + address + ", username=" + username + ", userPass=" + userPass + ", dsPort=" + dsPort
				+ ", dataBaseName=" + dataBaseName + ", validStatus=" + validStatus + ", updateTime=" + updateTime
				+ ", updateUserCode=" + updateUserCode + ", createTime=" + createTime + ", createUserCode="
				+ createUserCode + "]";
	}
	
	
}

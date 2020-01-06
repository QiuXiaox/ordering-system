package my.domain;

import java.io.Serializable;
import java.util.Date;
import my.util.BasePo;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table leave_message
 */
public class LeaveMessage extends BasePo implements Serializable {
    private Long id;

    /**
     *   客户id
     */
    private Long clientId;
    
    /**
     * 菜品id
     */
    private Long dishId;

    public Long getDishId() {
		return dishId;
	}

	public void setDishId(Long dishId) {
		this.dishId = dishId;
	}

	/**
     *   客户留言
     */
    private String message;

    /**
     *   留言时间
     */
    private Date leaveDate;

    /**
     *   留言类型(1、评价；2、投诉)
     */
    private String type;

    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column leave_message.id
     *
     * @return the value of leave_message.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column leave_message.id
     *
     * @param id the value for leave_message.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column leave_message.client_id
     *
     * @return the value of leave_message.client_id
     *
     * @mbg.generated
     */
    public Long getClientId() {
        return clientId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column leave_message.client_id
     *
     * @param clientId the value for leave_message.client_id
     *
     * @mbg.generated
     */
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column leave_message.message
     *
     * @return the value of leave_message.message
     *
     * @mbg.generated
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column leave_message.message
     *
     * @param message the value for leave_message.message
     *
     * @mbg.generated
     */
    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column leave_message.leave_date
     *
     * @return the value of leave_message.leave_date
     *
     * @mbg.generated
     */
    public Date getLeaveDate() {
        return leaveDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column leave_message.leave_date
     *
     * @param leaveDate the value for leave_message.leave_date
     *
     * @mbg.generated
     */
    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column leave_message.type
     *
     * @return the value of leave_message.type
     *
     * @mbg.generated
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column leave_message.type
     *
     * @param type the value for leave_message.type
     *
     * @mbg.generated
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table leave_message
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", clientId=").append(clientId);
        sb.append(", message=").append(message);
        sb.append(", leaveDate=").append(leaveDate);
        sb.append(", type=").append(type);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
package my.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import my.util.BasePo;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table notice
 */
public class NoticeVo extends BasePo implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * Database Column Remarks:
     *   员工id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.emp_id
     *
     * @mbg.generated
     */
    private Long empId;

    /**
     * Database Column Remarks:
     *   公告标题
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.title
     *
     * @mbg.generated
     */
    private String title;

    /**
     * Database Column Remarks:
     *   公告内容
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.context
     *
     * @mbg.generated
     */
    private String context;

    /**
     * Database Column Remarks:
     *   发布时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.create_time
     *
     * @mbg.generated
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table notice
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.id
     *
     * @return the value of notice.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.id
     *
     * @param id the value for notice.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.emp_id
     *
     * @return the value of notice.emp_id
     *
     * @mbg.generated
     */
    public Long getEmpId() {
        return empId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.emp_id
     *
     * @param empId the value for notice.emp_id
     *
     * @mbg.generated
     */
    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.title
     *
     * @return the value of notice.title
     *
     * @mbg.generated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.title
     *
     * @param title the value for notice.title
     *
     * @mbg.generated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.context
     *
     * @return the value of notice.context
     *
     * @mbg.generated
     */
    public String getContext() {
        return context;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.context
     *
     * @param context the value for notice.context
     *
     * @mbg.generated
     */
    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.create_time
     *
     * @return the value of notice.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.create_time
     *
     * @param createTime the value for notice.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notice
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
        sb.append(", empId=").append(empId);
        sb.append(", title=").append(title);
        sb.append(", context=").append(context);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
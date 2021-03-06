package my.domain;

import java.io.Serializable;
import my.util.BasePo;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table client
 */
public class Client extends BasePo implements Serializable {
    /**
     * Database Column Remarks:
     *   id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column client.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * Database Column Remarks:
     *   客户登入账号
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column client.account
     *
     * @mbg.generated
     */
    private String account;

    /**
     * Database Column Remarks:
     *   客户登入密码
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column client.pwd
     *
     * @mbg.generated
     */
    private String pwd;

    /**
     * Database Column Remarks:
     *   客户姓名
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column client.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     * Database Column Remarks:
     *   客户联系电话
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column client.tel
     *
     * @mbg.generated
     */
    private String tel;

    /**
     * Database Column Remarks:
     *   客户联系地址
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column client.address
     *
     * @mbg.generated
     */
    private String address;

    /**
     * Database Column Remarks:
     *   账户状态
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column client.status
     *
     * @mbg.generated
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table client
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column client.id
     *
     * @return the value of client.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column client.id
     *
     * @param id the value for client.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column client.account
     *
     * @return the value of client.account
     *
     * @mbg.generated
     */
    public String getAccount() {
        return account;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column client.account
     *
     * @param account the value for client.account
     *
     * @mbg.generated
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column client.pwd
     *
     * @return the value of client.pwd
     *
     * @mbg.generated
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column client.pwd
     *
     * @param pwd the value for client.pwd
     *
     * @mbg.generated
     */
    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column client.name
     *
     * @return the value of client.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column client.name
     *
     * @param name the value for client.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column client.tel
     *
     * @return the value of client.tel
     *
     * @mbg.generated
     */
    public String getTel() {
        return tel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column client.tel
     *
     * @param tel the value for client.tel
     *
     * @mbg.generated
     */
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column client.address
     *
     * @return the value of client.address
     *
     * @mbg.generated
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column client.address
     *
     * @param address the value for client.address
     *
     * @mbg.generated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column client.status
     *
     * @return the value of client.status
     *
     * @mbg.generated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column client.status
     *
     * @param status the value for client.status
     *
     * @mbg.generated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client
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
        sb.append(", account=").append(account);
        sb.append(", pwd=").append(pwd);
        sb.append(", name=").append(name);
        sb.append(", tel=").append(tel);
        sb.append(", address=").append(address);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
package rt.lewis.parking.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class ResultBean<T> implements Serializable {
    private static final long serialVersionUID = 7614627896964091960L;
    //@ApiModelProperty("错误码，标准restful错误才返回，成功不返回")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String stateCode;
    //@ApiModelProperty("错误描述，标准restful错误才返回，成功不返回")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;
    //@ApiModelProperty("数据内容")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private ResultBean() {
    }

    private ResultBean(T data) {
       /* this.stateCode = StateCommonCode.COMMON_SUCCESS.getKey();
        this.description = StateCommonCode.COMMON_SUCCESS.getValue();*/
        this.data = data;
    }

    public static ResultBean<Void> toNullDataBean() {
        ResultBean<Void> bean = new ResultBean();
        return bean;
    }

    public static <E> ResultBean<E> toBean(E data) {
        ResultBean<E> bean = new ResultBean(data);
        return bean;
    }

    public static <E> ResultBean<E> toBeanWithoutStatus(E data) {
        ResultBean<E> bean = new ResultBean();
        bean.data = data;
        return bean;
    }

   /* public static <E> ResultBeanWrap<ResultBean<E>> toBeanWrap(E data) {
       // return new ResultBeanWrap(toBeanWithoutStatus(data), HttpStatus.OK);
    }*/

    public static ResultBean<Void> catchNoProcessError(Throwable e) {
        ResultBean<Void> bean = new ResultBean();
        //bean.stateCode = StateCommonCode.COMMON_SYSTEM_ERROR.getKey();
        bean.description = e.toString();
        return bean;
    }

    public static ResultBean<Void> catchError(String stateCode, String description) {
        ResultBean<Void> bean = new ResultBean();
        bean.stateCode = stateCode;
        bean.description = description;
        return bean;
    }

    public static ResultBean<Void> catchError(String stateCode) {
        ResultBean<Void> bean = new ResultBean();
        bean.stateCode = stateCode;
       // bean.description = StateCommonCode.getEnumByKey(stateCode).getValue();
        return bean;
    }

    public static ResultBean<Void> toSuccessBean() {
        ResultBean<Void> bean = new ResultBean();
       // bean.stateCode = StateCommonCode.COMMON_SUCCESS.getKey();
       // bean.description = StateCommonCode.COMMON_SUCCESS.getValue();
        return bean;
    }

    public static ResultBean<Void> toSuccessBean(String stateCode, String description) {
        return catchError(stateCode, description);
    }

    public ResultBean(String status, String msg, T data) {
        this.stateCode = status;
        this.description = msg;
        this.data = data;
    }

    public String getStateCode() {
        return this.stateCode;
    }

    public String getDescription() {
        return this.description;
    }

    public T getData() {
        return this.data;
    }

    public void setStateCode(final String stateCode) {
        this.stateCode = stateCode;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ResultBean)) {
            return false;
        } else {
            ResultBean<?> other = (ResultBean) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47:
                {
                    Object this$stateCode = this.getStateCode();
                    Object other$stateCode = other.getStateCode();
                    if (this$stateCode == null) {
                        if (other$stateCode == null) {
                            break label47;
                        }
                    } else if (this$stateCode.equals(other$stateCode)) {
                        break label47;
                    }

                    return false;
                }

                Object this$description = this.getDescription();
                Object other$description = other.getDescription();
                if (this$description == null) {
                    if (other$description != null) {
                        return false;
                    }
                } else if (!this$description.equals(other$description)) {
                    return false;
                }

                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ResultBean;
    }

  /*  public int hashCode() {
        int PRIME = true;
        int result = 1;
        Object $stateCode = this.getStateCode();
        int result = result * 59 + ($stateCode == null ? 43 : $stateCode.hashCode());
        Object $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        return result;
    }*/

    public String toString() {
        return "ResultBean(stateCode=" + this.getStateCode() + ", description=" + this.getDescription() + ", data=" + this.getData() + ")";
    }
}
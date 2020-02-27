package com.example.demo.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 统一的执行完毕后的返回对象
 *
 * @author pandong
 * @date 2019年11月19日 下午14:48:09
 */
@SuppressWarnings("unchecked")
public class ApiResponse<T> implements Serializable {
    private static final long serialVersionUID = -1;
    private long flag;
    private String message;
    private List<T> list;
    private long total;

    public ApiResponse() {

    }

    public ApiResponse(long flag, String message) {
        this.flag = flag;
        this.message = message;
    }

    public ApiResponse(long flag, String message, List list, long total) {
        this.flag = flag;
        this.message = message;
        this.list = list;
        this.total = total;
    }

    public long getFlag() {
        return flag;
    }

    public void setFlag(long flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getLength() {
        if (this.list == null) {
            return 0;
        }
        return this.list.size();
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    /**
     * 判断是否成功
     *
     * @return
     */
    public Boolean isSuccess() {
        return ApiStatus.SC_OK == this.flag;
    }

    /**
     * 构建成功提示返回对象
     *
     * @param message
     * @return
     */
    public static ApiResponse buildSuccessMessage(String message) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setFlag(ApiStatus.SC_OK);
        apiResponse.setMessage(message);
        return apiResponse;
    }

    /**
     * 构建错误提示返回对象
     *
     * @param message
     * @return
     */
    public static <T> ApiResponse<T> buildErrorMessage(String message) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setFlag(ApiStatus.SC_COMMON_SERVICE_ERROR);
        apiResponse.setMessage(message);
        return apiResponse;
    }

    /**
     * 熔断错误提示返回对象
     *
     * @param message
     * @return
     */
    public static <T> ApiResponse<T> buildFeignErrorMessage(String message) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setFlag(ApiStatus.SC_FEIGN_SERVICE_ERROR);
        apiResponse.setMessage(message);
        return apiResponse;
    }

    /**
     * 构建成功返回数据对象(分页)
     *
     * @param obj
     * @return
     */
    public static <T> ApiResponse<T> buildSuccessResponse(long total, Object obj) {
        ApiResponse apiResponse = buildSuccessResponse(obj);
        apiResponse.setTotal(total);
        return apiResponse;
    }

    /**
     * 构建成功返回数据对象
     *
     * @param obj
     * @return
     */
    public static <T> ApiResponse<T> buildSuccessResponse(Object obj) {
        List respList;

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setFlag(ApiStatus.SC_OK);
        apiResponse.setMessage("success");
        if (obj == null) {
            return apiResponse;
        }
        if (List.class.isAssignableFrom(obj.getClass())) {
            respList = (List) obj;
        } else {
            respList = new ArrayList();
            respList.add(obj);
        }
        apiResponse.setList(respList);
        return apiResponse;
    }


    /**
     * 将当前对象转换为json
     *
     * @return
     */
    public String asJSON() {
        return "";
    }

    @Override
    public String toString() {
        String resultMsg = "FLAG:[" + flag + "],MESSAGE:[" + message + "]";
        if (list != null) {
            resultMsg += ",DATA:[LIST TOTAL=" + total + " LEN=" + getLength() + "]";
        }
        return resultMsg;
    }
}

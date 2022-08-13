package com.lonjoy.common;

/**
 * @author liko.wang
 * @Date 2019/12/18/018 14:19
 * @Description 全局异常定义
 **/
public class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    public ApplicationException() {
        this.code = ApplicationResponseCode.SYS_FAILED;
    }

    public ApplicationException(ApplicationResponseCode code) {
        this.code = code;
    }

    public ApplicationException(ApplicationResponseCode code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public ApplicationException(ApplicationResponseCode code, String... formatArgs) {
        super();
        this.code = code;
        this.formatArgs = formatArgs;
    }

    public ApplicationException(ApplicationResponseCode code, Throwable cause, String... formatArgs) {
        super(cause);
        this.code = code;
        this.formatArgs = formatArgs;
    }
	/**
	 * @author liko.wang
	 * @Date 2019/12/18/018 14:15
	 * @param
	 * @return java.lang.String
	 * @Description //TODO
	 **/
    public String getMessage() {
        int errorCode = getErrorCode();
        if (formatArgs != null)
            return (new StringBuilder()).append("[").append(errorCode).append("] ").append(String.format(code.getMessage(), (Object[]) formatArgs)).toString();
        else
            return (new StringBuilder()).append("[").append(errorCode).append("] ").append(code.getMessage()).toString();
    }

    public String getContent() {
        if (formatArgs != null)
            return (new StringBuilder()).append(String.format(code.getMessage(), (Object[]) formatArgs)).toString();
        else
            return (new StringBuilder()).append(code.getMessage()).toString();
    }

    public int getErrorCode() {
        return code.getCode();
    }


    public String getErrorStatus() {
        return code.getStatus();
    }

    private final ApplicationResponseCode code;
    private String formatArgs[];

}

package common;

public class ThisSystemException extends  RuntimeException {
    public ThisSystemException(String message) {
        super(message); //调用父类RuntimeException构造方法创建thisSystemException对象
    }

    public ThisSystemException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
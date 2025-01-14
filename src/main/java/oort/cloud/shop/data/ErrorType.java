package oort.cloud.shop.data;

public enum ErrorType {
    UNKNOWN("알 수 없는 에러입니다");

    private final String desc;
    ErrorType(String desc) {
        this.desc = desc;
    }
}

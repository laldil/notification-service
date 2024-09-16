package kz.edu.astanait.notification_service.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PageDto<T> {
    private final List<T> list;
    private final long totalElements;

    public PageDto(List<T> list, long totalElements) {
        this.list = list;
        this.totalElements = totalElements;
    }

    public PageDto(List<T> list) {
        this.list = list;
        this.totalElements = list.size();
    }
}

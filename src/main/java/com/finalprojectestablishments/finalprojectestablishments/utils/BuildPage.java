package com.finalprojectestablishments.finalprojectestablishments.utils;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class BuildPage {
    public String buildPageLinkHeader(String url, Page<?> page) {
        int pageNumber = page.getNumber() + 1;
        int pageSize = page.getSize();
        int totalPages = page.getTotalPages();

        StringBuilder linkHeader = new StringBuilder();
        if (pageNumber < totalPages) {
            linkHeader.append("<").append(url).append("?page=").append(pageNumber + 1).append("&size=").append(pageSize).append(">; rel=\"next\",");
        }
        if (pageNumber > 1) {
            linkHeader.append("<").append(url).append("?page=").append(pageNumber - 1).append("&size=").append(pageSize).append(">; rel=\"prev\",");
        }
        linkHeader.append("<").append(url).append("?page=").append(totalPages).append("&size=").append(pageSize).append(">; rel=\"last\",");
        linkHeader.append("<").append(url).append("?page=1").append("&size=").append(pageSize).append(">; rel=\"first\"");

        return linkHeader.toString();
    }
}

package com.restaurant.menuservice.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Standardized error response for the API.
 *
 * This class provides a consistent structure for all error responses,
 * making it easier for API clients to parse and handle errors.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp; // moment the error occurred

    private int status;
    private String error;
    private String message;
    private String path;

    @Builder.Default
    private List<String> details = new ArrayList<>();

    /**
     * Add a detail to the error response.
     *
     * @param detail the detail to add
     * @return this ErrorResponse instance for method chaining
     */
    public ErrorResponse addDetail(String detail) {
        this.details.add(detail);
        return this;
    }
}

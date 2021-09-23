package com.csme.admin.assist.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrishankuException {

    private Date timeStamp;
    private String errorMessage;
    private String Description;
   // private Exception exceptionStackTrace;
}

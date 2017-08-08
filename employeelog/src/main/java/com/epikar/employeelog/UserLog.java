package com.epikar.employeelog;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <pre>
 * Project : intra
 * Package : {@link com.epikar.intra.jpa.model}
 * Creator : abel
 * Date : 8/4/17
 * </pre>
 */
@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLog implements Comparable<UserLog> {

//    private Long id;

    private String username;

    private Date workday;

    private String inTime;

    private String outTime;

    private String office;

    @Override
    public int compareTo(UserLog o) {
        return o.workday.compareTo(this.workday);
    }
}

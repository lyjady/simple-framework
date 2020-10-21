package org.simpleframework.mvc.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: linyongjin
 * @create: 2020-10-21 10:06:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestPathInfo {

    private String method;

    private String path;
}

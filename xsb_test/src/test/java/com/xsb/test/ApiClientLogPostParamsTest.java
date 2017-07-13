package com.xsb.test;

import com.jayway.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by juanzhihe on 2017/7/13.
 */
public class ApiClientLogPostParamsTest {
    @Before
    public void before(){
        RestAssured.baseURI = "http://10.100.62.205";
        RestAssured.port = 80; //http默认端口为80，如有特殊端口可写具体定义的端口
    }
    @Test
    /*
    URL地址为：http://10.100.62.205/api/client_log/new_batch，post方式
     */
    public void testGetParamsStartPage() {
        try {
            RestAssured.given()
                    .param("aa", "1")
                    .param("logs", "[{\"id\":50000,\"level\":1,\"date\":1499908268452,\"model\":1,\"place\":201}]")
                    .param("session_id", "59546c8dedfe26460ac6e6cb")
                    .param("vc", "040000")
                    .post("/api/client_log/new_batch")
                    .then()
                    .assertThat()
                    //状态相应码验证
                    .statusCode(200)
                    //最外层数据类型及值验证
                    .body("msg", Matchers.equalTo("ok"))
                    .body("result", Matchers.equalTo(1))
                    .body("code", Matchers.equalTo(1));
        }finally {
            RestAssured.reset();
        }
    }
}

package com.kubrick.sbt.web.domain;

import lombok.Data;

/**
 * @author k
 * @version 1.0.0
 * @ClassName NewUser
 * @description: {
 * "id": 1,
 * "name": "Leanne Graham",
 * "username": "Bret",
 * "email": "Sincere@april.biz",
 * "address": {
 * "street": "Kulas Light",
 * "suite": "Apt. 556",
 * "city": "Gwenborough",
 * "zipcode": "92998-3874",
 * "geo": {
 * "lat": "-37.3159",
 * "lng": "81.1496"
 * }
 * },
 * "phone": "1-770-736-8031 x56442",
 * "website": "hildegard.org",
 * "company": {
 * "name": "Romaguera-Crona",
 * "catchPhrase": "Multi-layered client-server neural-net",
 * "bs": "harness real-time e-markets"
 * }
 * @date 2021/3/19 上午12:25
 */
@Data
public class NewUser {
    private int id;
    private String name;
    private String username;
    private String email;
    private String phone;


}

package net.mengkang.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import net.mengkang.entity.Client;


public class RequestService {

    /**
     * 根据客户端的请求生成 Client
     *
     * @param request 例如 {id:1;rid:21;token:'43606811c7305ccc6abb2be116579bfd'}
     * @return
     */
    public static Client clientRegister(String request) {
//        String res = new String(Base64.decodeBase64(request));
        JSONObject json = JSON.parseObject("{\"id\":1,\"rid\":21,\"token\":\"43606811c7305ccc6abb2be116579bfd\"}");

        Client client = new Client();

        if (!json.containsKey("rid")) {
            return client;
        }

        try {
            client.setRoomId(json.getInteger("rid"));
        } catch (JSONException e) {
            e.printStackTrace();
            return client;
        }

        if (!json.containsKey("id") || !json.containsKey("token")) {
            return client;
        }

        Long id;
        String token;

        try {
            id = json.getLong("id");
            token = json.getString("token");
        } catch (JSONException e) {
            e.printStackTrace();
            return client;
        }

        if (!checkToken(id, token)) {
            return client;
        }

        client.setId(id);

        return client;
    }

    /**
     * 从 redis 里根据 id 获取 token 与之对比
     *
     * @param id
     * @param token
     * @return
     */
    private static boolean checkToken(Long id, String token) {
        return true;
    }
}

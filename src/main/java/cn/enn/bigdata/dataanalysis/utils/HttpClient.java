package cn.enn.bigdata.dataanalysis.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.Iterator;
import java.util.Map;

@Slf4j
@Service
public class HttpClient {

    @Resource
    private RestTemplate restTemplate;

    public JSONObject postRequest(Map<String, String> params, String url) {
        MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        Iterator<Map.Entry<String, String>> entries = params.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            request.add(entry.getKey(), entry.getValue());
        }
        URI uri = URI.create(url);
        String resultStr = restTemplate.postForObject(uri, request, String.class);
        JSONObject resultJson = JSONObject.parseObject(resultStr);
        return resultJson;
    }

    /**
     * post RequestBody请求
     *
     * @param params
     * @param url
     * @return
     */
    public String postBodyRequest(Map<String, Object> params, String url) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(params, headers);
        ResponseEntity<String> entity = restTemplate.postForEntity(url, request, String.class);
        return entity.getBody();
    }

    public String postRequestString(Map<String, Object> params, String url) {
        try {
            if (StringUtils.isEmpty(url)) {
                return "ERROR";
            }
            String resultStr = restTemplate.postForObject(url, params, String.class);
            return resultStr;
        } catch (Exception e) {
            log.error("HttpUtil.postRequest error!", e);
            return "ERROR";
        }
    }

    /**
     * 需要填充数据对象，和headers（非必须）
     *
     * @param url
     * @param httpEntity
     * @return
     */
    public String postForEntity(String url, HttpEntity httpEntity) {
//        HttpHeaders headers = new HttpHeaders();
//        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
//        headers.setContentType(type);
//        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
//        HttpEntity<List<String>> request = new HttpEntity<List<String>>(emails, headers);

        ResponseEntity<String> entity = restTemplate.postForEntity(url, httpEntity, String.class);
        String bodyStr = entity.getBody();
        return bodyStr;
    }

    /**
     * get请求
     *
     * @param params
     * @param url
     * @return
     */
    public String getRequest(Map<String, Object> params, String url) {
        try {
            if (StringUtils.isEmpty(url)) {
                return "error";
            }
            String urlWithParams = appendUrlParams(url, params);
            String result = restTemplate.getForObject(urlWithParams, String.class);
            log.info("请求结果为, url:{}, result:{}", urlWithParams, result);
            return result;
        } catch (Exception e) {
            log.error("HttpUtil.postRequest error!", e);
            return "ERROR";
        }
    }

    /**
     * get请求
     *
     * @param params
     * @param url
     * @return
     */
    public String getRequest(Map<String, Object> params, String url, Map<String, String> headerMap) {
        try {
            if (StringUtils.isEmpty(url)) {
                return "ERROR";
            }

            HttpHeaders headers = new HttpHeaders();
            if (!headerMap.isEmpty()) {
                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                    headers.add(entry.getKey(), entry.getValue());
                }
            }
            HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);

            String urlWithParams = appendUrlParams(url, params);
            ResponseEntity<String> responseEntity = restTemplate.exchange(urlWithParams, HttpMethod.GET, requestEntity, String.class);

            if (HttpStatus.OK == responseEntity.getStatusCode()) {
                return responseEntity.getBody();
            } else {
                log.error("http调用失败 httpCode = [{}]", responseEntity.getStatusCode());
                return "ERROR";
            }

        } catch (Exception e) {
            log.error("HttpUtil.getRequest error!", e);
            return "ERROR";
        }
    }

    /**
     * 按首字母顺序拼接参数
     *
     * @param url
     * @param params
     * @return
     */
    public static String appendUrlParams(String url, Map params) {
        StringBuilder urlWithParams = new StringBuilder(url);
        if (!params.isEmpty()) {
            urlWithParams.append("?");
        } else {
            return url;
        }

        int count = 0;
        for (Object key : params.keySet()) {
            Object value = params.get(key);
            urlWithParams.append(key).append("=").append(value);

            if (count < params.size() - 1) {
                urlWithParams.append("&");
            }
            count++;
        }

        return urlWithParams.toString();
    }
}

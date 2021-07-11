//package cn.enn.bigdata.dataanalysis.controller;
//
//import cn.enn.bigdata.dataanalysis.WebApplication;
//import cn.enn.bigdata.dataanalysis.controller.entity.User;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.ApplicationContext;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpSession;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MockMvcBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.lang.Character;
//import java.text.SimpleDateFormat;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = WebApplication.class)
//public class HouseControllerTest {
//    @Autowired
//    private WebApplicationContext applicationContext;
//
//    private MockMvc mockMvc;
//    private MockHttpSession mockHttpSession;
//
//    @Before
//    public void setupMockMvc() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();//初始化Mockmvc对象
//        mockHttpSession = new MockHttpSession();
//        User user = new User("zhangsn", "123456");
//        mockHttpSession.setAttribute("cookies", user);
//    }
//
//    @Test
//    public void testHouse() throws Exception {
//        String json = "{\"author\":\"HAHAHAA\",\"title\":\"Spring\",\"url\":\"http://tengj.top/\"}";
//        mockMvc.perform(MockMvcRequestBuilders.get("/hello/get")
//                .accept(MediaType.APPLICATION_JSON_UTF8)
//                .content(json.getBytes())
//                .session(mockHttpSession)
//        )
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//
//    @Test
//    public void testCommon() {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
//        Character[] cStr = new Character[30];
//        for (int i = 0; i < cStr.length; i++) {
//            cStr[i] = (char) ('a' + i);
//            System.out.print(cStr[i] + " ");
//        }
//
//
//    }
//
//    public static void main(String[] args) {
//        HouseControllerTest houseControllerTest = new HouseControllerTest();
//        houseControllerTest.testCommon();
//    }
//
//
//}

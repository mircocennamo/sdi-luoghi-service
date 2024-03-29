package it.interno.luoghi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LuoghiMsApplicationTests {


    @Autowired
    private MockMvc mockMvc;

    @DisplayName(value = "Test per recuperare luoghi filtrando per descrizione luogo, tipo luogo e data")
   // @Test
    @Order(1)
    void getLuoghi() throws Exception {

//        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
//        multiValueMap.add("desLuo", "ROMA");
//        multiValueMap.add("inLuo", "01");
//        multiValueMap.add("dataRif", "01/01/2022");
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/")
//                .params(multiValueMap)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.body[*].codiceLuogo").exists())
//                .andExpect(jsonPath("$.body[*].dataInizioValidita").exists())
//                .andExpect(jsonPath("$.body[*].inLuogo").exists())
//                .andExpect(jsonPath("$.body[*].descrizioneLuogo").exists())
//                .andDo(print());
   }

}

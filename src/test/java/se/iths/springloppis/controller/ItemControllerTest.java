package se.iths.springloppis.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import se.iths.springloppis.dtos.Item;
import se.iths.springloppis.entity.ItemEntity;
import se.iths.springloppis.repository.ItemRepository;
import se.iths.springloppis.repository.RoleRepository;
import se.iths.springloppis.security.SecurityConfig;
import se.iths.springloppis.service.ItemService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemController.class)
@Import({SecurityConfig.class})
class ItemControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ItemService itemService;

    @MockBean
    private RoleRepository roleRepository;

    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
    @Test
    public void givenAuthRequestOnSecuredEndpoint_shouldSucceedWith200() throws Exception {
        mvc.perform(get("/items"))
                .andExpect(status().isOk());
    }

    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
    @Test
    public void givenAuthRequestOnSecuredPostEndpoint_shouldSucceedWith201() throws Exception {
        var item = new Item();
        item.setName("Martin");
        item.setCategory("Food");
        item.setQuantity(10);
        item.setPrice(14.90);
        when(itemService.createItem(any(Item.class))).thenReturn(item);

        var payload = """
                {
                    "name":"Martin",
                    "category":"Food",
                    "quantity":10,
                    "price":14.90
                }
                """;

        mvc.perform(post("/items").contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Martin"))
                .andExpect(jsonPath("$.category").value("Food"))
                .andExpect(jsonPath("$.quantity").value(10))
                .andExpect(jsonPath("$.price").value(14.90));
    }

    @Test
    public void givenNoAuthRequestOnSecuredEndpoint_shouldFailWith401() throws Exception {
        mvc.perform(get("/items"))
                .andExpect(status().isUnauthorized());
    }

    @WithMockUser()
    @Test
    public void givenWrongAuthRequestOnSecuredEndpoint_shouldFailWith403() throws Exception {
        mvc.perform(get("/items"))
                .andExpect(status().isForbidden());
    }
}

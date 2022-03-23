package se.iths.springloppis.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import se.iths.springloppis.repository.RoleRepository;
import se.iths.springloppis.security.SecurityConfig;
import se.iths.springloppis.service.ItemService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @WithMockUser(value = "admin", roles = {"USER","ADMIN"})
    @Test
    public void givenAuthRequestOnSecuredEndpoint_shouldSucceedWith200() throws Exception {
        mvc.perform(get("/items"))
                .andExpect(status().isOk());
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

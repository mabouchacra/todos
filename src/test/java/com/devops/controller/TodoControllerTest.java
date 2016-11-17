package com.devops.controller;

import com.devops.model.Todo;
import com.devops.service.TodoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by SQLI on 17/11/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class TodoControllerTest {

    @InjectMocks
    TodoController todoController;

    @Mock
    TodoService todoService;

    private MockMvc mockMvc;

    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Before
    public void setup(){

        mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        this.mockMvc = MockMvcBuilders.standaloneSetup(todoController).setMessageConverters(mappingJackson2HttpMessageConverter).build();
    }

    /**
     * @throws Exception
     */
//    @Test
//    public void should_add_item_to_list() throws Exception {
//
//        //GIVEN
//        int tailleOriginal = 0;
//        Todo item = new Todo();
//        item.setId(Long.valueOf(1));
//        item.setTexte("Un item de test");
//        item.setResponsable("Malika");
//
//        //WHEN
//        Mockito.doReturn(new ArrayList<Todo>()).when(todoService).getAll();
//        List<Todo> listeInitiale = todoService.getAll();
//        tailleOriginal = listeInitiale.size();
//todoController.createItem(item);
//        //THEN
//        assertThat(listeInitiale.size()).isEqualTo(tailleOriginal+1);
//
//    }

    /**
     * @throws Exception
     */
    @Test
    public void should_add_return_new_id() throws Exception {

        //GIVEN
        Todo item = new Todo();
        item.setTexte("Un item de test");
        item.setResponsable("Malika");
        String request = this.json(item);


        //WHEN
        ResultActions result = this.mockMvc.perform(post("/todos").contentType(MediaType.APPLICATION_JSON).content(request));
        Mockito.verify(todoService).addItem(Mockito.any(Todo.class));

        //THEN
        result.andDo(print())
                .andExpect(status().isCreated());

    }



    protected String json(Object o) throws IOException {

        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}

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
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

    /**
     * @throws Exception
     */
    @Test
    public void should_get_all_items_empty_list() throws Exception {

        //GIVEN

        //WHEN

        ResultActions result = this.mockMvc.perform(get("/todos").contentType(MediaType.APPLICATION_JSON));
        Mockito.verify(todoService).getAll();
        //THEN
        result.andDo(print()).andExpect(status().isOk());
         result.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$").isEmpty());

    }

    /**
     * @throws Exception
     */
    @Test
    public void should_get_all_items() throws Exception {

        //GIVEN
        List<Todo> todos=new ArrayList<>();
        Todo item = new Todo();
        item.setTexte("Un item de test");
        item.setResponsable("Malika");
        todos.add(item);

        //WHEN
        Mockito.doReturn(todos).when(todoService).getAll();
        ResultActions result = this.mockMvc.perform(get("/todos").contentType(MediaType.APPLICATION_JSON));
        Mockito.verify(todoService).getAll();

        //THEN
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].texte").value(todos.get(0).getTexte()));
    }

    protected String json(Object o) throws IOException {

        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}

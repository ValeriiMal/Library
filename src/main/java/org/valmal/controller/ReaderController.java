package org.valmal.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.valmal.bean.Reader;
import org.valmal.service.BookService;
import org.valmal.service.ReaderService;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    ReaderService readerService;
    @Autowired
    BookService bookService;

    @RequestMapping(value = "/findByExample", method = RequestMethod.POST)
    @ResponseBody
    public String findByExample(@RequestBody String example) throws IOException {
        Reader ex = new ObjectMapper().readValue(example, Reader.class);
        return new ObjectMapper().writeValueAsString(readerService.findReadersByExample(ex));
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addReader(@RequestBody String obj
    ) throws IOException {
        Reader reader = new ObjectMapper().readValue(obj, Reader.class);
        reader.setRegistrationDate(new Date());
        readerService.insert(reader);
        return "reader added";
    }

    @RequestMapping("/findReaderJSON")
    @ResponseBody
    public String findJSONbyId(@RequestParam("id") int id) throws IOException, ParseException {
        Reader reader = readerService.findReaderById(id);
        return new ObjectMapper().writeValueAsString(reader);
    }

    @RequestMapping("/remove")
    @ResponseBody
    public String remove(@RequestParam("id") int id) throws IOException {
        readerService.delete(readerService.findReaderById(id));
        return "reader removed";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String edit(@RequestBody String json
    ) throws IOException {

        Reader reader = new ObjectMapper().readValue(json, Reader.class);
        reader.setRegistrationDate(readerService.findReaderById(reader.getId()).getRegistrationDate());

        readerService.update(reader);

        return "reader edited";
    }
}

package org.valmal.controller;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.valmal.bean.Address;
import org.valmal.bean.Book;
import org.valmal.bean.Genre;
import org.valmal.bean.Reader;
import org.valmal.service.BookService;
import org.valmal.service.ReaderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

@Controller
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    ReaderService readerService;
    @Autowired
    BookService bookService;

    @RequestMapping("/load")
    @ResponseBody
    public String loadReaders() {

        return readerService.readersToString(readerService.getReaders());
    }

    @RequestMapping("/find")
    @ResponseBody
    public String findReaders(
            @RequestParam("id") String id,
            @RequestParam("fName") String fName,
            @RequestParam("mName") String mName,
            @RequestParam("lName") String lName,
            @RequestParam("phone") String phone,
            HttpServletRequest request
    ) {
        List<Reader> readers = null;
        Enumeration<String> params = request.getParameterNames();

        for(Enumeration<String> p = params; p.hasMoreElements(); ){
            switch (p.nextElement()){
                case "id" : {
                    if(!id.isEmpty()){
                        readers = new ArrayList<>();
                        readers.add(readerService.findReaderById(Integer.parseInt(id)));
                    }
                } break;
                case "fName" : {
                    if(!fName.isEmpty()){
                        if(readers == null){
                            readers = new ArrayList<>();
                            readers.addAll(readerService.findReadersByFName(fName));
                        } else {
                            readers.removeIf(r -> !r.getfName().contains(fName));
                        }
                    }
                } break;
                case "mName" : {
                    if(!mName.isEmpty()){
                        if(readers == null){
                            readers = new ArrayList<>();
                            readers.addAll(readerService.findReadersByMName(mName));
                        } else {
                            readers.removeIf(r -> !r.getmName().contains(mName));
                        }
                    }
                } break;
                case "lName" : {
                    if(!lName.isEmpty()){
                        if(readers == null){
                            readers = new ArrayList<>();
                            readers.addAll(readerService.findReadersByLName(lName));
                        } else {
                            readers.removeIf(r -> !r.getlName().contains(lName));
                        }
                    }
                } break;
                case "phone" : {
                    if(!phone.isEmpty()){
                        if(readers == null){
                            readers = new ArrayList<>();
                            readers.addAll(readerService.findReadersByPhone(phone));
                        } else {
                            readers.removeIf(r -> !r.getPhone().contains(phone));
                        }
                    }
                } break;
            }
        }

        if(readers == null){ readers = new ArrayList<>(); }

        return readerService.readersToString(readers);
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addReader(@RequestBody String obj
    ) throws IOException {
        Reader reader = new ObjectMapper().readValue(obj, Reader.class);
        reader.setRegistrationDate(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date()));

//        Address address = reader.getAddress();

//        reader.setAddress(address);
//        address.setReader(reader);

        readerService.insert(reader);

        return "reader added";
    }

    @RequestMapping("/findReaderJSON")
    @ResponseBody
    public String findJSONbyId(@RequestParam("id") int id) throws IOException {
        return new ObjectMapper().writeValueAsString(readerService.findReaderById(id));
    }

    @RequestMapping("/remove")
    @ResponseBody
    public String remove(@RequestParam("id") int id) throws IOException {

        readerService.delete(readerService.findReaderById(id));

        return "reader removed";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(
            @RequestParam("id") int id,
            @RequestParam("fName") String fName,
            @RequestParam("mName") String mName,
            @RequestParam("lName") String lName,
            @RequestParam("phone") String phone,
            @RequestParam("country") String country,
            @RequestParam("city") String city,
            @RequestParam("street") String street,
            @RequestParam("house") String house,
            @RequestParam("birth") String birth
    ) {

        Reader reader = readerService.findReaderById(id);

        reader.setfName(fName);
        reader.setmName(mName);
        reader.setlName(lName);
        reader.setPhone(phone);
        reader.setRegistrationDate(new SimpleDateFormat("yyyy/MM/dd HH/mm/ss").format(new java.util.Date()));
        reader.setDateOfBirth(birth);

        Address address = reader.getAddress();
        address.setCountry(country);
        address.setCity(city);
        address.setStreet(street);
        address.setHouse(house);

        readerService.update(reader);

        return "reader edited";
    }
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    @ResponseBody
    public String getBooks(@RequestParam("id") String id) throws IOException {
        return new ObjectMapper().writeValueAsString(readerService.findReaderById(Integer.parseInt(id)).getBooks());
    }

}

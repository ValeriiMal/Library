package org.valmal.controller;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.valmal.bean.Reader;
import org.valmal.service.ReaderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Controller
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    ReaderService service;

    @RequestMapping("/load")
    @ResponseBody
    public String loadReaders() {

        return service.readersToString(service.getReaders());
    }

    @RequestMapping("/find")
    @ResponseBody
    public String findReaders(
            @RequestParam("id") String id,
            @RequestParam("fName") String fName,
            @RequestParam("mName") String mName,
            @RequestParam("lName") String lName,
            @RequestParam("phone") String phone,
            @RequestParam("address") String address,
            HttpServletRequest request
    ) {
        List<Reader> readers = null;
        Enumeration<String> params = request.getParameterNames();

        for(Enumeration<String> p = params; p.hasMoreElements(); ){
            switch (p.nextElement()){
                case "id" : {
                    if(!id.isEmpty()){
                        readers = new ArrayList<>();
                        readers.add(service.findReaderById(Integer.parseInt(id)));
                    }
                } break;
                case "fName" : {
                    if(!fName.isEmpty()){
                        if(readers == null){
                            readers = new ArrayList<>();
                            readers.addAll(service.findReadersByFName(fName));
                        } else {
                            readers.removeIf(r -> !r.getfName().contains(fName));
                        }
                    }
                } break;
                case "mName" : {
                    if(!mName.isEmpty()){
                        if(readers == null){
                            readers = new ArrayList<>();
                            readers.addAll(service.findReadersByMName(mName));
                        } else {
                            readers.removeIf(r -> !r.getmName().contains(mName));
                        }
                    }
                } break;
                case "lName" : {
                    if(!lName.isEmpty()){
                        if(readers == null){
                            readers = new ArrayList<>();
                            readers.addAll(service.findReadersByLName(lName));
                        } else {
                            readers.removeIf(r -> !r.getlName().contains(lName));
                        }
                    }
                } break;
                case "phone" : {
                    if(!phone.isEmpty()){
                        if(readers == null){
                            readers = new ArrayList<>();
                            readers.addAll(service.findReadersByPhone(phone));
                        } else {
                            readers.removeIf(r -> !r.getPhone().contains(phone));
                        }
                    }
                } break;
                case "address" : {
//                    if(!address.isEmpty()){
//                        if(readers == null){
//                            readers = new ArrayList<>();
//                            readers.addAll(service.findReadersByPhone(phone));
//                        } else {
//                            readers.removeIf(r -> !r.getPhone().contains(phone));
//                        }
//                    }
                } break;
            }
        }

        if(readers == null){ readers = new ArrayList<>(); }

        return service.readersToString(readers);
    }


    @RequestMapping("/add")
    @ResponseBody
    public String addReader(
            @RequestParam("fName") String fName,
            @RequestParam("mName") String mName,
            @RequestParam("lName") String lName,
            @RequestParam("phone") String phone,
            @RequestParam("address") String address
    ) {
        Reader reader = new Reader();
        reader.setfName(fName);
        reader.setmName(mName);
        reader.setlName(lName);
        reader.setPhone(phone);

        service.insert(reader);

        return "reader added";
    }

    @RequestMapping("/findReaderJSON")
    @ResponseBody
    public String findJSONbyId(@RequestParam("id") int id) throws IOException {
        return new ObjectMapper().writeValueAsString(service.findReaderById(id));
    }

    @RequestMapping("/remove")
    @ResponseBody
    public String remove(@RequestParam("id") int id) throws IOException {

        service.delete(service.findReaderById(id));

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
            @RequestParam("address") String address
    ) {

        Reader reader = service.findReaderById(id);

        reader.setfName(fName);
        reader.setmName(mName);
        reader.setlName(lName);
        reader.setPhone(phone);
//        reader.setAddress(address);

        service.update(reader);

        return "reader edited";
    }

}
